package pl.to1maszproblem.menu;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.to1maszproblem.Main;
import pl.to1maszproblem.user.User;
import pl.to1maszproblem.user.UserManager;
import pl.to1maszproblem.utils.ItemBuilder;
import pl.to1maszproblem.utils.TextUtil;


public class MessageHistoryMenu implements InventoryProvider {


    @Override
    public void init(Player player, InventoryContents contents) {
        String title = contents.inventory().getTitle();
        if(title == null) return;

        String[] split = title.split(" ");
        String targetName = split[3];

        Player target = Bukkit.getPlayer(targetName);
        User user = UserManager.getUser(target);


            user.getMessages().forEach(messages ->
                    contents.add(ClickableItem.of(new ItemBuilder(Material.BOOK, 1)
                                    .setName(Main.getInstance().getConfig().getString("chathistory.name").replace("[player]", targetName))
                                    .addLore(Main.getInstance().getConfig().getStringList("chathistory.lore"))
                                    .addLorePlaceholder("[message]", messages)
                                    .build(),
                            false,
                            e -> player.closeInventory()
                    )));
    }

    public void openMessageHistoryMenu(Player player, Player target) {
        SmartInventory.builder()
                .provider(this)
                .size(5, 9)
                .title(TextUtil.fixColor("&fHistoria wiadomosci gracza&e " + target.getName()))
                .build()
                .open(player);

    }
}