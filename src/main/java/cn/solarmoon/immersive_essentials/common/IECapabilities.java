package cn.solarmoon.immersive_essentials.common;

import cn.solarmoon.immersive_essentials.Core;
import cn.solarmoon.immersive_essentials.api.ILevelData;
import cn.solarmoon.immersive_essentials.common.capability.LevelData;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

import javax.annotation.Nonnull;

public class IECapabilities {

    public static final Capability<ILevelData> LEVEL_DATA = CapabilityManager.get(new CapabilityToken<>(){});

    @SubscribeEvent
    public void registerCapabilities(RegisterCapabilitiesEvent event) {
        event.register(ILevelData.class);
    }

    @SubscribeEvent
    public void onAttachCapabilities(AttachCapabilitiesEvent<Level> event) {
        Level level = event.getObject();
        event.addCapability(new ResourceLocation(Core.MOD_ID, "level_data"), new LevelData(level));
    }

    /**
     * 获取levelData，这里限定overworld目的是只从一种世界中获得data，否则data可能会因为世界不同而不同
     * （也就是每个世界单独享有各自出生点）
     */
    public static ILevelData getLevelData(MinecraftServer server) {
        return getCapability(server.overworld(), LEVEL_DATA).orElse(null);
    }

    @Nonnull
    public static <T> LazyOptional<T> getCapability(ICapabilityProvider provider, Capability<T> cap) {
        if (provider == null || cap == null) {
            return LazyOptional.empty();
        }
        return provider.getCapability(cap);
    }

    @Nonnull
    public static <T> LazyOptional<T> getCapability(ICapabilityProvider provider, Capability<T> cap, Direction side) {
        if (provider == null || cap == null) {
            return LazyOptional.empty();
        }
        return provider.getCapability(cap, side);
    }

}
