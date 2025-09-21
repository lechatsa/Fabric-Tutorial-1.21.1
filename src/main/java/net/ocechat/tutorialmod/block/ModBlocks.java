package net.ocechat.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.ExperienceDroppingBlock;
import net.minecraft.block.StairsBlock;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.block.custom.MagicBlock;


public class ModBlocks {

    /////////////////////////////////////////////// Register of NORMAL Blocks ///////////////////////////////////////////////

    public static final Block PINK_GARNET_BLOCK = registerBlock("pink_garnet_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            ));

    public static final Block RAW_PINK_GARNET_BLOCK = registerBlock("raw_pink_garnet_block",
            new Block(AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            ));

    //////////////////////////////////////// Register of EXPERIENCE DROPPING Blocks ////////////////////////////////////////

    public static final Block PINK_GARNET_ORE_BLOCK = registerBlock("pink_garnet_ore_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 10),
                    AbstractBlock.Settings.create()
                            .strength(4f)
                            .requiresTool()
                            .sounds(BlockSoundGroup.STONE)
            ));

    public static final Block PINK_GARNET_DEEPSLATE_ORE_BLOCK = registerBlock("pink_garnet_deepslate_ore_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 15),
                    AbstractBlock.Settings.create()
                            .strength(4f)
                            .requiresTool()
                            .sounds(BlockSoundGroup.DEEPSLATE)
            ));

    /////////////////////////////////////////////// Register of CUSTOM Blocks ///////////////////////////////////////////////

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(
                    AbstractBlock.Settings.create()
                            .strength(1f)
                            .requiresTool()
                            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            ));

    ///////////////////////////////////////////// Register of NON-BLOCKS Blocks /////////////////////////////////////////////

    public static final Block PINK_GARNET_STAIRS =registerBlock("pink_garnet_stairs",
            new StairsBlock(ModBlocks.PINK_GARNET_BLOCK.getDefaultState(),
                    AbstractBlock.Settings.create()
                            .strength(2f)
                            .requiresTool()
                            .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    ));

    ////////////////////////////////////////////////// End of the Register //////////////////////////////////////////////////

    //////////////////////////////////////////////////// Helper Methods ////////////////////////////////////////////////////

    private static Block registerBlock(String name, Block block) {
        registerBlockItem(name, block);
        return Registry.register(Registries.BLOCK, Identifier.of(TutorialMod.MOD_ID, name), block);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(TutorialMod.MOD_ID, name),
            new BlockItem(block, new Item.Settings()));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {

            entries.add(ModBlocks.PINK_GARNET_BLOCK);
            entries.add(ModBlocks.RAW_PINK_GARNET_BLOCK);

        });

    }
    /////////////////////////////////////////////// End of the Helper Methods ///////////////////////////////////////////////
}
