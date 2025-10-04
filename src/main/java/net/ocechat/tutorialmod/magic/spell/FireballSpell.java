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

                );
    }

    @Override
    public void cast(World world, PlayerEntity player) {
        Vec3d vector = player.getRotationVector();
        FireballEntity fireball = new FireballEntity(world, player, vector, 3
        );


        fireball.setPos(MathHelper.getPointInFront(player, 1f).x , MathHelper.getPointInFront(player, 1f).y + 1 , MathHelper.getPointInFront(player, 1f).z);
        fireball.setVelocity(vector.multiply(0f));

        world.spawnEntity(fireball);


    }

    @Override
    public void tick() {
        super.tick();


    }
}
