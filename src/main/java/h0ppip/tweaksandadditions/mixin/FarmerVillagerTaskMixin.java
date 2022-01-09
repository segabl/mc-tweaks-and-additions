package h0ppip.tweaksandadditions.mixin;

import net.minecraft.entity.ai.brain.task.FarmerVillagerTask;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmerVillagerTask.class)
public class FarmerVillagerTaskMixin {
	private Item lastBrokenCropItem;

	@Inject(method = "keepRunning(Lnet/minecraft/server/world/ServerWorld;Lnet/minecraft/entity/passive/VillagerEntity;J)V", at = @At(value = "INVOKE", target = "Lnet/minecraft/server/world/ServerWorld;breakBlock(Lnet/minecraft/util/math/BlockPos;ZLnet/minecraft/entity/Entity;)Z"))
	public void keepRunningSeedCheck(ServerWorld serverWorld, VillagerEntity villagerEntity, long l, CallbackInfo ci) {
		FarmerVillagerTask task = (FarmerVillagerTask)(Object)this;

		Item brokenCropItem = serverWorld.getBlockState(task.currentTarget).getBlock().asItem();

		// If the current broken crop is the same as the previous one, don't need to check again
		if (brokenCropItem == lastBrokenCropItem) {
			return;
		}

		// Check inventory for the current broken crop index
		SimpleInventory simpleInventory = villagerEntity.getInventory();
		int firstCropItemIndex = -1;
		int brokenCropItemIndex = -1;
		for (int i = 0; i < simpleInventory.size(); ++i) {
			ItemStack itemStack = simpleInventory.getStack(i);
			if (!itemStack.isEmpty()) {
				if (firstCropItemIndex < 0) {
					firstCropItemIndex = i;
				}
				if (itemStack.isOf(brokenCropItem)) {
					brokenCropItemIndex = i;
					break;
				}
			}
		}

		// If we don't have that crop, return and simple let the villager plant anything
		if (brokenCropItemIndex < 0) {
			return;
		}

		// We have the crop in our inventory, so save the current broken crop as last broken
		lastBrokenCropItem = brokenCropItem;

		// If the current broken crop is the first usable crop in the inventory, everything is fine
		if (brokenCropItemIndex == firstCropItemIndex) {
			return;
		}

		// Otherwise, swap the item stacks in the inventory to make the villager use the same crop for replanting
		ItemStack firstCropStack = simpleInventory.getStack(firstCropItemIndex).copy();
		ItemStack brokenCropStack = simpleInventory.getStack(brokenCropItemIndex).copy();
		simpleInventory.setStack(firstCropItemIndex, brokenCropStack);
		simpleInventory.setStack(brokenCropItemIndex, firstCropStack);
	}
}
