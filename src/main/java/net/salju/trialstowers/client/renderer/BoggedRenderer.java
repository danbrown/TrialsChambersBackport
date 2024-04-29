package net.salju.trialstowers.client.renderer;

import net.salju.trialstowers.client.renderer.layers.BoggedClothingLayer;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.client.renderer.entity.SkeletonRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.model.geom.ModelLayers;

public class BoggedRenderer extends SkeletonRenderer {
	public BoggedRenderer(EntityRendererProvider.Context context) {
		super(context, ModelLayers.STRAY, ModelLayers.STRAY_INNER_ARMOR, ModelLayers.STRAY_OUTER_ARMOR);
		this.addLayer(new BoggedClothingLayer<>(this, context.getModelSet()));
	}

	@Override
	public ResourceLocation getTextureLocation(AbstractSkeleton kevin) {
		return new ResourceLocation("trials:textures/entity/bogged.png");
	}
}