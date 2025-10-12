package net.ocechat.tutorialmod.magic.spell.substantial;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import org.jetbrains.annotations.Nullable;

public class FireWallSpell extends ModSpell {
    private final int timeToCharged;

    public FireWallSpell(String id, int manaCost, int cooldown, KeyBinding keyBinding, boolean AffectedByGravity, int lifetime, boolean needToCharge) {
        super("fire_wall_spell", 60, 10, ModKeyBinding.FIRE_WALL_SPELL, false, 60, true);
        this.timeToCharged = 60;
    }

    @Override
    public void cast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        Vec3d playerPos = player.getPos();
        Vec3d direction = player.getRotationVector();
        double speed = 3;

        while (getCurrentLifetime() < getLifetime()) {
            Vec3d pos = playerPos.add(direction.multiply(speed));
            world.setBlockState(pos , new BlockState(pos,Blocks.FIRE.getDefaultState() )
        }


    }

    @Override
    public void tryCast(World world, PlayerEntity player, @Nullable Integer deltaTime) {

    }

    @Override
    public void tick(SpellInstance instance) {

    }
}
