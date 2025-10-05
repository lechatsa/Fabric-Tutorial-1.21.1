package net.ocechat.tutorialmod.entity.client.fireball_spell_entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.screen.Overlay;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.MathHelper;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.entity.custom.FireballSpellEntity;

import java.util.Objects;

import static net.minecraft.client.render.entity.LivingEntityRenderer.getOverlay;


@Environment(EnvType.CLIENT)
public class FireballSpellEntityRenderer extends EntityRenderer<FireballSpellEntity> {

    private final FireballSpellEntityModel model;

    public FireballSpellEntityRenderer(EntityRendererFactory.Context context) {
        super(context);
        this.model = new FireballSpellEntityModel(context.getPart(FireballSpellEntityModel.FIREBALL_SPELL));
    }

    @Override
    public void render(FireballSpellEntity entity, float entityYaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();

        matrices.translate(0.0F, -1.0F, 0.0F);


        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(180.0F - entity.getYaw(tickDelta)));
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(entity.getPitch(tickDelta)));





        this.model.setAngles(entity, 0f, 0f, entity.age + tickDelta, entity.getYaw(tickDelta), entity.getPitch(tickDelta));
        this.model.render(
                matrices,
                vertexConsumers.getBuffer(this.model.getLayer(getTexture(entity))),
                LightmapTextureManager.MAX_LIGHT_COORDINATE,
                OverlayTexture.DEFAULT_UV
        );




        matrices.pop();
        super.render(entity, entityYaw, tickDelta, matrices, vertexConsumers, light);
    }

    @Override
    public Identifier getTexture(FireballSpellEntity entity) {
        return Identifier.of(TutorialMod.MOD_ID, "textures/entity/fireball_spell_entity.png");
    }
}
