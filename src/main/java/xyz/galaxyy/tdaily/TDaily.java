package xyz.galaxyy.tdaily;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.galaxyy.tdaily.commands.DailyCommand;
import xyz.galaxyy.tdaily.commands.ReloadCommand;
import xyz.galaxyy.tdaily.commands.ResetDailyCommand;

public class TDaily extends JavaPlugin {
    public NamespacedKey KEY = new NamespacedKey(this, "lastran");
    public void onEnable() {
        this.saveDefaultConfig();
        this.getServer().getCommandMap().register("tdaily", new DailyCommand(this));
        this.getServer().getCommandMap().register("tdaily", new ResetDailyCommand(this));
        this.getServer().getCommandMap().register("tdaily", new ReloadCommand(this));
    }
}