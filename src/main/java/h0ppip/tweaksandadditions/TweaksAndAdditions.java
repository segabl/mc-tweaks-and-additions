package h0ppip.tweaksandadditions;

import h0ppip.tweaksandadditions.blocks.FruitBlock;
import h0ppip.tweaksandadditions.blocks.FruitLeavesBlock;
import h0ppip.tweaksandadditions.blocks.GeneratedBlock;
import h0ppip.tweaksandadditions.blocks.ItemSackBlock;
import h0ppip.tweaksandadditions.custom.CustomFlowerPotBlock;
import h0ppip.tweaksandadditions.custom.CustomLeavesBlock;
import h0ppip.tweaksandadditions.custom.CustomSaplingBlock;
import h0ppip.tweaksandadditions.world.features.tree.AppleSaplingGenerator;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.item.*;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.SignType;
import net.minecraft.util.registry.Registry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;

public class TweaksAndAdditions implements ModInitializer {
	public static final String MOD_ID = "tweaksandadditions";

	public static final Logger LOGGER = LogManager.getLogger();

	public static final ArrayList<Block> REGISTER_TRANSPARENT = new ArrayList<>();

	@Override
	public void onInitialize() {
		initGeneratedBlocks();
		initWoodTypes();
	}

	public void initGeneratedBlocks() {
		GeneratedBlock block;

		AbstractBlock.Settings itemSackSettings = AbstractBlock.Settings.of(Material.ORGANIC_PRODUCT).nonOpaque().hardness(0.25f).resistance(1);
		Item[] itemSackSources = {
				Items.APPLE, Items.GOLDEN_APPLE, Items.CARROT, Items.GOLDEN_CARROT,	Items.POTATO, Items.BEETROOT,	Items.SWEET_BERRIES,
				Items.GLOW_BERRIES,	Items.CHORUS_FRUIT, Items.WHEAT_SEEDS, Items.BEETROOT_SEEDS, Items.MELON_SEEDS, Items.PUMPKIN_SEEDS,
				Items.COCOA_BEANS, Items.BROWN_MUSHROOM, Items.RED_MUSHROOM, Items.CRIMSON_FUNGUS, Items.WARPED_FUNGUS
		};
		for (Item i: itemSackSources) {
			block = new ItemSackBlock(itemSackSettings, i);
			block.register();
		}
	}

	public void initWoodTypes() {
		Block appleFruit = new FruitBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).nonOpaque().breakInstantly().noCollision().sounds(BlockSoundGroup.CROP).dynamicBounds(), Items.APPLE);
		Block appleLeaves = new CustomLeavesBlock(AbstractBlock.Settings.copy(Blocks.AZALEA_LEAVES));
		Block floweringAppleLeaves = new FruitLeavesBlock(AbstractBlock.Settings.copy(Blocks.FLOWERING_AZALEA_LEAVES).ticksRandomly(), appleFruit);

		Block appleLog = new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_LOG));
		Block appleWood = new PillarBlock(AbstractBlock.Settings.copy(Blocks.OAK_WOOD));

		Block appleSapling = new CustomSaplingBlock(new AppleSaplingGenerator(appleLog, appleLeaves, floweringAppleLeaves), AbstractBlock.Settings.copy(Blocks.OAK_SAPLING));

		Block strippedAppleLog = new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_LOG));
		Block strippedAppleWood = new PillarBlock(AbstractBlock.Settings.copy(Blocks.STRIPPED_OAK_WOOD));

		Block applePlanks = new Block(AbstractBlock.Settings.copy(Blocks.OAK_PLANKS));
		Block appleStairs = new StairsBlock(applePlanks.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OAK_STAIRS));
		Block appleSlab = new SlabBlock(AbstractBlock.Settings.copy(Blocks.OAK_SLAB));

		Block appleButton = new WoodenButtonBlock(AbstractBlock.Settings.copy(Blocks.OAK_BUTTON));
		Block appleDoor = new DoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_DOOR));
		Block appleFence = new FenceBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE));
		Block appleFenceGate = new FenceGateBlock(AbstractBlock.Settings.copy(Blocks.OAK_FENCE_GATE));
		Block applePressurePlate = new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.copy(Blocks.OAK_PRESSURE_PLATE));
		Block appleTrapdoor = new TrapdoorBlock(AbstractBlock.Settings.copy(Blocks.OAK_TRAPDOOR));

		// Todo: Actual apple sign
		Block appleSign = new SignBlock(AbstractBlock.Settings.copy(Blocks.OAK_SIGN), SignType.OAK);
		Block appleWallSign = new WallSignBlock(AbstractBlock.Settings.copy(Blocks.OAK_WALL_SIGN), SignType.OAK);

		Block pottedAppleSapling = new CustomFlowerPotBlock(appleSapling, AbstractBlock.Settings.copy(Blocks.POTTED_OAK_SAPLING));

		registerBlockAndItem("apple_log", appleLog, copyItemSettings(Items.OAK_LOG));
		registerBlockAndItem("apple_wood", appleWood, copyItemSettings(Items.OAK_WOOD));
		registerBlockAndItem("apple_leaves", appleLeaves, copyItemSettings(Items.AZALEA_LEAVES));
		registerBlockAndItem("flowering_apple_leaves", floweringAppleLeaves, copyItemSettings(Items.AZALEA_LEAVES_FLOWERS));
		registerBlockAndItem("apple_sapling", appleSapling, copyItemSettings(Items.OAK_SAPLING));
		registerBlockAndItem("stripped_apple_log", strippedAppleLog, copyItemSettings(Items.OAK_LOG));
		registerBlockAndItem("stripped_apple_wood", strippedAppleWood, copyItemSettings(Items.OAK_WOOD));
		registerBlockAndItem("apple_planks", applePlanks, copyItemSettings(Items.OAK_PLANKS));
		registerBlockAndItem("apple_stairs", appleStairs, copyItemSettings(Items.OAK_STAIRS));
		registerBlockAndItem("apple_slab", appleSlab, copyItemSettings(Items.OAK_SLAB));
		registerBlockAndItem("apple_button", appleButton, copyItemSettings(Items.OAK_BUTTON));
		registerBlockAndItem("apple_door", appleDoor, copyItemSettings(Items.OAK_DOOR));
		registerBlockAndItem("apple_fence", appleFence, copyItemSettings(Items.OAK_FENCE));
		registerBlockAndItem("apple_fence_gate", appleFenceGate, copyItemSettings(Items.OAK_FENCE_GATE));
		registerBlockAndItem("apple_pressure_plate", applePressurePlate, copyItemSettings(Items.OAK_PRESSURE_PLATE));
		registerBlockAndItem("apple_trapdoor", appleTrapdoor, copyItemSettings(Items.OAK_TRAPDOOR));
		registerSignBlockAndItem("apple", appleSign, appleWallSign);
		registerBlock("potted_apple_sapling", pottedAppleSapling);
		registerBlock("apple_fruit", appleFruit);

		// TODO: Actual apple boat
		Registry.register(Registry.ITEM, new Identifier("tweaksandadditions", "apple_boat"), new BoatItem(BoatEntity.Type.OAK, copyItemSettings(Items.OAK_BOAT)));

		StrippableBlockRegistry.register(appleLog, strippedAppleLog);
		StrippableBlockRegistry.register(appleWood, strippedAppleWood);

		FuelRegistry.INSTANCE.add(appleFence.asItem(), 300);
		FuelRegistry.INSTANCE.add(appleFenceGate.asItem(), 300);
	}

	public static FabricItemSettings copyItemSettings(Item item) {
		return new FabricItemSettings()
				.group(item.getGroup())
				.rarity(item.getRarity(item.getDefaultStack()))
				.food(item.getFoodComponent());
	}

	public static void registerBlock(String name, Block block) {
		Registry.register(Registry.BLOCK, new Identifier("tweaksandadditions", name), block);
	}

	public static void registerBlockAndItem(String name, Block block, Item.Settings blockItemSettings) {
		Registry.register(Registry.BLOCK, new Identifier("tweaksandadditions", name), block);
		Registry.register(Registry.ITEM, new Identifier("tweaksandadditions", name), new BlockItem(block, blockItemSettings));
	}

	public static void registerSignBlockAndItem(String type, Block standingSign, Block wallSign) {
		Registry.register(Registry.BLOCK, new Identifier("tweaksandadditions", type + "_sign"), standingSign);
		Registry.register(Registry.BLOCK, new Identifier("tweaksandadditions", type + "_wall_sign"), wallSign);
		Registry.register(Registry.ITEM, new Identifier("tweaksandadditions", type + "_sign"), new SignItem(copyItemSettings(Items.OAK_SIGN), standingSign, wallSign));
	}
}
