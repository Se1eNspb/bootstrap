package bootstrap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import bootstrap.model.Role;
import bootstrap.model.User;
import bootstrap.repositories.UserRepository;

@Component
public class Test {
    private final UserRepository userRepository;

    @Autowired
    public Test(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Bean
    @Transactional
    public void addDefaultUsers() {
        User user = new User("userFirstName", "userLastName",18 , "user@mail.ru","user");
        Role roleUser = new Role("ROLE_USER");
        user.addRole(roleUser);
        roleUser.addUser(user);
        User admin = new User("adminFirstName", "adminLastName",22, "admin@mail.ru","admin");
        Role roleAdmin = new Role("ROLE_ADMIN");
        admin.addRole(roleAdmin);
        admin.addRole(roleUser);
        roleAdmin.addUser(admin);
        userRepository.save(user);
        userRepository.save(admin);
    }
}
