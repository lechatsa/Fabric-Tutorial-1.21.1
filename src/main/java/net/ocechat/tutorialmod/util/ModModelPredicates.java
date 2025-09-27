package net.ocechat.tutorialmod.util;

import net.minecraft.client.item.ModelPredicateProviderRegistry;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.component.ModDataComponentTypes;
import net.ocechat.tutorialmod.item.ModItems;

public class ModModelPredicates {
    public static void registerModelPredicates(){
        ModelPredicateProviderRegistry.register(ModItems.CHISEL, Identifier.of(TutorialMod.MOD_ID, "used"),
                ((stack, world, entity, seed) -> stack.get(ModDataComponentTypes.COORDINATES) != null ? 1 : 0));

    }
}
