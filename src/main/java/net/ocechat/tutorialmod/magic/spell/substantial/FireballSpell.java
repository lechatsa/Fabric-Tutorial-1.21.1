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

public class FireballSpell extends ModSpell {

    public float timeToCharged;


    public FireballSpell() {
        super("fireball", 0, 60, ModKeyBinding.FIREBALL_SPELL, true, 2000);

        this.timeToCharged = 60;

    }



    @Override
    public void cast(World world, PlayerEntity player, int deltaTime) {
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
        fireball.setVelocity(look.multiply(OcechatMath.velocityToTime(deltaTime)));

        // Empêche la boule de feu de brûler
        fireball.setOnFire(false);

        // Spawn dans le monde
        world.spawnEntity(fireball);
    }



    @Override
    public void tick(SpellInstance instance) {
        this.setCurrentCooldown(this.getCurrentCooldown() - 1);
        if (!(instance.getAttachedElement() instanceof FireballEntity fireball)) return;

        if (!fireball.isAlive()) {
            fireball.discard();
        }

        if (getLifetime() < )
    }

    @Override
    public void tryCast(World world, PlayerEntity player, int deltaTime) {

            if (canCast(player)) {
                this.setCurrentCooldown(this.getCooldown());

                cast(world, player, deltaTime);

                player.sendMessage(Text.literal("You cast the Spell : " + this.getId()), true);

            } else {

                player.sendMessage(Text.literal("The Spell is in cooldown !"), true);

            }

    }

    @Override
    public boolean isAffectedByGravity() {
        return true;
    }



}
