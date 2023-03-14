package bootstrap.service;

import bootstrap.model.Role;
import bootstrap.model.User;

import java.util.List;

public interface UserService {

    List<User> index();

    void create(User user);

    void update(int id, User user);

    void delete(int id);

    List<Role> listAllRoles();
}
