package net.ocechat.tutorialmod.entity.client.fireball_spell_entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.entity.ModEntities;
import net.ocechat.tutorialmod.entity.custom.FireballSpellEntity;

// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class FireballSpellEntityModel extends SinglePartEntityModel<FireballSpellEntity> {
    public static final EntityModelLayer FIREBALL_SPELL = new EntityModelLayer(Identifier.of(TutorialMod.MOD_ID, "fireball_spell"), "main");



    private final ModelPart Main;




    public FireballSpellEntityModel(ModelPart root) {
        this.Main = root.getChild("Main");

    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Main = modelPartData.addChild("Main", ModelPartBuilder.create().uv(0, 0).cuboid(-1.5F, -1.5F, -1.5F, 3.0F, 3.0F, 3.0F, new Dilation(0.0F)), ModelTransform.of(0.1165F, 23.9353F, -2.4761F, 0.0F, -1.5708F, -1.5708F));

        ModelPartData FirstDetails = Main.addChild("FirstDetails", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Up = FirstDetails.addChild("Up", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F, 0.0F, -1.5F));

        ModelPartData cube_r1 = Up.addChild("cube_r1", ModelPartBuilder.create().uv(12, 0).cuboid(0.0F, -1.5F, 0.0F, 2.5F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(6, 12).cuboid(2.5F, 0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(16, 7).cuboid(3.5F, -1.5F, 0.0F, 1.5F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(14, 12).cuboid(2.5F, -0.5F, 0.0F, 2.5F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 0.2618F, 0.0F));

        ModelPartData Down = FirstDetails.addChild("Down", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F, 0.0F, 1.5F));

        ModelPartData cube_r2 = Down.addChild("cube_r2", ModelPartBuilder.create().uv(0, 6).cuboid(0.0F, -1.5F, 0.0F, 3.5F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(12, 17).cuboid(3.5F, -1.5F, 0.0F, 1.0F, 2.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 0).cuboid(4.5F, -1.5F, 0.0F, 0.0F, 3.0F, 0.0F, new Dilation(0.0F))
                .uv(20, 3).cuboid(4.5F, -0.5F, 0.0F, 0.5F, 1.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -0.2618F, 0.0F));

        ModelPartData Right = FirstDetails.addChild("Right", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F, -1.5F, 0.0F));

        ModelPartData cube_r3 = Right.addChild("cube_r3", ModelPartBuilder.create().uv(20, 4).cuboid(4.5F, 0.5F, 0.0F, 0.5F, 0.5F, 0.0F, new Dilation(0.0F))
                .uv(16, 8).cuboid(3.5F, 0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(18, 11).cuboid(3.5F, -1.5F, 0.0F, 1.5F, 0.5F, 0.0F, new Dilation(0.0F))
                .uv(8, 6).cuboid(0.0F, -1.5F, 0.0F, 3.5F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, -0.2618F));

        ModelPartData Left = FirstDetails.addChild("Left", ModelPartBuilder.create(), ModelTransform.pivot(-1.5F, 1.5F, 0.0F));

        ModelPartData cube_r4 = Left.addChild("cube_r4", ModelPartBuilder.create().uv(20, 2).cuboid(3.5F, -1.5F, 0.0F, 0.7F, 1.0F, 0.0F, new Dilation(0.0F))
                .uv(0, 9).cuboid(0.0F, -1.5F, 0.0F, 3.5F, 3.0F, 0.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 1.5708F, 0.0F, 0.2618F));

        ModelPartData SecondDetails = Main.addChild("SecondDetails", ModelPartBuilder.create(), ModelTransform.of(-2.0F, 1.5F, 1.5F, 0.0F, 0.0F, -1.5708F));

        ModelPartData Left2 = SecondDetails.addChild("Left2", ModelPartBuilder.create().uv(4, 16).cuboid(0.0F, 3.2897F, -1.5F, 0.0F, 2.2F, 0.9059F, new Dilation(0.001F))
                .uv(0, 0).cuboid(0.0F, 3.2897F, -0.5941F, 0.0F, 1.5F, 0.3F, new Dilation(0.001F))
                .uv(16, 16).cuboid(0.0F, 3.2897F, -0.2941F, 0.0F, 1.5F, 0.9059F, new Dilation(0.001F))
                .uv(16, 3).cuboid(0.0F, 3.2897F, 0.6119F, 0.0F, 2.7103F, 0.8881F, new Dilation(0.001F))
                .uv(0, 0).cuboid(0.0F, 1.7794F, -1.5F, 0.0F, 1.5103F, 0.4059F, new Dilation(0.001F))
                .uv(2, 12).cuboid(0.0F, 1.7794F, -0.2941F, 0.0F, 1.5103F, 1.7941F, new Dilation(0.001F))
                .uv(18, 19).cuboid(0.0F, 0.8691F, -1.5F, 0.0F, 0.9103F, 0.6059F, new Dilation(0.001F))
                .uv(10, 13).cuboid(0.0F, 0.0F, -0.8941F, 0.0F, 1.7794F, 1.6F, new Dilation(0.001F)), ModelTransform.pivot(0.0F, 1.5F, -1.5F));

        ModelPartData Right2 = SecondDetails.addChild("Right2", ModelPartBuilder.create().uv(20, 0).cuboid(0.0F, 0.0F, 0.9119F, 0.0F, 0.9588F, 0.5881F, new Dilation(0.001F))
                .uv(0, 12).cuboid(0.0F, 0.0F, -0.0882F, 0.0F, 6.0F, 1.0F, new Dilation(0.001F))
                .uv(8, 17).cuboid(0.0F, 0.0F, -0.9882F, 0.0F, 1.9691F, 0.6941F, new Dilation(0.001F))
                .uv(2, 16).cuboid(0.0F, 1.9691F, -1.5F, 0.0F, 2.0103F, 1.2059F, new Dilation(0.001F))
                .uv(0, 0).cuboid(0.0F, 0.0F, -1.1941F, 0.0F, 1.9691F, 0.2059F, new Dilation(0.001F))
                .uv(0, 0).cuboid(0.0F, 0.0F, -1.5F, 0.0F, 0.9588F, 0.3059F, new Dilation(0.001F))
                .uv(0, 0).cuboid(0.0F, 3.9794F, -1.5F, 0.0F, 1.0103F, 0.2059F, new Dilation(0.001F))
                .uv(0, 0).cuboid(0.0F, 0.0F, -0.2941F, 0.0F, 6.0F, 0.2059F, new Dilation(0.001F))
                .uv(18, 2).cuboid(0.0F, 2.9794F, 0.9119F, 0.0F, 2.0103F, 0.5881F, new Dilation(0.001F)), ModelTransform.pivot(3.0F, 1.5F, -1.5F));

        ModelPartData Down2 = SecondDetails.addChild("Down2", ModelPartBuilder.create(), ModelTransform.pivot(1.5F, 1.5F, 0.0F));

        ModelPartData cube_r5 = Down2.addChild("cube_r5", ModelPartBuilder.create().uv(14, 13).cuboid(0.0F, 4.7897F, -0.7941F, 0.0F, 0.7F, 1.7059F, new Dilation(0.001F))
                .uv(18, 5).cuboid(0.0F, 0.0F, 0.7119F, 0.0F, 1.2588F, 0.7881F, new Dilation(0.001F))
                .uv(6, 13).cuboid(0.0F, 0.0F, -0.8941F, 0.0F, 1.7691F, 1.6059F, new Dilation(0.001F))
                .uv(0, 19).cuboid(0.0F, 1.7691F, 0.8119F, 0.0F, 1.5103F, 0.6881F, new Dilation(0.001F))
                .uv(18, 0).cuboid(0.0F, 1.7691F, -0.1881F, 0.0F, 1.0F, 1.0F, new Dilation(0.001F))
                .uv(18, 8).cuboid(0.0F, 1.7691F, -0.8941F, 0.0F, 1.5103F, 0.7059F, new Dilation(0.001F))
                .uv(14, 16).cuboid(0.0F, 3.2794F, 0.5178F, 0.0F, 1.5103F, 0.9822F, new Dilation(0.001F))
                .uv(18, 13).cuboid(0.0F, 3.2794F, -0.1881F, 0.0F, 1.5103F, 0.7059F, new Dilation(0.001F))
                .uv(14, 9).cuboid(0.0F, 5.4897F, -0.7941F, 0.0F, 0.5103F, 1.9F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));

        ModelPartData Up2 = SecondDetails.addChild("Up2", ModelPartBuilder.create(), ModelTransform.pivot(1.5F, 1.5F, -3.0F));

        ModelPartData cube_r6 = Up2.addChild("cube_r6", ModelPartBuilder.create().uv(10, 17).cuboid(0.0F, 0.0F, 0.5118F, 0.0F, 1.9691F, 0.6941F, new Dilation(0.001F))
                .uv(16, 19).cuboid(0.0F, 0.9588F, -0.0882F, 0.0F, 1.0103F, 0.6F, new Dilation(0.001F))
                .uv(2, 19).cuboid(0.0F, 5.4897F, -0.3881F, 0.0F, 0.5103F, 1.0059F, new Dilation(0.001F))
                .uv(12, 19).cuboid(0.0F, 2.9794F, -1.5F, 0.0F, 1.0103F, 0.7059F, new Dilation(0.001F))
                .uv(8, 9).cuboid(0.0F, 1.9691F, -1.5F, 0.0F, 1.0103F, 2.7059F, new Dilation(0.001F))
                .uv(12, 3).cuboid(0.0F, 2.9794F, -0.3941F, 0.0F, 1.0103F, 1.8941F, new Dilation(0.001F))
                .uv(14, 19).cuboid(0.0F, 0.0F, -1.5F, 0.0F, 0.9588F, 0.7059F, new Dilation(0.001F))
                .uv(6, 17).cuboid(0.0F, 0.0F, -0.7941F, 0.0F, 1.9691F, 0.7059F, new Dilation(0.001F))
                .uv(0, 0).cuboid(0.0F, 3.9897F, -1.1941F, 0.0F, 0.8897F, 0.4F, new Dilation(0.001F))
                .uv(18, 16).cuboid(0.0F, 3.9897F, -0.7941F, 0.0F, 1.5F, 0.7059F, new Dilation(0.001F))
                .uv(0, 0).cuboid(0.0F, 3.9897F, -0.0882F, 0.0F, 0.4897F, 0.5F, new Dilation(0.001F))
                .uv(4, 19).cuboid(0.0F, 3.9897F, 0.4118F, 0.0F, 1.5F, 0.5941F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, -1.5708F, 0.0F));
        return TexturedModelData.of(modelData, 32, 32);
    }


    @Override
    public void setAngles(FireballSpellEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.animateMovement(FireballSpellEntityAnimation.FIREBALL_SPELL_ENTITY, ageInTicks, 1, 1, 1);


    }


    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        Main.render(matrices, vertexConsumer, light, overlay, color);
    }

    @Override
    public ModelPart getPart() {
        return Main;
    }
}