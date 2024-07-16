package net.salju.trialstowers.init;

import net.salju.trialstowers.gui.CrafterGuiMenu;
import net.salju.trialstowers.TrialsMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraft.world.inventory.MenuType;

public class TrialsMenus {
	public static final DeferredRegister<MenuType<?>> REGISTRY = DeferredRegister.create(ForgeRegistries.MENU_TYPES, TrialsMod.MODID);
	public static final RegistryObject<MenuType<CrafterGuiMenu>> CRAFTER = REGISTRY.register("crafter_gui", () -> IForgeMenuType.create(CrafterGuiMenu::new));
}