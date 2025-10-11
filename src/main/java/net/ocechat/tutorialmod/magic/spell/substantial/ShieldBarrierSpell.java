package net.ocechat.tutorialmod.magic.spell.substantial;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.OcechatMath;
import net.ocechat.tutorialmod.entity.ModEntities;
import net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity.ShieldBarrierSpellEntity;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.utility.ActivesSpells;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import org.jetbrains.annotations.Nullable;

public class ShieldBarrierSpell extends ModSpell {


    public ShieldBarrierSpell() {
        super("shield_barrier_spell",60, 100, ModKeyBinding.SHIELD_BARRIER_SPELL, false, 10, false);
    }




    @Override
    public void cast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        if (world.isClient) return; // IMPORTANT : n'exécute le spawn QUE côté serveur


        Vec3d spawnPos = player.getPos();

        ShieldBarrierSpellEntity shieldBarrier = new ShieldBarrierSpellEntity(ModEntities.SHIELD_BARRIER_SPELL_ENTITY, world);

        // Position ET rotation AVANT le spawn pour que le paquet contienne les bons angles
        shieldBarrier.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

        float yaw = player.getYaw();

        shieldBarrier.setYaw(yaw);
        shieldBarrier.setPitch(0);

        // Optionnel mais utile : refreshPositionAndAngles si disponible
        try {
            shieldBarrier.refreshPositionAndAngles(spawnPos, yaw, 0);
        } catch (NoSuchMethodError ignored) {
            // fallback silencieux si la méthode n'existe pas dans ta mapping
        }

        ActivesSpells.addSpell(new SpellInstance(player, this, shieldBarrier));
        world.spawnEntity(shieldBarrier); // spawn côté serveur -> paquet au client
    }



    @Override
    public void tick(SpellInstance instance) {
        this.setCurrentCooldown(this.getCurrentCooldown() - 1);
        this.setCurrentLifetime(this.getCurrentLifetime() - 1);

        if (!(instance.getAttachedElement() instanceof ShieldBarrierSpellEntity shieldBarrier)) return;

        if (getLifetime() <= getCurrentLifetime() && !shieldBarrier.isDiscarding()) {
            shieldBarrier.setDiscarding(true);
        }
    }


    @Override
    public void tryCast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        if (canCast(player)) {
            this.setCurrentCooldown(this.getCooldown());

            cast(world, player, deltaTime);

            player.sendMessage(Text.literal("You cast the Spell : " + this.getId()), true);

        } else {

            player.sendMessage(Text.literal("The Spell is in cooldown !"), true);

        }


    }
}
