package net.ocechat.tutorialmod.entity.custom.ap_projectile_spell_entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.MovementType;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.OcechatMath;

import static net.ocechat.tutorialmod.OcechatMath.Vec3dToBlockPos;

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
        velocity = OcechatMath.addGravitation(velocity, OcechatMath.vectorGravitation(-0.01));
        this.setVelocity(velocity);
        this.move(MovementType.SELF, velocity);


        this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        this.getWorld().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        this.getWorld().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);

        this.WillCollideWithSomething(velocity);

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
        if (!this.getWorld().isClient) {
            Vec3d position = this.getPos();
            World world = this.getWorld();
            Vec3d nextPosition = position.add(velocity);
            BlockPos nextBlockPosition = Vec3dToBlockPos(nextPosition);

            if (!world.getBlockState(nextBlockPosition).isAir()) {
                if (numberOfBlockPenetrated >= maxPenetration) {
                    discard();
                } else {
                    world.setBlockState(nextBlockPosition, Blocks.AIR.getDefaultState());
                    numberOfBlockPenetrated++;
                }
            }
        }
    }

    public APProjectileSpellEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

}


