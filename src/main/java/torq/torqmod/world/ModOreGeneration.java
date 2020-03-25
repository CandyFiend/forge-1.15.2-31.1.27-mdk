package torq.torqmod.world;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraftforge.registries.ForgeRegistries;
import torq.torqmod.block.ModBlocks;
import torq.torqmod.world.biome.ModFeatures;

/**
 *
 *
 * @author torq
 * @version 0.1
 */
public class ModOreGeneration {
    public static void setupOreGeneration(){
        for(Biome biome : ForgeRegistries.BIOMES) {
            ModFeatures.addOreCopper(biome);
        }
    }
}
