package net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity;

import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;

public class ShieldBarrierSpellEntityRenderer extends EntityRenderer<ShieldBarrierSpellEntity> {

    private final ShieldBarrierSpellEntityModel model;

    public ShieldBarrierSpellEntityRenderer(EntityRendererFactory.Context ctx) {
        super(ctx);
        this.model = new ShieldBarrierSpellEntityModel(ctx.getPart(ShieldBarrierSpellEntityModel.SHIEL_BARRIER_SPELL));
    }


    @Override
    public void render(ShieldBarrierSpellEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        this.model.render(
                matrices,
                vertexConsumers.getBuffer(this.model.getLayer(getTexture(entity))),
                LightmapTextureManager.MAX_LIGHT_COORDINATE,
                OverlayTexture.DEFAULT_UV
        );

        matrices.pop();
        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(ShieldBarrierSpellEntity entity) {
        return Identifier.of(TutorialMod.MOD_ID, "textures/entity/shield_barrier_spell_entity.png");
    }
}
