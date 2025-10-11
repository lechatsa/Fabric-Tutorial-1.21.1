package net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.OcechatMath;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker;



import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ShieldBarrierSpellEntity extends ProjectileEntity {
    public ShieldBarrierSpellEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
        this.life = 2;
    }


    private boolean clientAnimationStarted = false; // local client flag pour lancer animationState
    private int discardTimer = 0;
    private final int DISCARD_DURATION_TICKS = 20; // ajuster selon durée d'animation (ex 20 ticks = 1s)
    public int life;
    public final AnimationState animationState = new AnimationState();


    @Override
    public void tick() {
        super.tick();

        // Si on est en mode destroy/discarding, compter et supprimer à la fin
        if (this.isDiscarding()) {
            discardTimer++;
            if (!this.getWorld().isClient()) {
                // Serveur : attendre la fin puis supprimer
                if (discardTimer > DISCARD_DURATION_TICKS) {
                    this.discard();
                }
            } else {
                // Client : lancer l'animation une seule fois
                if (!clientAnimationStarted) {
                    this.animationState.startIfNotRunning(age);
                    clientAnimationStarted = true;
                }
                // le client laisse renderer l'animation ; la suppression viendra du serveur
            }
            return;
        }

        // ton ancienne logique de vie
        int range = 5;
        // zone de détection autour du bouclier (correctement dimensionnée)
        Box detectionBox = new Box(this.getX() - 2.0, this.getY() - 1.0, this.getZ() - 2.0,
                this.getX() + 2.0, this.getY() + 2.0, this.getZ() + 2.0);
        List<ProjectileEntity> projectiles = this.getWorld().getEntitiesByClass(
                ProjectileEntity.class,
                detectionBox,
                p -> p.isAlive() && p != this
        );

        for (ProjectileEntity projectile : projectiles) {
            if (projectile.getBoundingBox().intersects(this.getBoundingBox().expand(0.5))) {
                // On traite l'impact (côté serveur si possible)
                if (!this.getWorld().isClient()) {
                    onEntityHit(new EntityHitResult(projectile));
                }
            }
        }
    }



    @Override
    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();

        if (entity instanceof ProjectileEntity projectile) {

            Vec3d projPos = projectile.getPos();
            Vec3d projDir = projectile.getRotationVector();

            // Position relative du projectile par rapport au bouclier
            double x = projPos.x - this.getX();
            double z = projPos.z - this.getZ();

            // Calcul de l'angle relatif
            boolean blocked = isBlocked(projDir, z, x);

            if (blocked) {
                // côté serveur : détruire le projectile et décrémenter la vie
                projectile.discard();
                life--;

                if (life <= 0) {
                    // entrer en mode disparition, synchroniser vers le client
                    this.setDiscarding(true);
                    // ne pas discard() ici : laisser le serveur supprimer après DISCARD_DURATION_TICKS
                }
            }

        }
    }

    private boolean isBlocked(Vec3d projDir, double z, double x) {
        double deltaGamma = Math.toDegrees(
                OcechatMath.angleBetween(
                        OcechatMath.toVec2d(this.getRotationVector()),
                        OcechatMath.toVec2d(projDir)
                )
        );

        // Vérifie chaque segment du bouclier
        boolean blocked = false;

        // Segment 1
        if (z >= 1 && z <= 2) {
            double expectedZ = -x + 1.5;
            if (Math.abs(z - expectedZ) < 0.2 && deltaGamma < -135)
                blocked = true;
        }

        // Segment 2
        if (z >= -2 && z <= 1) {
            double expectedZ = x - 1.5;
            if (Math.abs(z - expectedZ) < 0.2 && deltaGamma < 135)
                blocked = true;
        }

        // Segment 3
        if (z >= -1 && z <= 1) {
            double expectedX = 0.5;
            if (Math.abs(x - expectedX) < 0.2 && deltaGamma < 90)
                blocked = true;
        }
        return blocked;
    }

    private static final TrackedData<Boolean> DISCARDING = DataTracker.registerData(ShieldBarrierSpellEntity.class, TrackedDataHandlerRegistry.BOOLEAN);




    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        builder.add(DISCARDING, false);
    }

    public void setDiscarding(boolean value) {
        this.dataTracker.set(DISCARDING, value);
    }

    public boolean isDiscarding() {
        return this.dataTracker.get(DISCARDING);
    }


    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
