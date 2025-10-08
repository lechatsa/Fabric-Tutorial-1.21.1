package net.ocechat.tutorialmod.magic.spell.substantial;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import org.jetbrains.annotations.Nullable;

public class ShieldBarrierSpell extends ModSpell {
    public ShieldBarrierSpell(String id, int manaCost, int cooldown, KeyBinding keyBinding, boolean isAffectedByGravity, int lifetime) {
        super("shield_barrier_spell",60, 20, ModKeyBinding.SHIELD_BARRIER_SPELL, false, 600);
    }

    @Override
    public void cast(World world, PlayerEntity player, @Nullable int deltaTime) {

    }

    @Override
    public void tick(SpellInstance instance) {
        this.setCurrentCooldown(this.getCurrentCooldown() - 1);
        if (!(instance.getAttachedElement() instanceof FireballEntity fireball)) return;

        if (!fireball.isAlive()) {
            fireball.discard();
        }
    }

    @Override
    public void tryCast(World world, PlayerEntity player, @Nullable int deltaTime) {
        if (canCast(player)) {
            this.setCurrentCooldown(this.getCooldown());

            cast(world, player, deltaTime);

            player.sendMessage(Text.literal("You cast the Spell : " + this.getId()), true);

        } else {

            player.sendMessage(Text.literal("The Spell is in cooldown !"), true);

        }

    }
}
