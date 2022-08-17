package josegamerpt.cronplaceholderapi.cronplaceholderapi;

import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.parser.CronParser;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

import static com.cronutils.model.CronType.QUARTZ;

public final class CronPlaceholderAPI extends JavaPlugin {

    private CronParser parser;

    @Override
    public void onEnable() {
        CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);
        parser = new CronParser(cronDefinition);

        new CronHook(this).register();
        Bukkit.getLogger().log(Level.INFO, "INFO CronPlaceholderAPI - Hook registado! por JoseGamer_PT v1.0.2");
    }

    @Override
    public void onDisable() { }

    public CronParser getParser() {
        return this.parser;
    }
}
