package net.ocechat.tutorialmod.entity.custom.ap_projectile_spell_entity;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class APProjectileSpellEntityAnimation {
    public static final Animation AP_PROJECTILE_SPELL_ENTITY_ANIMATION = Animation.Builder.create(1f).looping()
            .addBoneAnimation("Main",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1f, AnimationHelper.createRotationalVector(0f, 0f, 360f),
                                    Transformation.Interpolations.LINEAR))).build();
}
