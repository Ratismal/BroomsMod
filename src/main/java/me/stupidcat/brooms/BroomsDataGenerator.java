package me.stupidcat.brooms;

import me.stupidcat.brooms.datagen.BroomsBlockTagGenerator;
import me.stupidcat.brooms.datagen.BroomsModelGenerator;
import me.stupidcat.brooms.datagen.BroomsRecipeGenerator;
import me.stupidcat.brooms.datagen.BroomsItemTagGenerator;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class BroomsDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator generator) {
		FabricDataGenerator.Pack pack = generator.createPack();

		pack.addProvider(BroomsModelGenerator::new);
		pack.addProvider(BroomsRecipeGenerator::new);
		BroomsBlockTagGenerator blockTagProvider = pack.addProvider(BroomsBlockTagGenerator::new);
		pack.addProvider((output, registries) -> new BroomsItemTagGenerator(output, registries, blockTagProvider));
	}
}
