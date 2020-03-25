package torq.torqmod.world.biome;

import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.OreFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.CountRangeConfig;
import net.minecraft.world.gen.placement.Placement;
import torq.torqmod.block.ModBlocks;

/**
 *
 *
 * @author torq
 * @version 0.1
 */
public class ModFeatures {
    public static void addOreCopper(Biome biomeIn) {
        ConfiguredPlacement customConfig = Placement.COUNT_RANGE
                .configure(new CountRangeConfig(30, 0, 0, 64));
        biomeIn.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.ORE
                .withConfiguration(new OreFeatureConfig(OreFeatureConfig.FillerBlockType.NATURAL_STONE, ModBlocks.COPPER_ORE.getDefaultState(), 10))
                .withPlacement(customConfig));
    }
}
