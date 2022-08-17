package josegamerpt.cronplaceholderapi.cronplaceholderapi;

import com.cronutils.model.Cron;
import com.cronutils.model.time.ExecutionTime;
import org.bukkit.OfflinePlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;

import java.time.Duration;
import java.time.ZonedDateTime;
import java.util.Optional;

public class CronHook extends PlaceholderExpansion {

    private CronPlaceholderAPI main;

    public CronHook(CronPlaceholderAPI m) {
        this.main = m;
    }

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
        return "1.0.2";
    }
    
    @Override
    public String onRequest(OfflinePlayer player, String params) {
        if (params.startsWith("ctdwn")) {
            String codigo = params.split("-")[1];
            codigo = codigo.replace("_", " ");

            Cron quartzCron = main.getParser().parse(codigo);

            ZonedDateTime now = ZonedDateTime.now();
            ExecutionTime executionTime = ExecutionTime.forCron(quartzCron);

            // Get date for last execution
            //Optional<ZonedDateTime> lastExecution = executionTime.lastExecution(now);

            // Get date for next execution
            //Optional<ZonedDateTime> nextExecution = executionTime.nextExecution(now);

            // Time from last execution
            //Optional<Duration> timeFromLastExecution = executionTime.timeFromLastExecution(now);

            // Time to next execution
            Optional<Duration> timeToNextExecution = executionTime.timeToNextExecution(now);

            long seconds = timeToNextExecution.get().getSeconds();

            long HH = seconds / 3600;
            long MM = (seconds % 3600) / 60;
            long SS = seconds % 60;
            
            //calculo de dias em horas
            int dias = 0;

            while (HH >= 24) {
                ++dias;
                HH -= 24;
            }

            String ret = "";

            if (dias > 0) {
                ret += dias + "d, ";
            }
            if (HH > 0) {
                ret += HH + "h, ";
            }
            if (MM > 0) {
                ret += MM + "m, ";
            }

            ret += SS + "s";

            return ret;
        }
        return null;
    }
}