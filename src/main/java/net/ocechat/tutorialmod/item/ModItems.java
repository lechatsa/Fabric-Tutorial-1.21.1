package net.ocechat.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;

public class ModItems {

    public static final Item PINK_GARNET = registerItem("pink_garnet", new Item(new Item.Settings(.fireproof())));




    private static Item registerItem(String name, Item item) {

        return Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name), item);

    }

    public static void registerModItems() {

        TutorialMod.LOGGER.info("Registering Mod Items for " + TutorialMod.MOD_ID);

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {

            entries.add(PINK_GARNET);

        });

    }

}
