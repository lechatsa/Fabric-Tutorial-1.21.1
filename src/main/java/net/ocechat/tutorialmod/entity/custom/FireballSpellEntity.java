package net.ocechat.tutorialmod.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.MathHelper;

public class FireballSpellEntity extends FireballEntity {

    public final AnimationState idleanimationState = new AnimationState();
    private int idleAnimationTimeout = 0;



    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 95;
            this.idleanimationState.start(this.age);

        }
    }



    @Override
    public void tick() {
        super.tick();
        Vec3d velocity = this.getVelocity();

        this.setVelocity(velocity.multiply(3f));
        this.setVelocity(MathHelper.addGravitation(velocity, MathHelper.vectorGravitation(-0.04)));

        if (this.getWorld().isClient) {
            this.setupAnimationStates();
        }


    }

    public FireballSpellEntity(EntityType<? extends FireballEntity> entityType, World world) {
        super(entityType, world);
    }

}
