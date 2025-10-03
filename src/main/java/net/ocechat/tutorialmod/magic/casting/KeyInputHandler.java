package net.ocechat.tutorialmod.magic.casting;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import net.ocechat.tutorialmod.network.SpellCastNetworking;

public class KeyInputHandler {

    public static void register() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            // Exemple : touche "fireball"
            while (ModKeyBinding.SPAWN_FIRE_IN_STRAIGHT_LIGNE.wasPressed()) {
                SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, "fireball"));
            }


        });
    }




public static void registerKeyInputHandler() {
        TutorialMod.LOGGER.info("Registering Key Input Handler for " + TutorialMod.MOD_ID);
    }
}
