package h0ppip.tweaksandadditions;

import h0ppip.tweaksandadditions.blocks.GeneratedBlock;
import h0ppip.tweaksandadditions.blocks.ItemSackBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Material;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TweaksAndAdditions implements ModInitializer {
	public static final String MOD_ID = "tweaksandadditions";

	public static final Logger LOGGER = LogManager.getLogger();

	public static final Item[] ITEM_SACK_SOURCES = {
			Items.APPLE, Items.GOLDEN_APPLE, Items.CARROT, Items.GOLDEN_CARROT,	Items.POTATO, Items.BEETROOT,
			Items.SWEET_BERRIES, Items.GLOW_BERRIES, Items.CHORUS_FRUIT, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS,
			Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.COCOA_BEANS, Items.BROWN_MUSHROOM, Items.RED_MUSHROOM,
			Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS
	};

	@Override
	public void onInitialize() {
		GeneratedBlock block;

		AbstractBlock.Settings itemSackSettings = AbstractBlock.Settings.of(Material.ORGANIC_PRODUCT).nonOpaque().hardness(0.25f).resistance(1);
		for (Item i: ITEM_SACK_SOURCES) {
			block = new ItemSackBlock(itemSackSettings, i);
			block.register();
		}
	}
}
