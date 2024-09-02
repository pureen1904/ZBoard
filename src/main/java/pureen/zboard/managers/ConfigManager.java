package pureen.zboard.managers;

import pureen.zboard.ZBoard;
import pureen.zboard.configs.Config;
import pureen.zboard.configs.ScoreboardConfig;

import java.util.ArrayList;
import java.util.List;

public class ConfigManager {

    private ZBoard zBoard;
    private List<Config> configs = new ArrayList<>();
    private ScoreboardConfig scoreboardConfig;

    public ConfigManager(ZBoard zBoard) {
        this.zBoard = zBoard;
        configs.add(scoreboardConfig = new ScoreboardConfig(zBoard));
    }

    public void loadConfigs() {
        for (Config config : configs) {
            config.loadConfig();
        }
    }

    public void saveConfigs() {
        for (Config config : configs) {
            config.saveConfig();
        }
    }

    public ScoreboardConfig getScoreboardConfig() {
        return scoreboardConfig;
    }
}
