package cn.solarmoon.immersive_essentials.common.command;

import cn.solarmoon.immersive_essentials.api.ILevelData;
import cn.solarmoon.immersive_essentials.api.command.BaseCommand;
import cn.solarmoon.immersive_essentials.api.util.TextUtil;
import cn.solarmoon.immersive_essentials.common.IECapabilities;
import cn.solarmoon.immersive_essentials.common.capability.serializable.SpawnData;
import cn.solarmoon.immersive_essentials.util.TeleportUtil;
import com.mojang.brigadier.Command;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;

public class Spawn extends BaseCommand {

    public Spawn() {
        super("spawn", 2, true);
    }

    @Override
    public void putExecution() {
        builder.executes(context -> spawn(context.getSource().getPlayerOrException()));
    }

    public int spawn(ServerPlayer player) {
        ServerLevel level = player.serverLevel();
        ILevelData levelData = IECapabilities.getLevelData(player.server);
        SpawnData spawnData = levelData.getSpawnData();
        if (spawnData.isValid()) {
            TeleportUtil.tp(player, spawnData);
        } else {
            TeleportUtil.tp(player, level.getSharedSpawnPos(), player.server.overworld());
        }
        TextUtil.sendTranslatableMessage(player, "command", "spawn_success");
        return Command.SINGLE_SUCCESS;
    }

}
