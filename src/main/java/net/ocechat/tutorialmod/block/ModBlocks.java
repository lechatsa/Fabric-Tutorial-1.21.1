package net.ocechat.tutorialmod.block;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.*;
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
import net.ocechat.tutorialmod.block.custom.PinkGarnetLampBlock;
import net.ocechat.tutorialmod.sound.ModSound;


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
            new ExperienceDroppingBlock(UniformIntProvider.create(2, 10), AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.STONE)
            ));

    public static final Block PINK_GARNET_DEEPSLATE_ORE_BLOCK = registerBlock("pink_garnet_deepslate_ore_block",
            new ExperienceDroppingBlock(UniformIntProvider.create(3, 15), AbstractBlock.Settings.create()
                    .strength(4f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.DEEPSLATE)
            ));

    /////////////////////////////////////////////// Register of CUSTOM Blocks ///////////////////////////////////////////////

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            new MagicBlock(AbstractBlock.Settings.create()
                    .strength(1f)
                    .requiresTool()
                    .sounds(ModSound.MAGIC_BLOCK_SOUND_GROUP)
            ));

    public static final Block PINK_GARNET_LAMP = registerBlock("pink_garnet_lamp",
            new PinkGarnetLampBlock(AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .luminance(state -> state.get(PinkGarnetLampBlock.CLICKED) ? 15 : 0)
            ));

    ///////////////////////////////////////////// Register of NON-BLOCKS Blocks /////////////////////////////////////////////

    public static final Block PINK_GARNET_STAIRS =registerBlock("pink_garnet_stairs",
            new StairsBlock(ModBlocks.PINK_GARNET_BLOCK.getDefaultState(), AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    ));
    public static final Block PINK_GARNET_SLAB =registerBlock("pink_garnet_slab",
            new SlabBlock(AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    ));

    public static final Block PINK_GARNET_BUTTON =registerBlock("pink_garnet_button",
            new ButtonBlock(BlockSetType.IRON, 2, AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .noCollision()
            ));
    public static final Block PINK_GARNET_PRESSURE_PLATE =registerBlock("pink_garnet_pressure_plate",
            new PressurePlateBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            ));

    public static final Block PINK_GARNET_FENCE =registerBlock("pink_garnet_fence",
            new FenceBlock(AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            ));
    public static final Block PINK_GARNET_FENCE_GATE =registerBlock("pink_garnet_fence_gate",
            new FenceGateBlock(WoodType.ACACIA, AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            ));
    public static final Block PINK_GARNET_WALL =registerBlock("pink_garnet_wall",
            new WallBlock(AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
            ));

    public static final Block PINK_GARNET_DOOR =registerBlock("pink_garnet_door",
            new DoorBlock(BlockSetType.IRON,AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
            ));
    public static final Block PINK_GARNET_TRAPDOOR =registerBlock("pink_garnet_trapdoor",
            new TrapdoorBlock(BlockSetType.IRON, AbstractBlock.Settings.create()
                    .strength(2f)
                    .requiresTool()
                    .sounds(BlockSoundGroup.AMETHYST_BLOCK)
                    .nonOpaque()
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
