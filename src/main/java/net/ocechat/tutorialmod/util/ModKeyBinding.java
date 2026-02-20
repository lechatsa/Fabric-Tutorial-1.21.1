package net.ocechat.tutorialmod.util;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.substantial.*;
import net.ocechat.tutorialmod.magic.spell.utility.ModSpellRegistry;
import org.lwjgl.glfw.GLFW;

import java.util.HashMap;
import java.util.Map;

public class ModKeyBinding {

    private static final Map<ModSpell, KeyBinding> SPELL_KEYBINDS = new HashMap<>();

    //////////////////////////////////////// Declare Here the keybind of a new Spell ////////////////////////////////////////
    public static final KeyBinding FIREBALL_SPELL =
            registerKeyBinding(FireballSpell.ID, InputUtil.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_4);

    public static final KeyBinding AP_PROJECTILE_SPELL =
            registerKeyBinding(APProjectileSpell.ID, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_R);

    public static final KeyBinding SHIELD_BARRIER_SPELL =
            registerKeyBinding(ShieldBarrierSpell.ID, InputUtil.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_5);

    public static final KeyBinding FIRE_WALL_SPELL =
            registerKeyBinding(FireWallSpell.ID, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_V);

    public static final KeyBinding METEOR_STRIKE_SPELL =
            registerKeyBinding(MeteorStrikeSpell.ID, InputUtil.Type.KEYSYM, GLFW.GLFW_KEY_B);




    private static KeyBinding registerKeyBinding(String spellId, InputUtil.Type type, int key) {

        KeyBinding keyBinding = KeyBindingHelper.registerKeyBinding(
                new KeyBinding(
                        "key.tutorialmod." + spellId,
                        type,
                        key,
                        "category.tutorialmod.keys"
                )
        );

        SPELL_KEYBINDS.put(ModSpellRegistry.get(spellId), keyBinding);

        return keyBinding;
    }

    public static void registerModKeyBinding() {
        TutorialMod.LOGGER.info("Registering Key Binding for " + TutorialMod.MOD_ID);
    }
}
