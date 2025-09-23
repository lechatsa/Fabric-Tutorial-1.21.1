package net.ocechat.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.block.ModBlocks;
import net.ocechat.tutorialmod.item.ModItems;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {

        List<ItemConvertible> PINK_GARNET_SMElTABLES = List.of(ModItems.RAW_PINK_GARNET, ModBlocks.PINK_GARNET_ORE_BLOCK, ModBlocks.PINK_GARNET_DEEPSLATE_ORE_BLOCK);

        offerSmelting(exporter, PINK_GARNET_SMElTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 200, "pink_garnet");
        offerBlasting(exporter, PINK_GARNET_SMElTABLES, RecipeCategory.MISC, ModItems.PINK_GARNET, 0.25f, 200, "pink_garnet");

        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.PINK_GARNET, RecipeCategory.MISC, ModBlocks.PINK_GARNET_BLOCK);
        offerReversibleCompactingRecipes(exporter, RecipeCategory.BUILDING_BLOCKS, ModItems.RAW_PINK_GARNET, RecipeCategory.MISC, ModBlocks.RAW_PINK_GARNET_BLOCK);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, ModItems.RAW_PINK_GARNET, 32)
                        .input(ModBlocks.MAGIC_BLOCK)
                        .criterion(hasItem(ModItems.RAW_PINK_GARNET), conditionsFromItem(ModItems.RAW_PINK_GARNET))
                        .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "raw_pink_garnet_from_magic_block"));

//        createDoorRecipe(ModBlocks.PINK_GARNET_DOOR, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
//                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
//                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID));
//
//
//        createSlabRecipe(RecipeCategory.BUILDING_BLOCKS, ModBlocks.PINK_GARNET_SLAB, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
//                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
//                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID));
//
//
//        createStairsRecipe(ModBlocks.PINK_GARNET_STAIRS, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
//                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
//                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID));
//
//
//        createFenceRecipe(ModBlocks.PINK_GARNET_FENCE, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
//                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
//                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID));
//
//
//        createFenceGateRecipe(ModBlocks.PINK_GARNET_FENCE_GATE, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
//                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
//                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID));
//
//
//        createPressurePlateRecipe(RecipeCategory.REDSTONE, ModBlocks.PINK_GARNET_PRESSURE_PLATE, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
//                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
//                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID));
//
//
//        createTrapdoorRecipe(ModBlocks.PINK_GARNET_TRAPDOOR, Ingredient.ofItems(ModBlocks.PINK_GARNET_BLOCK))
//                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
//                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID));


        ShapelessRecipeJsonBuilder.create(RecipeCategory.REDSTONE, ModBlocks.PINK_GARNET_BUTTON, 1)
                .input(ModBlocks.PINK_GARNET_BLOCK)
                .criterion(hasItem(ModBlocks.PINK_GARNET_BLOCK), conditionsFromItem(ModBlocks.PINK_GARNET_BLOCK))
                .offerTo(exporter, Identifier.of(TutorialMod.MOD_ID, "pink_garnet_button_from_pink_garnet_block"));

        offerWallRecipe(exporter, RecipeCategory.BUILDING_BLOCKS, ModBlocks.PINK_GARNET_WALL, ModBlocks.PINK_GARNET_BLOCK);


    }
}
