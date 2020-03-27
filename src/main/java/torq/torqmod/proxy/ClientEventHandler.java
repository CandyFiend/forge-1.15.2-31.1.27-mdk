package torq.torqmod.proxy;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraftforge.client.event.FOVUpdateEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import torq.torqmod.Reference;
import torq.torqmod.TorqMod;
import torq.torqmod.item.IFOVUpdateItem;

@Mod.EventBusSubscriber(modid = Reference.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ClientEventHandler {

    public static final ClientEventHandler INSTANCE = new ClientEventHandler();

    @SubscribeEvent
    public static void handleFOVUpdateEvent(FOVUpdateEvent event) {
        ItemStack stack = event.getEntity().getActiveItemStack();
        PlayerEntity playerEntity = event.getEntity();
        if (stack.getItem() instanceof IFOVUpdateItem) {
            IFOVUpdateItem fovUpdateItem = (IFOVUpdateItem) stack.getItem();
            event.setNewfov(event.getFov() - fovUpdateItem.getFOVMod(stack, playerEntity));
        }
    }
}
