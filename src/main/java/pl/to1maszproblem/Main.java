package pl.to1maszproblem;

import fr.minuskube.inv.InventoryManager;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.to1maszproblem.command.ChatCommand;
import pl.to1maszproblem.listener.onChatListener;



public final class Main extends JavaPlugin {
    public static Main instance;
    @Getter private static InventoryManager invManager;

    @Override
    public void onEnable() {
        instance = this;
        getCommand("chat").setExecutor(new ChatCommand());
        Bukkit.getPluginManager().registerEvents(new onChatListener(), this);
        invManager = new InventoryManager(this);
        invManager.init();
    }
    public static Main getInstance() {
        return instance;
    }
}
