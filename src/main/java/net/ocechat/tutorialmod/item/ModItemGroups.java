package net.ocechat.tutorialmod.item;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.block.ModBlocks;
import net.ocechat.tutorialmod.block.custom.PinkGarnetLampBlock;

public class ModItemGroups {

    public static final ItemGroup PINK_GARNET_ITEMS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "pink_garnet_items"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModItems.PINK_GARNET))      //Defined the Item PINK_GARNET as the Icon
                    .displayName(Text.translatable("itemgroup.tutorialmod.pink_garnet_items"))  //Defined the name and tell that it's translatable via the key
                    .entries(((displayContext, entries) -> {    //Defined the entries

                        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        entries.add(ModItems.RAW_PINK_GARNET);
                        entries.add(ModItems.PINK_GARNET);
                        entries.add(ModItems.CHISEL);
                        entries.add(ModItems.EXPLOSION_STICK);
                        entries.add(ModItems.CAULIFLOWER);
                        entries.add(ModItems.STARLITGHT_ASHES);

                        entries.add(ModItems.PINK_GARNET_SWORD);
                        entries.add(ModItems.PINK_GARNET_AXE);
                        entries.add(ModItems.PINK_GARNET_HOE);
                        entries.add(ModItems.PINK_GARNET_SHOVEL);
                        entries.add(ModItems.PINK_GARNET_PICKAXE);

                        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    })).build());

    public static final ItemGroup PINK_GARNET_BLOCKS_GROUP = Registry.register(Registries.ITEM_GROUP,
            Identifier.of(TutorialMod.MOD_ID, "pink_garnet_blocks"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(ModBlocks.PINK_GARNET_BLOCK))      //Defined the Block PINK_GARNET_BLOCK as the Icon
                    .displayName(Text.translatable("itemgroup.tutorialmod.pink_garnet_blocks"))  //Defined the name and tell that it's translatable via the key
                    .entries(((displayContext, entries) -> {    //Defined the entries

                        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                        entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.PINK_GARNET_BLOCK);
                        entries.add(ModBlocks.PINK_GARNET_ORE_BLOCK);
                        entries.add(ModBlocks.PINK_GARNET_DEEPSLATE_ORE_BLOCK);

                        entries.add(ModBlocks.PINK_GARNET_STAIRS);
                        entries.add(ModBlocks.PINK_GARNET_DOOR);
                        entries.add(ModBlocks.PINK_GARNET_TRAPDOOR);
                        entries.add(ModBlocks.PINK_GARNET_BUTTON);
                        entries.add(ModBlocks.PINK_GARNET_FENCE);
                        entries.add(ModBlocks.PINK_GARNET_SLAB);
                        entries.add(ModBlocks.PINK_GARNET_WALL);
                        entries.add(ModBlocks.PINK_GARNET_PRESSURE_PLATE);
                        entries.add(ModBlocks.PINK_GARNET_FENCE_GATE);

                        entries.add(ModBlocks.PINK_GARNET_LAMP);

                        //////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

                    })).build());




    public static void registerItemGroups() {
        TutorialMod.LOGGER.info("Registering Item Groups for " + TutorialMod.MOD_ID);
    }
}
