package net.ocechat.tutorialmod.magic.spell;

import net.minecraft.client.option.KeyBinding;
import net.minecraft.text.Text;
import net.minecraft.world.World;

import net.minecraft.entity.player.PlayerEntity;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import org.jetbrains.annotations.Nullable;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import static net.ocechat.tutorialmod.TutorialMod.*;


public abstract class ModSpell {

    private final String id;
    private int manaCost;
    private int cooldown;
    private KeyBinding keyBinding;
    private boolean AffectedByGravity;
    private final int lifetime;

    private int currentCooldown;
    private int currentLifetime;
    private final boolean needToCharge;
    private final Map<UUID, Integer> playerCooldowns = new ConcurrentHashMap<>();


    public ModSpell(String id, int manaCost, int cooldown, KeyBinding keyBinding, boolean AffectedByGravity, int lifetime, boolean needToCharge) {

        this.id = id;
        this.manaCost = manaCost;
        this.cooldown = cooldown;
        this.AffectedByGravity = AffectedByGravity;
        this.lifetime = lifetime;

        this.keyBinding = keyBinding;
        this.needToCharge = needToCharge;

        this.currentLifetime = 0;
        this.currentCooldown = 0;

    }


    public abstract void cast(World world, PlayerEntity player, @Nullable Integer deltaTime);

    public void tryCast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        if (!canCast(player)) {
            int remaining = playerCooldowns.getOrDefault(player.getUuid(), 0);
            player.sendMessage(Text.literal("Cooldown: " + (remaining / 20f)  + "s"), true);
            return;
        }

        // Reset cooldown pour ce joueur
        playerCooldowns.put(player.getUuid(), cooldown);
        cast(world, player, deltaTime);

        if (DEBUG_MODE) LOGGER.info("[{}] Cast triggered by {}", id.toUpperCase(), player.getName().getString());

        player.sendMessage(Text.literal("You cast the spell: " + id), true);
    }

    public void tick(SpellInstance instance) {
        for (UUID id : playerCooldowns.keySet()) {
            int current = playerCooldowns.get(id);
            if (current > 0) {
                playerCooldowns.put(id, current - 1);
            }
        }
    }


    //////////////////////////////////////////////////////// Getter ////////////////////////////////////////////////////////

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

    public int getLifetime() {
        return lifetime;
    }

    public int getCurrentCooldown() {
        return currentCooldown;
    }

    public int getCurrentLifetime() {
        return currentLifetime;
    }

    public boolean canCast(PlayerEntity player) {
        return playerCooldowns.getOrDefault(player.getUuid(), 0) <= 0;
    }

    public boolean isAffectedByGravity() {
        return AffectedByGravity;
    }


    //////////////////////////////////////////////////////// Setter ////////////////////////////////////////////////////////

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
        this.AffectedByGravity = affectedByGravity;
    }

    public void setCurrentLifetime(int currentLifetime) {
        this.currentLifetime = currentLifetime;
    }

    public boolean isNeedToCharge() {
        return needToCharge;
    }


}