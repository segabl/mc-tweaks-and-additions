package h0ppip.tweaksandadditions.mixin;

import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ItemStack.class)
public class ItemStackMixin {
	// Mending removes repair cost
	@Inject(at = @At("HEAD"), method = "getRepairCost()I", cancellable = true)
	public void getRepairCost(CallbackInfoReturnable<Integer> cir) {
		ItemStack i = (ItemStack)(Object)this;
		if (i.hasEnchantments() && EnchantmentHelper.getLevel(Enchantments.MENDING, i) > 0) {
			cir.setReturnValue(0);
		}
	}
}
