package net.ocechat.tutorialmod.magic.spell;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.world.World;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public abstract class ModSpell {

    private final String id;       // Identifiant unique du sort
    private final int manaCost;    // Coût en mana
    private final int cooldown;    // Temps de recharge en ticks (20 ticks = 1 sec)
    private final KeyBinding keyBinding;


    private int currentCooldown;   // Cooldown restant

    public ModSpell(String id, int manaCost, int cooldown, KeyBinding keyBinding) {
        this.id = id;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.currentCooldown = 0;
        this.keyBinding = keyBinding;
    }



    public abstract void cast(World world, PlayerEntity player);


    public boolean canCast(PlayerEntity player) {
        return currentCooldown <= 0;
    }


    public void tryCast(World world, PlayerEntity player) {
        if (canCast(player)) {
            cast(world, player);
            currentCooldown = cooldown; // Réinitialise le cooldown
            player.sendMessage(Text.literal("Vous lancez le sort : " + id), true);
        } else {
            player.sendMessage(Text.literal("Sort en recharge !"), true);
        }
    }

    public void tick() {
        if (currentCooldown > 0) {
            currentCooldown--;
        }
    }

    public String getId() {
        return id;
    }

    public int getManaCost() {
        return manaCost;
    }

    public int getCooldown() {
        return cooldown;
    }

    public KeyBinding getKeyBinding() {
        return keyBinding;
    }
}

