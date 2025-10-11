package net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.SinglePartEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;

// Made with Blockbench 4.12.6
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class ShieldBarrierSpellEntityModel extends SinglePartEntityModel<ShieldBarrierSpellEntity> {

    public static final EntityModelLayer SHIEL_BARRIER_SPELL = new EntityModelLayer(Identifier.of(TutorialMod.MOD_ID, "shield_barrier_spell"), "main");

    private final ModelPart Main;
    private final ModelPart Front;
    private final ModelPart Right;
    private final ModelPart Left;
    public ShieldBarrierSpellEntityModel(ModelPart root) {
        this.Main = root.getChild("Main");
        this.Front = this.Main.getChild("Front");
        this.Right = this.Main.getChild("Right");
        this.Left = this.Main.getChild("Left");
    }
    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        ModelPartData Main = modelPartData.addChild("Main", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 24.0F, 0.0F));

        ModelPartData Front = Main.addChild("Front", ModelPartBuilder.create(), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

        ModelPartData Front_r1 = Front.addChild("Front_r1", ModelPartBuilder.create().uv(0, 0).cuboid(-16.0F, -40.0F, -8.0F, 32.0F, 40.0F, 0.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 0.0F, 0.0F, 0.0F, 1.5708F, 0.0F));

        ModelPartData Right = Main.addChild("Right", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, 0.0F, 16.0F));

        ModelPartData Right_r1 = Right.addChild("Right_r1", ModelPartBuilder.create().uv(46, 40).mirrored().cuboid(-23.0F, -40.0F, -0.001F, 23.0F, 40.0F, 0.0F, new Dilation(0.001F)).mirrored(false), ModelTransform.of(0.0007F, 0.0F, -0.0007F, 3.1416F, 0.7854F, 3.1416F));

        ModelPartData Left = Main.addChild("Left", ModelPartBuilder.create(), ModelTransform.pivot(-8.0F, 0.0F, -16.0F));

        ModelPartData Left_r1 = Left.addChild("Left_r1", ModelPartBuilder.create().uv(9, 80).cuboid(0.0F, -40.0F, -0.001F, 23.0F, 40.0F, 0.0F, new Dilation(0.001F)), ModelTransform.of(0.001F, 0.0F, 0.0F, 0.0F, 0.7854F, 0.0F));
        return TexturedModelData.of(modelData, 128, 128);
    }


    @Override
    public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
        Main.render(matrices, vertexConsumer, light, overlay, color);
    }


    @Override
    public ModelPart getPart() {
        return Main;
    }

    @Override
    public void setAngles(ShieldBarrierSpellEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
        this.getPart().traverse().forEach(ModelPart::resetTransform);
        this.animateMovement(ShieldBarrierSpellEntityAnimation.DISCARD, 1, 1, 1, 1);

    }
}
