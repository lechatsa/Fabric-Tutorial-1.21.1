package net.ocechat.tutorialmod.magic.spell;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.world.World;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import org.jetbrains.annotations.Nullable;

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


    public abstract void cast(World world, PlayerEntity player, @Nullable int deltaTime);

    public boolean canCast(PlayerEntity player) {
        return currentCooldown <= 0;
    }



    public abstract void tick(SpellInstance instance);


    public String getId() { return id;}

    public int getManaCost() { return manaCost; }

    public int getCooldown() { return cooldown; }

    public KeyBinding getKeyBinding() { return keyBinding; }

    public int getLifetime() { return lifetime; }

    public int getCurrentCooldown() { return currentCooldown; }


    public abstract void tryCast(World world, PlayerEntity player, @Nullable int deltaTime);


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