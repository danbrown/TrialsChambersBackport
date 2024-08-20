package net.salju.trialstowers.events;

import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import java.util.List;

public class TrialsManager {
	public static List<ItemStack> getLoot(BlockEntity target, Level world, String table) {
		return world.getServer().getLootData().getLootTable(new ResourceLocation(table)).getRandomItems((new LootParams.Builder((ServerLevel) world)).withParameter(LootContextParams.BLOCK_ENTITY, target).create(LootContextParamSets.EMPTY));
	}
}