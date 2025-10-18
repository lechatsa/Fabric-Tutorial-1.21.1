package net.ocechat.tutorialmod.magic.casting;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.substantial.FireWallSpell;
import net.ocechat.tutorialmod.magic.spell.substantial.FireballSpell;
import net.ocechat.tutorialmod.magic.spell.substantial.ShieldBarrierSpell;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import net.ocechat.tutorialmod.network.SpellCastNetworking;

import java.util.HashMap;
import java.util.Map;

public class KeyInputHandler {

    // état par KeyBinding pour edge detection
    private static final Map<KeyBinding, Boolean> wasPressedMap = new HashMap<>();

    // pour limiter l'envoi en cas de spam (supplementaire) : dernier tick d'envoi (client tick)
    private static final Map<KeyBinding, Integer> lastSentTick = new HashMap<>();
    private static final int SPAM_TICK_DELAY = 5; // en ticks (20 ticks = 1s)

    public static void registerHandlers() {
        TutorialMod.LOGGER.info("Registering KeyInputHandler");
        ClientTickEvents.END_CLIENT_TICK.register(KeyInputHandler::onEndTick);
    }

    private static void onEndTick(MinecraftClient client) {
        if (client.player == null) return;

        // Non-charging spells (press -> send once)
        handleNonCharging(client, ModKeyBinding.SHIELD_BARRIER_SPELL, ShieldBarrierSpell.ID);
        handleNonCharging(client, ModKeyBinding.FIRE_WALL_SPELL, FireWallSpell.ID);

        // Charging spells (press -> start (false), release -> cast (true))
        handleCharging(client, ModKeyBinding.FIREBALL_SPELL, FireballSpell.ID);

    }

    private static void handleCharging(MinecraftClient client, KeyBinding kb, String id) {
        boolean pressed = kb.isPressed();
        boolean wasPressed = wasPressedMap.getOrDefault(kb, false);
        int tick = client.world == null ? 0 : client.world.getTime() > Integer.MAX_VALUE ? 0 : (int)(client.world.getTime() % Integer.MAX_VALUE);

        if (pressed && !wasPressed) {
            TutorialMod.LOGGER.info("[KeyInput] Pressed start for " + id);
            SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, id), false); // start charging
            lastSentTick.put(kb, tick);
        }

        if (!pressed && wasPressed) {
            TutorialMod.LOGGER.info("[KeyInput] Released -> send cast for " + id);
            SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, id), true); // release => cast
            lastSentTick.put(kb, tick);
        }

        wasPressedMap.put(kb, pressed);
    }

    private static void handleNonCharging(MinecraftClient client, KeyBinding kb, String id) {
        boolean pressed = kb.isPressed();
        boolean wasPressed = wasPressedMap.getOrDefault(kb, false);
        int tick = client.world == null ? 0 : (int) client.world.getTime();
        int lastTick = lastSentTick.getOrDefault(kb, -9999);

        // on envoie uniquement lors du bord montant (pressed && !wasPressed)
        if (pressed && !wasPressed) {
            // anti-spam: ne pas envoyer si dernier envoi trop récent
            if (tick - lastTick >= SPAM_TICK_DELAY) {
                TutorialMod.LOGGER.info("[KeyInput] NonCharging: send " + id);
                SpellCastNetworking.sendSpell(Identifier.of(TutorialMod.MOD_ID, id), true);
                lastSentTick.put(kb, tick);
            } else {
                TutorialMod.LOGGER.info("[KeyInput] NonCharging: skipped spam " + id);
            }
        }

        wasPressedMap.put(kb, pressed);
    }

}
