package com.tester.spring.rest.webservices.controller;

import com.tester.spring.rest.webservices.exception.UserNotFoundException;
import com.tester.spring.rest.webservices.repository.PostRepository;
import com.tester.spring.rest.webservices.repository.UserRepository;
import com.tester.spring.rest.webservices.repository.pojo.Post;
import com.tester.spring.rest.webservices.repository.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.mvc.ControllerLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
public class UserJPAResource {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping(path = "/Jpa/users")
    public List<User> users() {
        return userRepository.findAll();
    }

    @GetMapping(path = "/Jpa/users/{userId}")
    public User find(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User id: " + userId);
        }
        return user.get();
    }


    @PostMapping(path = "/Jpa/users")
    public Resource<User> save(@Valid @RequestBody User user) {
        User rUser = userRepository.saveAndFlush(user);
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

    @PutMapping(path = "/Jpa/users")
    public User update(@RequestBody User user) {
        User user1 = userRepository.saveAndFlush(user);
        if (user1 == null) {
            throw new UserNotFoundException("User id: " + user.getUserId());
        }
        return user1;
    }

    @DeleteMapping(path = "/Jpa/users/{userId}")
    public ResponseEntity.BodyBuilder delete(@PathVariable Long userId) {
        userRepository.deleteById(userId);
        return ResponseEntity.ok();
    }


    @GetMapping(path = "/Jpa/users/{userId}/posts")
    public List<Post> findPostById(@PathVariable Long userId) {
        Optional<User> user = userRepository.findById(userId);
        if (!user.isPresent()) {
            throw new UserNotFoundException("User id: " + userId);
        }
        return user.get().getPostList();
    }

    @PostMapping(path = "/Jpa/users/{userId}/post")
    public Resource<Post> savePost(@PathVariable Long userId, @RequestBody Post post) {
        Optional<User> rUser = userRepository.findById(userId);
        if (!rUser.isPresent()) {
            throw new UserNotFoundException("User id: " + userId);
        }
        post.setUser(rUser.get());
        postRepository.save(post);
        Resource<Post> resource = new Resource<Post>(post);
        ControllerLinkBuilder linkBuilder = ControllerLinkBuilder.linkTo(ControllerLinkBuilder.methodOn(this.getClass()).findPostById(userId));
        resource.add(linkBuilder.withSelfRel());
        return resource;
    }


}
