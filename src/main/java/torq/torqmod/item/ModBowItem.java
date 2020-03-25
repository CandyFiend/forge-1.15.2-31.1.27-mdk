package torq.torqmod.item;

import java.util.function.Predicate;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.AbstractArrowEntity;
import net.minecraft.item.*;
import net.minecraft.stats.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.SoundEvents;
import net.minecraft.world.World;
import net.minecraftforge.fml.common.Mod;
import torq.torqmod.TorqMod;

/**
 *
 *
 * @author torq
 * @version 0.1
 */
public class ModBowItem extends ShootableItem {

    private final float pullingTime;
    private final float maxArrowVelocity;
    private final double arrowDamage;

    public ModBowItem(ModBowItem.Properties properties, Item.Properties builder) {
        super(builder);

        this.pullingTime = properties.pullingTime;
        this.maxArrowVelocity = properties.maxArrowVelocity;
        this.arrowDamage = properties.arrowDamage;

        this.addPropertyOverride(new ResourceLocation("pull"), (itemStackIn, worldIn, livingEntityIn) -> {
            if (livingEntityIn == null) {
                return 0.0F;
            } else {
                /**
                 * itemStackIn.getUseDuration() - livingEntityIn.getItemInUseCount() how much time has passed
                 * this.getPullingTime() how much time should pass
                 * (itemStackIn.getUseDuration() - livingEntityIn.getItemInUseCount()) / this.getPullingTime() = 1+ when the right amount of time has passed
                 */
                return !(this.getClass().isInstance(livingEntityIn.getActiveItemStack().getItem())) ? 0.0F : (float) (itemStackIn.getUseDuration() - livingEntityIn.getItemInUseCount()) / this.getPullingTime();
            }
        });
        this.addPropertyOverride(new ResourceLocation("pulling"), (itemStackIn, worldIn, livingEntityIn) -> {
            return livingEntityIn != null && livingEntityIn.isHandActive() && livingEntityIn.getActiveItemStack() == itemStackIn ? 1.0F : 0.0F;
        });
    }

    /**
     * Called when the player stops using an Item (stops holding the right mouse button).
     */
    public void onPlayerStoppedUsing(ItemStack stack, World worldIn, LivingEntity entityLiving, int timeLeft) {
        if (entityLiving instanceof PlayerEntity) {
            PlayerEntity playerentity = (PlayerEntity) entityLiving;
            boolean infinityArrowFlag = playerentity.abilities.isCreativeMode || EnchantmentHelper.getEnchantmentLevel(Enchantments.INFINITY, stack) > 0;
            ItemStack itemstack = playerentity.findAmmo(stack);

            int i = this.getUseDuration(stack) - timeLeft;
            i = net.minecraftforge.event.ForgeEventFactory.onArrowLoose(stack, worldIn, playerentity, i, !itemstack.isEmpty() || infinityArrowFlag);
            if (i < 0) return;

            if (!itemstack.isEmpty() || infinityArrowFlag) {
                if (itemstack.isEmpty()) {
                    itemstack = new ItemStack(Items.ARROW);
                }

                float f = getArrowVelocity(i);
                if (!((double) f < 0.1D)) {
                    boolean flag1 = playerentity.abilities.isCreativeMode || (itemstack.getItem() instanceof ArrowItem && ((ArrowItem) itemstack.getItem()).isInfinite(itemstack, stack, playerentity));
                    if (!worldIn.isRemote) {
                        ArrowItem arrowitem = (ArrowItem) (itemstack.getItem() instanceof ArrowItem ? itemstack.getItem() : Items.ARROW);
                        AbstractArrowEntity abstractarrowentity = arrowitem.createArrow(worldIn, itemstack, playerentity);
                        abstractarrowentity = customeArrow(abstractarrowentity);
                        abstractarrowentity.setDamage(getArrowDamage());
                        abstractarrowentity.shoot(playerentity, playerentity.rotationPitch, playerentity.rotationYaw, 0.0F, f * 3.0F, 1.0F);
                        if (f == 1.0F) {
                            abstractarrowentity.setIsCritical(true);
                        }

                        int j = EnchantmentHelper.getEnchantmentLevel(Enchantments.POWER, stack);
                        if (j > 0) {
                            abstractarrowentity.setDamage(abstractarrowentity.getDamage() + (double) j * 0.5D + 0.5D);
                        }

                        int k = EnchantmentHelper.getEnchantmentLevel(Enchantments.PUNCH, stack);
                        if (k > 0) {
                            abstractarrowentity.setKnockbackStrength(k);
                        }

                        if (EnchantmentHelper.getEnchantmentLevel(Enchantments.FLAME, stack) > 0) {
                            abstractarrowentity.setFire(100);
                        }

                        stack.damageItem(1, playerentity, (p_220009_1_) -> {
                            p_220009_1_.sendBreakAnimation(playerentity.getActiveHand());
                        });
                        if (flag1 || playerentity.abilities.isCreativeMode && (itemstack.getItem() == Items.SPECTRAL_ARROW || itemstack.getItem() == Items.TIPPED_ARROW)) {
                            abstractarrowentity.pickupStatus = AbstractArrowEntity.PickupStatus.CREATIVE_ONLY;
                        }

                        worldIn.addEntity(abstractarrowentity);
                    }

                    worldIn.playSound((PlayerEntity) null, playerentity.getPosX(), playerentity.getPosY(), playerentity.getPosZ(), SoundEvents.ENTITY_ARROW_SHOOT, SoundCategory.PLAYERS, 1.0F, 1.0F / (random.nextFloat() * 0.4F + 1.2F) + f * 0.5F);
                    if (!flag1 && !playerentity.abilities.isCreativeMode) {
                        itemstack.shrink(1);
                        if (itemstack.isEmpty()) {
                            playerentity.inventory.deleteStack(itemstack);
                        }
                    }

                    playerentity.addStat(Stats.ITEM_USED.get(this));
                }
            }
        }
    }

    /**
     * Gets the velocity of the arrow entity from the bow's charge
     *
     * This method adjusts to the indicated arrow velocity and pulling time.
     * f=charge/pulling_time
     * if charge=pulling_time, f=1 (the bow have full charge -> the arrow need's to have max velocity)
     * (1*1+1*k)/3=max_arrow_velocity; (1+k)/3=max_arrow_velocity
     * k=max_arrow_velocity*3-1 (calculate k)
     */
    public float getArrowVelocity(int charge) {
        float f = (float) charge / this.getPullingTime();
        float k = getMaxArrowVelocity() * 3 - 1;
        f = (f * f + f * k) / 3.0F;
        if (f > getMaxArrowVelocity()) {
            f = getMaxArrowVelocity();
        }

        return f;
    }

    /**
     * How long it takes to use or consume an item
     */
    public int getUseDuration(ItemStack stack) { return 72000; }

    /**
     * returns the action that specifies what animation to play when the items is being used
     */
    public UseAction getUseAction(ItemStack stack) {
        return UseAction.BOW;
    }

    /**
     * Called to trigger the item's "innate" right click behavior. To handle when this item is used on a Block, see
     * {@link #onItemUse}.
     */
    public ActionResult<ItemStack> onItemRightClick(World worldIn, PlayerEntity playerIn, Hand handIn) {
        ItemStack itemstack = playerIn.getHeldItem(handIn);
        boolean haveAmmoFlag = !playerIn.findAmmo(itemstack).isEmpty();

        ActionResult<ItemStack> ret = net.minecraftforge.event.ForgeEventFactory.onArrowNock(itemstack, worldIn, playerIn, handIn, haveAmmoFlag);
        if (ret != null) return ret;

        if (!playerIn.abilities.isCreativeMode && !haveAmmoFlag) {
            return ActionResult.resultFail(itemstack);
        } else {
            playerIn.setActiveHand(handIn);
            return ActionResult.resultConsume(itemstack);
        }
    }

    /**
     * Get the predicate to match ammunition when searching the player's inventory, not their main/offhand
     */
    public Predicate<ItemStack> getInventoryAmmoPredicate() {
        return ARROWS;
    }

    public AbstractArrowEntity customeArrow(AbstractArrowEntity arrow) {
        return arrow;
    }

    public float getPullingTime() {
        return this.pullingTime;
    }

    public float getMaxArrowVelocity() {
        return this.maxArrowVelocity;
    }

    public double getArrowDamage() {
        return this.arrowDamage;
    }

    public static class Properties{
        /**
         * Default bow has 20.0F.
         */
        private float pullingTime = 20.0F;
        /**
         * Default bow has 1.0F.
         */
        private float maxArrowVelocity = 1.0F;
        /**
         * Default arrow has 2.0D.
         */
        private double arrowDamage = 2.0D;

        public ModBowItem.Properties pullingTime(float pullingTimeIn) {
            this.pullingTime = pullingTimeIn;
            return this;
        }

        public  ModBowItem.Properties maxArrowVelocity(float maxArrowVelocityIn) {
            this.maxArrowVelocity = maxArrowVelocityIn;
            return this;
        }

        public ModBowItem.Properties arrowDamage(double arrowDamageIn) {
            this.arrowDamage = arrowDamageIn;
            return this;
        }
    }
}