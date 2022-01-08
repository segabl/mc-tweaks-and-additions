package h0ppip.tweaksandadditions.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FollowOwnerGoal.class)
public class FollowOwnerGoalMixin {
	// Let following animals follow closer if they can't see their target

	@Inject(method = "canStart()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/TameableEntity;squaredDistanceTo(Lnet/minecraft/entity/Entity;)D"), cancellable = true)
	public void canStart(CallbackInfoReturnable<Boolean> cir) {
		FollowOwnerGoal goal = ((FollowOwnerGoal)(Object)this);
		LivingEntity livingEntity = goal.tameable.getOwner();

		double distance = goal.tameable.squaredDistanceTo(livingEntity);
		if (distance <= (double)(goal.maxDistance * goal.maxDistance)) {
			cir.setReturnValue(false);
			return;
		}

		if (distance < (double)(goal.minDistance * goal.minDistance) && goal.tameable.canSee(livingEntity)) {
			cir.setReturnValue(false);
			return;
		}

		goal.owner = livingEntity;
		cir.setReturnValue(true);
	}
}
