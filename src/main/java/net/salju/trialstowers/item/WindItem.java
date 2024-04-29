package net.salju.trialstowers.item;

import net.salju.trialstowers.init.TrialsModSounds;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.InteractionHand;
import net.minecraft.util.Mth;
import net.minecraft.sounds.SoundSource;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.core.particles.ParticleTypes;

public class WindItem extends Item {
	public WindItem(Item.Properties props) {
		super(props);
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level world, Player player, InteractionHand hand) {
		ItemStack stack = player.getItemInHand(hand);
		if (!player.isCreative()) {
			stack.shrink(1);
		}
		double y = ((double) Mth.nextInt(player.level().getRandom(), 2, 3) * (player.isCrouching() && player.onGround() ? 3 : 2));
		player.setDeltaMovement(player.getDeltaMovement().x(), (y * 0.15), player.getDeltaMovement().z());
		player.fallDistance = 0.0F;
		if (world instanceof ServerLevel lvl) {
			player.getCooldowns().addCooldown(stack.getItem(), 15);
			lvl.sendParticles(ParticleTypes.CLOUD, player.getX(), player.getY(), player.getZ(), 8, 1.5, 0.15, 1.5, 0);
			lvl.sendParticles(ParticleTypes.EXPLOSION, player.getX(), player.getY(), player.getZ(), 4, 1.8, 0.15, 1.8, 0);
			lvl.playSound(null, player.blockPosition(), TrialsModSounds.WIND_CHARGE.get(), SoundSource.PLAYERS, 1.0F, 1.0F);
		}
		return InteractionResultHolder.success(stack);
	}
}