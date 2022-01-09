package h0ppip.tweaksandadditions.world.features.tree;

import h0ppip.tweaksandadditions.TweaksAndAdditions;
import net.minecraft.block.Block;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.Identifier;
import net.minecraft.util.collection.DataPool;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.stateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.Random;

public class AppleSaplingGenerator extends SaplingGenerator {
	public static final Identifier IDENTIFIER = new Identifier(TweaksAndAdditions.MOD_ID, "apple");
	public static final RegistryKey<ConfiguredFeature<?, ?>> CONFIGURED_FEATURE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, IDENTIFIER);

	private ConfiguredFeature<?, ?> configuredFeature;

	public AppleSaplingGenerator(Block logBlock, Block leavesBlock, Block floweringLeavesBlock) {
		StraightTrunkPlacer trunkPlacer = new StraightTrunkPlacer(5, 1, 0);
		BlockStateProvider logProvider = BlockStateProvider.of(logBlock);
		DataPool.Builder pool = DataPool.builder().add(leavesBlock.getDefaultState(), 2).add(floweringLeavesBlock.getDefaultState(), 1);
		BlockStateProvider leavesProvider = new WeightedBlockStateProvider(pool);
		BlobFoliagePlacer foliagePlacer = new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 3);
		TwoLayersFeatureSize featureSize = new TwoLayersFeatureSize(1, 0, 1);

		TreeFeatureConfig build = new TreeFeatureConfig.Builder(logProvider, trunkPlacer, leavesProvider, foliagePlacer, featureSize).build();
		configuredFeature = Feature.TREE.configure(build);

		Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, CONFIGURED_FEATURE_KEY.getValue(), configuredFeature);
	}

	@Override
	protected ConfiguredFeature<?, ?> getTreeFeature(Random random, boolean bees) {
		return configuredFeature;
	}
}
