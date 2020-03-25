package torq.torqmod;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import net.minecraftforge.fml.common.Mod;

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
		LOGGER.info("Torqmod Constructor");
		LOGGER.debug("Debug Logging Enabled");

		INSTANCE = this;
	}


}