package net.salju.trialstowers.entity;

import net.salju.trialstowers.init.TrialsModSounds;
import net.salju.trialstowers.init.TrialsMobs;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.projectile.Arrow;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.Difficulty;
import net.minecraft.sounds.SoundEvent;

public class Bogged extends AbstractSkeleton {
	public Bogged(EntityType<Bogged> type, Level world) {
		super(type, world);
	}

	@Override
	public SoundEvent getAmbientSound() {
		return TrialsModSounds.BOGGED_AMBIENT.get();
	}

	@Override
	public SoundEvent getHurtSound(DamageSource source) {
		return TrialsModSounds.BOGGED_HURT.get();
	}

	@Override
	public SoundEvent getDeathSound() {
		return TrialsModSounds.BOGGED_DEATH.get();
	}

	@Override
	public SoundEvent getStepSound() {
		return TrialsModSounds.BOGGED_STEP.get();
	}

	@Override
	public AbstractArrow getArrow(ItemStack stack, float f) {
		AbstractArrow arrow = super.getArrow(stack, f);
		if (arrow instanceof Arrow) {
			((Arrow) arrow).addEffect(new MobEffectInstance(MobEffects.POISON, 300));
		}
		return arrow;
	}

	public static AttributeSupplier.Builder createAttributes() {
		AttributeSupplier.Builder builder = Mob.createMobAttributes();
		builder = builder.add(Attributes.MOVEMENT_SPEED, 0.25);
		builder = builder.add(Attributes.MAX_HEALTH, 18);
		builder = builder.add(Attributes.ARMOR, 0);
		builder = builder.add(Attributes.ATTACK_DAMAGE, 3);
		return builder;
	}

	public static void init() {
		SpawnPlacements.register(TrialsMobs.BOGGED.get(), SpawnPlacements.Type.ON_GROUND, Heightmap.Types.MOTION_BLOCKING_NO_LEAVES,
				(entityType, world, reason, pos, random) -> (world.getDifficulty() != Difficulty.PEACEFUL && Monster.isDarkEnoughToSpawn(world, pos, random) && Mob.checkMobSpawnRules(entityType, world, reason, pos, random)));
	}
}
