package net.salju.trialstowers.block;

import net.salju.trialstowers.init.TrialsProperties;
import net.salju.trialstowers.init.TrialsBlockEntities;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.BlockEntityTicker;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.RandomSource;
import net.minecraft.sounds.SoundSource;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.Direction;
import net.minecraft.core.BlockPos;
import javax.annotation.Nullable;

public class CrafterBlock extends BaseEntityBlock {
	public static final DirectionProperty FACING = BlockStateProperties.HORIZONTAL_FACING;
	public static final BooleanProperty TRIGGERED = BlockStateProperties.TRIGGERED;
	public static final BooleanProperty CRAFTING = TrialsProperties.CRAFTING;

	public CrafterBlock(BlockBehaviour.Properties props) {
		super(props);
		this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH).setValue(TRIGGERED, Boolean.valueOf(false)).setValue(CRAFTING, Boolean.valueOf(false)));
	}

	@Override
	protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
		super.createBlockStateDefinition(builder);
		builder.add(FACING);
		builder.add(TRIGGERED);
		builder.add(CRAFTING);
	}

	@Override
	public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult rez) {
		if (world.isClientSide) {
			return InteractionResult.SUCCESS;
		} else {
			if (world.getBlockEntity(pos) instanceof CrafterEntity target) {
				player.openMenu(target);
			}
			return InteractionResult.CONSUME;
		}
	}

	@Override
	public void onPlace(BlockState state, Level world, BlockPos pos, BlockState newState, boolean check) {
		super.onPlace(state, world, pos, newState, check);
		if (!newState.is(state.getBlock())) {
			if (!world.isClientSide() && !state.getValue(TRIGGERED) && !state.getValue(CRAFTING)) {
				world.scheduleTick(pos, this, 1);
			}
		}
	}

	@Override
	public void onRemove(BlockState newState, Level world, BlockPos pos, BlockState oldState, boolean check) {
		if (!newState.is(oldState.getBlock())) {
			if (world.getBlockEntity(pos) instanceof CrafterEntity target) {
				target.clearContent();
				world.updateNeighbourForOutputSignal(pos, this);
			}
			super.onRemove(newState, world, pos, oldState, check);
		}
	}

	@Override
	public void neighborChanged(BlockState state, Level world, BlockPos pos, Block blok, BlockPos poz, boolean check) {
		super.neighborChanged(state, world, pos, blok, poz, check);
		if (!world.isClientSide() && !state.getValue(TRIGGERED) && !state.getValue(CRAFTING)) {
			world.scheduleTick(pos, this, 1);
		}
	}

	@Override
	public BlockState getStateForPlacement(BlockPlaceContext context) {
		Direction dir = context.getNearestLookingDirection().getOpposite();
		if (dir == Direction.UP || dir == Direction.DOWN) {
			dir = Direction.NORTH;
		}
		return this.defaultBlockState().setValue(FACING, dir);
	}

	@Override
	public void tick(BlockState state, ServerLevel lvl, BlockPos pos, RandomSource rng) {
		super.tick(state, lvl, pos, rng);
		if (lvl.getBlockEntity(pos) instanceof CrafterEntity target) {
			if (state.getValue(TRIGGERED) && state.getValue(CRAFTING)) {
				lvl.setBlock(pos, state.setValue(TRIGGERED, false).setValue(CRAFTING, false), 2);
				lvl.updateNeighbourForOutputSignal(pos, state.getBlock());
				if (target.isEmpty() || !target.canCraftItem()) {
					lvl.playSound(null, pos, SoundEvents.DISPENSER_FAIL, SoundSource.BLOCKS, 1.0F, 1.0F);
				} else {
					lvl.playSound(null, pos, SoundEvents.DISPENSER_LAUNCH, SoundSource.BLOCKS, 1.0F, 1.0F);
					target.craftItem(state.getValue(FACING));
				}
			} else if (state.getValue(TRIGGERED) && !state.getValue(CRAFTING)) {
				lvl.setBlock(pos, state.setValue(CRAFTING, true), 2);
				lvl.updateNeighbourForOutputSignal(pos, state.getBlock());
				lvl.scheduleTick(pos, this, 4);
			} else if (lvl.hasNeighborSignal(pos) && !state.getValue(TRIGGERED)) {
				lvl.setBlock(pos, state.setValue(TRIGGERED, true), 2);
				lvl.updateNeighbourForOutputSignal(pos, state.getBlock());
				lvl.scheduleTick(pos, this, 4);
			}
		}
	}

	@Override
	public boolean triggerEvent(BlockState state, Level world, BlockPos pos, int i, int e) {
		return world.getBlockEntity(pos) == null ? super.triggerEvent(state, world, pos, i, e) : world.getBlockEntity(pos).triggerEvent(i, e);
	}

	@Override
	public @Nullable BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
		return new CrafterEntity(pos, state);
	}

	@Override
	public @Nullable <T extends BlockEntity> BlockEntityTicker<T> getTicker(Level world, BlockState state, BlockEntityType<T> type) {
		return createTickerHelper(type, TrialsBlockEntities.CRAFTER.get(), CrafterEntity::tick);
	}

	@Override
	public RenderShape getRenderShape(BlockState state) {
		return RenderShape.MODEL;
	}

	@Override
	public boolean hasAnalogOutputSignal(BlockState state) {
		return state.getValue(CRAFTING);
	}

	@Override
	public int getAnalogOutputSignal(BlockState state, Level world, BlockPos pos) {
		return (state.getValue(CRAFTING) ? 15 : 0);
	}
}
