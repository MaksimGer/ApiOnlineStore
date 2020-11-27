package org.example.onlinestore.services;

import org.example.onlinestore.domain.entityes.Product;
import org.example.onlinestore.domain.entityes.User;
import org.example.onlinestore.repos.UserRepo;
import org.example.onlinestore.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService implements IUserService {
    @Autowired
    private UserRepo userRepo;

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
    public User update(Long id, String username, String password, List<String> roles, Boolean isActive) {
        if(id == null)
            return null;

        User curUser = userRepo.findById(id).orElse(null);

        if(curUser == null)
            return null;

        curUser.setPassword(password);
        if(id != 1){
            curUser.setUsername(username);
            curUser.setRoles(roles);
        }
        curUser.setActive(isActive);

        return userRepo.save(curUser);
    }

    @Override
    public User deleteById(Long id) {
        if(id == null || id == 1)
            return null;

        User curUser = userRepo.findById(id).orElse(null);

        if(curUser != null)
            userRepo.delete(curUser);

        return curUser;
    }

    @Override
    public void deleteAll() {
        userRepo.deleteAll();

        User userAdmin = new User();
        List<String> userRoles = new ArrayList<>();

        userRoles.add("USER");
        userRoles.add("ADMIN");

        userAdmin.setId((long)1);
        userAdmin.setUsername("Admin");
        userAdmin.setPassword("Admin");
        userAdmin.setRoles(userRoles);

        userRepo.save(userAdmin);
    }
}
