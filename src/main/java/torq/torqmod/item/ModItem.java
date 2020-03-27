package torq.torqmod.item;

import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import torq.torqmod.Reference;
import torq.torqmod.TorqMod;
import torq.torqmod.block.ModBlocks;

/**
 * @author torq
 * @version 0.1
 */
@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class ModItem {

    @SubscribeEvent
    public static void registerItems(RegistryEvent.Register<Item> event) {

        final Item[] items = {
                new Item(new Item.Properties().group(ModItemGroup.TORQMOD_MISC)).setRegistryName(Reference.MOD_ID,
                        "copper_ingot"),
                new Item((new Item.Properties()).group(ModItemGroup.TORQMOD_MISC)).setRegistryName(Reference.MOD_ID,
                        "copper_nugget"),
                new Item(new Item.Properties().group(ModItemGroup.TORQMOD_MISC).maxStackSize(64))
                        .setRegistryName(Reference.MOD_ID, "glowstone_emerald_dust"),
                new Item(new Item.Properties().group(ModItemGroup.TORQMOD_MISC).maxStackSize(64))
                        .setRegistryName(Reference.MOD_ID, "glowstone_diamond_dust"),
                new Item(new Item.Properties().group(ModItemGroup.TORQMOD_MISC).maxStackSize(64))
                        .setRegistryName(Reference.MOD_ID, "glowstone_prismarine_dust"),
        };

        event.getRegistry().registerAll(items);

        TorqMod.LOGGER.info("items registered");
    }

    @SubscribeEvent
    public static void registerItemsFood(RegistryEvent.Register<Item> event) {


        final Item[] itemsFood = {
                new Item(new Item.Properties().group(ModItemGroup.TORQMOD_FOOD)
                        .food(new Food.Builder().hunger(8).saturation(0.3F).setAlwaysEdible().build()))
                        .setRegistryName(Reference.MOD_ID, "apple_pie")
        };

        event.getRegistry().registerAll(itemsFood);

        TorqMod.LOGGER.info("itemsFood registered");
    }

    @SubscribeEvent
    public static void registerItemsBlock(RegistryEvent.Register<Item> event) {

        final Item[] itemsBlock = {
                new BlockItem(ModBlocks.COPPER_ORE,
                        new Item.Properties().group(ModItemGroup.TORQMOD_BUILDING_BLOCKS).maxStackSize(64))
                        .setRegistryName(ModBlocks.COPPER_ORE.getRegistryName()),
                new BlockItem(ModBlocks.COPPER_BLOCK,
                        new Item.Properties().group(ModItemGroup.TORQMOD_BUILDING_BLOCKS).maxStackSize(64))
                        .setRegistryName(ModBlocks.COPPER_BLOCK.getRegistryName()),
                new BlockItem(ModBlocks.GLOWSTONE_EMERALD,
                        new Item.Properties().group(ModItemGroup.TORQMOD_BUILDING_BLOCKS).maxStackSize(64))
                        .setRegistryName(ModBlocks.GLOWSTONE_EMERALD.getRegistryName()),
                new BlockItem(ModBlocks.GLOWSTONE_DIAMOND,
                        new Item.Properties().group(ModItemGroup.TORQMOD_BUILDING_BLOCKS).maxStackSize(64))
                        .setRegistryName(ModBlocks.GLOWSTONE_DIAMOND.getRegistryName()),
                new BlockItem(ModBlocks.GLOWSTONE_PRISMARINE,
                        new Item.Properties().group(ModItemGroup.TORQMOD_BUILDING_BLOCKS).maxStackSize(64))
                        .setRegistryName(ModBlocks.GLOWSTONE_PRISMARINE.getRegistryName())
        };

        event.getRegistry().registerAll(itemsBlock);

        TorqMod.LOGGER.info("itemsBlock registered");
    }

    @SubscribeEvent
    public static void registerItemsTool(RegistryEvent.Register<Item> event) {

        final Item[] itemsTool = {
                new SwordItem(ModItemTier.COPPER, 3, -2.4F,
                        (new Item.Properties()).group(ModItemGroup.TORQMOD_COMBAT)).setRegistryName(Reference.MOD_ID,
                        "copper_sword"),
                new PickaxeItem(ModItemTier.COPPER, 1, -2.8F,
                        (new Item.Properties()).group(ModItemGroup.TORQMOD_TOOLS)).setRegistryName(Reference.MOD_ID,
                        "copper_pickaxe"),
                new ShovelItem(ModItemTier.COPPER, 1.5F, -3.0F,
                        (new Item.Properties()).group(ModItemGroup.TORQMOD_TOOLS)).setRegistryName(Reference.MOD_ID,
                        "copper_shovel"),
                new AxeItem(ModItemTier.COPPER, 6.0F, -3.1F,
                        (new Item.Properties()).group(ModItemGroup.TORQMOD_TOOLS)).setRegistryName(Reference.MOD_ID,
                        "copper_axe"),
                new HoeItem(ModItemTier.COPPER, -1.0F, (new Item.Properties()).group(ModItemGroup.TORQMOD_TOOLS))
                        .setRegistryName(Reference.MOD_ID, "copper_hoe"),
                new ModBowItem((new ModBowItem.Properties()).pullingTime(50.0F)
                        .arrowSettings(new ArrowSettings((new ArrowSettings.Properties()).maxArrowVelocity(2.0F))),
                        (new Item.Properties()).maxDamage(384).group(ModItemGroup.TORQMOD_COMBAT))
                        .setRegistryName(Reference.MOD_ID, "longbow")
        };

        event.getRegistry().registerAll(itemsTool);

        TorqMod.LOGGER.info("itemsTool registered");
    }

    @SubscribeEvent
    public static void registerItemsArmor(RegistryEvent.Register<Item> event) {

        final Item[] itemsArmor = {
                new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.HEAD,
                        (new Item.Properties()).group(ModItemGroup.TORQMOD_COMBAT)).setRegistryName(Reference.MOD_ID,
                        "copper_helmet"),
                new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.CHEST,
                        (new Item.Properties()).group(ModItemGroup.TORQMOD_COMBAT)).setRegistryName(Reference.MOD_ID,
                        "copper_chestplate"),
                new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.LEGS,
                        (new Item.Properties()).group(ModItemGroup.TORQMOD_COMBAT)).setRegistryName(Reference.MOD_ID,
                        "copper_leggings"),
                new ArmorItem(ModArmorMaterial.COPPER, EquipmentSlotType.FEET,
                        (new Item.Properties()).group(ModItemGroup.TORQMOD_COMBAT)).setRegistryName(Reference.MOD_ID,
                        "copper_boots")};

        event.getRegistry().registerAll(itemsArmor);

        TorqMod.LOGGER.info("itemsArmor registered");
    }

}
