package net.ocechat.tutorialmod.entity.custom.ap_projectile_spell_entity;

import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.OcechatMath;


public class APProjectileSpellEntity extends ProjectileEntity {

    public final AnimationState animationState = new AnimationState();
    public final int maxPenetration = 5;
    public int numberOfBlockPenetrated = 0;

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();


        Vec3d velocity = this.getVelocity();
        velocity = OcechatMath.addGravitation(velocity, OcechatMath.vectorGravitation(-0.03));

        this.setVelocity(velocity);
        this.WillCollideWithSomething(velocity);
        this.setPosition(this.getPos().add(velocity));

        this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        this.getWorld().addParticle(ParticleTypes.CRIT, this.getX(), this.getY(), this.getZ(), 0, 0, 0);

        this.animationState.startIfNotRunning(this.age);
    }

    public Vec3d getVelocityDirection() {
        Vec3d velocity = this.getVelocity();
        return velocity.normalize();
    }

    public float getVelocityYaw() {
        Vec3d v = getVelocity();
        return (float) (Math.atan2(v.z, v.x) * (180.0 / Math.PI)) - 90.0F;
    }

    public float getVelocityPitch() {
        Vec3d v = getVelocity();
        double horizontal = Math.sqrt(v.x * v.x + v.z * v.z);
        return (float) (-(Math.atan2(v.y, horizontal) * (180.0 / Math.PI)));
    }

    protected void WillCollideWithSomething(Vec3d velocity) {
        if (this.getWorld().isClient) return;

        Vec3d position = this.getPos();
        World world = this.getWorld();

        Vec3d start = position;
        double distance = velocity.length();
        int steps = Math.max(1, (int) Math.ceil(distance / 0.1)); // une vérif tous les 0.1 bloc

        Vec3d step = velocity.multiply(1.0 / steps);

        for (int i = 0; i < steps; i++) {

            Vec3d stepStart = start.add(step.multiply(i));
            Vec3d stepEnd = stepStart.add(step);

            HitResult hitResult = world.raycast(new RaycastContext(
                    stepStart,
                    stepEnd,
                    RaycastContext.ShapeType.COLLIDER,
                    RaycastContext.FluidHandling.NONE,
                    this
            ));

            if (hitResult.getType() == HitResult.Type.BLOCK) {

                BlockHitResult blockHitResult = (BlockHitResult) hitResult;
                BlockPos positionOfHit = blockHitResult.getBlockPos();

                if (world.getBlockState(positionOfHit).isAir()) return;
                if (world.getBlockState(positionOfHit) == Blocks.BEDROCK.getDefaultState()) {

                    world.playSound(null, positionOfHit, SoundEvents.BLOCK_ANVIL_PLACE, SoundCategory.BLOCKS, 1, 1.0f + (this.getWorld().random.nextFloat() * 0.2f - 0.1f));
                    this.discard();
                    return;

                }


                if (numberOfBlockPenetrated >= 5) {

                    this.discard();
                    return;

                } else {

                    SoundEvent soundOfBlockDestroy =  world.getBlockState(positionOfHit).getSoundGroup().getBreakSound();

                    world.setBlockState(positionOfHit, Blocks.AIR.getDefaultState());
                    world.playSound(null, positionOfHit, soundOfBlockDestroy, SoundCategory.BLOCKS, 1, 1.0f + (this.getWorld().random.nextFloat() * 0.2f - 0.1f));
                    numberOfBlockPenetrated++;

                }
            }

            else if (hitResult.getType() == HitResult.Type.ENTITY) {

                EntityHitResult entityHitResult = (EntityHitResult) hitResult;
                entityHitResult.getEntity().damage(world.getDamageSources().magic(), 40f);

            }
        }
    }

    public APProjectileSpellEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

}


