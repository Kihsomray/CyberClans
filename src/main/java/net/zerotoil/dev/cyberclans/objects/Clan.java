package net.zerotoil.dev.cyberclans.objects;

import me.croabeast.beanslib.utilities.TextUtils;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;

public class Clan {

    private final HashSet<ClanMember> clanMembers = new HashSet<>();

    private String name;
    private String ownerUUID;
    private Date creationDate;
    private int memberSize = 1;
    private int memberMaxSize = 99;

    public Clan() {

        new SimpleDateFormat().format(creationDate = new Date());

    }

    public Clan addMember(Player sender, ClanMember player) {
        if (clanMembers.contains(player)) {
            sender.sendMessage(TextUtils.colorize(sender, "clan already contains member message"));
            return this;
        }
        clanMembers.add(player);
        return this;
    }

    public Clan removeMember(Player sender, ClanMember player) {
        if (!clanMembers.contains(player)) {
            sender.sendMessage(TextUtils.colorize(sender, "your clan does not contain that player message"));
            return this;
        }
        clanMembers.remove(player);
        return this;
    }

    public String getName() {
        return name;
    }

    public String getOwner() {
        return ownerUUID;
    }

    public int getMemberSize() {
        return memberSize;
    }

    public int getMemberMaxSize() {
        return memberMaxSize;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Clan setName(String name) {
        this.name = name;
        return this;
    }

    public Clan setOwner(String ownerUUID) {
        this.ownerUUID = ownerUUID;
        return this;
    }

    public Clan setMemberSize(int size) {
        this.memberSize = size;
        return this;
    }

    public Clan setMemberMaxSize(int size) {
        this.memberMaxSize = size;
        return this;
    }

    public Clan setCreationDate(Date date) {
        this.creationDate = date;
        return this;
    }

}