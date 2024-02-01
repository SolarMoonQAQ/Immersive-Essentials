package cn.solarmoon.immersive_essentials.common;

import cn.solarmoon.immersive_essentials.Core;
import cn.solarmoon.immersive_essentials.common.event.SpawnEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;

@Mod.EventBusSubscriber(modid = Core.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class IECommonEvents {

    //服务端事件订阅
    @SubscribeEvent
    public static void onFMLCommonSetupEvent(final FMLCommonSetupEvent event) {
        MinecraftForge.EVENT_BUS.register(new SpawnEvent());
    }

}
