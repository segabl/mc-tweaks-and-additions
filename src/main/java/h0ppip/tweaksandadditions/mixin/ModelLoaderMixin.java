package h0ppip.tweaksandadditions.mixin;

import h0ppip.tweaksandadditions.TweaksAndAdditions;
import h0ppip.tweaksandadditions.blocks.DynGenBlock;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.client.render.model.json.JsonUnbakedModel;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(ModelLoader.class)
public class ModelLoaderMixin {

	@Inject(method = "loadModelFromJson", at = @At(value = "INVOKE", target = "Lnet/minecraft/resource/ResourceManager;getResource(Lnet/minecraft/util/Identifier;)Lnet/minecraft/resource/Resource;"), cancellable = true)
	public void loadModelFromJson(Identifier id, CallbackInfoReturnable<JsonUnbakedModel> cir) {
		if (!id.getNamespace().equals(TweaksAndAdditions.MOD_ID)) {
			return;
		};

		String[] path = id.getPath().split("/");

		DynGenBlock block = TweaksAndAdditions.dynGenBlocks.getOrDefault(new Identifier(id.getNamespace(), path[1]), null);
		if (block == null) {
			return;
		}

		TweaksAndAdditions.log(Level.INFO, "Generating " + path[0] + " model for " + path[1]);
		JsonUnbakedModel model = block.createModel(path[0]);
		if (model == null) {
			TweaksAndAdditions.log(Level.ERROR, "Could not generate model type " + path[0] + " for " + path[1]);
			return;
		}

		model.id = id.toString();
		cir.setReturnValue(model);
	}
}
