package pl.to1maszproblem.menu;

import fr.minuskube.inv.ClickableItem;
import fr.minuskube.inv.SmartInventory;
import fr.minuskube.inv.content.InventoryContents;
import fr.minuskube.inv.content.InventoryProvider;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import pl.to1maszproblem.Main;
import pl.to1maszproblem.factory.ChatFactory;
import pl.to1maszproblem.utils.ItemBuilder;
import pl.to1maszproblem.utils.TextUtil;

public class ChatMenu implements InventoryProvider {

    @Override
    public void init(Player player, InventoryContents contents) {
        contents.set(1, 2,
                ClickableItem.of(new ItemBuilder(Material.GREEN_DYE)
                                .setName("&aWłącz czat!")
                                .build(),
                        false,
                        e -> {
                            ChatFactory.changeChatStatus(player, true);
                            player.closeInventory();
                        })
        );

        contents.set(1, 4,
                ClickableItem.of(new ItemBuilder(Material.WHITE_DYE)
                                .setName("&fWyczyść czat!")
                                .build(),
                        false,
                        e -> {
                            ChatFactory.clearChat(player);
                            player.closeInventory();
                        })
        );

        contents.set(1, 6,
                ClickableItem.of(new ItemBuilder(Material.RED_DYE)
                                .setName("&cWyłącz czat!")
                                .build(),
                        false,
                        e -> {
                            ChatFactory.changeChatStatus(player, false);
                            player.closeInventory();
                        })
        );

    }


    public void openChatMenu(Player player) {
        SmartInventory.builder()
                .provider(this)
                .size(3, 9)
                .title(TextUtil.fixColor("&cZarządzanie czatem"))
                .build()
                .open(player);
    }
}