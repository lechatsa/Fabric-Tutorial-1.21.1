package net.ocechat.tutorialmod.util;

import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.ocechat.tutorialmod.TutorialMod;
import org.lwjgl.glfw.GLFW;

public class ModKeyBinding {

    public static final KeyBinding SPAWN_FIRE_IN_STRAIGHT_LIGNE = registerKeyBinding("spawn_fire_in_straight_ligne", InputUtil.Type.MOUSE, GLFW.GLFW_MOUSE_BUTTON_4);



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
