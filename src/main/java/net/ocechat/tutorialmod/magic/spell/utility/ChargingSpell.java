package net.ocechat.tutorialmod.magic.spell.utility;

import net.ocechat.tutorialmod.magic.spell.ModSpell;

import java.util.*;

public class ChargingSpell {
    public record ChargingData(ModSpell spell, int timeAtFirstCall) {}



    public static Map<UUID, ChargingData> CHARGING_SPELLS = new HashMap<>();

    public static ChargingData getCharging(UUID player) {
        return CHARGING_SPELLS.get(player);
    }

    public static void removeSpellOfCharged(UUID player) {
        CHARGING_SPELLS.remove(player);
    }

    public static void setSpellToCharged(UUID player, ModSpell spell, int timeAtFirstCall) {
        CHARGING_SPELLS.put(player, new ChargingData(spell, timeAtFirstCall));
    }

    public static boolean isCharging(UUID player) {
        return CHARGING_SPELLS.containsKey(player);
    }

}
