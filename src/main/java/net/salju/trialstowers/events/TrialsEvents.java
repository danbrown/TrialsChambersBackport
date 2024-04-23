package net.salju.trialstowers.events;

import net.salju.trialstowers.init.TrialsMobs;
import net.salju.trialstowers.block.TrialSpawnerEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.living.LivingDropsEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.BlockPos;

@Mod.EventBusSubscriber
public class TrialsEvents {
	@SubscribeEvent
	public static void onEntityDrops(LivingDropsEvent event) {
		LivingEntity target = event.getEntity();
		if (target.level() instanceof ServerLevel lvl) {
			TrialSpawnerEntity block = TrialsManager.getSpawner(target, target.blockPosition(), lvl, 32);
			if (block != null) {
				block.setRemainingEnemies(block.getRemainingEnemies() - 1);
			}
		}
	}

	@SubscribeEvent
	public static void onEntitySpawn(EntityJoinLevelEvent event) {
		Entity target = event.getEntity();
		BlockPos pos = target.blockPosition();
		if (event.getLevel() instanceof ServerLevel lvl && !event.loadedFromDisk()) {
			if (lvl.getBiome(pos).is(Biomes.SWAMP) && target instanceof Skeleton) {
				TrialsMobs.BOGGED.get().spawn(lvl, pos, MobSpawnType.NATURAL);
				event.setCanceled(true);
			}
		}
	}
}