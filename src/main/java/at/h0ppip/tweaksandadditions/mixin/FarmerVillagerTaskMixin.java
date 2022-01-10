package at.h0ppip.tweaksandadditions.mixin;

import net.minecraft.entity.ai.brain.task.FarmerVillagerTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmerVillagerTask.class)
public class FarmerVillagerTaskMixin {

	@Mixin(FarmerVillagerTask.class)
	public interface FarmerVillagerTaskAccessor {
		@Accessor
		BlockPos getCurrentTarget();
		@Accessor("nextResponseTime")
		void setNextResponseTime(long nextResponseTime);
		@Accessor
		int getTicksRan();
		@Accessor("ticksRan")
		void setTicksRan(int ticksRan);
	}

	private Item lastBrokenCropItem;

	@Inject(method = "keepRunning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/VillagerEntity;J)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;breakBlock(Lnet/minecraft/util/math/BlockPos;ZLnet/minecraft/entity/Entity;)Z"), cancellable = true)
	public void keepRunningSeedCheck(ServerWorld serverWorld, VillagerEntity villagerEntity, long l, CallbackInfo ci) {
		FarmerVillagerTaskAccessor taskAccessor = (FarmerVillagerTaskAccessor)this;

		Item brokenCropItem = serverWorld.getBlockState(taskAccessor.getCurrentTarget()).getBlock().asItem();

		// If the current broken crop is the same as the previous one, don't need to check again
		if (brokenCropItem == lastBrokenCropItem) {
			return;
		}

		lastBrokenCropItem = brokenCropItem;

		// Check inventory for the current broken crop index
		SimpleInventory simpleInventory = villagerEntity.getInventory();
		int brokenCropItemIndex = -1;
		int firstEmptyStackIndex = -1;
		for (int i = 0; i < simpleInventory.size(); ++i) {
			ItemStack itemStack = simpleInventory.getStack(i);

			if (itemStack.isEmpty()) {
				if (firstEmptyStackIndex < 0) {
					firstEmptyStackIndex = i;
				}
			} else if (itemStack.isOf(brokenCropItem)) {
				brokenCropItemIndex = i;
				break;
			}
		}

		// If we don't have that crop...
		if (brokenCropItemIndex < 0) {
			// ... and no free stack, let villager plant anything
			if (firstEmptyStackIndex < 0) {
				return;
			}

			// ... else, wait for a potential item pickup to plant the correct seed
			serverWorld.breakBlock(taskAccessor.getCurrentTarget(), true, villagerEntity);
			taskAccessor.setNextResponseTime(l + 30L);
			taskAccessor.setTicksRan(taskAccessor.getTicksRan() + 1);

			if (firstEmptyStackIndex > 0) {
				simpleInventory.setStack(firstEmptyStackIndex, simpleInventory.getStack(0).copy());
				simpleInventory.setStack(0, ItemStack.EMPTY);
			}

			ci.cancel();
			return;
		}

		// If the current broken crop is the first crop in the inventory, everything is fine
		if (brokenCropItemIndex == 0) {
			return;
		}

		// Otherwise, swap the item stacks in the inventory to make the villager use the same crop for replanting
		ItemStack firstCropStack = simpleInventory.getStack(0).copy();
		ItemStack brokenCropStack = simpleInventory.getStack(brokenCropItemIndex).copy();
		simpleInventory.setStack(0, brokenCropStack);
		simpleInventory.setStack(brokenCropItemIndex, firstCropStack);
	}
}
