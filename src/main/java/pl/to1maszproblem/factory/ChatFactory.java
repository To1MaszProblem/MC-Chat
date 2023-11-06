package pl.to1maszproblem.factory;

import com.google.common.collect.ImmutableMultimap;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.to1maszproblem.Main;

@Getter
public class ChatFactory {
    private static boolean chatStatus = true;
    private static final @NotNull FileConfiguration config = Main.getInstance().getConfig();

    public static void changeChatStatus(CommandSender sender, boolean status) {
        if (status) {
            if (chatStatus) {
                Main.getInstance().getConfiguration().getChatIsOn().send((Player) sender);
                return;
            }

            Main.getInstance().getConfiguration().getChatOnSender().send((Player) sender);


            Bukkit.getOnlinePlayers().forEach(player ->
                    Main.getInstance().getConfiguration().getChatOnBroadCast()
                            .addPlaceholder(ImmutableMultimap.of("[player]", sender.getName()))
                            .send(player));
            chatStatus = true;
        } else {
            if (!chatStatus) {
                Main.getInstance().getConfiguration().getChatIsOff().send((Player) sender);
                return;
            }

            Main.getInstance().getConfiguration().getChatOffSender().send((Player) sender);

            Bukkit.getOnlinePlayers().forEach(player ->
                    Main.getInstance().getConfiguration().getChatOffBroadCast()
                            .addPlaceholder(ImmutableMultimap.of("[player]", sender.getName()))
                            .send(player));
            chatStatus = false;
        }
    }
    public static void clearChat(CommandSender sender) {
        for (int i = 0; i < 100; i++) Bukkit.getOnlinePlayers().forEach(player -> player.sendMessage(" "));

        Main.getInstance().getConfiguration().getChatClearSender().send((Player) sender);

        Bukkit.getOnlinePlayers().forEach(player ->
                Main.getInstance().getConfiguration().getChatClearBroadCast()
                        .addPlaceholder(ImmutableMultimap.of("[player]", sender.getName()))
                        .send(player));
    }
    public static boolean isChatStatus() {
        return chatStatus;
    }
}
