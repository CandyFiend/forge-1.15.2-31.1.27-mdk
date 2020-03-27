package torq.torqmod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.MathHelper;

/**
 *
 *
 * @author torq
 * @version 0.1
 */
public class FOVUpdateItem implements IFOVUpdateItem{
    private float zoomTime;
    private float zoomMultiplier;

    public FOVUpdateItem(FOVUpdateItem.Properties properties) {
        this.zoomTime = properties.zoomTime;
        this.zoomMultiplier = properties.zoomMultiplier;
    }

    public float getFOVMod(ItemStack stack, PlayerEntity player) {
        float progress = MathHelper.clamp(getItemInUseMaxCount(stack, player) / zoomTime, 0, 1.0F);
        return progress * progress * zoomMultiplier;
    }

    public float getItemInUseMaxCount(ItemStack stack, PlayerEntity player) {
        return stack.getUseDuration() - player.getItemInUseCount();
    }

    public static class Properties {
        private float zoomTime = 20.0F;
        private float zoomMultiplier = 0.15F;

        public FOVUpdateItem.Properties zoomTime(float zoomTimeIn) {
            this.zoomTime = zoomTimeIn;
            return this;
        }

        public FOVUpdateItem.Properties zoomMultiplier(float zoomMultiplierIn) {
            this.zoomMultiplier = zoomMultiplierIn;
            return this;
        }
    }
}
