package net.ocechat.tutorialmod.magic.spell;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.world.World;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;

public abstract class ModSpell {

    private final String id;
    private int manaCost;
    private int cooldown;
    private KeyBinding keyBinding;
    private boolean isAffectedByGravity;
    private final int lifetime;

    private int currentCooldown;






    public ModSpell(String id, int manaCost, int cooldown, KeyBinding keyBinding, boolean isAffectedByGravity, int lifetime) {

        this.id = id;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.isAffectedByGravity = isAffectedByGravity;
        this.lifetime = lifetime;

        this.keyBinding = keyBinding;

        this.currentCooldown = 0;

    }


    public abstract void cast(World world, PlayerEntity player);

    public boolean canCast(PlayerEntity player) {
        return currentCooldown <= 0;
    }

    public void tryCast(World world, PlayerEntity player) {
        if (canCast(player)) {
            cast(world, player);
            currentCooldown = cooldown; // Réinitialise le cooldown
            player.sendMessage(Text.literal("You cast the Spell : " + id), true);
        } else {
            player.sendMessage(Text.literal("The Spell is in cooldown !"), true);
        }
    }

    public abstract void tick(SpellInstance instance);


    public String getId() { return id;}

    public int getManaCost() { return manaCost; }

    public int getCooldown() { return cooldown; }

    public KeyBinding getKeyBinding() { return keyBinding; }

    public int getLifetime() { return lifetime; }

    public int getCurrentCooldown() { return currentCooldown; }

    public boolean isAffectedByGravity() { return isAffectedByGravity; }



    public void setManaCost(int manaCost) {
        this.manaCost = manaCost;
    }

    public void setCooldown(int cooldown) {
        this.cooldown = cooldown;
    }

    public void setKeyBinding(KeyBinding keyBinding) {
        this.keyBinding = keyBinding;
    }

    public void setCurrentCooldown(int currentCooldown) {
        this.currentCooldown = currentCooldown;
    }

    public void setAffectedByGravity(boolean affectedByGravity) {
        this.isAffectedByGravity = affectedByGravity;
    }


}