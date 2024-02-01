package cn.solarmoon.immersive_essentials.common.command;

import cn.solarmoon.immersive_essentials.api.ILevelData;
import cn.solarmoon.immersive_essentials.api.command.BaseCommand;
import cn.solarmoon.immersive_essentials.api.util.TextUtil;
import cn.solarmoon.immersive_essentials.common.IECapabilities;
import cn.solarmoon.immersive_essentials.common.capability.serializable.SpawnData;
import com.mojang.brigadier.Command;
import net.minecraft.ChatFormatting;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;

public class SetSpawn extends BaseCommand {

    public SetSpawn() {
        super("setspawn", 2, true);
    }

    @Override
    public void putExecution() {
        builder.executes(context -> setSpawn(context.getSource().getPlayerOrException()));
    }

    public int setSpawn(ServerPlayer player) {
        ILevelData levelData = IECapabilities.getLevelData(player.server);
        SpawnData spawnData = levelData.getSpawnData();
        spawnData.put(player);

        record(spawnData, player);

        return Command.SINGLE_SUCCESS;
    }

    public void record(SpawnData spawnData, ServerPlayer player) {
        BlockPos spawnPos = spawnData.getPos();
        String dimension = spawnData.getDimension(player).dimension().location().toString();
        TextUtil.sendTranslatableMessage(player, "command", "setspawn_success", ChatFormatting.BLUE, spawnPos.getX(), spawnPos.getY(), spawnPos.getZ(), dimension);
    }

}
