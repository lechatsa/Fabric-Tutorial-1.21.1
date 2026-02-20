package net.ocechat.tutorialmod.magic.casting;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.substantial.*;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import net.ocechat.tutorialmod.network.SpellCastNetworking;

import static net.ocechat.tutorialmod.TutorialMod.*;
import java.util.HashMap;
import java.util.Map;

public class KeyInputHandler {

    // état par KeyBinding pour edge detection
    private static final Map<KeyBinding, Boolean> hasBeenReleasedMap = new HashMap<>();

    // pour limiter l'envoi en cas de spam (supplementaire) : dernier tick d'envoi (client tick)
    private static final Map<KeyBinding, Integer> lastSentTick = new HashMap<>();
    private static final int SPAM_TICK_DELAY = 5; // en ticks (20 ticks = 1s)

    public static void registerHandlers() {
        LOGGER.info("Registering KeyInputHandler");
        ClientTickEvents.END_CLIENT_TICK.register(KeyInputHandler::onEndTick);
    }

    private static void onEndTick(MinecraftClient client) {
        if (client.player == null) return;

        for (ModKeyBinding.)


    }


























    /*private static void handleCharging(MinecraftClient client, KeyBinding keybind, String id) {


        boolean isCurrentlyBeingPressed = keybind.isPressed();
        boolean hasBeenReleased = hasBeenReleasedMap.getOrDefault(keybind, false);

        int actualTime = client.world == null ? 0 : client.world.getTime() > Integer.MAX_VALUE ? 0 : (int)(client.world.getTime() % Integer.MAX_VALUE);

        if (isCurrentlyBeingPressed && !hasBeenReleased) {

            if (DEBUG_MODE) LOGGER.info("[KeyInput] Pressed start for " + id);


            SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, id), false); // start charging
            lastSentTick.put(keybind, actualTime);
        }

        if (!isCurrentlyBeingPressed && hasBeenReleased) {

            if (DEBUG_MODE) LOGGER.info("[KeyInput] Released -> send cast for " + id);

            SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, id), true); // release => cast
            lastSentTick.put(keybind, actualTime);
        }

        hasBeenReleasedMap.put(keybind, isCurrentlyBeingPressed);
    }

    private static void handleNonCharging(MinecraftClient client, KeyBinding keybind, String id) {

        /////////////////////////////////// Variable HasBeenPressed & IsCurrentlyBeingPressed ///////////////////////////////////
        boolean isCurrentlyBeingPressed = keybind.isPressed();
        boolean hasBeenReleased = hasBeenReleasedMap.getOrDefault(keybind, false);


        int actualTime = client.world == null ? 0 : (int) client.world.getTime(); /// The current Time

        int lastTick = lastSentTick.getOrDefault(keybind, -9999); /// Last Time the player has pressed this Key

        /// If the key is pressed but has not been released yet, => The player is currently holding the key
        if (isCurrentlyBeingPressed && !hasBeenReleased) {

            /// Anti-Spam: DeltaT needs to be greater than the min delay between two casting
            if (actualTime - lastTick >= SPAM_TICK_DELAY) {

                if (DEBUG_MODE) LOGGER.info("[KeyInput] NonCharging: send " + id);

                SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, id), true);
                lastSentTick.put(keybind, actualTime);

            } else {

                if (DEBUG_MODE) LOGGER.info("[KeyInput] NonCharging: skipped spam " + id);

            }
        }

        hasBeenReleasedMap.put(keybind, isCurrentlyBeingPressed);
    }
    // Non-charging spells (press -> send once)
        handleNonCharging(client, ModKeyBinding.SHIELD_BARRIER_SPELL, ShieldBarrierSpell.ID);
        handleNonCharging(client, ModKeyBinding.FIRE_WALL_SPELL, FireWallSpell.ID);
        handleNonCharging(client, ModKeyBinding.METEOR_STRIKE_SPELL, MeteorStrikeSpell.ID);

        // Charging spells (press -> start (false), release -> cast (true))
        handleCharging(client, ModKeyBinding.FIREBALL_SPELL, FireballSpell.ID);
        handleCharging(client, ModKeyBinding.AP_PROJECTILE_SPELL, APProjectileSpell.ID);

     */
}
