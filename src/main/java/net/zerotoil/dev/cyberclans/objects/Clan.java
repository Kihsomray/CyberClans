package net.zerotoil.dev.cyberclans.objects;

import net.zerotoil.dev.cyberclans.CyberClans;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class Clan {

    private final CyberClans main;
    private final ClanType type;

    private final OfflinePlayer ownerPlayer;
    private final ClanMember ownerMember;
    private final Map<OfflinePlayer, ClanMember> clanMembers = new HashMap<>();

    private int id;
    private String name;
    private final long creationTime;


    public Clan(CyberClans main, OfflinePlayer owner, ClanType type, long creationTime) {

        this.main = main;
        this.ownerPlayer = owner;
        this.ownerMember = new ClanMember(owner);
        this.type = type;
        this.creationTime = creationTime;

    }

    public Clan addMember(Player sender, OfflinePlayer player) {
        if (clanMembers.containsKey(player)) {
            main.langUtils().sendMessage(sender, "already-in-clan");
            return this;
        }
        clanMembers.put(player, new ClanMember(player));
        return this;
    }

    public Clan removeMember(Player sender, OfflinePlayer player) {
        if (!clanMembers.containsKey(player)) {
            main.langUtils().sendMessage(sender, "not-in-clan");
            return this;
        }
        clanMembers.remove(player);
        return this;
    }

    public String getName() {
        return name;
    }

    public int getMemberSize() {
        return clanMembers.size() + 1;
    }

    public String getCreationDate() {
        return new Date(creationTime).toString();
    }

    public Clan setName(String name) {
        this.name = name;
        return this;
    }

    // todo check if owner
    public boolean isMember(OfflinePlayer player) {
        if (clanMembers.containsKey(player)) return true;
        return false;
    }

    // todo finish this
    public boolean promote(OfflinePlayer player, ClanRank rank) {

        clanMembers.get(player).promote(rank);
        return false;
    }


}