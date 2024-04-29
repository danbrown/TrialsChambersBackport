package net.salju.trialstowers.init;

import net.salju.trialstowers.block.*;
import net.salju.trialstowers.TrialsMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.WallBlock;
import net.minecraft.world.level.block.StairBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.SlabBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Block;

public class TrialsBlocks {
	public static final DeferredRegister<Block> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCKS, TrialsMod.MODID);
	public static final RegistryObject<Block> TUFF_STAIRS = REGISTRY.register("tuff_stairs", () -> new StairBlock(Blocks.TUFF.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> TUFF_SLAB = REGISTRY.register("tuff_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> TUFF_WALL = REGISTRY.register("tuff_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> POLISHED_TUFF = REGISTRY.register("polished_tuff", () -> new Block(BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> POLISHED_TUFF_STAIRS = REGISTRY.register("polished_tuff_stairs", () -> new StairBlock(Blocks.TUFF.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> POLISHED_TUFF_SLAB = REGISTRY.register("polished_tuff_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> POLISHED_TUFF_WALL = REGISTRY.register("polished_tuff_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> TUFF_BRICKS = REGISTRY.register("tuff_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> TUFF_BRICKS_STAIRS = REGISTRY.register("tuff_brick_stairs", () -> new StairBlock(Blocks.TUFF.defaultBlockState(), BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> TUFF_BRICKS_SLAB = REGISTRY.register("tuff_brick_slab", () -> new SlabBlock(BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> TUFF_BRICKS_WALL = REGISTRY.register("tuff_brick_wall", () -> new WallBlock(BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> CHISELED_TUFF = REGISTRY.register("chiseled_tuff", () -> new Block(BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> CHISELED_TUFF_BRICKS = REGISTRY.register("chiseled_tuff_bricks", () -> new Block(BlockBehaviour.Properties.copy(Blocks.TUFF)));
	public static final RegistryObject<Block> SPAWNER = REGISTRY.register("trial_spawner", () -> new TrialSpawnerBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.NETHERITE_BLOCK).strength(200.0F, 2000.0F).requiresCorrectToolForDrops().noOcclusion()));
	public static final RegistryObject<Block> VAULT = REGISTRY.register("trial_vault", () -> new TrialVaultBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.NETHERITE_BLOCK).strength(200.0F, 2000.0F).requiresCorrectToolForDrops().noOcclusion()));
	public static final RegistryObject<Block> HEAVY_CORE = REGISTRY.register("heavy_core", () -> new HeavyCoreBlock(BlockBehaviour.Properties.of().mapColor(MapColor.METAL).sound(SoundType.NETHERITE_BLOCK).strength(20.0F, 1000.0F).requiresCorrectToolForDrops().noOcclusion()));
}