package net.lumamc.dhs;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.OkaeriConfig;
import eu.okaeri.configs.serdes.standard.StandardSerdes;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.List;


public class DynamicHopperSpeed extends JavaPlugin {

    @Getter
    private static DynamicHopperSpeed instance;
    @Getter
    private static Config dynamicConfig;


    @Override
    public void onEnable() {
        instance = this;
        dynamicConfig = loadConfig(Config.class, "config.yml");

        Bukkit.getScheduler().runTask(this, this::instate);
        getCommand("dhs").setExecutor(new ReloadCommand());
    }

    public <T extends OkaeriConfig> T loadConfig(Class<T> configClass, String fileName) {
        return ConfigManager.create(configClass, it -> {
            it.withBindFile(getDataPath().resolve(fileName));
            it.withRemoveOrphans(true);
            it.withConfigurer(new YamlBukkitConfigurer(), new StandardSerdes());
            it.saveDefaults();
            it.load(true);
        });
    }


    public void instate() {
        List<World> worlds = Bukkit.getWorlds().stream()
                .filter(it -> !dynamicConfig.excludedWorlds().contains(it.getName()))
                .toList();

        for (World world : worlds) {
            SpigotWorldConfigModifier modifier = new SpigotWorldConfigModifier(world);
            modifier.setHopperTransferCooldown(dynamicConfig.ticksPer().hopperTransfer());
            modifier.setHopperCheckSpeed(dynamicConfig.ticksPer().hopperCheck());
            modifier.setHopperTransferItemStackAmount(dynamicConfig.hopperAmount());
            modifier.setHoppersCanLoadChunks(dynamicConfig.hoppersCanLoadChunks());
            getLogger().info("[" + world.getName() + "] Set hopperTransferCooldown to " + dynamicConfig.ticksPer().hopperTransfer());
            getLogger().info("[" + world.getName() + "] Set hopperCheckSpeed to " + dynamicConfig.ticksPer().hopperCheck());
            getLogger().info("[" + world.getName() + "] Set hopperTransferItemStackAmount to " + dynamicConfig.hopperAmount());
            getLogger().info("[" + world.getName() + "] Set hoppersCanLoadChunks to " + dynamicConfig.hoppersCanLoadChunks());
        }
    }

}