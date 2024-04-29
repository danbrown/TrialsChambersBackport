package net.salju.trialstowers.enchantment;

import net.salju.trialstowers.item.MaceItem;
import net.minecraft.world.item.enchantment.EnchantmentCategory;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.entity.EquipmentSlot;

public class MaceEnchantment extends Enchantment {
	private final int level;

	public MaceEnchantment(int i, Enchantment.Rarity rare, EquipmentSlot... slots) {
		super(rare, EnchantmentCategory.DIGGER, slots);
		this.level = i;
	}

	@Override
	public int getMaxLevel() {
		return level;
	}

	@Override
	public boolean canApplyAtEnchantingTable(ItemStack stack) {
		return (stack.getItem() instanceof MaceItem);
	}
}