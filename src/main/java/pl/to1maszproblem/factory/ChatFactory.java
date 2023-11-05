package pl.to1maszproblem.factory;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.to1maszproblem.Main;
import pl.to1maszproblem.utils.TextUtil;

@Getter
public class ChatFactory {
    private static boolean chatStatus = true;

    public static void changeChatStatus(CommandSender sender, boolean status) {
        if (status) {
            if (chatStatus) {
                TextUtil.sendMessage(Main.getInstance().getConfig().getString("message.on.is-on.chat-type"), (Player) sender, Main.getInstance().getConfig().getString("message.on.is-on.message"));
                return;
            }

            TextUtil.sendMessage(Main.getInstance().getConfig().getString("message.on.sender.chat-type"), (Player) sender, Main.getInstance().getConfig().getString("message.on.sender.message"));


            Bukkit.getOnlinePlayers().forEach(player ->
                    TextUtil.sendMessage(Main.getInstance().getConfig().getString("message.on.broadcast.chat-type"), player, Main.getInstance().getConfig().getString("message.on.broadcast.message").replace("[player]", sender.getName())));

            chatStatus = true;
        } else {
            if (!chatStatus) {
                TextUtil.sendMessage(Main.getInstance().getConfig().getString("message.on.is-off.chat-type"), (Player) sender, Main.getInstance().getConfig().getString("message.on.is-off.message"));
                return;
            }

            TextUtil.sendMessage(Main.getInstance().getConfig().getString("message.off.sender.chat-type"), (Player) sender, Main.getInstance().getConfig().getString("message.off.sender.message"));

            Bukkit.getOnlinePlayers().forEach(player -> TextUtil.sendMessage("CHAT", player, "&cChat został wyłączony przez administratora &f[player]".replace("[player]", sender.getName())));
            chatStatus = false;
        }
    }
    public static void clearChat(CommandSender sender) {
        for (int i = 0; i < 100; i++) Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(" "));

        TextUtil.sendMessage(Main.getInstance().getConfig().getString("message.clear.sender.chat-type"), (Player) sender, Main.getInstance().getConfig().getString("message.clear.sender.message"));

        Bukkit.getOnlinePlayers().forEach(player ->
                TextUtil.sendMessage(Main.getInstance().getConfig().getString("message.clear.broadcast.chat-type"), (Player) sender,
                        Main.getInstance().getConfig().getString("message.clear.broadcast.message").replace("[player]", sender.getName())));
    }
    public static boolean isChatStatus() {
        return chatStatus;
    }
}
