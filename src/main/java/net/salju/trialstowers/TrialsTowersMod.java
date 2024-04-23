package net.salju.trialstowers;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import net.salju.trialstowers.init.*;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.List;
import java.util.Collection;
import java.util.ArrayList;
import java.util.AbstractMap;

@Mod("trials_towers")
public class TrialsTowersMod {
	public static final Logger LOGGER = LogManager.getLogger(TrialsTowersMod.class);
	public static final String MODID = "trials";

	public TrialsTowersMod() {
		MinecraftForge.EVENT_BUS.register(this);
		IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
		TrialsSounds.REGISTRY.register(bus);
		TrialsMobs.REGISTRY.register(bus);
		TrialsBlocks.REGISTRY.register(bus);
		TrialsBlockEntities.REGISTRY.register(bus);
		TrialsItems.REGISTRY.register(bus);
		TrialsBanners.REGISTRY.register(bus);
		TrialsTabs.REGISTRY.register(bus);
	}

	private static final Collection<AbstractMap.SimpleEntry<Runnable, Integer>> workQueue = new ConcurrentLinkedQueue<>();

	public static void queueServerWork(int tick, Runnable action) {
		workQueue.add(new AbstractMap.SimpleEntry(action, tick));
	}

	@SubscribeEvent
	public void tick(TickEvent.ServerTickEvent event) {
		if (event.phase == TickEvent.Phase.END) {
			List<AbstractMap.SimpleEntry<Runnable, Integer>> actions = new ArrayList<>();
			workQueue.forEach(work -> {
				work.setValue(work.getValue() - 1);
				if (work.getValue() == 0)
					actions.add(work);
			});
			actions.forEach(e -> e.getKey().run());
			workQueue.removeAll(actions);
		}
	}
}