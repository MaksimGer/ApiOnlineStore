package org.example.onlinestore.controllers;

import org.example.onlinestore.domain.entityes.Product;
import org.example.onlinestore.domain.entityes.User;
import org.example.onlinestore.exceptions.NotFoundException;
import org.example.onlinestore.services.interfaces.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
@PreAuthorize("hasAuthority('ADMIN')")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(method = RequestMethod.GET)
    public List<User> getUsers(){ return userService.findAll(); }

    @RequestMapping(value = "/", method = RequestMethod.GET, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<User> getUser(@RequestParam(value = "id") Long userId) {
        User user = userService.findById(userId).orElseThrow(NotFoundException::new);

        return  new ResponseEntity<>(user, HttpStatus.OK);
    }

    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public ResponseEntity<User> createUser(@RequestBody User user) {
        if (userService.loadUserByUsername(user.getUsername()) != null) {
            throw new RuntimeException("Username already exist");
        }
        return new ResponseEntity<>(userService.save(user), HttpStatus.CREATED);
    }

    @RequestMapping(value = "/users", method = RequestMethod.PUT)
    public User updateUser(@RequestBody User user) {
        Long userId = userService.findById(user.getId()).orElseThrow(NotFoundException::new).getId();

        return userService.update(userId, user.getUsername(), user.getPassword(), user.getRoles(), user.isActive());
    }

    @RequestMapping(value = "", method = RequestMethod.DELETE, produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<User> deleteProduct(@RequestParam("id") User curUser){
        if(curUser == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(userService.deleteById(curUser.getId()) == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(curUser, HttpStatus.OK);
    }
}
