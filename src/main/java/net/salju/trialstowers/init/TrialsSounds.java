package net.salju.trialstowers.init;

import net.salju.trialstowers.TrialsTowersMod;
import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

public class TrialsSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TrialsTowersMod.MODID);
	public static final RegistryObject<SoundEvent> VAULT_OPEN = REGISTRY.register("vault_open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "vault_open")));
	public static final RegistryObject<SoundEvent> VAULT_KEY = REGISTRY.register("vault_key", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "vault_key")));
	public static final RegistryObject<SoundEvent> VAULT_EJECT = REGISTRY.register("vault_eject", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "vault_eject")));
	public static final RegistryObject<SoundEvent> VAULT_ACTIVATE = REGISTRY.register("vault_activate", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "vault_activate")));
	public static final RegistryObject<SoundEvent> SPAWNER_CLOSE = REGISTRY.register("spawner_close", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "spawner_close")));
	public static final RegistryObject<SoundEvent> SPAWNER_DETECT = REGISTRY.register("spawner_detect", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "spawner_detect")));
	public static final RegistryObject<SoundEvent> SPAWNER_OPEN = REGISTRY.register("spawner_open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "spawner_open")));
	public static final RegistryObject<SoundEvent> SPAWNER_ITEM = REGISTRY.register("spawner_item", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "spawner_item")));
	public static final RegistryObject<SoundEvent> SPAWNER_SUMMON = REGISTRY.register("spawner_summon", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "spawner_summon")));
	public static final RegistryObject<SoundEvent> BOGGED_AMBIENT = REGISTRY.register("bogged_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "bogged_ambient")));
	public static final RegistryObject<SoundEvent> BOGGED_DEATH = REGISTRY.register("bogged_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "bogged_death")));
	public static final RegistryObject<SoundEvent> BOGGED_HURT = REGISTRY.register("bogged_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "bogged_hurt")));
	public static final RegistryObject<SoundEvent> BOGGED_STEP = REGISTRY.register("bogged_step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "bogged_step")));
	public static final RegistryObject<SoundEvent> BOTTLE = REGISTRY.register("bottle_dispose", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "bottle_dispose")));
}
