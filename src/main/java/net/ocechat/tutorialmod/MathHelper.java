package net.ocechat.tutorialmod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class MathHelper {

    public static Vec3d getPointInFront(PlayerEntity player, double distance) {

        Vec3d playerPos = player.getPos();
        Vec3d lookDir = player.getRotationVec(1.0F);

        return playerPos.add(lookDir.multiply(distance));
    }





}
