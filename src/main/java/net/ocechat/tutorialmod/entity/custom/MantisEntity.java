package net.ocechat.tutorialmod.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class MantisEntity extends AnimalEntity {
    public MantisEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public final AnimationState idleanimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
