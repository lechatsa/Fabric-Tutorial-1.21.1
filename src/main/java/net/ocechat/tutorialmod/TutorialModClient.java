package net.ocechat.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.RenderLayer;
import net.ocechat.tutorialmod.block.ModBlocks;
import net.ocechat.tutorialmod.entity.ModEntities;
import net.ocechat.tutorialmod.entity.custom.fireball_spell_entity.FireballSpellEntityModel;
import net.ocechat.tutorialmod.entity.custom.fireball_spell_entity.FireballSpellEntityRenderer;
import net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity.ShieldBarrierSpellEntity;
import net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity.ShieldBarrierSpellEntityModel;
import net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity.ShieldBarrierSpellEntityRenderer;
import net.ocechat.tutorialmod.magic.casting.KeyInputHandler;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import net.ocechat.tutorialmod.util.ModModelPredicates;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_GARNET_DOOR, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ModBlocks.PINK_GARNET_TRAPDOOR, RenderLayer.getCutout());

        ModModelPredicates.registerModelPredicates();

        ModKeyBinding.registerModKeyBinding();


        KeyInputHandler.sendSpellWhenPressed();
        KeyInputHandler.sendSpellWhenPressedAndRelease();

        EntityModelLayerRegistry.registerModelLayer(FireballSpellEntityModel.FIREBALL_SPELL, FireballSpellEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.FIREBALL_SPELL_ENTITY, FireballSpellEntityRenderer::new);

        EntityModelLayerRegistry.registerModelLayer(ShieldBarrierSpellEntityModel.SHIEL_BARRIER_SPELL, ShieldBarrierSpellEntityModel::getTexturedModelData);
        EntityRendererRegistry.register(ModEntities.SHIELD_BARRIER_SPELL_ENTITY, ShieldBarrierSpellEntityRenderer::new);

    }
}
