package org.example.onlinestore.services;

import org.example.onlinestore.domain.entityes.User;
import org.example.onlinestore.domain.entityes.resources.Role;
import org.example.onlinestore.repos.UserRepo;
import org.example.onlinestore.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class UserService implements UserDetailsService, IUserService {
    private static final Long ADMIN_ID = (long)1;
    private static final Long GUEST_ID = (long)2;

    @Autowired
    private UserRepo userRepo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepo.findByUsername(username);

        if(user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        user.setActive(true);
        return user;
    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public User findById(Long id) {
        if(id == null)
            return null;

        Optional<User> userById = userRepo.findById(id);

        return userById.orElse(null);
    }

    @Override
    public void save(User user) {
        if(user != null)
            userRepo.save(user);
    }

    @Override
    public User update(Long id, String username, String password, Set<Role> roles, Boolean isActive) {
        if(id == null)
            return null;

        User curUser = userRepo.findById(id).orElse(null);

        if(curUser == null)
            return null;

        curUser.setPassword(password);
        if(!id.equals(ADMIN_ID) && !id.equals(GUEST_ID)){
            curUser.setUsername(username);
            curUser.setRoles(roles);
        }
        curUser.setActive(isActive);

        return userRepo.save(curUser);
    }

    @Override
    public User deleteById(Long id) {
        if(id == null || id.equals(ADMIN_ID) || id.equals(GUEST_ID))
            return null;

        User curUser = userRepo.findById(id).orElse(null);

        if(curUser != null)
            userRepo.delete(curUser);

        return curUser;
    }

    @Override
    public void deleteAll() {
        User admin = userRepo.findById(ADMIN_ID).orElse(null);
        User guest = userRepo.findById(GUEST_ID).orElse(null);

        userRepo.deleteAll();

        if(admin != null)
            userRepo.save(admin);

        if(guest != null)
            userRepo.save(guest);
    }
}
