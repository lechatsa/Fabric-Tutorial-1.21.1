package net.ocechat.tutorialmod.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.entity.custom.FireballSpellEntity;
import net.ocechat.tutorialmod.entity.custom.MantisEntity;

public class ModEntities {

    public static EntityType<FireballSpellEntity> FIREBALL_SPELL_ENTITY = Registry.register(
    Registries.ENTITY_TYPE, Identifier.of(TutorialMod.MOD_ID, "fireball_spell_entity"),
            EntityType.Builder
                    .create(FireballSpellEntity::new, SpawnGroup.MISC)
                    .dimensions(0.75f, 0.75f)
                    .maxTrackingRange(20)
                    .build());

    public static EntityType<MantisEntity> MANTIS = Registry.register(
    Registries.ENTITY_TYPE, Identifier.of(TutorialMod.MOD_ID, "mantis"),
            EntityType.Builder
                    .create(MantisEntity::new, SpawnGroup.CREATURE)
                    .dimensions(1f, 2.5f)
                    .build()
    );





    public static void registerModEntities() {
        TutorialMod.LOGGER.info("Registering Mod Entities for " + TutorialMod.MOD_ID);
    }

}
