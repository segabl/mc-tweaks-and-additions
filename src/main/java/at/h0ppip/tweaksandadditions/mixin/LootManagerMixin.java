package at.h0ppip.tweaksandadditions.mixin;

import at.h0ppip.tweaksandadditions.TweaksAndAdditions;
import at.h0ppip.tweaksandadditions.blocks.GeneratedBlock;
import com.google.gson.JsonElement;
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
		for (GeneratedBlock b: GeneratedBlock.BLOCKS) {
			TweaksAndAdditions.LOGGER.log(Level.INFO, "Adding loot drops for " + b.getIdentifier().getPath());
			map.putAll(b.createLootDrops());
		}
	}
}
