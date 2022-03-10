package net.zerotoil.dev.cyberclans;

import me.croabeast.beanslib.BeansLib;
import me.croabeast.beanslib.utilities.TextUtils;
import net.zerotoil.dev.cyberclans.objects.files.Files;
import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.plugin.java.JavaPlugin;

public final class CyberClans extends JavaPlugin {

    private Files files;

    @Override
    public void onEnable() {

        files = new Files(this);
        initBeansLib(); // after files

    }

    private void initBeansLib() {
        BeansLib.init(this);
        ConfigurationSection section = files.getConfig("lang").getConfigurationSection("messages");
        if (section == null) return;
        TextUtils.setLangPrefix(section.getString("prefix"));
        TextUtils.setCenterPrefix("<c>");
        TextUtils.setLangPrefixKey("<p>");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void logger(String... message) {

    }
    public int serverVersion() {
        return Integer.parseInt(Bukkit.getBukkitVersion().split("-")[0].split("\\.")[1]);
    }

    public Files files() {
        return files;
    }

}
