package net.salju.trialstowers.init;

import net.minecraft.world.level.levelgen.structure.Structure;
import net.minecraft.tags.TagKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.registries.Registries;

public class TrialsTags {
	public static TagKey<Structure> TRIALS_MAPS = TagKey.create(Registries.STRUCTURE, new ResourceLocation("trials:on_trials_maps"));
}