package net.ocechat.tutorialmod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;

public class MathHelper {

    public static Vec3d vectorGravitation(double gravitation) {
        return new Vec3d(0, gravitation, 0);
    }


    /// Give a New Vector / Position in front of the player based on a distance from him.
    public static Vec3d getPointInFront(PlayerEntity player, double distance) {

        Vec3d playerPos = player.getPos();
        Vec3d lookDir = player.getRotationVec(1.0F);

        return playerPos.add(lookDir.multiply(distance));
    }

    ///  Give a New Vector | Useful to simulate Trajectories.
    public static Vec3d addGravitation(Vec3d vectorOrigin, Vec3d vectorGravitation) {
        return vectorOrigin.add(vectorGravitation);
    }



}
