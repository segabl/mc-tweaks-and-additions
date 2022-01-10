package at.h0ppip.tweaksandadditions.mixin;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowOwnerGoal;
import net.minecraft.entity.passive.TameableEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(FollowOwnerGoal.class)
public class FollowOwnerGoalMixin {
	@Mixin(FollowOwnerGoal.class)
	public interface FollowOwnerGoalAccessor {
		@Accessor
		TameableEntity getTameable();
		@Accessor("owner")
		void setOwner(LivingEntity owner);
		@Accessor
		float getMaxDistance();
		@Accessor
		float getMinDistance();
	}

	@Inject(method = "canStart()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/entity/passive/TameableEntity;squaredDistanceTo(Lnet/minecraft/entity/Entity;)D"), cancellable = true)
	public void canStart(CallbackInfoReturnable<Boolean> cir) {
		FollowOwnerGoalAccessor goalAccessor = (FollowOwnerGoalAccessor)this;
		LivingEntity livingEntity = goalAccessor.getTameable().getOwner();

		double distance = goalAccessor.getTameable().squaredDistanceTo(livingEntity);
		if (distance <= (double)(goalAccessor.getMaxDistance() * goalAccessor.getMaxDistance())) {
			cir.setReturnValue(false);
			return;
		}

		if (distance < (double)(goalAccessor.getMinDistance() * goalAccessor.getMinDistance()) && goalAccessor.getTameable().canSee(livingEntity)) {
			cir.setReturnValue(false);
			return;
		}

		goalAccessor.setOwner(livingEntity);
		cir.setReturnValue(true);
	}
}
