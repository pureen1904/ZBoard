package pureen.zboard.configs;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;
import pureen.zboard.ZBoard;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import static pureen.zboard.Utils.log;

public abstract class Config extends YamlConfiguration {

    protected ZBoard main;
    protected String name;
    protected File file;

    public Config(ZBoard main, String name) {
        this.main = main;
        this.name = name;
        file = new File(main.getDataFolder(), name);
    }

    private void checkFile() {
        if (!file.exists()) {
            file.getParentFile().mkdirs();
            main.saveResource(name, false);
        }
    }

    public Set<String> getSection(String path) {
        ConfigurationSection section = getConfigurationSection(path);
        if (section != null) return section.getKeys(false);
        return new HashSet<>();
    }

    public void loadConfig() {
        try {
            load(file);
            log("Datalar " + name + " dosyasından alındı!");
        } catch (InvalidConfigurationException | IOException exception) {
            exception.printStackTrace();
            log(name + " dosyasından data alınamadı!!!");
        }
    }

    public void saveConfig() {
        try {
            save(file);
            log(name + " dosyası kaydedildi!!");
        } catch (IOException exception) {
            exception.printStackTrace();
            log(name + " dosyası kaydedilemedi!!");
        }
    }

}
