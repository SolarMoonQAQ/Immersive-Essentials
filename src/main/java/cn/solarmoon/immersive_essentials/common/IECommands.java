package cn.solarmoon.immersive_essentials.common;

import cn.solarmoon.immersive_essentials.api.command.BaseCommand;
import cn.solarmoon.immersive_essentials.common.command.SetSpawn;
import cn.solarmoon.immersive_essentials.common.command.Spawn;
import net.minecraftforge.event.RegisterCommandsEvent;

import java.util.ArrayList;
import java.util.List;

public class IECommands {

    public static void onCommandsRegister(final RegisterCommandsEvent event) {
        commands.add(new SetSpawn());
        commands.add(new Spawn());

        for (var command : commands) {
            if (command.isEnabled()) {
                event.getDispatcher().register(command.getBuilder());
            }
        }
    }

    public static List<BaseCommand> commands = new ArrayList<>();

}
