package net.ocechat.tutorialmod.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.entity.custom.FireballSpellEntity;


public class ModEntities {

    //////////////////////////////////////// Here are Register the Customs Entities ////////////////////////////////////////

    public static EntityType<FireballSpellEntity> FIREBALL_SPELL_ENTITY = registerEntity("fireball_spell_entity", FireballSpellEntity::new, SpawnGroup.MISC, 0.5f, 0.5f, 100);






    /////////////////////////////////// Helper Methods to Register an Entity/EntitySpell ///////////////////////////////////

    public static <T extends Entity> EntityType<T> registerEntity(
            String name,
            EntityType.EntityFactory<T> factory,
            SpawnGroup group,
            float width,
            float height,
            int maxTrackingRange
    )
    {
         EntityType<T> type = Registry.register(
                 Registries.ENTITY_TYPE,
                 Identifier.of(TutorialMod.MOD_ID, name),
                 EntityType.Builder
                         .create(factory, group)
                         .dimensions(width, height)
                         .maxTrackingRange(maxTrackingRange)
                         .build());
        return type;
    }

    //////////////////////////////////// Registering in TutorialMod onInitialize Method ////////////////////////////////////

    public static void registerModEntities() {
        TutorialMod.LOGGER.info("Registering Mod Entities for " + TutorialMod.MOD_ID);
    }

}
