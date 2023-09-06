package xyz.galaxyy.tdaily.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.jetbrains.annotations.NotNull;
import xyz.galaxyy.tdaily.TDaily;

import java.util.ArrayList;
import java.util.List;

public class ResetDailyCommand extends Command {
    private final TDaily plugin;
    public ResetDailyCommand(TDaily plugin) {
        super("resetdaily", "Resets your /daily cooldown", "/resetdaily", List.of());
        this.plugin = plugin;
        this.setPermission("tdaily.reset");
    }

    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (sender instanceof Player player) {
            if (args.length < 1) {
                player.getPersistentDataContainer().remove(this.plugin.KEY);
                sender.sendRichMessage("<green>Reset <white>your <green>daily cooldown.");
                return true;
            }
        }

        if (args.length == 1) {
            if (args[0].equals("@a")) {

                for (Player target : Bukkit.getOnlinePlayers()) {
                    if (target.getPersistentDataContainer().has(this.plugin.KEY, PersistentDataType.LONG)) {
                        target.getPersistentDataContainer().remove(this.plugin.KEY);
                    }
                }

                sender.sendRichMessage("<green>Reset <yellow>everyone's <green>daily cooldown.");

                return true;
            }

            Player target = Bukkit.getPlayerExact(args[0]);
            if (target == null) {
                sender.sendRichMessage("<red>Player <yellow>" + args[0] + " <red>not found.");
                return true;
            }
            if (target.getPersistentDataContainer().has(this.plugin.KEY, PersistentDataType.LONG)) {
                target.getPersistentDataContainer().remove(this.plugin.KEY);
                sender.sendRichMessage("<green>Reset <yellow>" + target.getName() + "'s <green>daily cooldown.");
            }
        }

        return true;
    }

    @NotNull
    public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
        List<String> completions = new ArrayList<>();
        if (args.length == 1) {
            String search = args[0].toLowerCase();
            if ("@a".startsWith(search)) {
                completions.add("@a");
            }

            for (Player player : Bukkit.getOnlinePlayers()) {
                String playerName = player.getName();
                if (playerName.toLowerCase().startsWith(search)) {
                    completions.add(playerName);
                }
            }
        }

        return completions;
    }
}