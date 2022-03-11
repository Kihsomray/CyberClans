package net.zerotoil.dev.cyberclans.cache;

import net.zerotoil.dev.cyberclans.CyberClans;
import net.zerotoil.dev.cyberclans.objects.Clan;

import java.util.HashMap;
import java.util.Map;

public class ClanCache {

    private final CyberClans main;

    private final Map<Integer, Clan> clans = new HashMap<>();

    private final int minMembers = 1;
    private final int maxMembers = 100;

    private int clanCount = 4;

    public ClanCache(CyberClans main) {
        this.main = main;

    }



}
