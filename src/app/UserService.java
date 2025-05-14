package app;

import java.util.List;

public interface UserService {
    void addUser(User user);

    List<User> getAllUsers();

    User findUserById(int id);

    boolean deleteUserById(int id);
}
