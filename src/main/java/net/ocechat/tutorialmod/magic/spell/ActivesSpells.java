package net.ocechat.tutorialmod.magic.spell;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ActivesSpells {

    private static final Map<UUID, List<ModSpell>> ActiveSpells = new HashMap<>();

    public static void registerActivesSpells() {
        ServerTickEvents.END_SERVER_TICK.register(server -> {
            for (ServerWorld world : server.getWorlds()) {
                for (PlayerEntity player : world.getPlayers()) {
                    List<ModSpell> ActiveSpellsAssociatedToPlayer = ActiveSpells.get(player.getUuid());
                    if (ActiveSpellsAssociatedToPlayer != null) {
                        ActiveSpellsAssociatedToPlayer.removeIf(modSpell -> {
                           modSpell.tick();
                           return modSpell.getIs;
                        });
                    }
                }
            }




        });
    }



}
