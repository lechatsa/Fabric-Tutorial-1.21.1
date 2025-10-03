package net.ocechat.tutorialmod.magic.spell;

import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.FireballSpell;
import net.ocechat.tutorialmod.magic.spell.ModSpell;

import java.util.HashMap;
import java.util.Map;

public class ModSpellRegistry {
    private static final Map<String, ModSpell> SPELLS = new HashMap<>();

    // Enregistre un spell
    public static <T extends ModSpell> T register(String name, T spell) {
        Identifier id = Identifier.of(TutorialMod.MOD_ID, name);
        SPELLS.put(id.toString(), spell);
        return spell;
    }

    // Récupère un spell par ID
    public static ModSpell get(String id) {
        return SPELLS.get(id);
    }


    public static void registerAll() {

        register("fireball", new FireballSpell());

    }
}
