package net.ocechat.tutorialmod.entity.custom.fireball_spell_entity;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.client.render.OverlayTexture;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.RotationAxis;
import net.minecraft.util.math.Vec3d;
import net.ocechat.tutorialmod.TutorialMod;


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




        Vec3d vel = entity.getVelocity();
        if (vel.lengthSquared() > 0.0001) { // Évite les NaN quand elle est à l'arrêt
            float yaw = entity.getVelocityYaw();
            float pitch = entity.getVelocityPitch();

            // Rotation Y (axe vertical)
            matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-yaw + 180.0F));
            // Rotation X (axe horizontal)
            matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(-pitch));
        }
        matrices.translate(0f, -1.5f, 0f);

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
