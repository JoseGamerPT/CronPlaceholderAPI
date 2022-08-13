package josegamerpt.cronplaceholderapi.cronplaceholderapi;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class CronPlaceholderAPI extends JavaPlugin {

    @Override
    public void onEnable() {
        new CronHook().register();
        Bukkit.getLogger().log(Level.INFO, "INFO CronPlaceholderAPI - Hook registado! por JoseGamer_PT");
    }

    @Override
    public void onDisable() { }
}
