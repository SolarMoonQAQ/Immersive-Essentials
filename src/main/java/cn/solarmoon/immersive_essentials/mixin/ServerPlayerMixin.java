package cn.solarmoon.immersive_essentials.mixin;

import cn.solarmoon.immersive_essentials.api.ILevelData;
import cn.solarmoon.immersive_essentials.common.IECapabilities;
import cn.solarmoon.immersive_essentials.common.capability.serializable.SpawnData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public class ServerPlayerMixin {

    /**
     * 使得首次进入游戏的玩家也能强制移动到spawn处
     */
    @Inject(method = "fudgeSpawnLocation", at = @At("HEAD"), cancellable = true)
    public void firstSpawn(ServerLevel level, CallbackInfo ci) {
        ServerPlayer player = (ServerPlayer)(Object)this;
        ILevelData levelData = IECapabilities.getLevelData(level.getServer());
        SpawnData spawnData = levelData.getSpawnData();
        if (spawnData.isValid()) {
            player.moveTo(spawnData.getPos(), spawnData.getYaw(), spawnData.getPitch());
            ci.cancel();
        }
    }

}
