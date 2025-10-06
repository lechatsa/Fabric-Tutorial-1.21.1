package net.ocechat.tutorialmod.magic.spell.substantial;


import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.entity.custom.fireball_spell_entity.FireballSpellEntity;
import net.ocechat.tutorialmod.entity.ModEntities;
import net.ocechat.tutorialmod.magic.spell.utility.ActivesSpells;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;

public class FireballSpell extends ModSpell {
    public FireballSpell() {
        super(
                "fireball",
                0,
                0,
                ModKeyBinding.SPAWN_FIRE_IN_STRAIGHT_LIGNE,
                true,
                80
                );
    }

    @Override
    public void cast(World world, PlayerEntity player) {
        // Direction du regard
        Vec3d look = player.getRotationVec(1.0F);

        // Position des yeux du joueur
        Vec3d eyePos = player.getEyePos();

        // Distance devant les yeux (0.6 = juste devant la tête)
        Vec3d spawnPos = eyePos.add(look.multiply(1));

        // Création de la boule de feu
        FireballSpellEntity fireball = new FireballSpellEntity(ModEntities.FIREBALL_SPELL_ENTITY, world);
        ActivesSpells.addSpell(new SpellInstance(player, this, fireball));

        // Placement
        fireball.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

        // Direction + vitesse
        fireball.setVelocity(look.multiply(1.5f));

        // Empêche la boule de feu de brûler
        fireball.setOnFire(false);

        // Spawn dans le monde
        world.spawnEntity(fireball);
    }


    @Override
    public void tick(SpellInstance instance) {
        if (!(instance.getAttachedElement() instanceof FireballEntity fireball)) return;

        if (!fireball.isAlive()) {
            fireball.discard();
        }
    }


    @Override
    public boolean isAffectedByGravity() {
        return true;
    }



}
