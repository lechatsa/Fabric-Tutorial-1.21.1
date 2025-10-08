package net.ocechat.tutorialmod.magic.spell.utility;

import net.ocechat.tutorialmod.magic.spell.ModSpell;

import java.util.*;

public class ChargingSpell {
    public record chargingSpell(ModSpell spell, int timeAtFirstCall) {}



    public static Map<UUID,chargingSpell> CHARGING_SPELL = new HashMap<>();


    public static void removeSpellOfCharged(UUID player) {
        CHARGING_SPELL.remove(player);
    }

    public static void setSpellToCharged(UUID player, ModSpell spell, int timeAtFirstCall) {
        CHARGING_SPELL.put(player, new chargingSpell(spell, timeAtFirstCall));
    }

}
