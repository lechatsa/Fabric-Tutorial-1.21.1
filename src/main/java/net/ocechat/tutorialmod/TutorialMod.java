package net.ocechat.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.ocechat.tutorialmod.block.ModBlocks;
import net.ocechat.tutorialmod.component.ModDataComponentTypes;
import net.ocechat.tutorialmod.item.ModItemGroups;
import net.ocechat.tutorialmod.item.ModItems;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModItemGroups.registerItemGroups();
        ModDataComponentTypes.registerDataComponentTypes();


        FuelRegistry.INSTANCE.add(ModItems.STARLITGHT_ASHES, 3000);
	}
}