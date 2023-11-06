package pl.to1maszproblem;

import eu.okaeri.configs.ConfigManager;
import eu.okaeri.configs.yaml.bukkit.YamlBukkitConfigurer;
import eu.okaeri.configs.yaml.bukkit.serdes.SerdesBukkit;
import fr.minuskube.inv.InventoryManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.to1maszproblem.command.ChatCommand;
import pl.to1maszproblem.configuration.Configuration;
import pl.to1maszproblem.listener.onChatListener;
import pl.to1maszproblem.notice.NoticeSerializer;

@Getter
public final class Main extends JavaPlugin {

    @Getter public static Main instance;
    @Getter private static InventoryManager invManager;

    private Configuration configuration;

    @Override
    public void onEnable() {
        instance = this;

        configuration = ConfigManager.create(Configuration.class, it -> {
            it.withConfigurer(new YamlBukkitConfigurer(), new SerdesBukkit());
            it.withSerdesPack(registry -> registry.register(new NoticeSerializer()));
            it.withBindFile(this.getDataFolder() + "/configuration.yml");
            it.withRemoveOrphans(true);
            it.saveDefaults();
            it.load(true);
        });
        getCommand("chat").setExecutor(new ChatCommand());

        Bukkit.getPluginManager().registerEvents(new onChatListener(), this);

        invManager = new InventoryManager(this);
        invManager.init();
    }
}
