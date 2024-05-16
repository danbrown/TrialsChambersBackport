package net.salju.trialstowers.events;

import net.salju.trialstowers.network.ApplyKnockback;
import net.salju.trialstowers.item.MaceItem;
import net.salju.trialstowers.init.TrialsModSounds;
import net.salju.trialstowers.init.TrialsEnchantments;
import net.salju.trialstowers.init.TrialsEffects;
import net.salju.trialstowers.block.TrialSpawnerEntity;
import net.salju.trialstowers.TrialsMod;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.CriticalHitEvent;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.event.entity.living.LivingDeathEvent;
import net.minecraftforge.event.entity.living.LivingDamageEvent;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.monster.Slime;
import net.minecraft.world.entity.monster.Silverfish;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageTypes;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

@Mod.EventBusSubscriber
public class TrialsEvents {
	@SubscribeEvent
	public static void onHurt(LivingHurtEvent event) {
		if (event.getEntity() != null) {
			LivingEntity target = event.getEntity();
			if (target.level() instanceof ServerLevel lvl) {
				if (target.hasEffect(TrialsEffects.INFESTED.get()) && Mth.nextInt(target.level().getRandom(), 1, 10) == 10) {
					int e = (Mth.nextInt(target.level().getRandom(), 1, 3) * (target.getEffect(TrialsEffects.INFESTED.get()).getAmplifier() + 1));
					for (int i = 0; i != e; ++i) {
						Silverfish fish = EntityType.SILVERFISH.create(lvl);
						fish.moveTo(Vec3.atBottomCenterOf(target.blockPosition()));
						lvl.addFreshEntity(fish);
					}
				}
				if (event.getSource().getEntity() != null && event.getSource().getEntity() instanceof LivingEntity meanie) {
					if (meanie.hasEffect(TrialsEffects.WEAVE.get()) && target.level().isEmptyBlock(target.blockPosition()) && Mth.nextInt(target.level().getRandom(), 1, 10) == 10) {
						target.level().setBlock(target.blockPosition(), Blocks.COBWEB.defaultBlockState(), 3);
					}
				}
			}
			if (event.getSource().is(DamageTypes.FALL) && target.hasEffect(TrialsEffects.WINDED.get())) {
				event.setAmount(0.0F);
			}
			if (event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity() instanceof LivingEntity meanie) {
				if (meanie.getMainHandItem().getItem() instanceof MaceItem mace) {
					mace.setMaceDamage(event.getAmount());
				}
			}
		}
	}

	@SubscribeEvent
	public static void onDamage(LivingDamageEvent event) {
		if (event.getEntity() != null) {
			LivingEntity target = event.getEntity();
			if (event.getSource().getDirectEntity() != null && event.getSource().getDirectEntity() instanceof LivingEntity meanie) {
				if (meanie.getMainHandItem().getItem() instanceof MaceItem mace) {
					int i = EnchantmentHelper.getItemEnchantmentLevel(TrialsEnchantments.BREACH.get(), meanie.getMainHandItem());
					if (i > 0) {
						event.setAmount(mace.getMaceDamage(event.getSource(), event.getEntity(), i));
					}
					mace.setMaceDamage(0.0F);
				}
			}
		}
	}

	@SubscribeEvent
	public static void onCritical(CriticalHitEvent event) {
		Player player = event.getEntity();
		Entity target = event.getTarget();
		if (event.isVanillaCritical() && player.getMainHandItem().getItem() instanceof MaceItem && player.level() instanceof ServerLevel lvl) {
			int e = EnchantmentHelper.getItemEnchantmentLevel(TrialsEnchantments.WIND.get(), player.getMainHandItem());
			double y = ((double) Mth.nextInt(player.level().getRandom(), 2, 3) * e);
			if (e > 0) {
				if (player instanceof ServerPlayer ply) {
					TrialsMod.sendToClientPlayer(new ApplyKnockback(y), ply);
				}
				player.addEffect(new MobEffectInstance(TrialsEffects.WINDED.get(), 45, 0));
				lvl.sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 8, 1.5, 0.15, 1.5, 0);
				lvl.sendParticles(ParticleTypes.EXPLOSION, player.getX(), player.getY(), player.getZ(), 4, 1.8, 0.15, 1.8, 0);
				lvl.playSound(null, player.blockPosition(), TrialsModSounds.WIND_CHARGE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
			}
			if (player.fallDistance > 3.0F) {
				int i = EnchantmentHelper.getItemEnchantmentLevel(TrialsEnchantments.DENSITY.get(), player.getMainHandItem());
				float f = (player.fallDistance - 3.0F);
				if (i > 0) {
					f = (f * (i + 1.0F));
				}
				if (target.onGround()) {
					if (player.fallDistance > 10.0F) {
						lvl.playSound(null, target.blockPosition(), TrialsModSounds.MACE_SMASH_GROUND_HEAVY.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
					} else {
						lvl.playSound(null, target.blockPosition(), TrialsModSounds.MACE_SMASH_GROUND.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
					}
				} else {
					lvl.playSound(null, target.blockPosition(), TrialsModSounds.MACE_SMASH_AIR.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
				}
				player.fallDistance = 0.0F;
				event.setDamageModifier(event.getDamageModifier() + (0.1F * f));
			}
		}
	}

	@SubscribeEvent
	public static void onDeath(LivingDeathEvent event) {
		if (event.getEntity() != null) {
			LivingEntity target = event.getEntity();
			if (target.level() instanceof ServerLevel lvl) {
				TrialSpawnerEntity block = TrialsManager.getSpawner(target, target.blockPosition(), lvl, 64);
				if (block != null) {
					block.setRemainingEnemies(block.getRemainingEnemies() - 1);
				}
				if (target.hasEffect(TrialsEffects.OOZE.get())) {
					int e = (2 * (target.getEffect(TrialsEffects.OOZE.get()).getAmplifier() + 1));
					for (int i = 0; i != e; ++i) {
						Slime slim = EntityType.SLIME.create(lvl);
						slim.setSize(2, true);
						slim.moveTo(Vec3.atBottomCenterOf(target.blockPosition()));
						lvl.addFreshEntity(slim);
					}
				}
			}
		}
	}
}