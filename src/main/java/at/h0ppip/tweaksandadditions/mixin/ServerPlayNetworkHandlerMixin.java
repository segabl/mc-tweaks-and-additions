package at.h0ppip.tweaksandadditions.mixin;

import net.minecraft.server.network.ServerPlayNetworkHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(ServerPlayNetworkHandler.class)
public class ServerPlayNetworkHandlerMixin {
	// Disable vehicle movement speed check
	@Redirect(method = "onVehicleMove(Lnet/minecraft/network/packet/c2s/play/VehicleMoveC2SPacket;)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayNetworkHandler;isHost()Z"))
	private boolean isHost(ServerPlayNetworkHandler handler) {
		return true;
	}
	// Disable player movement check if disableElytraMovementCheck gamerule is enabled
	@Redirect(method = "onPlayerMove(Lnet/minecraft/network/packet/c2s/play/PlayerMoveC2SPacket;)V",
			at = @At(value = "INVOKE", target = "Lnet/minecraft/server/network/ServerPlayerEntity;isFallFlying()Z"))
	private boolean isFallFlying(ServerPlayerEntity playerEntity) {
		return true;
	}
}
