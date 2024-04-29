package net.salju.trialstowers.item;

import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.Level;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.DiggerItem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.damagesource.CombatRules;
import net.minecraft.tags.BlockTags;
import net.minecraft.core.BlockPos;

public class MaceItem extends DiggerItem {
	private float damage;

	public MaceItem(Tier t, float f1, float f2, Item.Properties props) {
		super(f1, f2, t, BlockTags.BEDS, props);
	}

	@Override
	public boolean canAttackBlock(BlockState state, Level world, BlockPos pos, Player player) {
		return !player.isCreative();
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		stack.hurtAndBreak(1, attacker, (user) -> user.broadcastBreakEvent(EquipmentSlot.MAINHAND));
		return true;
	}

	public float getMaceDamage(DamageSource source, LivingEntity target, int i) {
		float f1 = (1.0F - (i * 0.15F));
		this.damage = CombatRules.getDamageAfterAbsorb(this.damage, ((float) target.getArmorValue() * f1), ((float) target.getAttributeValue(Attributes.ARMOR_TOUGHNESS) * f1));
		if (target.hasEffect(MobEffects.DAMAGE_RESISTANCE)) {
			int e = (target.getEffect(MobEffects.DAMAGE_RESISTANCE).getAmplifier() + 1) * 5;
			this.damage = Math.max((this.damage * ((float) 25 - e)) / 25.0F, 0.0F);
		}
		this.damage = CombatRules.getDamageAfterMagicAbsorb(this.damage, ((float) EnchantmentHelper.getDamageProtection(target.getArmorSlots(), source)));
		return this.damage;
	}

	public void setMaceDamage(float f) {
		this.damage = f;
	}
}