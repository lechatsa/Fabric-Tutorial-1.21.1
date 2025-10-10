package net.ocechat.tutorialmod.entity.custom.shield_barrier_spell_entity;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class ShieldBarrierSpellEntityAnimation {

    public static final Animation DISCARD = Animation.Builder.create(2f)
            .addBoneAnimation("Main",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("Main",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0.75f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(1.375f, AnimationHelper.createScalingVector(1f, 1f, 0.1f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(2f, AnimationHelper.createScalingVector(1f, 0f, 0.1f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("Right",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, -45f, 0f),
                                    Transformation.Interpolations.LINEAR)))
            .addBoneAnimation("Left",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.LINEAR),
                            new Keyframe(0.75f, AnimationHelper.createRotationalVector(0f, 45f, 0f),
                                    Transformation.Interpolations.LINEAR))).build();
}
