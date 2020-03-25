package torq.torqmod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import torq.torqmod.item.IFOVUpdateItem;

public class ClientEventHandler {
    public static ClientEventHandler INSTANCE = new ClientEventHandler();

    @SubscribeEvent
    public void handleFOVUpdateEvent(FOVUpdateEvent event) {
        ItemStack stack = event.getEntity().getActiveItemStack();
        PlayerEntity playerEntity = event.getEntity();

        if (stack.getItem() instanceof IFOVUpdateItem) {
            IFOVUpdateItem fovUpdateItem = (IFOVUpdateItem) stack.getItem();
            event.setNewfov(event.getFov() - fovUpdateItem.getFOVMod(stack, playerEntity));
        }
    }
}
