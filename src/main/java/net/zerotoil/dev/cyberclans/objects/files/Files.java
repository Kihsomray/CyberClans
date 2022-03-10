package net.zerotoil.dev.cyberclans.objects.files;

import net.zerotoil.dev.cyberclans.CyberClans;
import org.bukkit.configuration.Configuration;

import java.util.HashMap;

public class Files {

    private final CyberClans main;
    private HashMap<String, YAMLFile> files = new HashMap<>();
    private int counter = 0;

    public Files(CyberClans main) {
        this.main = main;
        loadFiles();
    }

    public void loadFiles() {
        if (!files.isEmpty()) files.clear();
        main.logger("&dLoading YAML files...");
        long startTime = System.currentTimeMillis();

        // front end
        addFile("config");
        addFile("lang");

        if (updateFile("config")) get("config").updateConfig();
        if (updateFile("lang")) get("lang").updateConfig();

        main.logger("&7Loaded &e" + counter + "&7 files in &a" +
                (System.currentTimeMillis() - startTime) + "ms&7.", ""
        );

    }

    private boolean updateFile(String name) {
        return getConfig("config").getBoolean("config.auto-update." + name, false);
    }

    private void addFile(String file) {
        counter++;
        files.put(file, new YAMLFile(main, file));
        files.get(file).reloadConfig();
        main.logger("&7Loaded file &e" + file + ".yml&7.");
    }

    public void addFile(String file, String folder) {
        counter++;
        files.put(file, new YAMLFile(main, file, folder));
        files.get(file).reloadConfig();
        main.logger("&7Loaded file &e" + file + ".yml&7 in &e" + folder + "&7 folder.");
    }

    public HashMap<String, YAMLFile> getFiles() { return this.files; }
    public YAMLFile get(String file){  return files.get(file); }
    public Configuration getConfig(String file) { return files.get(file).getConfig(); }
}
