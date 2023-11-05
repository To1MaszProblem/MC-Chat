package pl.to1maszproblem.user;

import lombok.Getter;
import lombok.Setter;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Setter
@Getter
public class User {
    private final UUID uuid;
    private String name;
    private List<String> messages;


    public User(UUID uuid) {
        this.uuid = uuid;
        this.name = null;
        this.messages = new ArrayList<>();

    }

    public Player getPlayer() {
        return Bukkit.getPlayerExact(this.name);
    }
    public void addMessageToPlayerHistory(User user, String message) { user.getMessages().add(message); }
}