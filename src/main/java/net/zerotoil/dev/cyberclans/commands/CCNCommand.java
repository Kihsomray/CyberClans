package net.zerotoil.dev.cyberclans.commands;

import com.sun.istack.internal.NotNull;
import me.croabeast.beanslib.utilities.TextUtils;
import net.zerotoil.dev.cyberclans.CyberClans;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.Arrays;
import java.util.List;

public class CCNCommand implements CommandExecutor {

    private final CyberClans main;
    private final List<String> consoleCmds;

    public CCNCommand(CyberClans main) {
        this.main = main;
        main.getCommand("ccn").setExecutor(this);
        consoleCmds = Arrays.asList("about", "reload");
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {

        Player player;
        String uuid;

        // console check
        if (!(sender instanceof Player)) {
            if (args.length == 0 || !consoleCmds.contains(args[0].toLowerCase())) {
                main.logger("&cConsole cannot use this command!");
                return true;
            }
            player = null;
            uuid = null;
        } else {
            player = (Player) sender;
            uuid = player.getUniqueId().toString();
        }

        if (args.length == 1) {

            switch (args[0].toLowerCase()) {
                case "about":

                    if (noPlayerPerm(player, "player.about")) return true;
                    TextUtils.sendFileMsg(player, Arrays.asList(
                            " &a&lCyber&f&lStatistics &fv" + main.getDescription().getVersion() + " &7(&7&nhttps://bit.ly/2YSlqYq&7).",
                            " &fDeveloped by &a" + main.getAuthors() + "&f.",
                            " A placeholder MySQL transferring plugin."));
                    return true;

                case "reload":
                    if (noPlayerPerm(player, "admin.reload")) return true;
                    main.langUtils().sendMessage(player, "reloading");

                    // unload
                    main.onDisable();

                    // load
                    main.reloadClasses(true);

                    main.langUtils().sendMessage(player, "reloaded");
                    return true;

            }
        }

        // final outcome, if command does not exist:
        if (sender.hasPermission("CyberStats.admin.help")) main.langUtils().sendHelp(player, true);
        else if (sender.hasPermission("CyberStats.player.help")) main.langUtils().sendHelp(player, false);
        else main.langUtils().sendMessage(player, "no-permission");
        return true;
    }

    private boolean noPlayerPerm(Player player, String permissionKey) {
        if (player == null) return false;
        if (!player.hasPermission("CyberStats." + permissionKey)) {
            main.langUtils().sendMessage(player, "no-permission");
            return true;
        }
        return false;
    }

    private Player getPlayer(String player) {
        for (Player p : Bukkit.getOnlinePlayers()) if (p.getName().equalsIgnoreCase(player)) return p;
        return null;
    }

    private boolean notLong(Player player, String arg) {
        try {
            Long.parseLong(arg);
            return false;
        } catch (Exception e) {
            main.langUtils().sendMessage(player, "not-number");
            return true;
        }
    }
    private boolean notDouble(Player player, String arg) {
        try {
            Double.parseDouble(arg);
            return false;
        } catch (Exception e) {
            main.langUtils().sendMessage(player, "not-number");
            return true;
        }
    }


}
