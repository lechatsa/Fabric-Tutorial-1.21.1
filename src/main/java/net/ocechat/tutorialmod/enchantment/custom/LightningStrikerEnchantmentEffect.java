package net.ocechat.tutorialmod.enchantment.custom;

import com.mojang.serialization.MapCodec;
import net.minecraft.enchantment.EnchantmentEffectContext;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.server.world.ServerWorld;

import net.minecraft.util.TypeFilter;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public record LightningStrikerEnchantmentEffect() implements EnchantmentEntityEffect {
    public static final MapCodec<LightningStrikerEnchantmentEffect> CODEC = MapCodec.unit(LightningStrikerEnchantmentEffect::new);


    @Override
    public void apply(ServerWorld world, int level, EnchantmentEffectContext context, Entity user, Vec3d pos) {

        int range = 2*(level);

        Predicate<HostileEntity> nearest = entity -> entity.squaredDistanceTo(pos) <= range * range;

        List<Entity> entityList = new ArrayList<>();

        world.collectEntitiesByType(
                TypeFilter.instanceOf(HostileEntity.class),
                nearest,
                entityList,
                level*2 + 1);


        for (Entity e : entityList) {
            BlockPos targetPos = e.getBlockPos();
            EntityType.LIGHTNING_BOLT.spawn(world, targetPos, SpawnReason.EVENT);
        }
    }

    @Override
    public MapCodec<? extends EnchantmentEntityEffect> getCodec() {
        return CODEC;
    }
}
