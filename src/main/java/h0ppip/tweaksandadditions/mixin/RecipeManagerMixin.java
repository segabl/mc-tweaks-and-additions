package h0ppip.tweaksandadditions.mixin;

import com.google.gson.JsonElement;
import h0ppip.tweaksandadditions.TweaksAndAdditions;
import h0ppip.tweaksandadditions.blocks.GeneratedBlock;
import net.minecraft.recipe.RecipeManager;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(RecipeManager.class)
public class RecipeManagerMixin {

	@Inject(method = "apply", at = @At("HEAD"))
	public void apply(Map<Identifier, JsonElement> map, ResourceManager resourceManager, Profiler profiler, CallbackInfo info) {
		for (GeneratedBlock b: TweaksAndAdditions.generatedBlocks.values()) {
			TweaksAndAdditions.log(Level.INFO, "Adding recipes for " + b.getIdentifier().getPath());
			map.putAll(b.createRecipes());
		}
	}

}
