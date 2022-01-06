package h0ppip.tweaksandadditions.custom;

import net.minecraft.block.Block;
import net.minecraft.block.FlowerPotBlock;

import static h0ppip.tweaksandadditions.TweaksAndAdditions.REGISTER_TRANSPARENT;

public class CustomFlowerPotBlock extends FlowerPotBlock {
	public CustomFlowerPotBlock(Block content, Settings settings) {
		super(content, settings);

		REGISTER_TRANSPARENT.add(this);
	}
}
