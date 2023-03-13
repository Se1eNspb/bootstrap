package bootstrap.service;

import bootstrap.model.Role;
import bootstrap.model.User;
import bootstrap.repositories.RoleRepository;
import bootstrap.repositories.UserRepository;
import bootstrap.security.UserDetailsImp;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    @Autowired
    public UserService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("User with this email not found.");
        }
        Hibernate.initialize(user.get().getRoles());
        return new UserDetailsImp(user.get(),user.get().getRoles());
    }

    public List<User> index() {
        return userRepository.findAll();
    }

    public void create(User user) {
        userRepository.save(user);
    }

    public void update(int id, User user) {
        user.setId(id);
        userRepository.save(user);
    }

    public void delete(int id) {
        userRepository.deleteById(id);
    }

    public List<Role> listAllRoles() {
        return roleRepository.findAll();
    }
}
