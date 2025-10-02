package net.ocechat.tutorialmod.magic.casting;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.minecraft.block.entity.VaultBlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.FireballSpell;
import net.ocechat.tutorialmod.magic.spell.ModSpell;

public class ModKeyListener {
    public static void keyListener() {
        ClientTickEvents.END_CLIENT_TICK.register(client -> {

            while (ModKeyBinding.SPAWN_FIRE_IN_STRAIGHT_LIGNE.wasPressed()) {
                if (client.player != null) {
                    ModSpell fireball = new FireballSpell();
                    fireball.tryCast(client.world, client.player);
                }
            }

        });




    }





    public static void registerModKeyListener() {
        TutorialMod.LOGGER.info("Registering Key Listener for " + TutorialMod.MOD_ID);
    }
}
