package torq.torqmod.item;

import java.util.function.Supplier;
import net.minecraft.item.IItemTier;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.LazyValue;

/**
 * 
 * 
 * @author torq
 * @version 0.1
 */
public enum ModItemTier implements IItemTier {
	COPPER(2, 200, 5.0F, 1.5F, 12, () -> {
		return Ingredient.fromItems(ModItems.COPPER_INGOT);
	});

	/**
	 * The level of material this tool can harvest (3 = DIAMOND, 2 = IRON, 1 =
	 * STONE, 0 = WOOD/GOLD)
	 */
	private final int harvestLevel;
	/**
	 * The number of uses this material allows. (wood = 59, stone = 131, iron = 250,
	 * diamond = 1561, gold = 32)
	 */
	private final int maxUses;
	/**
	 * The strength of this tool material against blocks which it is effective
	 * against.
	 */
	private final float efficiency;
	/** Damage versus entities. */
	private final float attackDamage;
	/** Defines the natural enchantability factor of the material. */
	private final int enchantability;
	private final LazyValue<Ingredient> repairMaterial;

	private ModItemTier(int harvestLevelIn, int maxUsesIn, float efficiencyIn, float attackDamageIn,
			int enchantabilityIn, Supplier<Ingredient> repairMaterialIn) {
		this.harvestLevel = harvestLevelIn;
		this.maxUses = maxUsesIn;
		this.efficiency = efficiencyIn;
		this.attackDamage = attackDamageIn;
		this.enchantability = enchantabilityIn;
		this.repairMaterial = new LazyValue<>(repairMaterialIn);
	}

	@Override
	public int getMaxUses() {
		return this.maxUses;
	}

	@Override
	public float getEfficiency() {
		return this.efficiency;
	}

	@Override
	public float getAttackDamage() {
		return this.attackDamage;
	}

	@Override
	public int getHarvestLevel() {
		return this.harvestLevel;
	}

	@Override
	public int getEnchantability() {
		return this.enchantability;
	}

	@Override
	public Ingredient getRepairMaterial() {
		return this.repairMaterial.getValue();
	}

}
