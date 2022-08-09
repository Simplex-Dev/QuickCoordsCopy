package io.github.simplexdev.quickcoordscopy;

import io.github.simplexdev.quickcoordscopy.config.QuickCoordsCopyConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.Toml4jConfigSerializer;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class QuickCoordsCopy implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("quickcoordscopy");

	@Override
	public void onInitialize() {
		AutoConfig.register(QuickCoordsCopyConfig.class, Toml4jConfigSerializer::new);
		LOGGER.info("Consider your taters tinied.");
	}
}
