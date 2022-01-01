package h0ppip.tweaksandadditions.client;

import h0ppip.tweaksandadditions.blocks.GeneratedBlock;
import net.fabricmc.api.ClientModInitializer;

@net.fabricmc.api.Environment(net.fabricmc.api.EnvType.CLIENT)
public class TweaksAndAdditionsClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		for (GeneratedBlock b: GeneratedBlock.BLOCKS) {
			b.applyClientSettings();
		}
	}
}
