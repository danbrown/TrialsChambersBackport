package net.salju.trialstowers.events;

import net.salju.trialstowers.init.TrialsTags;
import net.salju.trialstowers.block.TrialSpawnerEntity;
import net.minecraft.world.level.storage.loot.parameters.LootContextParams;
import net.minecraft.world.level.storage.loot.parameters.LootContextParamSets;
import net.minecraft.world.level.storage.loot.LootParams;
import net.minecraft.world.level.chunk.LevelChunk;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.monster.Zombie;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.SectionPos;
import net.minecraft.core.BlockPos;
import javax.annotation.Nullable;
import java.util.List;

public class TrialsManager {
	public static List<ItemStack> getLoot(BlockEntity target, Level world, String table) {
		return world.getServer().getLootData().getLootTable(new ResourceLocation(table)).getRandomItems((new LootParams.Builder((ServerLevel) world)).withParameter(LootContextParams.BLOCK_ENTITY, target).create(LootContextParamSets.EMPTY));
	}

	@Nullable
	public static TrialSpawnerEntity getSpawner(Entity entity, BlockPos pos, Level world, int radius) {
		int minX = SectionPos.blockToSectionCoord(pos.getX() - radius);
		int minZ = SectionPos.blockToSectionCoord(pos.getZ() - radius);
		int maxX = SectionPos.blockToSectionCoord(pos.getX() + radius);
		int maxZ = SectionPos.blockToSectionCoord(pos.getZ() + radius);
		for (int chunkX = minX; chunkX <= maxX; chunkX++) {
			for (int chunkZ = minZ; chunkZ <= maxZ; chunkZ++) {
				LevelChunk chunk = world.getChunkSource().getChunk(chunkX, chunkZ, false);
				if (chunk != null) {
					for (BlockPos poz : chunk.getBlockEntitiesPos()) {
						if (world.getBlockEntity(poz) instanceof TrialSpawnerEntity target) {
							if (target.getSpawnType() != null && entity.getPersistentData().getInt("TrialSpawned") == target.getDifficulty() && target.getRemainingEnemies() != 0 && pos.closerThan(poz, radius)) {
								if (checkType(entity, target.getSpawnType())) {
									return target;
								}
							}
						}
					}
				}
			}
		}
		return null;
	}

	public static boolean checkType(Entity entity, EntityType<?> type) {
		if (entity.getType() == type) {
			return true;
		} else if (type == EntityType.HUSK || type == EntityType.ZOMBIE) {
			return (entity instanceof Zombie);
		} else if (type == EntityType.SKELETON) {
			return (entity instanceof AbstractSkeleton);
		} else if (type.is(TrialsTags.KOBOLDS)) {
			return (entity.getType().is(TrialsTags.KOBOLDS));
		} else {
			return false;
		}
	}
}