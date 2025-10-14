package net.ocechat.tutorialmod.util;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.ocechat.tutorialmod.TutorialMod;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinding {

    public static final KeyBinding FIREBALL_SPELL = registerKeyBinding("fireball_spell", InputUtil.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_4);
    public static final KeyBinding SHIELD_BARRIER_SPELL = registerKeyBinding("shield_barrier_spell", InputUtil.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_5);
    public static final KeyBinding FIRE_WALL_SPELL = registerKeyBinding("fire_wall_spell", InputUtil.Type.MOUSE, GLFW.GLFW_KEY_R);



    private static KeyBinding registerKeyBinding(String name, InputUtil.Type type, int buttonPressed ) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
                "key.tutorialmod." + name,
                 type,
                buttonPressed,
                "category.tutorialmod.keys"
        ));
    }

    public static void registerModKeyBinding() {
        TutorialMod.LOGGER.info("Registering Key Binding for " + TutorialMod.MOD_ID);
    }
}
