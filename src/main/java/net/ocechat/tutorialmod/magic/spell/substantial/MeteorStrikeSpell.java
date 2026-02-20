package net.ocechat.tutorialmod.magic.spell.substantial;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.OcechatMath;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.utility.ActivesSpells;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import org.jetbrains.annotations.Nullable;

public class MeteorStrikeSpell extends ModSpell {

    public static final String ID = "meteor_strike_spell";
    public final float timeToCharge = 60;

    public MeteorStrikeSpell() {
        super(
                ID,
                60,
                40,
                ModKeyBinding.METEOR_STRIKE_SPELL,
                false,
                1200,
                false
        );
    }


    @Override
    public void tryCast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        super.tryCast(world, player, deltaTime);


    }

    @Override
    public void cast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        if (world.isClient) return;
        /* if (deltaTime == null) return;
        //if (deltaTime < timeToCharge) {
        //    TutorialMod.LOGGER.info("The player hasn't press long enough ! only {}s", deltaTime);
        //    return;
        }*/


        HitResult hitResult = player.raycast(100, 1.0f, false);

        Vec3d positionOfTarget = hitResult.getPos();

        // Will find a random starting point for the laser, high up in the sky.

        int highOfStartingPoint = 500;
        int fieldOfView = 10; // WARNING : The reel field of view is double, so you need to dived by 2 the angle you really want

        double rayon = Math.tan(fieldOfView)/highOfStartingPoint;

        Random random = world.getRandom();
        double cosinus = random.nextBetween(-1, 1); // -> Coefficient of x
        double sinus = random.nextBetween(-1, 1); // -> Coefficient of y

        double increaseX = rayon * cosinus;
        double increaseY = rayon * sinus;

        double positionOfOrigineX = positionOfTarget.x + increaseX;
        double positionOfOrigineY = positionOfTarget.y + increaseY;
        double positionOfOrigineZ = positionOfTarget.z + highOfStartingPoint;

        Vec3d positionOfOrigine = new Vec3d(positionOfOrigineX, positionOfOrigineY, positionOfOrigineZ);

        OcechatMath.drawLine2(world, positionOfOrigine, positionOfTarget, 80, ParticleTypes.HAPPY_VILLAGER);

        ActivesSpells.addSpell(new SpellInstance(player, this, null));
    }

    @Override
    public void tick(SpellInstance instance) {
        super.tick(instance);
    }
}
