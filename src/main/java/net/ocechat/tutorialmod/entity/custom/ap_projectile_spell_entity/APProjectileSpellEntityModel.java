package net.ocechat.tutorialmod.entity.custom.ap_projectile_spell_entity;

import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.Entity;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.substantial.APProjectileSpell;

// Made with Blockbench 5.0.2
// Exported for Minecraft version 1.17+ for Yarn
// Paste this class into your mod and generate all required imports
public class APProjectileSpellEntityModel extends EntityModel<Entity> {
	private final ModelPart Main;
    public static final EntityModelLayer AP_PROJECTILE_SPELL = new EntityModelLayer(Identifier.of(TutorialMod.MOD_ID, APProjectileSpell.ID), "main");


    public APProjectileSpellEntityModel(ModelPart root) {
		this.Main = root.getChild("Main");
	}
	public static TexturedModelData getTexturedModelData() {

        ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		ModelPartData Main = modelPartData.addChild("Main", ModelPartBuilder.create().uv(0, 0).cuboid(-1.0F, -1.0F, -5.0F, 2.0F, 2.0F, 11.0F, new Dilation(0.0F)), ModelTransform.pivot(0.0F, 0.0F, 0.0F));

		ModelPartData cube_r1 = Main.addChild("cube_r1", ModelPartBuilder.create().uv(1, 26).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(0.0F, -1.0F, -4.0F, 0.0873F, 0.0F, -3.1416F));

		ModelPartData cube_r2 = Main.addChild("cube_r2", ModelPartBuilder.create().uv(27, 13).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(-1.0F, 0.0F, -4.0F, 0.0873F, 0.0F, -1.5708F));

		ModelPartData cube_r3 = Main.addChild("cube_r3", ModelPartBuilder.create().uv(27, 1).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 10.0F, new Dilation(0.0F)), ModelTransform.of(1.0F, 0.0F, -4.0F, 0.0873F, 0.0F, 1.5708F));

		ModelPartData cube_r4 = Main.addChild("cube_r4", ModelPartBuilder.create().uv(1, 14).cuboid(-1.0F, -1.0F, 0.0F, 2.0F, 1.0F, 10.0F, new Dilation(0.001F)), ModelTransform.of(0.0F, 1.0F, -4.0F, 0.0873F, 0.0F, 0.0F));
		return TexturedModelData.of(modelData, 64, 64);
	}

	@Override
	public void setAngles(Entity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
	}

	@Override
	public void render(MatrixStack matrices, VertexConsumer vertexConsumer, int light, int overlay, int color) {
		Main.render(matrices, vertexConsumer, light, overlay, color);
	}
}