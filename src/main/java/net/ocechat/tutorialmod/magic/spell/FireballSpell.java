package net.ocechat.tutorialmod.magic.spell;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.MathHelper;
import net.ocechat.tutorialmod.util.ModKeyBinding;

public class FireballSpell extends ModSpell{
    public FireballSpell() {
        super(
                "fireball",
                0,
                0,
                ModKeyBinding.SPAWN_FIRE_IN_STRAIGHT_LIGNE,
                true,
                80
                );
    }

    @Override
    public void cast(World world, PlayerEntity player) {
        Vec3d vector = player.getRotationVector();
        FireballEntity fireball = new FireballEntity(world, player, vector, 3);

        ActivesSpells.addSpell(new SpellInstance(player, this, fireball));

        fireball.setPos(MathHelper.getPointInFront(player, 1f).x , MathHelper.getPointInFront(player, 1f).y + 1 , MathHelper.getPointInFront(player, 1f).z);
        fireball.setVelocity(vector.multiply(3f));

        world.spawnEntity(fireball);


    }

    @Override
    public void tick(SpellInstance instance) {
        if (!(instance.getAttachedElement() instanceof FireballEntity fireball)) return;


        if (this.isAffectedByGravity()) {
            Vec3d velocity = fireball.getVelocity();
            fireball.setVelocity(MathHelper.addGravitation(velocity, MathHelper.vectorGravitation(-0.04)));

        }





        if (!fireball.isAlive()) {
            fireball.discard();
        }
    }


    @Override
    public boolean isAffectedByGravity() {
        return true;
    }



}
