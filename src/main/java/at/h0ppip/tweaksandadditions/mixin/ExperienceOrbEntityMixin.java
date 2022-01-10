package at.h0ppip.tweaksandadditions.mixin;

import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ExperienceOrbEntity.class)
public class ExperienceOrbEntityMixin {
	// Mending does not repair
	@Inject(at = @At("HEAD"), method = "repairPlayerGears(Lnet/minecraft/entity/player/PlayerEntity;I)I", cancellable = true)
	private void repairPlayerGears(PlayerEntity player, int amount, CallbackInfoReturnable<Integer> cir) {
		cir.cancel();
	}
}
