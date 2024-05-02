package net.salju.trialstowers.entity;

import net.salju.trialstowers.network.ApplyKnockback;
import net.salju.trialstowers.init.TrialsModSounds;
import net.salju.trialstowers.init.TrialsMobs;
import net.salju.trialstowers.init.TrialsItems;
import net.salju.trialstowers.init.TrialsEffects;
import net.salju.trialstowers.TrialsMod;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class WindCharge extends ThrowableItemProjectile {
	public WindCharge(EntityType<? extends WindCharge> type, Level world) {
		super(type, world);
	}

	public WindCharge(Level world, LivingEntity target) {
		super(TrialsMobs.WIND.get(), target, world);
	}

	@Override
	public void shoot(double x, double y, double z, float f1, float f2) {
		Vec3 vec3 = (new Vec3(x, y, z)).normalize().scale((double) f1);
		this.setDeltaMovement(vec3);
		double d0 = vec3.horizontalDistance();
		this.setYRot((float) (Mth.atan2(vec3.x, vec3.z) * (double) (180F / (float) Math.PI)));
		this.setXRot((float) (Mth.atan2(vec3.y, d0) * (double) (180F / (float) Math.PI)));
		this.yRotO = this.getYRot();
		this.xRotO = this.getXRot();
	}

	@Override
	protected void onHitEntity(EntityHitResult rez) {
		super.onHitEntity(rez);
		rez.getEntity().hurt(this.damageSources().thrown(this, this.getOwner()), 1.0F);
		this.explodeWind();
	}

	@Override
	protected void onHitBlock(BlockHitResult rez) {
		super.onHitBlock(rez);
		this.explodeWind();
	}

	@Override
	protected Item getDefaultItem() {
		return TrialsItems.WIND_CHARGE.get();
	}

	@Override
	protected float getGravity() {
		return 0.0F;
	}

	public void explodeWind() {
		if (this.level() instanceof ServerLevel lvl) {
			for (LivingEntity target : this.level().getEntitiesOfClass(LivingEntity.class, this.getBoundingBox().inflate(5.76F))) {
				if (target.hasLineOfSight(this)) {
					target.fallDistance = 0.0F;
					target.addEffect(new MobEffectInstance(TrialsEffects.WINDED.get(), 45, 0));
					double y = (((double) Mth.nextInt(target.level().getRandom(), 2, 3) * (target.isCrouching() ? 4 : 2)) - (this.distanceTo(target) * 0.75));
					if (target instanceof ServerPlayer ply) {
						TrialsMod.sendToClientPlayer(new ApplyKnockback(y), ply);
					} else {
						target.setDeltaMovement(target.getDeltaMovement().x(), (y * 0.15), target.getDeltaMovement().z());
					}
				}
			}
			lvl.sendParticles(ParticleTypes.CLOUD, this.getX(), this.getY(), this.getZ(), 8, 1.5, 0.15, 1.5, 0);
			lvl.sendParticles(ParticleTypes.EXPLOSION, this.getX(), this.getY(), this.getZ(), 4, 1.8, 0.15, 1.8, 0);
			lvl.playSound(null, this.blockPosition(), TrialsModSounds.WIND_CHARGE.get(), SoundSource.NEUTRAL, 1.0F, 1.0F);
		}
		this.discard();
	}
}