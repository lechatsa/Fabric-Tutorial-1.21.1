package net.ocechat.tutorialmod.entity.client.fireball_spell_entity;

import net.minecraft.client.render.entity.animation.Animation;
import net.minecraft.client.render.entity.animation.AnimationHelper;
import net.minecraft.client.render.entity.animation.Keyframe;
import net.minecraft.client.render.entity.animation.Transformation;

public class FireballSpellEntityAnimation {

    public static final Animation FIREBALL_SPELL_ENTITY = Animation.Builder.create(4.75f).looping()
            .addBoneAnimation("Main",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(0.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0.25f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1f, AnimationHelper.createTranslationalVector(-0.25f, 0f, 0.25f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1.5f, AnimationHelper.createTranslationalVector(-0.25f, 0.25f, 0.25f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(3f, AnimationHelper.createTranslationalVector(0f, 0f, -0.25f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(3.5f, AnimationHelper.createTranslationalVector(0.25f, 0f, -0.25f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(4f, AnimationHelper.createTranslationalVector(0.25f, -0.25f, -0.25f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(4.5f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("Main",
                    new Transformation(Transformation.Targets.SCALE,
                            new Keyframe(0f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1.25f, AnimationHelper.createScalingVector(1.1f, 1.05f, 1.05f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2.5f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(3.5f, AnimationHelper.createScalingVector(1.1f, 1.05f, 1.05f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(4.66667f, AnimationHelper.createScalingVector(1f, 1f, 1f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("Up",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.25f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1.375f, AnimationHelper.createRotationalVector(0f, 10f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2.70833f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(3.75f, AnimationHelper.createRotationalVector(0f, 10f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(4.66667f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("Down",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1.625f, AnimationHelper.createRotationalVector(0f, -10f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2.29167f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(3.16667f, AnimationHelper.createRotationalVector(0f, -10f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(4.04167f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("Right",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.45833f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1.5f, AnimationHelper.createRotationalVector(0f, 0f, -10f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2.58333f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(3.66667f, AnimationHelper.createRotationalVector(0f, 0f, -10f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(4.54167f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("Left",
                    new Transformation(Transformation.Targets.ROTATE,
                            new Keyframe(0.45833f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1.41667f, AnimationHelper.createRotationalVector(0f, 0f, 10f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2.375f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(3.70833f, AnimationHelper.createRotationalVector(0f, 0f, 10f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(4.5f, AnimationHelper.createRotationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC)))
            .addBoneAnimation("SecondDetails",
                    new Transformation(Transformation.Targets.TRANSLATE,
                            new Keyframe(0.25f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(1.25f, AnimationHelper.createTranslationalVector(0.75f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2.08333f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(2.91667f, AnimationHelper.createTranslationalVector(0.75f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(3.70833f, AnimationHelper.createTranslationalVector(0.75f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC),
                            new Keyframe(4.75f, AnimationHelper.createTranslationalVector(0f, 0f, 0f),
                                    Transformation.Interpolations.CUBIC))).build();




}
