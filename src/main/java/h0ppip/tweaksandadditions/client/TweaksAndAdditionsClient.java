package h0ppip.tweaksandadditions.client;

import h0ppip.tweaksandadditions.TweaksAndAdditions;
import h0ppip.tweaksandadditions.blocks.DynGenBlock;
import net.fabricmc.api.ClientModInitializer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class TweaksAndAdditionsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		for (DynGenBlock b: TweaksAndAdditions.dynGenBlocks.values()) {
			b.applyClientSettings();
		}
	}
}
