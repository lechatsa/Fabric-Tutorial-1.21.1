package net.ocechat.tutorialmod.magic.casting;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import net.ocechat.tutorialmod.network.SpellCastNetworking;

public class KeyInputHandler {

    static boolean wasPressed = false;


    public static void sendSpellWhenPressed() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            registerNonChargingSpell(client, ModKeyBinding.SHIELD_BARRIER_SPELL, "shield_barrier");

        });
    }

    public static void sendSpellWhenPressedAndRelease() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            registerChargingSpell(client, ModKeyBinding.FIREBALL_SPELL, "fireball_spell");
            registerChargingSpell(client, ModKeyBinding.FIRE_WALL_SPELL, "fire_wall_spell");

        });
    }

    public static void registerChargingSpell(MinecraftClient client, KeyBinding keyBinding, String ID) {

        if (client.player == null) return; // sécurité
        boolean isCurrentlyPressed = keyBinding.isPressed();

        // Quand la touche vient d'être pressée :
        if (isCurrentlyPressed && !wasPressed) {
            SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, ID), false);
            wasPressed = true;
        }

        // Quand la touche vient d'être relâchée :
        if (!isCurrentlyPressed && wasPressed) {
            SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, ID), true);
            wasPressed = false;

        }
    }

    public static void registerNonChargingSpell(MinecraftClient client, KeyBinding keyBinding, String ID) {
        if (client.player == null) return; // sécurité

        if (keyBinding.isPressed()) {
            SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, ID), true);

        }
    }

    public static void registerKeyInputHandler() {
        TutorialMod.LOGGER.info("Registering Key Input Handler for " + TutorialMod.MOD_ID);
    }
}
