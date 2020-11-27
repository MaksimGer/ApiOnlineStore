package org.example.onlinestore.repos;

import org.example.onlinestore.domain.entityes.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.lang.NonNull;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Long> {
    @Override
    @NonNull
    List<User> findAll();
}
