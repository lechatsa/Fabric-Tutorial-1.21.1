package net.ocechat.tutorialmod.magic.spell.substantial;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.OcechatMath;
import net.ocechat.tutorialmod.entity.ModEntities;
import net.ocechat.tutorialmod.entity.custom.ap_projectile_spell_entity.APProjectileSpellEntity;
import net.ocechat.tutorialmod.magic.spell.ModSpell;
import net.ocechat.tutorialmod.magic.spell.utility.ActivesSpells;
import net.ocechat.tutorialmod.magic.spell.utility.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import org.jetbrains.annotations.Nullable;

public class APProjectileSpell extends ModSpell {

    public static final String ID = "ap_projectile_spell";

    public APProjectileSpell() {
        super(ID, 60, 20, ModKeyBinding.AP_PROJECTILE_SPELL, true, 1200, true);
    }

    @Override
    public void cast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        Vec3d playerPosition = player.getEyePos();
        Vec3d playerRotation = player.getRotationVec(1f);

        APProjectileSpellEntity apProjectile = new APProjectileSpellEntity(ModEntities.AP_PROJECTILE_SPELL_ENTITY, world);

        Vec3d spawnPos = playerPosition.add(playerRotation);


        ActivesSpells.addSpell(new SpellInstance(player, this, apProjectile));

        apProjectile.setPos(spawnPos.x, spawnPos.y, spawnPos.z);

        apProjectile.setVelocity(playerRotation.multiply(OcechatMath.velocityToTime(deltaTime) + 0.5));

        world.spawnEntity(apProjectile);
    }

    @Override
    public void tryCast(World world, PlayerEntity player, @Nullable Integer deltaTime) {
        super.tryCast(world, player, deltaTime);

    }

    @Override
    public void tick(SpellInstance instance) {
        super.tick(instance);
    }

}
