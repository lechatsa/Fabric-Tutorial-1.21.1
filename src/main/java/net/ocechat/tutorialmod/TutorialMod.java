package net.ocechat.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.ocechat.tutorialmod.block.ModBlocks;
import net.ocechat.tutorialmod.component.ModDataComponentTypes;
import net.ocechat.tutorialmod.enchantment.ModEnchantmentEffects;
import net.ocechat.tutorialmod.item.ModItemGroups;
import net.ocechat.tutorialmod.item.ModItems;
import net.ocechat.tutorialmod.magic.spell.ActivesSpells;
import net.ocechat.tutorialmod.magic.spell.SpellInstance;
import net.ocechat.tutorialmod.util.ModKeyBinding;
import net.ocechat.tutorialmod.magic.casting.KeyInputHandler;
import net.ocechat.tutorialmod.magic.casting.SpellCastPayload;
import net.ocechat.tutorialmod.network.SpellCastNetworking;
import net.ocechat.tutorialmod.sound.ModSound;
import net.ocechat.tutorialmod.util.HammerUsageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.ocechat.tutorialmod.magic.spell.ModSpellRegistry;



public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {

        ModItems.registerModItems();
        ModBlocks.registerModBlocks();
        ModItemGroups.registerItemGroups();
        ModDataComponentTypes.registerDataComponentTypes();
        ModSound.registerSounds();
        ModEnchantmentEffects.registerEnchantmentEffect();

        ModKeyBinding.registerModKeyBinding();
        KeyInputHandler.registerKeyInputHandler();

        FuelRegistry.INSTANCE.add(ModItems.STARLITGHT_ASHES, 3000);

        PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());


        ModSpellRegistry.registerAll();

        PayloadTypeRegistry.playC2S().register(
                SpellCastPayload.ID,
                SpellCastPayload.CODEC
        );

        SpellCastNetworking.registerC2SPackets();

        ActivesSpells.register();

	}
}