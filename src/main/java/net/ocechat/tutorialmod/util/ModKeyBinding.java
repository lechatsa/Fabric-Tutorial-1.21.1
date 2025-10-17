package net.ocechat.tutorialmod.util;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.substantial.FireWallSpell;
import net.ocechat.tutorialmod.magic.spell.substantial.FireballSpell;
import net.ocechat.tutorialmod.magic.spell.substantial.ShieldBarrierSpell;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinding {

    public static final KeyBinding FIREBALL_SPELL = registerKeyBinding(new FireballSpell(), InputUtil.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_4);
    public static final KeyBinding SHIELD_BARRIER_SPELL = registerKeyBinding(new ShieldBarrierSpell(), InputUtil.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_5);
    public static final KeyBinding FIRE_WALL_SPELL = registerKeyBinding(new FireWallSpell(), InputUtil.Type.MOUSE, GLFW.GLFW_KEY_R);



    private static <T extends ModSpell> KeyBinding registerKeyBinding(T spell, InputUtil.Type type, int buttonPressed ) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.tutorialmod." + spell.getId(),
                 type,
                buttonPressed,
                "category.tutorialmod.keys"
        ));
    }

    public static void registerModKeyBinding() {
        TutorialMod.LOGGER.info("Registering Key Binding for " + TutorialMod.MOD_ID);
    }
}
