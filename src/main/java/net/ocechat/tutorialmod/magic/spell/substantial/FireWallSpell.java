package net.ocechat.tutorialmod.magic.spell.substantial;

import net.minecraft.block.Blocks;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public class FireWallSpell extends ModSpell {

    public FireWallSpell() {
        super("fire_wall_spell", 0, 0, ModKeyBinding.FIRE_WALL_SPELL, false, 60, false);
    }

    @Override
    public void cast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        super.cast(world, player, deltaTime);

        if (world.isClient) return; // uniquement côté serveur

        List<BlockPos> blocksToIgnite = new ArrayList<>();
        int rangeHorizontal = 15;
        int rangeVertical = 3;

        // direction du regard du joueur
        Vec3d look = player.getRotationVec(1f).normalize();
        // position légèrement en avant du joueur (au sol)
        Vec3d basePos = player.getPos().add(0, -1, 0);

        for (int i = 2; i < rangeHorizontal + 2; i++) {
            // Position le long du vecteur de direction
            Vec3d pos = basePos.add(look.multiply(i));
            BlockPos blockPos = new BlockPos((int) pos.x, (int) pos.y, (int) pos.z);

            // Cherche le premier bloc solide en dessous
            BlockPos current = blockPos;
            int tries = 0;
            while (world.getBlockState(current).isAir() && tries < rangeVertical) {
                current = current.down();
                tries++;
            }

            // Allume le feu au-dessus du bloc solide trouvé
            BlockPos firePos = current.up();
            if (world.isAir(firePos)) {
                blocksToIgnite.add(firePos);
            }
        }


        // Allume le feu
        for (BlockPos pos : blocksToIgnite) {
            world.setBlockState(pos, Blocks.FIRE.getDefaultState());
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
        // Sort instantané, rien à faire ici
    }
}
