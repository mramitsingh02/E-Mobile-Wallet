package com.tester.spring.rest.webservices.controller;

import com.tester.spring.rest.webservices.exception.UserNotFoundException;
import com.tester.spring.rest.webservices.repository.pojo.User;
import com.tester.spring.rest.webservices.services.UserRepositoryServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class UserResource {
    @Autowired
    private UserRepositoryServices userRepositoryServices;

    @GetMapping(path = "/users")
    public List<User> users() {
        return userRepositoryServices.listUsers();
    }

    @GetMapping(path = "/users/{userId}")
    public User find(@PathVariable Long userId) {
        User user = userRepositoryServices.loadUserByUserId(userId);
        if (user == null) {
            throw new UserNotFoundException("User id: " + userId);
        }
        return user;
    }


    @PostMapping(path = "/users")
    public Resource<User> save(@Valid @RequestBody User user) {
        User rUser = userRepositoryServices.saveUser(user);
        if (rUser == null) {
            throw new UserNotFoundException("User id: " + rUser.getUserId());
        }
        Resource<User> resource = new Resource<>(rUser);

        ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).users());
        resource.add(linkBuilder.withRel("All Users"));
        linkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).find(rUser.getUserId()));
        resource.add(linkBuilder.withRel("Self"));
        return resource;
    }

    @PutMapping(path = "/users")
    public User update(@RequestBody User user) {
        User user1 = userRepositoryServices.updateUser(user);
        if (user1 == null) {
            throw new UserNotFoundException("User id: " + user.getUserId());
        }
        return user1;
    }

    @DeleteMapping(path = "/users/{userId}")
    public User delete(@PathVariable Long userId) {
        User user = userRepositoryServices.deleteUser(userRepositoryServices.loadUserByUserId(userId));
        if (user == null) {
            throw new UserNotFoundException("User id: " + userId);
        }
        return user;
    }


}
