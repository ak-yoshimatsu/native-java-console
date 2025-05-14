package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserServiceImpl implements UserService {
    private final Map<Integer, User> userMap = new HashMap<Integer, User>();

    @Override
    public void addUser(User user) {
        userMap.put(user.getId(), user);
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
        return userMap.remove(id) != null;
    }

}
