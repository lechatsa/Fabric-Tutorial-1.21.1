package net.ocechat.tutorialmod.entity.custom;

import net.minecraft.entity.AnimationState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.world.World;
import net.ocechat.tutorialmod.entity.ModEntities;
import net.ocechat.tutorialmod.item.ModItems;
import org.jetbrains.annotations.Nullable;

public class MantisEntity extends AnimalEntity {
    public MantisEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public final AnimationState idleanimationState = new AnimationState();
    private int idleAnimationTimeout = 0;


    private void setupAnimationStates() {
        if (this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 95;
            this.idleanimationState.start(this.age);

        }
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWorld().isClient) {
            this.setupAnimationStates();
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return stack.isOf(ModItems.CAULIFLOWER);
    }

    @Override
    public @Nullable PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntities.MANTIS.create(world);
    }
}
