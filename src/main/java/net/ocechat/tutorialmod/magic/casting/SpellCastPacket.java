package net.ocechat.tutorialmod.magic.casting;



import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;
import net.ocechat.tutorialmod.magic.spell.ModSpell
import net.ocechat.tutorialmod.magic.spell.FireballSpell;


public class SpellCastPacket {

    public static final Identifier ID = new Identifier(TutorialMod.MOD_ID, "cast_spell");

    // Enregistrement côté serveur
    public static void registerReceiver() {

        ServerPlayNetworking.registerGlobalReceiver(ID, (server, player) -> {
            String spellId = server.

            server.execute(() -> {
                // Ici tu peux choisir quel sort lancer en fonction de l'ID
                ModSpell spell = new FireballSpell(); // exemple en dur

                spell.tryCast(player.getServerWorld(), player);
            });
        });
    }

    // Fonction utilitaire pour envoyer le packet côté client
    public static void send(String spellId) {
        PacketByteBuf buf = new PacketByteBuf(io.netty.buffer.Unpooled.buffer());
        buf.writeString(spellId);
        net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking.send(ID, buf);
    }
}

