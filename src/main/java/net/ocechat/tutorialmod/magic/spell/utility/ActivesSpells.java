package net.ocechat.tutorialmod.magic.spell.utility;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.world.ServerWorld;

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
                        toRemove.add(instance);
                        if (instance.getAttachedElement() != null)
                            instance.getAttachedElement().discard();
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

