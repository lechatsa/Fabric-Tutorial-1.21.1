package net.ocechat.tutorialmod.magic.spell.substantial;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.entity.ModEntities;
import net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity.ShieldBarrierSpellEntity;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.utility.ActivesSpells;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import org.jetbrains.annotations.Nullable;

public class ShieldBarrierSpell extends ModSpell {


    public ShieldBarrierSpell() {
        super("shield_barrier_spell",60, 20, ModKeyBinding.SHIELD_BARRIER_SPELL, false, 600, false);
    }




    @Override
    public void cast(World world, PlayerEntity player, @Nullable int deltaTime) {
        Vec3d look = player.getRotationVec(1.0F);
        Vec3d eyePos = player.getEyePos();

        Vec3d spawnPos = eyePos.add(look.multiply(1));

        // Création de la boule de feu
        ShieldBarrierSpellEntity shieldBarrier = new ShieldBarrierSpellEntity(ModEntities.SHIELD_BARRIER_SPELL_ENTITY, world);
        ActivesSpells.addSpell(new SpellInstance(player, this, shieldBarrier));

        // Placement
        shieldBarrier.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

        // Spawn dans le monde
        world.spawnEntity(shieldBarrier);
    }

    @Override
    public void tick(SpellInstance instance) {
        this.setCurrentCooldown(this.getCurrentCooldown() - 1);
        if (!(instance.getAttachedElement() instanceof ShieldBarrierSpellEntity shieldBarrier)) return;

        if (!shieldBarrier.isAlive() || getLifetime() < getCurrentLifetime()) {
            shieldBarrier.discard();
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
