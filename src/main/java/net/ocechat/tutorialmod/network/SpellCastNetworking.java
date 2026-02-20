package net.ocechat.tutorialmod.network;

import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.casting.SpellCastPayload;
import net.ocechat.tutorialmod.magic.spell.utility.ChargingSpell;
import net.ocechat.tutorialmod.magic.spell.utility.ModSpellRegistry;

import java.util.UUID;

import static net.ocechat.tutorialmod.TutorialMod.*;

public class SpellCastNetworking {
    //////////////////////////// Handle the sending and the receiving of Client To Server Packets ///////////////////////////


    /// Sending Client Side | Client -> Server
    public static void sendSpell(Identifier spellId, boolean asCharged) {
        ClientPlayNetworking.send(new SpellCastPayload(spellId, asCharged));
    }

    /// Receiving Server Side | Client -> Server
    public static void registerC2SPackets() {

        if (DEBUG_MODE) LOGGER.info("Registering C2S packets for " + TutorialMod.MOD_ID);


        ServerPlayNetworking.registerGlobalReceiver(SpellCastPayload.ID, (payload, context) -> {

            if (DEBUG_MODE) LOGGER.info("Received packet " + payload.spellId());

            //////////////////////////////////////////// Initialisation of the Variables ////////////////////////////////////////////
            var player = context.player();
            var spellId = payload.spellId();



            player.getServer().execute(() -> {
                ////////////////////////////////////////////////// The server Execute //////////////////////////////////////////////////

                //////////////////////////////////////////// Initialisation of the Variables ////////////////////////////////////////////

                var spell = ModSpellRegistry.get(spellId.toString());
                int time = player.getServer().getTicks();
                UUID playerId = player.getUuid();

                if (spell == null) {
                    LOGGER.warn("The Spell send isn't associated to a ModSpell in ModSpellRegistry ");
                    return;
                }

                ////////////////////////////////////////////////////// Operation ///////////////////////////////////////////////////////

                if (spell.isNeedToCharge()) {

                    if (payload.asCharged()) { /// If the Payload say that the spell is charged, cast the Spell // If the Spell needs to charged, it'll be put in the ChargingSpell list.

                        var chargingSpell = ChargingSpell.CHARGING_SPELLS.get(playerId);

                        if (chargingSpell == null) { // Verification
                            LOGGER.warn("Aucun sort chargé trouvé pour {}", player.getName().getString());
                            return;
                        }

                        int deltaTime = time - chargingSpell.timeAtFirstCall(); // First Call - Actual Time = Duration

                        spell.tryCast(player.getServerWorld(), player, deltaTime); // Cast the Spell with the World, The Player, Duration

                        ChargingSpell.removeSpellOfCharged(playerId); // Remove the spell associated to the player.

                    } else {

                        ChargingSpell.setSpellToCharged(player.getUuid(), spell, time);

                    }


                } else {

                    spell.tryCast(player.getServerWorld(), player, 0);

                }

                ///////////////////////////////////////////////// End of the Operation /////////////////////////////////////////////////

            });
        });
    }
}

