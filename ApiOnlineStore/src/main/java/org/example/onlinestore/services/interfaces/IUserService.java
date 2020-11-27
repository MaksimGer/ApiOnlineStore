package org.example.onlinestore.services.interfaces;

import org.example.onlinestore.domain.entityes.Product;
import org.example.onlinestore.domain.entityes.User;

import java.util.List;

public interface IUserService {
    List<User> findAll();
    User findById(Long id);
    void save(User user);
    User update(Long id, String username, String password, List<String> roles, Boolean isActive);
    User deleteById(Long id);
    void deleteAll();
}
