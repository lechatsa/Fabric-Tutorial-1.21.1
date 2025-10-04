package net.ocechat.tutorialmod.magic.spell;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.world.World;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.text.Text;

public abstract class ModSpell {

    private final String id;
    private final int manaCost;
    private final int cooldown;
    private final KeyBinding keyBinding;
    private boolean isAffectedByGravity;
    private int lifespan;

    private int currentCooldown;
    private boolean isExpire;

    public ModSpell(String id, int manaCost, int cooldown, KeyBinding keyBinding, boolean isAffectedByGravity, int lifespan) {

        this.id = id;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.isAffectedByGravity = isAffectedByGravity;
        this.lifespan = lifespan;

        this.keyBinding = keyBinding;

        this.isExpire = false;
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
            player.sendMessage(Text.literal("Vous lancez le sort : " + id), true);
        } else {
            player.sendMessage(Text.literal("Sort en recharge !"), true);
        }
    }

    public void tick() {
        if (currentCooldown > 0) {
            currentCooldown--;
        }

        if (lifespan > 0) {
            lifespan--;
        } else {

        }
    }


    public String getId() { return id;}

    public int getManaCost() { return manaCost; }

    public int getCooldown() { return cooldown; }

    public KeyBinding getKeyBinding() { return keyBinding; }

    public int getLifespan() { return lifespan; }

    public int getCurrentCooldown() { return currentCooldown; }

    public boolean isAffectedByGravity() { return isAffectedByGravity; }

    public boolean isExpire() { return isExpire; }


    public void setCurrentCooldown(int currentCooldown) {this.currentCooldown = currentCooldown; }

    public void setLifespan(int lifespan) {this.lifespan = lifespan;}

    public void setExpire(boolean expire) { isExpire = expire; }

    public void setAffectedByGravity(boolean affectedByGravity) { this.isAffectedByGravity = affectedByGravity; }
}