package net.zerotoil.dev.cyberclans.objects.clan;

import net.zerotoil.dev.cyberclans.CyberClans;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

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

    private ClanRank owner;
    private ClanRank member;
    private final Map<String, ClanRank> ranks = new HashMap<>();

    public ClanType(CyberClans main, String type) {
        this.main = main;
        this.type = type;

        loadSettings();
        loadRanks();
    }

    public void loadSettings() {
        ConfigurationSection section = main.files().getConfig("clans").getConfigurationSection("clan-types." + type);
        if (section == null) return;

        // settings
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

    public void loadRanks() {
        ConfigurationSection section = main.files().getConfig("clans").getConfigurationSection("clan-types." + type + ".ranks");
        if (section == null) return;

        // ranks
        boolean hasOwner = false, hasMember = false;
        for (String s : section.getKeys(false)) {
            if (s.matches("(?i)OWNER")) {
                // owner settings
                hasOwner = true;
                owner = new ClanRank(main, this, s);
                ranks.put(s.toUpperCase(Locale.ROOT), owner);
            } else if (s.matches("(?i)MEMBER")) {
                // member settings
                hasMember = true;
                member = new ClanRank(main, this, s);
                ranks.put(s.toUpperCase(Locale.ROOT), member);
            } else {
                // other ranks in-between
                ranks.put(s.toUpperCase(Locale.ROOT), new ClanRank(main, this, s));
            }
        }

        // checks if the clan type contains at least an owner and member type
        if (!hasOwner || !hasMember) throw new UnsupportedOperationException();

    }

    public String getName() {
        return type;
    }
    public String getDisplayName() {
        return displayName;
    }
    public int getMaxPlayers() {
        return maxPlayers;
    }
    public double getCreationCost() {
        return creationCost;
    }
    public String getCreationPermission() {
        return creationPermission;
    }
    public int getKickAllCooldown() {
        return kickAllCooldown;
    }
    public int getKickCooldown() {
        return kickCooldown;
    }
    public int getPromoteCooldown() {
        return promoteCooldown;
    }
    public int getDemoteCooldown() {
        return demoteCooldown;
    }
    public boolean isInheritPerms() {
        return inheritPerms;
    }
    public ClanRank getOwner() {
        return owner;
    }
    public ClanRank getMember() {
        return member;
    }

}
