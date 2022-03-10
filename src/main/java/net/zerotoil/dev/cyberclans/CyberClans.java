package net.zerotoil.dev.cyberclans;

import me.croabeast.beanslib.BeansLib;
import me.croabeast.beanslib.utilities.LogUtils;
import me.croabeast.beanslib.utilities.TextUtils;
import net.zerotoil.dev.cyberclans.commands.CCNCommand;
import net.zerotoil.dev.cyberclans.objects.files.Files;
import net.zerotoil.dev.cyberclans.utilities.LangUtils;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public final class CyberClans extends JavaPlugin {

    private Files files;
    private LangUtils langUtils;

    @Override
    public void onEnable() {

        files = new Files(this);
        initBeansLib(); // after files
        langUtils = new LangUtils(this);

        new CCNCommand(this);

        reloadClasses(true);

    }

    private void initBeansLib() {
        BeansLib.init(this);
        ConfigurationSection section = files.getConfig("lang").getConfigurationSection("messages");
        if (section == null) return;
        TextUtils.setLangPrefix(section.getString("prefix"));
        TextUtils.setCenterPrefix("<c>");
        TextUtils.setLangPrefixKey("<p>");
    }

    public void reloadClasses(boolean footer) {
        langUtils = new LangUtils(this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void logger(String... message) {
        LogUtils.doLog(message);
    }

    public void logger(CommandSender sender, String... message) {
        LogUtils.doLog(sender, message);
    }

    public int serverVersion() {
        return Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]);
    }
    public String getAuthors() {
        return String.join(", ", getDescription().getAuthors());
    }

    public Files files() {
        return files;
    }
    public LangUtils langUtils() {
        return langUtils;
    }

}
