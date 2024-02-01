package cn.solarmoon.immersive_essentials;

import cn.solarmoon.immersive_essentials.common.IECapabilities;
import cn.solarmoon.immersive_essentials.common.IECommands;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(Core.MOD_ID)
public class ImmersiveEssentials {

    public ImmersiveEssentials() {
        IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();

        //forge能力
        MinecraftForge.EVENT_BUS.register(new IECapabilities());

        //指令
        MinecraftForge.EVENT_BUS.addListener(IECommands::onCommandsRegister);

        bus.addListener(this::loadComplete);
    }

    private void loadComplete(final FMLLoadCompleteEvent event) {

    }

}
