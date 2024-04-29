package net.salju.trialstowers.events;

import net.salju.trialstowers.item.MaceItem;
import net.salju.trialstowers.init.TrialsModSounds;
import net.salju.trialstowers.init.TrialsMobs;
import net.salju.trialstowers.init.TrialsEnchantments;
import net.salju.trialstowers.block.TrialSpawnerEntity;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraftforge.event.entity.EntityJoinLevelEvent;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Skeleton;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Entity;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.core.BlockPos;

@Mod.EventBusSubscriber
public class TrialsEvents {
	@SubscribeEvent
	public static void onHurt(LivingHurtEvent event) {
		if (event.getEntity() != null && event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity() instanceof Player target) {
			if (target.getMainHandItem().getItem() instanceof MaceItem mace) {
				mace.setMaceDamage(event.getAmount());
			}
		}
	}

	@SubscribeEvent
	public static void onDamage(LivingDamageEvent event) {
		if (event.getEntity() != null && event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity() instanceof LivingEntity target) {
			if (target.getMainHandItem().getItem() instanceof MaceItem mace) {
				int i = EnchantmentHelper.getItemEnchantmentLevel(TrialsEnchantments.BREACH.get(), target.getMainHandItem());
				if (i > 0) {
					event.setAmount(mace.getMaceDamage(event.getSource(), event.getEntity(), i));
				}
				mace.setMaceDamage(0.0F);
			}
		}
	}

	@SubscribeEvent
	public static void onCritical(CriticalHitEvent event) {
		Player player = event.getEntity();
		Entity target = event.getTarget();
		if (event.isVanillaCritical()) {
			if (player.getMainHandItem().getItem() instanceof MaceItem && player.fallDistance > 3.0F) {
				int i = EnchantmentHelper.getItemEnchantmentLevel(TrialsEnchantments.DENSITY.get(), player.getMainHandItem());
				int e = EnchantmentHelper.getItemEnchantmentLevel(TrialsEnchantments.WIND.get(), player.getMainHandItem());
				float f = (player.fallDistance - 3.0F);
				if (i > 0) {
					f = (f * (i + 1.0F));
				}
				if (e > 0) {
					double y = ((double) Mth.nextInt(player.level().getRandom(), 2, 3) * e);
					player.setDeltaMovement(player.getDeltaMovement().x(), (y * 0.15), player.getDeltaMovement().z());
					if (player.level() instanceof ServerLevel lvl) {
						lvl.sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 8, 1.5, 0.15, 1.5, 0);
						lvl.sendParticles(ParticleTypes.EXPLOSION, player.getX(), player.getY(), player.getZ(), 4, 1.8, 0.15, 1.8, 0);
						lvl.playSound(null, player.blockPosition(), TrialsModSounds.WIND_CHARGE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
					}
				}
				if (player.level() instanceof ServerLevel lvl) {
					if (target.onGround()) {
						if (player.fallDistance > 10.0F) {
							lvl.playSound(null, target.blockPosition(), TrialsModSounds.MACE_SMASH_GROUND_HEAVY.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
						} else {
							lvl.playSound(null, target.blockPosition(), TrialsModSounds.MACE_SMASH_GROUND.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
						}
					} else {
						lvl.playSound(null, target.blockPosition(), TrialsModSounds.MACE_SMASH_AIR.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
					}
				}
				player.fallDistance = 0.0F;
				event.setDamageModifier(event.getDamageModifier() + (0.1F * f));
			}
		}
	}

	@SubscribeEvent
	public static void onDeath(LivingDeathEvent event) {
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