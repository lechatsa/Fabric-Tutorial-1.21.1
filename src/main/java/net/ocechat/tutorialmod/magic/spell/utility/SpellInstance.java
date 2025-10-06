package net.ocechat.tutorialmod.magic.spell.utility;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import net.ocechat.tutorialmod.magic.spell.ModSpell;

public class SpellInstance {
    private final PlayerEntity caster;
    private final ModSpell spell;
    private final Entity attachedElement;
    private int lifetime = 0;
    private boolean isExpire = false;

    public SpellInstance(PlayerEntity caster, ModSpell spell, Entity attachedElement) {
        this.caster = caster;
        this.spell = spell;
        this.attachedElement = attachedElement;
    }

    public void tick() {
        lifetime++;
        spell.tick(this); // on délègue le comportement à la classe du sort
    }


    public PlayerEntity getCaster() {
        return caster;
    }

    public ModSpell getSpell() {
        return spell;
    }

    public Entity getAttachedElement() {
        return attachedElement;
    }

    public int getLifetime() {
        return lifetime;
    }

    public boolean isExpire() {
        boolean expire;
        expire = lifetime < 0;

        return expire;
    }


    public void setExpired(Boolean expired) {
        this.isExpire = expired;
    }

    public void setLifetime(int lifetime) {
        this.lifetime = lifetime;
    }

    public void setExpire(boolean expire) {
        isExpire = expire;
    }
}
