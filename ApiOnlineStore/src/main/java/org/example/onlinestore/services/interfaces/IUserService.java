package org.example.onlinestore.services.interfaces;

import org.example.onlinestore.domain.entityes.User;
import org.example.onlinestore.domain.entityes.resources.Role;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;
import java.util.Set;

public interface IUserService {
    List<User> findAll();
    User findById(Long id);
    User save(User user);
    User update(Long id, String username, String password, Set<Role> roles, Boolean isActive);
    User deleteById(Long id);
    void deleteAll();
    UserDetails loadUserByUsername(String username);
}
