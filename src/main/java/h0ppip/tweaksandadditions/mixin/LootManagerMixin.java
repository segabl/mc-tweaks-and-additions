package h0ppip.tweaksandadditions.mixin;

import com.google.gson.JsonElement;
import h0ppip.tweaksandadditions.TweaksAndAdditions;
import h0ppip.tweaksandadditions.blocks.DynGenBlock;
import net.minecraft.loot.LootManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(LootManager.class)
public class LootManagerMixin {
	@Inject(at = @At("HEAD"), method = "apply")
	public void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
		for (DynGenBlock b: TweaksAndAdditions.dynGenBlocks.values()) {
			TweaksAndAdditions.log(Level.INFO, "Adding loot drops for " + b.getIdentifier().getPath());
			map.putAll(b.createLootDrops());
		}
	}
}
