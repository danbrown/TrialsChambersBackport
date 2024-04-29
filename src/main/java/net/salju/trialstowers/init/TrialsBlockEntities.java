package net.salju.trialstowers.init;

import net.salju.trialstowers.block.*;
import net.salju.trialstowers.TrialsMod;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;


public class TrialsBlockEntities {
	public static final DeferredRegister<BlockEntityType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, TrialsMod.MODID);
	public static final RegistryObject<BlockEntityType<TrialSpawnerEntity>> SPAWNER = REGISTRY.register("trials_spawner", () -> BlockEntityType.Builder.of(TrialSpawnerEntity::new, TrialsBlocks.SPAWNER.get()).build(null));
	public static final RegistryObject<BlockEntityType<TrialVaultEntity>> VAULT = REGISTRY.register("trials_vault", () -> BlockEntityType.Builder.of(TrialVaultEntity::new, TrialsBlocks.VAULT.get()).build(null));
}