package net.ocechat.tutorialmod.magic.spell.substantial;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.text.Text;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.OcechatMath;
import net.ocechat.tutorialmod.entity.custom.fireball_spell_entity.FireballSpellEntity;
import net.ocechat.tutorialmod.entity.ModEntities;
import net.ocechat.tutorialmod.magic.spell.utility.ActivesSpells;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import org.jetbrains.annotations.Nullable;

public class FireballSpell extends ModSpell {

    public float timeToCharged;
    public static final String ID = "fireball_spell";

    public FireballSpell() {
        super(ID, 0, 60, ModKeyBinding.FIREBALL_SPELL, true, 2000, true);

        this.timeToCharged = 60;

    }



    @Override
    public void cast(World world, PlayerEntity player, Integer deltaTime) {
        Vec3d playerPosition = player.getEyePos();
        Vec3d playerRotation = player.getRotationVec(1f);

        // Distance devant les yeux (0.6 = juste devant la tête)
        Vec3d spawnPos = playerPosition.add(playerRotation.multiply(1));

        // Création de la boule de feu
        FireballSpellEntity fireball = new FireballSpellEntity(ModEntities.FIREBALL_SPELL_ENTITY, world);
        ActivesSpells.addSpell(new SpellInstance(player, this, fireball));

        // Placement
        fireball.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

        // Direction + vitesse
        fireball.setVelocity(playerRotation.multiply(OcechatMath.velocityToTime(deltaTime)));

        // Empêche la boule de feu de brûler
        fireball.setOnFire(false);

        // Spawn dans le monde
        world.spawnEntity(fireball);
    }



    @Override
    public void tick(SpellInstance instance) {
        super.tick(instance);
        if (!(instance.getAttachedElement() instanceof FireballEntity fireball)) return;

        if (!fireball.isAlive() || getLifetime() < getCurrentLifetime()) {
            fireball.discard();
        }


    }

    @Override
    public void tryCast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
            super.tryCast(world, player, deltaTime);
    }

}
