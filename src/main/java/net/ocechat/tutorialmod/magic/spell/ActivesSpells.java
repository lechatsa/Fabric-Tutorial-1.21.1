package net.ocechat.tutorialmod.magic.spell;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.lang.reflect.Type;
import java.util.*;

public class ActivesSpells {

    private static final List<SpellInstance> ACTIVE_SPELLS = new ArrayList<>();

    public static void register() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {

            List<SpellInstance> toRemove = new ArrayList<>();

            for (ServerWorld world : server.getWorlds()) {
                for (SpellInstance instance : ACTIVE_SPELLS) {
                    if (instance.getCaster().getWorld() != world) continue;

                    instance.tick();

                    if (instance.isExpire()) {
                        if (instance.getAttachedElement() != null)
                            instance.getAttachedElement().discard();
                        toRemove.add(instance);
                    }
                }
            }

            ACTIVE_SPELLS.removeAll(toRemove);
        });
    }

    public static void addSpell(SpellInstance instance) {
        ACTIVE_SPELLS.add(instance);
    }
}

