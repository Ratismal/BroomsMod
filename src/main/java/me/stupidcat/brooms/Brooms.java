package me.stupidcat.brooms;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Brooms implements ModInitializer {
	public static final String MOD_ID = "brooms";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		LOGGER.info("Getting ready to sweep!");

		BroomsItemGroups.register();
		BroomsParticles.register();
		BroomsEntities.register();
		BroomsRecipes.register();
	}

	public static Identifier Id(String path) {
		return new Identifier(MOD_ID, path);
	}
}