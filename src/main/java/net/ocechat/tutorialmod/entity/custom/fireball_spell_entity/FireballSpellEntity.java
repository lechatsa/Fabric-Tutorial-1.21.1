package net.ocechat.tutorialmod.entity.custom.fireball_spell_entity;

import net.minecraft.entity.*;
import net.minecraft.entity.data.DataTracker;

import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.OcechatMath;

public class FireballSpellEntity extends ProjectileEntity {

    public final AnimationState animationState = new AnimationState();


    @Override
    protected void initDataTracker(DataTracker.Builder builder) {

    }

    @Override
    public void tick() {
        super.tick();


        Vec3d velocity = this.getVelocity();
        velocity = OcechatMath.addGravitation(velocity, OcechatMath.vectorGravitation(-0.04));
        this.setVelocity(velocity);
        this.setPosition(this.getPos().add(velocity));


        this.getWorld().addParticle(ParticleTypes.SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        this.getWorld().addParticle(ParticleTypes.CAMPFIRE_COSY_SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);
        this.getWorld().addParticle(ParticleTypes.CAMPFIRE_SIGNAL_SMOKE, this.getX(), this.getY(), this.getZ(), 0, 0, 0);


        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);

        if (hitResult.getType() != HitResult.Type.MISS && !this.getWorld().isClient) {
            this.onCollision(hitResult);
        }



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

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        if (!this.getWorld().isClient) {
            boolean bl = this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
            this.getWorld().createExplosion(this, this.getX(), this.getY(), this.getZ(), 3, bl, World.ExplosionSourceType.TRIGGER);
            this.discard();
        }
    }




    public FireballSpellEntity(EntityType<? extends ProjectileEntity> entityType, World world) {
        super(entityType, world);
    }

}
