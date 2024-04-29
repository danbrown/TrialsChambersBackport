
/*
 *    MCreator note: This file will be REGENERATED on each build.
 */
package net.salju.trialstowers.init;

import net.salju.trialstowers.TrialsMod;

import net.minecraftforge.registries.RegistryObject;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.DeferredRegister;

import net.minecraft.sounds.SoundEvent;
import net.minecraft.resources.ResourceLocation;

public class TrialsModSounds {
	public static final DeferredRegister<SoundEvent> REGISTRY = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, TrialsMod.MODID);
	public static final RegistryObject<SoundEvent> BOTTLE_DISPOSE = REGISTRY.register("bottle_dispose", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "bottle_dispose")));
	public static final RegistryObject<SoundEvent> BOGGED_AMBIENT = REGISTRY.register("bogged_ambient", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "bogged_ambient")));
	public static final RegistryObject<SoundEvent> BOGGED_DEATH = REGISTRY.register("bogged_death", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "bogged_death")));
	public static final RegistryObject<SoundEvent> BOGGED_HURT = REGISTRY.register("bogged_hurt", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "bogged_hurt")));
	public static final RegistryObject<SoundEvent> BOGGED_STEP = REGISTRY.register("bogged_step", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "bogged_step")));
	public static final RegistryObject<SoundEvent> SPAWNER_CLOSE = REGISTRY.register("spawner_close", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "spawner_close")));
	public static final RegistryObject<SoundEvent> SPAWNER_DETECT_PLAYER = REGISTRY.register("spawner_detect_player", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "spawner_detect_player")));
	public static final RegistryObject<SoundEvent> SPAWNER_ITEM = REGISTRY.register("spawner_item", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "spawner_item")));
	public static final RegistryObject<SoundEvent> SPAWNER_OPEN = REGISTRY.register("spawner_open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "spawner_open")));
	public static final RegistryObject<SoundEvent> SPAWNER_SUMMON = REGISTRY.register("spawner_summon", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "spawner_summon")));
	public static final RegistryObject<SoundEvent> VAULT_ACTIVATE = REGISTRY.register("vault_activate", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "vault_activate")));
	public static final RegistryObject<SoundEvent> VAULT_EJECT = REGISTRY.register("vault_eject", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "vault_eject")));
	public static final RegistryObject<SoundEvent> VAULT_INSERT_KEY = REGISTRY.register("vault_insert_key", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "vault_insert_key")));
	public static final RegistryObject<SoundEvent> VAULT_OPEN = REGISTRY.register("vault_open", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "vault_open")));
	public static final RegistryObject<SoundEvent> MACE_SMASH_AIR = REGISTRY.register("mace_smash_air", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "mace_smash_air")));
	public static final RegistryObject<SoundEvent> MACE_SMASH_GROUND = REGISTRY.register("mace_smash_ground", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "mace_smash_ground")));
	public static final RegistryObject<SoundEvent> MACE_SMASH_GROUND_HEAVY = REGISTRY.register("mace_smash_ground_heavy", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "mace_smash_ground_heavy")));
	public static final RegistryObject<SoundEvent> WIND_CHARGE = REGISTRY.register("wind_charge", () -> SoundEvent.createVariableRangeEvent(new ResourceLocation("trials", "wind_charge")));
}
