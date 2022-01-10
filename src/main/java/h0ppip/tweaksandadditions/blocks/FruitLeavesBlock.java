package h0ppip.tweaksandadditions.blocks;

import h0ppip.tweaksandadditions.custom.CustomLeavesBlock;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class FruitLeavesBlock extends CustomLeavesBlock implements Fertilizable {
	private Block fruitBlock;

	public FruitLeavesBlock(AbstractBlock.Settings settings, Block fruitBlock) {
		super(settings);

		this.fruitBlock = fruitBlock;
	}

	@Override
	public boolean hasRandomTicks(BlockState state) {
		return true;
	}

	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		super.randomTick(state, world, pos, random);

		if (random.nextInt(40) != 0) {
			return;
		}

		grow(world, random, pos, state);
	}

	@Override
	public boolean isFertilizable(BlockView world, BlockPos pos, BlockState state, boolean isClient) {
		return world.getBlockState(pos).isOf(this);
	}

	@Override
	public boolean canGrow(World world, Random random, BlockPos pos, BlockState state) {
		return world.getBlockState(pos).isOf(this) && world.getBlockState(pos.offset(Direction.DOWN)).isAir();
	}

	@Override
	public void grow(ServerWorld world, Random random, BlockPos pos, BlockState state) {
		if (canGrow(world, random, pos, state)) {
			world.setBlockState(pos.offset(Direction.DOWN), fruitBlock.getDefaultState());
		}
	}
}
