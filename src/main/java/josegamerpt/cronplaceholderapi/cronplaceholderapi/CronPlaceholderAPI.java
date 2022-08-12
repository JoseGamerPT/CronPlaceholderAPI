package josegamerpt.cronplaceholderapi.cronplaceholderapi;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class CronPlaceholderAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        new CronHook().register();
        Bukkit.getLogger().log(Level.INFO, "INFO - Cron Hook registado!");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
