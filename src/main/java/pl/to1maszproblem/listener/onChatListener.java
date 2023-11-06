package pl.to1maszproblem.listener;

import com.google.common.collect.ImmutableMultimap;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import pl.to1maszproblem.Main;
import pl.to1maszproblem.factory.ChatFactory;
import pl.to1maszproblem.user.User;
import pl.to1maszproblem.user.UserManager;

public class onChatListener implements Listener {
    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        User user = UserManager.getUser(player);
        user.addMessageToPlayerHistory(user, event.getMessage());

        if (!ChatFactory.isChatStatus()  && !player.hasPermission("chat.bypass")) {
            Main.getInstance().getConfiguration().getChatCancelPlayer().send(player);

            Bukkit.getOnlinePlayers().stream().filter(p -> p.hasPermission("chat.admin")).forEach(p ->
                           Main.getInstance().getConfiguration().getChatCancelAdmin()
                                   .addPlaceholder(ImmutableMultimap.of("[player]", player.getName()))
                                   .send(p)
            );

            event.setCancelled(true);
        }
    }
}
