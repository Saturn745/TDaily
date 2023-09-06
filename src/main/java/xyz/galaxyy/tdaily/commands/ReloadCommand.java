package xyz.galaxyy.tdaily.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.jetbrains.annotations.NotNull;
import xyz.galaxyy.tdaily.TDaily;

import java.util.List;

public class ReloadCommand extends Command {
    private final TDaily plugin;

    public ReloadCommand(TDaily plugin) {
        super("tdailyreload", "Reloads TDaily's configuration", "/tdailyreload", List.of("tdailyrl", "tdailyr", "tdailyreload"));
        this.setPermission("tdaily.reload");
        this.plugin = plugin;
    }

    @Override
    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        this.plugin.reloadConfig();
        sender.sendRichMessage("<green>Reloaded <white>TDaily's <green>configuration.");
        return true;
    }
}
