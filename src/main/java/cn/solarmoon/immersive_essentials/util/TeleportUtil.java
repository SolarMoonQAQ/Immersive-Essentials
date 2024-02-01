package cn.solarmoon.immersive_essentials.util;

import cn.solarmoon.immersive_essentials.api.util.TextUtil;
import cn.solarmoon.immersive_essentials.common.capability.serializable.SpawnData;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;

public class TeleportUtil {

    /**
     * 保持视角传送到方块中心
     */
    public static void tp(Player player, BlockPos pos) {
        player.teleportTo(pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5);
    }

    /**
     * 保持视角传送到方块中心
     * 指定维度
     */
    public static void tp(ServerPlayer player, BlockPos pos, ServerLevel level) {
        player.teleportTo(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, player.getYRot(), player.getXRot());
    }

    /**
     * 保持视角传送到方块中心
     * 指定维度 和 视角
     */
    public static void tp(ServerPlayer player, BlockPos pos, ServerLevel level, float yRot, float xRot) {
        player.teleportTo(level, pos.getX() + 0.5, pos.getY(), pos.getZ() + 0.5, yRot, xRot);
    }

    public static void tp(ServerPlayer player, SpawnData spawnData) {
        tp(player, spawnData.getPos(), spawnData.getDimension(player), spawnData.getYaw(), spawnData.getPitch());
    }

}
