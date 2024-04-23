package net.salju.trialstowers.init;

import net.salju.trialstowers.item.OmniItem;
import net.salju.trialstowers.TrialsTowersMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.item.SmithingTemplateItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.BannerPatternItem;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

public class TrialsItems {
	public static final DeferredRegister<Item> REGISTRY = DeferredRegister.create(ForgeRegistries.ITEMS, TrialsTowersMod.MODID);
	public static final RegistryObject<Item> TUFF_STAIRS = block(TrialsBlocks.TUFF_STAIRS);
	public static final RegistryObject<Item> TUFF_SLAB = block(TrialsBlocks.TUFF_SLAB);
	public static final RegistryObject<Item> TUFF_WALL = block(TrialsBlocks.TUFF_WALL);
	public static final RegistryObject<Item> POLISHED_TUFF = block(TrialsBlocks.POLISHED_TUFF);
	public static final RegistryObject<Item> POLISHED_TUFF_STAIRS = block(TrialsBlocks.POLISHED_TUFF_STAIRS);
	public static final RegistryObject<Item> POLISHED_TUFF_SLAB = block(TrialsBlocks.POLISHED_TUFF_SLAB);
	public static final RegistryObject<Item> POLISHED_TUFF_WALL = block(TrialsBlocks.POLISHED_TUFF_WALL);
	public static final RegistryObject<Item> TUFF_BRICKS = block(TrialsBlocks.TUFF_BRICKS);
	public static final RegistryObject<Item> TUFF_BRICKS_STAIRS = block(TrialsBlocks.TUFF_BRICKS_STAIRS);
	public static final RegistryObject<Item> TUFF_BRICKS_SLAB = block(TrialsBlocks.TUFF_BRICKS_SLAB);
	public static final RegistryObject<Item> TUFF_BRICKS_WALL = block(TrialsBlocks.TUFF_BRICKS_WALL);
	public static final RegistryObject<Item> CHISELED_TUFF = block(TrialsBlocks.CHISELED_TUFF);
	public static final RegistryObject<Item> CHISELED_TUFF_BRICKS = block(TrialsBlocks.CHISELED_TUFF_BRICKS);
	public static final RegistryObject<Item> SPAWNER = block(TrialsBlocks.SPAWNER);
	public static final RegistryObject<Item> VAULT = block(TrialsBlocks.VAULT);
	public static final RegistryObject<Item> TRIAL_KEY = REGISTRY.register("trial_key", () -> new Item(new Item.Properties()));
	public static final RegistryObject<Item> TRIAL_BOTTLE = REGISTRY.register("ominous_bottle", () -> new OmniItem(new Item.Properties()));
	public static final RegistryObject<Item> BOGGED_SPAWN_EGG = REGISTRY.register("bogged_spawn_egg", () -> new ForgeSpawnEggItem(TrialsMobs.BOGGED, -6684775, -13421773, new Item.Properties()));
	public static final RegistryObject<Item> BOLT_TEMPLATE = REGISTRY.register("bolt_template", () -> SmithingTemplateItem.createArmorTrimTemplate(new ResourceLocation("bolt")));
	public static final RegistryObject<Item> FLOW_TEMPLATE = REGISTRY.register("flow_template", () -> SmithingTemplateItem.createArmorTrimTemplate(new ResourceLocation("flow")));
	public static final RegistryObject<Item> BANNER_PATTERN_FLOW = REGISTRY.register("banner_pattern_flow", () -> new BannerPatternItem(TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(TrialsTowersMod.MODID, "pattern_for_flow")), (new Item.Properties()).stacksTo(1)));
	public static final RegistryObject<Item> BANNER_PATTERN_GUSTER = REGISTRY.register("banner_pattern_guster", () -> new BannerPatternItem(TagKey.create(Registries.BANNER_PATTERN, new ResourceLocation(TrialsTowersMod.MODID, "pattern_for_guster")), (new Item.Properties()).stacksTo(1)));

	private static RegistryObject<Item> block(RegistryObject<Block> block) {
		return REGISTRY.register(block.getId().getPath(), () -> new BlockItem(block.get(), new Item.Properties()));
	}
}