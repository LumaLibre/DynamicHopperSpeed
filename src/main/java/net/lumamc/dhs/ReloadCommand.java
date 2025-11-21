package net.lumamc.dhs;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;

public class ReloadCommand implements CommandExecutor {

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String @NotNull [] args) {
        Config config = DynamicHopperSpeed.getDynamicConfig();
        config.load(true);
        DynamicHopperSpeed.getInstance().instate();
        sender.sendMessage("Reloaded DynamicHopperSpeed configuration and applied changes.");
        return true;
    }
}
