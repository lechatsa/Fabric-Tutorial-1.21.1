package net.ocechat.tutorialmod.network;

import net.minecraft.client.option.KeyBinding;

import java.util.UUID;

public class InformationsCastingSpells {
    public KeyBinding key;
    public int Time;
    public UUID Player;
    public String id;

    public InformationsCastingSpells(KeyBinding key, int Time, UUID Player, String id) {
        this.key = key;
        this.Time = Time;
        this.Player = Player;
        this.id = id;
    }

    public KeyBinding getKey() {
        return key;
    }

    public int getTime() {
        return Time;
    }

    public UUID getPlayer() {
        return Player;
    }

    public String getId() {
        return id;
    }

    public void erase() {
        this.key = null;
        this.Time = 0;
        this.Player = null;
        this.id = null;
    }
}
