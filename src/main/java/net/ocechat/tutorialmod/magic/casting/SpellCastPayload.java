package net.ocechat.tutorialmod.magic.casting;

import net.minecraft.network.PacketByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.packet.CustomPayload;
import net.minecraft.util.Identifier;
import net.ocechat.tutorialmod.TutorialMod;

public record SpellCastPayload(Identifier spellId, boolean asCharged) implements CustomPayload {

    public static final Id<SpellCastPayload> ID =
            new Id<>(Identifier.of(TutorialMod.MOD_ID, "cast_spell"));

    public static final PacketCodec<PacketByteBuf, SpellCastPayload> CODEC =
            CustomPayload.codecOf(SpellCastPayload::write, SpellCastPayload::new);

    public SpellCastPayload(PacketByteBuf buf) {
        this(Identifier.of(buf.readString()), buf.readBoolean());
    }

    public void write(PacketByteBuf buf) {
        buf.writeString(spellId.toString());
        buf.writeBoolean(asCharged);
    }

    @Override
    public Id<? extends CustomPayload> getId() {
        return ID;
    }
}

