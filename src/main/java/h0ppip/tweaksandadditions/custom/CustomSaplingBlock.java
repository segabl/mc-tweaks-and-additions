package h0ppip.tweaksandadditions.custom;

import h0ppip.tweaksandadditions.TweaksAndAdditions;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;

public class CustomSaplingBlock extends SaplingBlock {
	public CustomSaplingBlock(SaplingGenerator generator, Settings settings) {
		super(generator, settings);

		TweaksAndAdditions.REGISTER_TRANSPARENT.add(this);
	}
}
