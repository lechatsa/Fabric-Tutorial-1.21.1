package net.ocechat.tutorialmod;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec2f;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class OcechatMath {

    public static Vec3d vectorGravitation(double gravitation) {
        return new Vec3d(0, gravitation, 0);
    }


    /////////////////// Give a New Vector / Position in front of the player based on a distance from him ///////////////////
    public static Vec3d getPointInFront(PlayerEntity player, double distance) {

        Vec3d playerPos = player.getPos();
        Vec3d lookDir = player.getRotationVec(1.0F);

        return playerPos.add(lookDir.multiply(distance));
    }

    ////////////////////////////////// Give a New Vector | Useful to simulate Trajectories //////////////////////////////////
    public static Vec3d addGravitation(Vec3d vectorOrigin, Vec3d vectorGravitation) {
        return vectorOrigin.add(vectorGravitation);
    }

    ///////// Rotate a Point giving the Angles returning the New Position | Only in two dimension -> y becomes null /////////
    public static Vec2f rotatePoint2D(double x, double z, double centerX, double centerZ, double angleDegrees) {

        /// In Minecraft 3D is X, Y = Height, Z. This hurts with the convention to work with X and Y in 2D. It's why :

        double y = z;
        double centerY = centerZ;

        //////////////////////////////////////////////////////////////////////////////////////////////////////////////

        double theta = Math.toRadians(angleDegrees);
        double deltaX = x - centerX;
        double deltaY = y - centerY;
        double xPrime = centerX + deltaX * Math.cos(theta) - deltaY * Math.sin(theta);
        double yPrime = centerY + deltaX * Math.sin(theta) + deltaY * Math.cos(theta);

        return new Vec2f((float) xPrime, (float) yPrime);

    }

    public static double angleBetween(Vec2f a, Vec2f b) {
        // produit scalaire et produit vectoriel 2D
        double dot = a.x * b.x + a.y * b.y;
        double det = a.x * b.y - a.y * b.x;

        return Math.atan2(det, dot); // angle signé en radians (-π à +π)
    }

    public static Vec2f toVec2d(Vec3d vec3) {
        return new Vec2f((float) vec3.x, (float) vec3.z); // on "écrase" la coordonnée Z
    }

    public static double velocityToTime(int deltaTime) {

        double k = 0.07675; // coefficient de croissance

        return 2 * (1 - Math.exp(-k * deltaTime));
    }

    public static BlockPos Vec3dToBlockPos(Vec3d vector) {
        return new BlockPos((int) Math.ceil(vector.x), (int) Math.ceil(vector.y), (int) Math.ceil(vector.z));
    }
}
