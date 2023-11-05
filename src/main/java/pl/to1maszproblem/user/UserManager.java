package pl.to1maszproblem.user;

import org.bukkit.entity.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class UserManager {
    private static Map<UUID, User> users = new HashMap<>();

    public static User getUser(String name) {
        return users.values().stream().filter(user -> user.getName().equals(name)).findFirst().orElse(null);
    }

    public static User getUser(Player player) {
        return users.computeIfAbsent(player.getUniqueId(), $ -> new User(player.getUniqueId()));
    }

    public static User userCreate(Player player) {
        return users.computeIfAbsent(player.getUniqueId(), $ -> new User(player.getUniqueId()));
    }

    public static void putUser(UUID uuid, User user) {
        users.put(uuid, user);
    }

    public static Map<UUID, User> getUsers() {
        return users;
    }
}
