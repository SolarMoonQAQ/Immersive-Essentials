package cn.solarmoon.immersive_essentials.common.event;

import cn.solarmoon.immersive_essentials.api.ILevelData;
import cn.solarmoon.immersive_essentials.common.IECapabilities;
import cn.solarmoon.immersive_essentials.common.capability.serializable.SpawnData;
import cn.solarmoon.immersive_essentials.util.TeleportUtil;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;

public class SpawnEvent {

    /**
     * 重生时若没有设置床重生点则重生到spawn处
     */
    @SubscribeEvent
    public void respawn(PlayerEvent.PlayerRespawnEvent event) {
        Player player = event.getEntity();
        if (player instanceof ServerPlayer sp) {
            ILevelData levelData = IECapabilities.getLevelData(sp.server);
            SpawnData spawnData = levelData.getSpawnData();
            if (spawnData.isValid() && sp.getRespawnPosition() == null) {
                TeleportUtil.tp(sp, spawnData);
            }
        }
    }

    @SubscribeEvent
    public void onPlayerJoin(PlayerEvent.PlayerLoggedInEvent event) {
        Player player = event.getEntity();
        CompoundTag tag = player.getPersistentData();
        boolean isFirst = !tag.getBoolean("firstJoin");
        if (isFirst && player instanceof ServerPlayer sp) {
            ILevelData levelData = IECapabilities.getLevelData(sp.server);
            SpawnData spawnData = levelData.getSpawnData();
            if (spawnData.isValid()) TeleportUtil.tp(sp, spawnData);
            tag.putBoolean("firstJoin", true);
        }
    }

}
