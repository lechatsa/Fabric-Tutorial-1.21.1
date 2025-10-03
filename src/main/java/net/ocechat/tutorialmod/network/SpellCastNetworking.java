package net.ocechat.tutorialmod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.magic.casting.SpellCastPayload;
import net.ocechat.tutorialmod.magic.spell.ModSpellRegistry;

public class SpellCastNetworking {
    // Envoi côté client
    public static void sendSpell(Identifier spellId) {
        ClientPlayNetworking.send(new SpellCastPayload(spellId));
    }

    // Réception côté serveur
    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(SpellCastPayload.ID, (payload, context) -> {
            var player = context.player();
            var spellId = payload.spellId();

            player.getServer().execute(() -> {
                var spell = ModSpellRegistry.get(spellId.toString());
                if (spell != null) {
                    spell.tryCast(player.getServerWorld(), player);
                }
            });
        });
    }
}

