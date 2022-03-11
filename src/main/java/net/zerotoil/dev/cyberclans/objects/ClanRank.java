package net.zerotoil.dev.cyberclans.objects;

import net.zerotoil.dev.cyberclans.CyberClans;
import org.bukkit.configuration.ConfigurationSection;

import java.util.HashMap;
import java.util.Map;

public class ClanRank {

    private final CyberClans main;
    private final ClanType type;
    private final String rank;

    private String displayName = "Member";
    private int priority = 0;
    private final Map<String, Boolean> permissions = new HashMap<>();

    public ClanRank(CyberClans main, ClanType type, String rank) {
        this.main = main;
        this.type = type;
        this.rank = rank;

        ConfigurationSection section = main.files().getConfig("clans")
                .getConfigurationSection("clan-types." + type.getType() + ".ranks." + rank);
        if (section == null) return;

        displayName = section.getString("display", displayName);
        priority = section.getInt("priority", priority);
        for (String s : section.getStringList("permissions")) {

            if (s.contains(":")) {
                String[] split = s.split(":");
                boolean temp = false;
                if (split[1].equalsIgnoreCase("bypass")) temp = true;
                permissions.put(split[0], temp);
            } else permissions.put(s, false);


        }



    }


}
