package pl.to1maszproblem.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.to1maszproblem.factory.ChatFactory;
import pl.to1maszproblem.menu.ChatMenu;
import pl.to1maszproblem.menu.MessageHistoryMenu;
import pl.to1maszproblem.user.User;
import pl.to1maszproblem.user.UserManager;
import pl.to1maszproblem.utils.TextUtil;

import java.util.List;

public class ChatCommand implements TabExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(!sender.hasPermission("chat.cmd")) {
            TextUtil.sendMessage("CHAT", (Player) sender, "&8>> Nie posiadasz uprawnień do tej komendy &8(&4chat.cmd&8)");
        }
        if (args.length == 0) {
            if (!(sender instanceof Player player)) {
                TextUtil.sendMessage("CHAT", (Player) sender, "&cPodana komenda jest dostepna tylko dla graczy!");
                return false;
            }
            new ChatMenu().openChatMenu(player);
        } else if (args.length == 1) {
            switch (args[0].toLowerCase()) {
                case "on", "wlacz" -> ChatFactory.changeChatStatus(sender, true);
                case "off", "wylacz" -> ChatFactory.changeChatStatus(sender, false);
                case "clear", "wyczysc" -> ChatFactory.clearChat(sender);

                default -> TextUtil.sendMessage("CHAT", (Player) sender, "&7Poprawne użycie: /chat on/off/clear/[history] [gracz]");
            }
        } else if (args.length == 2 && args[0].equalsIgnoreCase("history")) {
            if (!(sender instanceof Player player)) {
                TextUtil.sendMessage("CHAT", (Player) sender, "&cPodana komenda jest dostepna tylko dla graczy!");
                return false;
            }
            Player target = Bukkit.getPlayer(args[1]);
            if (target == null) {
                TextUtil.sendMessage("CHAT", player, "&cGracz nie istnieje lub jest offline!");
                return false;
            }

            User user = UserManager.getUser(player);
            if (user.getMessages() == null || user.getMessages().isEmpty()) {
                TextUtil.sendMessage("CHAT", player, "&cPodany gracz nie posiada histori czatu!");
                return false;
            }

            new MessageHistoryMenu().openMessageHistoryMenu(player, target);
        }


        return false;
    }

    @Override
    public @Nullable List<String> onTabComplete(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(args.length == 1) return List.of("on", "off", "clear", "history");
        else if(args.length == 2) if(args[0].equalsIgnoreCase("history")) return Bukkit.getOnlinePlayers().stream().map(Player::getName).toList();
        return null;
    }
}
