package at.h0ppip.tweaksandadditions.mixin;

import at.h0ppip.tweaksandadditions.TweaksAndAdditions;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;

@Mixin(Block.class)
public class BlockMixin {
	@Inject(method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V",
			at = @At("HEAD"), cancellable = true)
	private static void dropStacks(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack mainHandStack, CallbackInfo ci) {
		if (!(world instanceof ServerWorld) || !(entity instanceof ServerPlayerEntity)) {
			return;
		}

		if (EnchantmentHelper.getLevel(TweaksAndAdditions.ENCHANTMENT_VACUUM, mainHandStack) <= 0) {
			return;
		}

		PlayerInventory inventory = ((ServerPlayerEntity)entity).getInventory();
		List<ItemStack> droppedStacks = Block.getDroppedStacks(state, (ServerWorld)world, pos, blockEntity, entity, mainHandStack);

		droppedStacks.forEach(stack -> {
			inventory.insertStack(stack);
			if (stack.getCount() > 0) {
				Block.dropStack(world, pos, stack);
			}
		});

		state.onStacksDropped((ServerWorld)world, pos, mainHandStack);

		ci.cancel();
	}
}
