package net.ocechat.tutorialmod.magic.casting;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import net.ocechat.tutorialmod.network.SpellCastNetworking;

import java.util.concurrent.atomic.AtomicInteger;

public class KeyInputHandler {

    static boolean wasPressed = false;


    public static void sendSpellWhenPressed() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {



        });
    }

    public static void sendSpellWhenPressedAndRelease() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            //////////////////////////////////////////////////// FIREBALL_SPELL ////////////////////////////////////////////////////
            if (client.player == null) return; // sécurité

            boolean isCurrentlyPressed = ModKeyBinding.FIREBALL_SPELL.isPressed();

            // Quand la touche vient d'être pressée :
            if (isCurrentlyPressed && !wasPressed) {
                SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, "fireball"), false);
                wasPressed = true;
            }

            // Quand la touche vient d'être relâchée :
            if (!isCurrentlyPressed && wasPressed) {
                SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, "fireball"), true);
                wasPressed = false;

            }


        });
    }


public static void registerKeyInputHandler() {
        TutorialMod.LOGGER.info("Registering Key Input Handler for " + TutorialMod.MOD_ID);
    }
}
