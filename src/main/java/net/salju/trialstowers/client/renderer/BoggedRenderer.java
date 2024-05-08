package net.salju.trialstowers.client.renderer;

import net.salju.trialstowers.init.TrialsModels;
import net.salju.trialstowers.client.renderer.layers.BoggedClothingLayer;
import net.minecraft.world.entity.monster.AbstractSkeleton;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class BoggedRenderer extends SkeletonRenderer {
	public BoggedRenderer(EntityRendererProvider.Context context) {
		super(context, TrialsModels.BOGGED, TrialsModels.BOGGED_INNER_ARMOR, TrialsModels.BOGGED_OUTER_ARMOR);
		this.addLayer(new BoggedClothingLayer<>(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractSkeleton kevin) {
		return new ResourceLocation("trials:textures/entity/bogged.png");
	}
}