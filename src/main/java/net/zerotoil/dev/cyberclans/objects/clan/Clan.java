package net.zerotoil.dev.cyberclans.objects.clan;

import net.zerotoil.dev.cyberclans.CyberClans;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.*;

public class Clan {

    // basic information
    private final CyberClans main;
    private final int id;
    private final ClanType type;
    private String name;
    private final long creationTime;

    // clan members
    private OfflinePlayer ownerPlayer;
    private ClanMember ownerMember;
    private final Map<OfflinePlayer, ClanMember> clanMembers = new HashMap<>();


    public Clan(CyberClans main, int id, OfflinePlayer owner, ClanType type, long creationTime) {

        this.main = main;
        this.id = id;
        this.type = type;
        this.creationTime = creationTime;
        setOwner(owner);

    }

    public boolean isOwner(OfflinePlayer player) {
        return ownerPlayer.equals(player);
    }

    public boolean isMember(OfflinePlayer player) {
        return clanMembers.containsKey(player) || isOwner(player);
    }

    // todo finish this
    public boolean promote(OfflinePlayer player, ClanRank rank) {
        clanMembers.get(player).promote(rank);
        return true;
    }

    public Clan setOwner(OfflinePlayer player) {
        this.ownerPlayer = player;
        this.ownerMember = new ClanMember(player, type, true).setClan(name);
        return this;
    }

    public Clan addMember(Player sender, OfflinePlayer player) {
        if (clanMembers.containsKey(player)) {
            main.langUtils().sendMessage(sender, "already-in-clan");
            return this;
        }
        return addMember(player);
    }

    public Clan addMember(OfflinePlayer player) {
        clanMembers.put(player, new ClanMember(player, type).setClan(name));
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

    public Clan setName(String name) {
        this.name = name;
        return this;
    }

    public int getMemberSize() {
        return clanMembers.size() + 1;
    }
    public String getCreationDate() {
        return new Date(creationTime).toString();
    }
    public String getName() {
        //if (name == null) return type;
        return name;
    }
    public int getId() {
        return id;
    }
    public ClanType getType() {
        return type;
    }
    public OfflinePlayer getOwnerPlayer() {
        return ownerPlayer;
    }
    public ClanMember getOwnerMember() {
        return ownerMember;
    }
    public Map<OfflinePlayer, ClanMember> getClanMembers() {
        return clanMembers;
    }

}