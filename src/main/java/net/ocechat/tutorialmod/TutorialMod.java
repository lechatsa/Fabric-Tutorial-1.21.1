package net.ocechat.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.event.player.AttackEntityCallback;
import net.fabricmc.fabric.api.event.player.PlayerBlockBreakEvents;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.SheepEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.ActionResult;
import net.ocechat.tutorialmod.block.ModBlocks;
import net.ocechat.tutorialmod.component.ModDataComponentTypes;
import net.ocechat.tutorialmod.enchantment.ModEnchantmentEffects;
import net.ocechat.tutorialmod.item.ModItemGroups;
import net.ocechat.tutorialmod.item.ModItems;
import net.ocechat.tutorialmod.magic.casting.ModKeyBinding;
import net.ocechat.tutorialmod.magic.casting.ModKeyListener;
import net.ocechat.tutorialmod.sound.ModSound;
import net.ocechat.tutorialmod.util.HammerUsageEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        ModKeyListener.registerModKeyListener();

        ModKeyListener.keyListener();

        FuelRegistry.INSTANCE.add(ModItems.STARLITGHT_ASHES, 3000);

        PlayerBlockBreakEvents.BEFORE.register(new HammerUsageEvent());

        AttackEntityCallback.EVENT.register((player, world, hand, entity, hitResult) -> {

            if (player.getMainHandStack().getItem() == Items.IRON_BARS && entity instanceof SheepEntity sheepEntity && !world.isClient) {
                player.getMainHandStack().decrement(1);
                sheepEntity.addStatusEffect(new StatusEffectInstance(StatusEffects.POISON, 600, 6));
            return ActionResult.PASS;
            }
            return ActionResult.PASS;
        });


	}
}