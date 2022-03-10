package net.zerotoil.dev.cyberclans.utilities;

import com.google.common.collect.Lists;
import me.croabeast.beanslib.utilities.TextUtils;
import net.zerotoil.dev.cyberclans.CyberClans;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.Configuration;
import org.bukkit.entity.Player;

import java.util.List;

public class LangUtils extends TextUtils {

    private final CyberClans main;

    public LangUtils(CyberClans main) {
        this.main = main;
    }

    // sends with optional default placeholders and prefix
    public void sendMessage(CommandSender sender, String location) {
        sendMessage(sender, location, null, null);
    }

    // add extra placeholders
    public void sendMessage(CommandSender sender, String location, String[] placeholders, String[] replacements) {
        List<String> message = convertList(main.files().getConfig("lang"), "messages." + location);
        TextUtils.sendFileMsg(sender, message, placeholders, replacements);
    }

    public void sendHelp(Player player, boolean adminHelp) {
        String location = "help-player";
        if (!adminHelp && main.files().getConfig("lang").getString("messages.help-player") == null) return;
        if (adminHelp) {
            location = "help-admin";
            if (main.files().getConfig("lang").getString("messages.help-admin") == null) return;
        }
        sendMessage(player, location);
    }

    // converts message to list
    public List<String> convertList(Configuration config, String path) {
        return  !config.isList(path) ?
                Lists.newArrayList(config.getString(path)) :
                config.getStringList(path);
    }

}
