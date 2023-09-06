package xyz.galaxyy.tdaily.commands;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import net.kyori.adventure.text.minimessage.MiniMessage;
import net.kyori.adventure.text.minimessage.tag.resolver.Placeholder;
import net.kyori.adventure.text.minimessage.tag.resolver.TagResolver;
import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;
import xyz.galaxyy.tdaily.TDaily;

public class DailyCommand extends Command {
    private final TDaily plugin;

    public DailyCommand(TDaily plugin) {
        super("daily", "Claim your daily crate", "/daily", List.of("claimdaily", "dailyreward", "dailyclaim"));
        this.setPermission("tdaily.claim");
        this.plugin = plugin;
    }

    public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
        if (!(sender instanceof Player player)) {
            return false;
        }

        long currentTime = System.currentTimeMillis();
        Long lastExecutedTime = player.getPersistentDataContainer().get(this.plugin.KEY, PersistentDataType.LONG);

        if (lastExecutedTime != null && currentTime - lastExecutedTime < 86400000L) {
            long timeRemaining = 86400000L - (currentTime - lastExecutedTime);
            long hours = timeRemaining / 3600000L % 24L;
            long minutes = timeRemaining / 60000L % 60L;
            long seconds = timeRemaining / 1000L % 60L;
            player.sendMessage(MiniMessage.miniMessage().deserialize(
                    Objects.requireNonNull(this.plugin.getConfig().getString("messages.cooldown")),
                    Placeholder.parsed("hours", String.valueOf(hours)),
                    Placeholder.parsed("minutes", String.valueOf(minutes)),
                    Placeholder.parsed("seconds", String.valueOf(seconds))));
            return true;
        }

        player.getPersistentDataContainer().set(this.plugin.KEY, PersistentDataType.LONG, currentTime);
        executeCommands(player);
        player.sendMessage(MiniMessage.miniMessage().deserialize(Objects.requireNonNull(this.plugin.getConfig().getString("messages.claimed")), Placeholder.parsed("player", player.getName())));

        return true;
    }

    private void executeCommands(Player player) {
        List<String> commandsToExecute = new ArrayList<>();
        List<Map<?, ?>> commands = this.plugin.getConfig().getMapList("commands");

        for (Map<?, ?> commandMap : commands) {
            String commandName = (String) commandMap.get("name");
            List<String> subCommandsToRun = (List<String>) commandMap.get("commands");
            Boolean permission = (Boolean) commandMap.get("permission");

            if (permission == null || !permission || player.hasPermission("tdaily.claim." + commandName)) {
                commandsToExecute.addAll(subCommandsToRun);
            }
        }

        if (commandsToExecute.isEmpty()) {
            player.sendMessage(MiniMessage.miniMessage().deserialize(Objects.requireNonNull(this.plugin.getConfig().getString("messages.no-rewards"))));
            return;
        }

        for (String command : commandsToExecute) {
            Bukkit.dispatchCommand(Bukkit.getConsoleSender(), command.replaceAll("<player>", player.getName()));
        }
    }
}

