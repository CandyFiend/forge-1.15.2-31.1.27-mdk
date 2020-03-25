package torq.torqmod;

import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;
import torq.torqmod.world.ModOreGeneration;

/**
 * 
 * @author torq
 * @version 0.1
 */
@Mod(Reference.MOD_ID)
public class TorqMod {
	public static final Logger LOGGER = LogManager.getLogger(Reference.MOD_ID);

	public static TorqMod INSTANCE;

	public TorqMod() {
		INSTANCE = this;
	}

	@SubscribeEvent
	public  static void onLoadComplete(FMLLoadCompleteEvent event) {
		ModOreGeneration.setupOreGeneration();
	}
}