package net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.TypeFilter;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Position;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.OcechatMath;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class ShieldBarrierSpellEntity extends ProjectileEntity {
    public ShieldBarrierSpellEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

    public final AnimationState animationState = new AnimationState();


    @Override
    public void tick() {
        super.tick();

        int range = 5;
        Predicate<ProjectileEntity> nearest = projectileEntity -> projectileEntity.squaredDistanceTo(this.getPos()) <= range * range;
        List<ProjectileEntity> entityList = new ArrayList<>();
        Box box = new Box(this.getBlockPos());

        this.getWorld().collectEntitiesByType(
                TypeFilter.instanceOf(ProjectileEntity.class),
                box,
                nearest,
                entityList,
                range
                );
        for (ProjectileEntity entity : entityList) {
            if (this.collidesWith(entity)) {
                onEntityHit(new EntityHitResult(entity));
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
                projectile.discard(); // supprime le projectile
                World world =  this.getWorld();
                      world.playSound(null, this.getBlockPos(), SoundEvents.ITEM_SHIELD_BLOCK,
                              net.minecraft.sound.SoundCategory.PLAYERS, 1.0F, 1.0F);

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


    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    protected void readCustomDataFromNbt(NbtCompound nbt) {

    }

    @Override
    protected void writeCustomDataToNbt(NbtCompound nbt) {

    }
}
