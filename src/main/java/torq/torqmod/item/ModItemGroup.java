package torq.torqmod.item;

import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import torq.torqmod.block.ModBlocks;

/**
 * 
 * 
 * @author torq
 * @version 0.1
 */
public abstract class ModItemGroup extends ItemGroup {

	public static final ItemGroup TORQMOD_BUILDING_BLOCKS = (new ItemGroup("torqmodBuildingBlocks") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(ModBlocks.GLOWSTONE_EMERALD);
		}
	});

	public static final ItemGroup TORQMOD_MISC = (new ItemGroup("torqmodMisc") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(ModItems.GLOWSTONE_EMERALD_DUST);
		}
	});

	public static final ItemGroup TORQMOD_FOOD = (new ItemGroup("torqmodFood") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(ModItems.APPLE_PIE);
		}
	});
	
	public static final ItemGroup TORQMOD_TOOLS = (new ItemGroup("torqmodTools") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(ModItems.COPPER_AXE);
		}
	});
	
	public static final ItemGroup TORQMOD_COMBAT = (new ItemGroup("torqmodCombat") {
		@OnlyIn(Dist.CLIENT)
		public ItemStack createIcon() {
			return new ItemStack(ModItems.COPPER_SWORD);
		}
	});

	public ModItemGroup(String label) {
		super(label);
	}

	public ModItemGroup(int index, String label) {
		super(index, label);
	}

}
