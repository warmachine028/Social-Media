package social.media;

import java.util.HashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public class Users {
    private static final HashMap<Long, User> data = new HashMap<>();

    public static User authenticate(String email, String password) {
        User foundUser = data.values().stream()
                .filter(user -> email.equals(user.getEmail()))
                .findFirst()
                .orElse(null);
        if (foundUser == null) {
            System.out.println("User doesn't exist with this email");
        } else if (!foundUser.getPassword().equals(password))
            System.out.println("Password Incorrect");
        return foundUser;
    }
    public static int storeUser(User user) {
        try {
            data.put(user.getId(), user);
            return 1;
        } catch (Exception exception) {
            System.out.println(exception.getMessage());
        }
        return 0;
    }
}

