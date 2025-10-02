package net.ocechat.tutorialmod.magic.spell;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.magic.casting.ModKeyBinding;

public class FireballSpell extends ModSpell{
    public FireballSpell() {
        super("fireball", 0, 20, ModKeyBinding.SPAWN_FIRE_IN_STRAIGHT_LIGNE);
    }

    @Override
    public void cast(World world, PlayerEntity player) {
        Vec3d vector = player.getRotationVector();
        FireballEntity fireball = new FireballEntity( world, player, vector, 10
        );

        fireball.setPos(player.getX(), player.getY(), player.getZ());
        world.spawnEntity(fireball);
    }
}
