package net.ocechat.tutorialmod.magic.spell.substantial;

import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import org.jetbrains.annotations.Nullable;

public class FireWallSpell extends ModSpell {

    public FireWallSpell() {
        super("fire_wall_spell", 60, 10, ModKeyBinding.FIRE_WALL_SPELL, false, 60, false);
    }

    @Override
    public void cast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        if (world.isClient) return; // seulement côté serveur

        Vec3d direction = player.getRotationVec(1.0F).normalize(); // direction du regard
        Vec3d start = player.getPos().add(0, 0.1, 0); // léger décalage vertical
        int length = 15; // longueur du mur

        for (int i = 1; i <= length; i++) {
            Vec3d pos = start.add(direction.multiply(i));
            BlockPos blockPos = new BlockPos((int) pos.x, (int) pos.y, (int) pos.z);

            // Place le feu uniquement si l'emplacement est libre et qu’il y a un bloc dessous
            BlockPos below = blockPos.down();
            if (world.isAir(blockPos) && world.getBlockState(below).isSolidBlock(world, below)) {
                world.setBlockState(blockPos, Blocks.FIRE.getDefaultState());
            }
        }
    }

    @Override
    public void tryCast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        if (canCast(player)) {
            setCurrentCooldown(getCooldown());
            cast(world, player, deltaTime);
        }
    }

    @Override
    public void tick(SpellInstance instance) {
        // pas besoin ici, sort instantané
    }
}
