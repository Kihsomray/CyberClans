package net.zerotoil.dev.cyberclans.objects;

import org.bukkit.OfflinePlayer;

import java.util.ArrayList;
import java.util.List;

public class ClanMember {

    private final OfflinePlayer player;

    private final List<ClanRank> ranks = new ArrayList<>();

    public ClanMember(OfflinePlayer player) {
        this.player = player;

    }

    public void promote(ClanRank rank) {
        ranks.add(rank);
    }


}
