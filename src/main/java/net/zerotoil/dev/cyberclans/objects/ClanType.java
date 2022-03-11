package net.zerotoil.dev.cyberclans.objects;

import net.zerotoil.dev.cyberclans.CyberClans;

public class ClanType {

    private final CyberClans main;
    private final String type;

    // basic clan defaults
    private String displayName = "&7{player}'s clan";
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
    }

    public String getType() {
        return type;
    }
}
