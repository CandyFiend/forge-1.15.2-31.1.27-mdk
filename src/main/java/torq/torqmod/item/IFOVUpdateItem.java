package torq.torqmod.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;

public interface IFOVUpdateItem {

    float getFOVMod(ItemStack stack, PlayerEntity player);

}
