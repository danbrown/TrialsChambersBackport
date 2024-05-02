package net.salju.trialstowers.init;

import net.salju.trialstowers.enchantment.MaceEnchantment;
import net.salju.trialstowers.TrialsMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.world.item.enchantment.Enchantment;

public class TrialsEnchantments {
	public static final DeferredRegister<Enchantment> REGISTRY = DeferredRegister.create(ForgeRegistries.ENCHANTMENTS, TrialsMod.MODID);
	public static final RegistryObject<Enchantment> DENSITY = REGISTRY.register("density", () -> new MaceEnchantment(5, Enchantment.Rarity.COMMON));
	public static final RegistryObject<Enchantment> BREACH = REGISTRY.register("breach", () -> new MaceEnchantment(4, Enchantment.Rarity.COMMON));
	public static final RegistryObject<Enchantment> WIND = REGISTRY.register("wind_burst", () -> new MaceEnchantment(3, Enchantment.Rarity.RARE));
}