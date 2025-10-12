package net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.OcechatMath;

import java.util.List;


public class ShieldBarrierSpellEntity extends Entity {
    private final int lifeTime;
    private final boolean DEBUG_MODE = true;

    public ShieldBarrierSpellEntity(EntityType<? extends Entity> entityType, World world) {
        super(entityType, world);

        if (!DEBUG_MODE) {

            this.lifePoint = 2;
            this.lifeTime = 600;

        } else {

            this.lifePoint = 400;
            this.lifeTime = 12000;

        }
    }


    private boolean clientAnimationStarted = false; // local client flag pour lancer animationState
    private int discardTimer = 0;
    private final int DISCARD_DURATION_TICKS = 40; // ajuster selon durée d'animation (ex 20 ticks = 1s)
    public int lifePoint;
    public final AnimationState animationState = new AnimationState();


    @Override
    public void tick() {
        super.tick();
        if (lifeTime <= age && !this.isDiscarding()) {
            this.setDiscarding(true);
        }

        if (!this.getWorld().isClient()) {
            // --- Visualisation des segments pour debug ---
            drawShieldFunctions((ServerWorld) this.getWorld());
        }

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




        // zone de détection autour du bouclier (correctement dimensionnée)
        Box detectionBox = new Box(this.getX() - 2.0, this.getY() - 1.0, this.getZ() - 2.0, this.getX() + 2.0, this.getY() + 2.0, this.getZ() + 2.0);

        List<ProjectileEntity> projectiles = this.getWorld().getEntitiesByClass(
                ProjectileEntity.class,
                detectionBox,
                ProjectileEntity::isAlive
        );

        for (ProjectileEntity projectile : projectiles) {
            if (projectile.getBoundingBox().intersects(this.getBoundingBox().expand(0.5))) {
                // On traite l'impact (côté serveur si possible.)
                if (!this.getWorld().isClient()) {
                    onEntityHit(new EntityHitResult(projectile));
                }
            }
        }
    }




    private static final boolean DEBUG_VISUAL = true; // Active/désactive les particules de debug


    protected void onEntityHit(EntityHitResult entityHitResult) {
        Entity entity = entityHitResult.getEntity();

        if (entity instanceof ProjectileEntity projectile) {
            Vec3d projPos = projectile.getPos();
            Vec3d projDir = projectile.getRotationVector();
            Vec3d projVelocity = projectile.getVelocity();

            double x = projPos.x - this.getX();
            double z = projPos.z - this.getZ();

            boolean blocked = isBlocked(projVelocity, z, x, projDir, (ServerWorld) this.getWorld());

            if (blocked) {
                projectile.discard();
                lifePoint--;

                System.out.println("[Bouclier] Projectile BLOQUÉ à x=" + x + ", z=" + z);

                if (lifePoint <= 0) {
                    this.setDiscarding(true);
                }
            } else {
                System.out.println("[Bouclier] Projectile détecté mais PAS bloqué (x=" + x + ", z=" + z + ")");
            }
        }
    }

    private boolean isBlocked(Vec3d projVelocity, double z0, double x0, Vec3d projDirWorld, ServerWorld world) {
        double deltaGamma = Math.toDegrees(
                OcechatMath.angleBetween(
                        OcechatMath.toVec2d(this.getRotationVector()),
                        OcechatMath.toVec2d(projDirWorld)
                )
        );

        Vec3d dir = projVelocity;
        double vx = dir.x;
        double vz = dir.z;

        boolean blocked = false;

        // --- Segments du bouclier corrigés ---
        // a, b, zMin, zMax, xMin, xMax, angleLimite, sensAngle, vertical
        double[][] segments = {
                {-1.0,  1.5,  1.0,  2.0,   1,   2, -135.0,  90, -1, 0}, // f1(x) = -x + 1.5
                { 1.0, -1.5, -2.0,  1.0,  -2,  -1,  135.0, -90,  1, 0}, // f2(x) =  x - 1.5
                { 0.0,  0.0, -1.0,  1.0, 0.5, 0.5,   90.0, -90,  1, 1} // f3 : x = 0.5 (vertical)
        };

        drawShieldFunctions(world); // debug visuel

        for (double[] seg : segments) {
            double a = seg[0];
            double b = seg[1];
            double zMin = seg[2];
            double zMax = seg[3];
            double xMin = seg[4];
            double xMax = seg[5];
            double angleLimite = seg[6];
            double angleLimite2 = seg[7];
            double sensAngle = seg[8];
            boolean isVertical = seg[9] == 1;

            double t, xI, zI;

            if (!isVertical) {
                // Cas normal : z = a*x + b
                double denom = vz - a * vx;
                if (Math.abs(denom) < 1e-6) continue;
                t = (a * x0 + b - z0) / denom;
                if (t < 0) continue;

                xI = x0 + vx * t;
                zI = z0 + vz * t;
            } else {
                // Cas vertical : x = constante
                double denom = vx;
                if (Math.abs(denom) < 1e-6) continue;
                t = (xMin - x0) / vx; // xMin = xMax = constante
                if (t < 0) continue;

                xI = x0 + vx * t;
                zI = z0 + vz * t;
            }

            if (zI < zMin || zI > zMax) {
                System.out.println("Laissé passé car les coordonnées du point d'intersection sont hors des Z maximum et minimum%n");
                continue;
            }
            if (xI < Math.min(xMin, xMax) || xI > Math.max(xMin, xMax)) {
                System.out.println("Laissé passé car les coordonnées du point d'intersection sont hors des X maximum et minimum%n");
                continue;
            }

            // --- Correction : test d’angle selon le sens ---
            boolean angleOK;
            if (sensAngle < 0) {
                angleOK = deltaGamma < angleLimite2 && angleLimite < deltaGamma;
                System.out.printf("L'angle %f est inférieur %f et supérieur à %f%n", deltaGamma, angleLimite2, angleLimite);
            } else {
                angleOK = deltaGamma > angleLimite2 && angleLimite > deltaGamma;
                System.out.printf("L'angle %f est inférieur %f et supérieur à %f%n", deltaGamma, angleLimite, angleLimite2);

            }

            if (angleOK) {
                System.out.println("[Bouclier] Intersection détectée : Δγ=" + String.format("%.2f", deltaGamma)
                        + "° à (x=" + String.format("%.2f", xI) + ", z=" + String.format("%.2f", zI) + ")");
                spawnDebugParticle(world, this.getX() + xI, this.getY() + 1.5, this.getZ() + zI);
                blocked = true;
            } else {
                System.out.println("[Bouclier] Intersection trouvée mais angle incorrect Δγ="
                        + String.format("%.2f", deltaGamma) + "°");
            }
        }

        return blocked;
    }


    private void drawShieldFunctions(ServerWorld world) {
        for (double x = -2; x <= 2; x += 0.1) {
            double z1 = -x + 1.5; // f1
            double z2 = x - 1.5;  // f2

            world.spawnParticles(ParticleTypes.END_ROD, this.getX() + x, this.getY() + 1.0, this.getZ() + z1, 1, 0, 0, 0, 0);
            world.spawnParticles(ParticleTypes.FLAME, this.getX() + x, this.getY() + 1.0, this.getZ() + z2, 1, 0, 0, 0, 0);
        }

        // f3 : x = 0.5 (verticale)
        for (double z = -1; z <= 1; z += 0.1) {
            world.spawnParticles(ParticleTypes.HAPPY_VILLAGER, this.getX() + 0.5, this.getY() + 1.0, this.getZ() + z, 1, 0, 0, 0, 0);
        }
    }


    private void spawnDebugParticle(ServerWorld world, double x, double y, double z) {
        world.spawnParticles(ParticleTypes.END_ROD, x, y, z, 3, 0, 0, 0, 0);
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
