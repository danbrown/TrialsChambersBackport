package net.salju.trialstowers.init;

import net.salju.trialstowers.TrialsMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.network.chat.Component;
import net.minecraft.core.registries.Registries;

public class TrialsTabs {
	public static final DeferredRegister<CreativeModeTab> REGISTRY = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, TrialsMod.MODID);
	public static final RegistryObject<CreativeModeTab> TRIALS = REGISTRY.register("trials",
			() -> CreativeModeTab.builder().title(Component.translatable("itemGroup.trials")).icon(() -> new ItemStack(TrialsItems.TRIAL_KEY.get())).displayItems((parameters, tabData) -> {
				tabData.accept(Blocks.TUFF.asItem());
				tabData.accept(TrialsBlocks.TUFF_STAIRS.get().asItem());
				tabData.accept(TrialsBlocks.TUFF_SLAB.get().asItem());
				tabData.accept(TrialsBlocks.TUFF_WALL.get().asItem());
				tabData.accept(TrialsBlocks.CHISELED_TUFF.get().asItem());
				tabData.accept(TrialsBlocks.POLISHED_TUFF.get().asItem());
				tabData.accept(TrialsBlocks.POLISHED_TUFF_STAIRS.get().asItem());
				tabData.accept(TrialsBlocks.POLISHED_TUFF_SLAB.get().asItem());
				tabData.accept(TrialsBlocks.POLISHED_TUFF_WALL.get().asItem());
				tabData.accept(TrialsBlocks.TUFF_BRICKS.get().asItem());
				tabData.accept(TrialsBlocks.TUFF_BRICKS_STAIRS.get().asItem());
				tabData.accept(TrialsBlocks.TUFF_BRICKS_SLAB.get().asItem());
				tabData.accept(TrialsBlocks.TUFF_BRICKS_WALL.get().asItem());
				tabData.accept(TrialsBlocks.CHISELED_TUFF_BRICKS.get().asItem());
				tabData.accept(TrialsBlocks.SPAWNER.get().asItem());
				tabData.accept(TrialsBlocks.VAULT.get().asItem());
				tabData.accept(TrialsBlocks.HEAVY_CORE.get().asItem());
				tabData.accept(TrialsItems.BOGGED_SPAWN_EGG.get());
				tabData.accept(TrialsItems.BOLT_TEMPLATE.get());
				tabData.accept(TrialsItems.FLOW_TEMPLATE.get());
				tabData.accept(TrialsItems.BANNER_PATTERN_FLOW.get());
				tabData.accept(TrialsItems.BANNER_PATTERN_GUSTER.get());
				tabData.accept(TrialsItems.WIND_CHARGE.get());
				tabData.accept(TrialsItems.TRIAL_BOTTLE.get());
				tabData.accept(TrialsItems.TRIAL_KEY.get());
				tabData.accept(TrialsItems.BREEZE_ROD.get());
				tabData.accept(TrialsItems.MACE.get());
			}).build());
}