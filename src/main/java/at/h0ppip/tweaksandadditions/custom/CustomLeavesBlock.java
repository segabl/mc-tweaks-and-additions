package at.h0ppip.tweaksandadditions.custom;

import at.h0ppip.tweaksandadditions.TweaksAndAdditions;
import net.minecraft.block.LeavesBlock;

public class CustomLeavesBlock extends LeavesBlock {
	public CustomLeavesBlock(Settings settings) {
		super(settings);

		TweaksAndAdditions.REGISTER_TRANSPARENT.add(this);
	}
}
