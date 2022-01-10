package at.h0ppip.tweaksandadditions.client;

import at.h0ppip.tweaksandadditions.TweaksAndAdditions;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.block.Block;
import net.minecraft.client.render.RenderLayer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class TweaksAndAdditionsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		for (Block b: TweaksAndAdditions.REGISTER_TRANSPARENT) {
			BlockRenderLayerMap.INSTANCE.putBlock(b, RenderLayer.getCutout());
		}
	}
}
