package net.zerotoil.dev.cyberclans.objects;

import net.zerotoil.dev.cyberclans.CyberClans;
import org.bukkit.configuration.ConfigurationSection;

public class ClanType {

    private final CyberClans main;
    private final String type;

    // basic clan defaults
    private String displayName = "&7{player}'s Clan";
    private int maxPlayers = 100;
    private double creationCost = 1000;
    private String creationPermission = "clans.create.default";

    // default cooldowns
    private int kickAllCooldown = 3600;
    private int kickCooldown = 360;
    private int promoteCooldown = 0;
    private int demoteCooldown = 30;
    private boolean inheritPerms = true;

    public ClanType(CyberClans main, String type) {
        this.main = main;
        this.type = type;

        initSettings();
    }

    public void initSettings() {
        ConfigurationSection section = main.files().getConfig("clans").getConfigurationSection("clan-types");
        if (section == null) return;
        displayName = section.getString("default-display", displayName);
        maxPlayers = section.getInt("maximum-players", maxPlayers);
        creationCost = section.getDouble("creation-cost", creationCost);
        creationPermission = section.getString("creation-permission", creationPermission);
        kickAllCooldown = section.getInt("kick-all-cooldown", kickAllCooldown);
        kickCooldown = section.getInt("kick-cooldown", kickCooldown);
        promoteCooldown = section.getInt("promote-cooldown", promoteCooldown);
        demoteCooldown = section.getInt("demote-cooldown", demoteCooldown);
        inheritPerms = section.getBoolean("inherit-permissions", inheritPerms);
    }

    public String getType() {
        return type;
    }
}
