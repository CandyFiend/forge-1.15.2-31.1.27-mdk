package torq.torqmod.block;

import net.minecraft.block.Block;
import net.minecraft.block.OreBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.material.MaterialColor;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import torq.torqmod.Reference;
import torq.torqmod.TorqMod;

/**
 * 
 * 
 * @author torq
 * @version 0.1
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModBlock {

	@SubscribeEvent
	public static void registerBlocks(RegistryEvent.Register<Block> event) {
		
		final Block[] blocks = {
				new Block(Block.Properties.create(Material.IRON, MaterialColor.GOLD).hardnessAndResistance(3.0F, 6.0F)
						.sound(SoundType.METAL)).setRegistryName(Reference.MOD_ID, "copper_block")
		};
		
		event.getRegistry().registerAll(blocks);
		
		TorqMod.LOGGER.info("blocks registered");
	}
	
	@SubscribeEvent
	public static void registerBlocksGlowstone(RegistryEvent.Register<Block> event) {
		
		final Block[] blocksGlowstone = {
				new Block(Block.Properties.create(Material.GLASS, MaterialColor.SAND)
						.hardnessAndResistance(0.3F).sound(SoundType.GLASS).lightValue(15))
								.setRegistryName(Reference.MOD_ID, "glowstone_emerald"),
				new Block(Block.Properties.create(Material.GLASS, MaterialColor.SAND)
						.hardnessAndResistance(0.3F).sound(SoundType.GLASS).lightValue(15))
								.setRegistryName(Reference.MOD_ID, "glowstone_diamond"),
				new Block(Block.Properties.create(Material.GLASS, MaterialColor.SAND)
						.hardnessAndResistance(0.3F).sound(SoundType.GLASS).lightValue(15))
								.setRegistryName(Reference.MOD_ID, "glowstone_prismarine")
		};
		
		event.getRegistry().registerAll(blocksGlowstone);
		
		TorqMod.LOGGER.info("blocksGlowstone registered");
	}
	
	@SubscribeEvent
	public static void registerBlocksOre(RegistryEvent.Register<Block> event) {
		
		final Block[] blocksOre = {
				new OreBlock(Block.Properties.create(Material.ROCK).hardnessAndResistance(3.0F, 3.0F))
				.setRegistryName(Reference.MOD_ID, "copper_ore")
		};
		
		event.getRegistry().registerAll(blocksOre);
		
		TorqMod.LOGGER.info("blocksOre registered");
	}	
}
