package h0ppip.tweaksandadditions;

import h0ppip.tweaksandadditions.blocks.DynGenBlock;
import h0ppip.tweaksandadditions.blocks.ItemSackBlock;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashMap;

public class TweaksAndAdditions implements ModInitializer {
	public static final String MOD_ID = "tweaksandadditions";

	public static Logger LOGGER = LogManager.getLogger();

	public static final Item[] ITEM_SACK_SOURCES = {
			Items.APPLE, Items.GOLDEN_APPLE, Items.CARROT, Items.GOLDEN_CARROT,	Items.POTATO, Items.BEETROOT,
			Items.SWEET_BERRIES, Items.GLOW_BERRIES, Items.CHORUS_FRUIT, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS,
			Items.MELON_SEEDS, Items.PUMPKIN_SEEDS, Items.COCOA_BEANS
	};

	public static HashMap<Identifier, DynGenBlock> dynGenBlocks = new HashMap<>();

	public static void log(Level level, String message) {
		LOGGER.log(level, message);
	}

	@Override
	public void onInitialize() {
		for (Item i: ITEM_SACK_SOURCES) {
			DynGenBlock b = new ItemSackBlock(i);
			dynGenBlocks.put(b.getIdentifier(), b);
			b.registerBlock();
		}
	}
}
