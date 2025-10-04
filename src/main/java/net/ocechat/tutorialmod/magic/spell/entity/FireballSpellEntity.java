package net.ocechat.tutorialmod.magic.spell.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.MathHelper;

public class FireballSpellEntity extends FireballEntity {

    @Override
    public void tick() {
        super.tick();
        Vec3d velocity = this.getVelocity();

        this.setVelocity(velocity.multiply(3f));
        this.setVelocity(MathHelper.addGravitation(velocity, MathHelper.vectorGravitation(-0.04)));

    }

    public FireballSpellEntity(EntityType<? extends FireballEntity> entityType, World world) {
        super(entityType, world);
    }
}
