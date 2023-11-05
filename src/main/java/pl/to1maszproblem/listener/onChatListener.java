package pl.to1maszproblem.listener;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.to1maszproblem.Main;
import pl.to1maszproblem.factory.ChatFactory;
import pl.to1maszproblem.user.User;
import pl.to1maszproblem.user.UserManager;
import pl.to1maszproblem.utils.TextUtil;

public class onChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.getUser(player);
        user.addMessageToPlayerHistory(user, event.getMessage());

        if (!ChatFactory.isChatStatus()  && !player.hasPermission("chat.bypass")) {
            TextUtil.sendMessage(Main.getInstance().getConfig().getString("chat.is-off.chat-type"), player, Main.getInstance().getConfig().getString("chat.is-off.message"));

            Bukkit.getOnlinePlayers().stream().filter(p -> p.hasPermission("chat.admin")).forEach(p ->
                           TextUtil.sendMessage(Main.getInstance().getConfig().getString("chat.cancel-message.chat-type"), player, Main.getInstance().getConfig().getString("chat.cancel-message.message").replace("[player]", player.getName()))
                    );

            event.setCancelled(true);
        }
    }
}
