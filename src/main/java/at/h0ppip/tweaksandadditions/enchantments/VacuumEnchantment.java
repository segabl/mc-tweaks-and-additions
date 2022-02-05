package at.h0ppip.tweaksandadditions.enchantments;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public class VacuumEnchantment extends Enchantment {
	public VacuumEnchantment(Rarity weight, EquipmentSlot ... slotTypes) {
		super(weight, EnchantmentTarget.DIGGER, slotTypes);
	}

	@Override
	public void onTargetDamaged(LivingEntity user, Entity target, int level) {
		super.onTargetDamaged(user, target, level);
	}

	@Override
	public int getMinPower(int level) {
		return 10;
	}

	@Override
	public int getMaxPower(int level) {
		return 50;
	}

	@Override
	public boolean isAcceptableItem(ItemStack stack) {
		return super.isAcceptableItem(stack);
	}
}
