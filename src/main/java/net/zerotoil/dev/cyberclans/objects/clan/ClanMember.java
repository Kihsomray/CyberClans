package net.zerotoil.dev.cyberclans.objects.clan;

import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.List;

public class ClanMember {

    private final OfflinePlayer player;
    private final ClanType clanType;
    private String clan = null;

    private final List<ClanRank> ranks = new ArrayList<>();

    public ClanMember(OfflinePlayer player, ClanType clanType, boolean owner) {
        this(player, clanType);
        if (owner) ranks.add(clanType.getOwner());

    }

    public ClanMember(OfflinePlayer player, ClanType clanType) {
        this.player = player;
        this.clanType = clanType;
        ranks.add(clanType.getMember()); // adds member rank to rank list automatically
    }

    public boolean promote(ClanRank rank) {
        if (ranks.contains(rank)) return false;
        ranks.add(rank);
        return true;
    }
    public boolean demote(ClanRank rank) {
        if (!ranks.contains(rank)) return false;
        ranks.remove(rank);
        return true;
    }

    public boolean hasPermission(String permission) {
        for (ClanRank rank : ranks)
            if (rank.hasPermission(permission)) return true;
        return false;
    }

    public ClanMember setClan(String clan) {
        this.clan = clan;
        return this;
    }

    public OfflinePlayer getPlayer() {
        return player;
    }
    public ClanType getClanType() {
        return clanType;
    }
    public String getClan() {
        return clan;
    }
    public List<ClanRank> getRanks() {
        return ranks;
    }

}