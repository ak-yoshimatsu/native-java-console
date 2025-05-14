package app;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceFilesystemImpl implements UserService {
    private final Map<Integer, User> userMap = new HashMap<Integer, User>();
    private final String FILE_NAME = "users.csv";

    public UserServiceFilesystemImpl() {
        loadFromFile();
    }

    @Override
    public void addUser(User user) {
        userMap.put(user.getId(), user);
        saveToFile();
    }

    @Override
    public List<User> getAllUsers() {
        return new ArrayList<User>(userMap.values());
    }

    @Override
    public User findUserById(int id) {
        return userMap.get(id);
    }

    @Override
    public boolean deleteUserById(int id) {
        boolean removed = userMap.remove(id) != null;
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    private void loadFromFile() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            int maxId = 0;

            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", -1);

                if (parts.length == 3) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String email = parts[2];

                    userMap.put(id, new User(id, name, email));

                    if (id > maxId) {
                        maxId = id;
                    }
                }
            }
            User.setCounter(maxId + 1);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void saveToFile() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (User user : userMap.values()) {
                writer.write(user.getId() + "," + user.getName() + "," + user.getEmail());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
