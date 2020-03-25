package torq.torqmod.event;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.*;
import net.minecraftforge.fml.event.server.FMLServerAboutToStartEvent;
import net.minecraftforge.fml.event.server.FMLServerStartingEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import torq.torqmod.Reference;
import torq.torqmod.world.ModOreGeneration;

/**
 *
 *
 * @author torq
 * @version 0.1
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ForgeEventHandler {
    public static final Logger LOGGER = LogManager.getLogger();

    @SubscribeEvent
    public void setup(FMLCommonSetupEvent event) {

    }

    @SubscribeEvent
    public void doClientStuff(FMLClientSetupEvent event) {

    }

    @SubscribeEvent
    public static void beforeServerStart(FMLServerAboutToStartEvent event) {

    }

    @SubscribeEvent
    public void enqueueIMC(InterModEnqueueEvent event) {

    }

    @SubscribeEvent
    public void processIMC(InterModProcessEvent event) {

    }

    @SubscribeEvent
    public void onServerStarting(FMLServerStartingEvent event) {

    }

    @SubscribeEvent
    public  static void onLoadComplete(FMLLoadCompleteEvent event) {
        ModOreGeneration.setupOreGeneration();
        LOGGER.info("ORE REGISTER");
    }
}
