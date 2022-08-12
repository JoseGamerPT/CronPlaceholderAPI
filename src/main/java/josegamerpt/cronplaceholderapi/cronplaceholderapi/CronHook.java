package josegamerpt.cronplaceholderapi.cronplaceholderapi;

import com.cronutils.model.Cron;
import com.cronutils.model.definition.CronDefinition;
import com.cronutils.model.definition.CronDefinitionBuilder;
import com.cronutils.model.time.ExecutionTime;
import com.cronutils.parser.CronParser;
import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Optional;

import static com.cronutils.model.CronType.QUARTZ;

public class CronHook extends PlaceholderExpansion {

    @Override
    public String getAuthor() {
        return "JoseGamer_PT";
    }
    
    @Override
    public String getIdentifier() {
        return "cronph";
    }

    @Override
    public String getVersion() {
        return "1.0.0";
    }
    
    @Override
    public String onRequest(OfflinePlayer player, String params) {

        if (params.startsWith("ctdwn")) {
            String codigo = params.split("-")[1];
            codigo = codigo.replace("_", " ");
            CronDefinition cronDefinition = CronDefinitionBuilder.instanceDefinitionFor(QUARTZ);

            CronParser parser = new CronParser(cronDefinition);
            Cron quartzCron = parser.parse(codigo);

            // Get date for last execution
            ZonedDateTime now = ZonedDateTime.now();
            ExecutionTime executionTime = ExecutionTime.forCron(quartzCron);
            Optional<ZonedDateTime> lastExecution = executionTime.lastExecution(now);

// Get date for next execution
            Optional<ZonedDateTime> nextExecution = executionTime.nextExecution(now);

// Time from last execution
            Optional<Duration> timeFromLastExecution = executionTime.timeFromLastExecution(now);

// Time to next execution
            Optional<Duration> timeToNextExecution = executionTime.timeToNextExecution(now);

            long seconds = timeToNextExecution.get().getSeconds();

            long HH = seconds / 3600;
            long MM = (seconds % 3600) / 60;
            long SS = seconds % 60;

            String ret = "";
            if (HH > 0) {
                ret += HH + "h, ";
            }
            if (MM > 0) {
                ret += MM + "m, ";
            }

            ret += SS + "s";

            return ret;
        }

        
        return null; // Placeholder is unknown by the Expansion
    }
}