package net.ocechat.tutorialmod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.magic.casting.SpellCastPayload;
import net.ocechat.tutorialmod.magic.spell.utility.ModSpellRegistry;

public class SpellCastNetworking {
    //////////////////////////// Handle the sending and the receiving of Client To Server Packets ///////////////////////////


    /// Sending Client Side | Client -> Server
    public static void sendSpell(Identifier spellId, boolean asCharged) {
        ClientPlayNetworking.send(new SpellCastPayload(spellId, asCharged));
    }

    /// Receiving Server Side | Client -> Server
    public static void registerC2SPackets() {
        ServerPlayNetworking.registerGlobalReceiver(SpellCastPayload.ID, (payload, context) -> {

            var player = context.player();
            var spellId = payload.spellId();
            var deltaTime = payload.

            player.getServer().execute(() -> {
                var spell = ModSpellRegistry.get(spellId.toString());
                if (spell != null) {
                    if (payload.asCharged()) {

                    }


                    spell.tryCast(player.getServerWorld(), player);
                }



            });
        });
    }
}

