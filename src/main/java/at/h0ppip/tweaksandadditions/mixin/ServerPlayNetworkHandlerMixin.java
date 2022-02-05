package at.h0ppip.tweaksandadditions.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
	@ModifyConstant(method = "onVehicleMove(Lnet/minecraft/network/packet/c2s/play/VehicleMoveC2SPacket;)V", constant = @Constant(doubleValue = 100.0))
	private double increaseVehicleSpeedLimit(double limit) {
		return 4 * limit;
	}

	@ModifyConstant(method = "onPlayerMove(Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;)V", constant = @Constant(doubleValue = 100.0))
	private double increasePlayerMoveSpeedLimit(double limit) {
		return 4 * limit;
	}

	@ModifyConstant(method = "onPlayerMove(Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;)V", constant = @Constant(doubleValue = 300.0))
	private double increasePlayerFallSpeedLimit(double limit) {
		return 4 * limit;
	}
}
