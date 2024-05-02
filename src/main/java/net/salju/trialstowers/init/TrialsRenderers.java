package net.salju.trialstowers.init;

import net.salju.trialstowers.client.renderer.*;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class TrialsRenderers {
	@SubscribeEvent
	public static void registerEntityRenderers(EntityRenderersEvent.RegisterRenderers event) {
		event.registerEntityRenderer(TrialsMobs.BOGGED.get(), BoggedRenderer::new);
		event.registerEntityRenderer(TrialsMobs.WIND.get(), ThrownItemRenderer::new);
	}
}