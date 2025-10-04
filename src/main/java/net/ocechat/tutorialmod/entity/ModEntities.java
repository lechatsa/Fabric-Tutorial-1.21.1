package net.ocechat.tutorialmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.entity.custom.FireballSpellEntity;

public class ModEntities {

    public static EntityType<FireballSpellEntity> FIREBALL_SPELL_ENTITY;

    public static void registerEntities() {
        FIREBALL_SPELL_ENTITY = Registry.register(
                Registries.ENTITY_TYPE,
                Identifier.of(TutorialMod.MOD_ID, "fireball_spell_entity"),
                EntityType.Builder
                        .create(FireballSpellEntity::new, SpawnGroup.MISC)
                        .dimensions(0.75f, 0.75f)
                        .maxTrackingRange(20)
                        .build()
        );
    }
}
