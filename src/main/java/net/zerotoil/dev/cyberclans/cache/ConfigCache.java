package net.zerotoil.dev.cyberclans.cache;

import net.zerotoil.dev.cyberclans.CyberClans;
import org.bukkit.configuration.ConfigurationSection;

import java.util.ArrayList;
import java.util.List;

public class ConfigCache {

    private final CyberClans main;
    private int clanCreationCooldown = 0;
    private int clanSwapCooldown = 0;
    private List<String> blacklistedClanNames = new ArrayList<>();

    public ConfigCache(CyberClans main) {
        this.main = main;

        loadSettings();
    }

    public void loadSettings() {
        ConfigurationSection section = main.files().getConfig("config").getConfigurationSection("config");
        clanCreationCooldown = section.getInt("clan-creation-cooldown", clanCreationCooldown);
        clanSwapCooldown = section.getInt("clan-swap-cooldown", clanSwapCooldown);
        blacklistedClanNames = section.getStringList("clan-names-blacklist");
    }

    public int getClanCreationCooldown() { return clanCreationCooldown; }
    public int getClanSwapCooldown() { return clanSwapCooldown; }
    public List<String> getBlacklistedClanNames() {
        return blacklistedClanNames;
    }
}
