package com.vincentmegia.controllers;

import com.vincentmegia.apis.UserApi;
import com.vincentmegia.models.User;
import com.vincentmegia.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/users")
public class UserController implements UserApi {

    @Autowired
    private UserRepository userRepository;

    public HttpEntity<User> getById(@PathVariable long id) throws Exception {
        var userOptional = this.userRepository.findById(id);
        if (!userOptional.isPresent()) {
            throw new Exception("");
        }
        var user = userOptional.get();
        user.add(linkTo(methodOn(UserController.class)
                .getById(id))
                .withSelfRel());
        user.add(linkTo(methodOn(PostController.class)
                .getAll())
                .withSelfRel()
                .withRel("posts"));
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    public HttpEntity<User> add(@RequestBody User user) throws Exception {
        var result = this.userRepository.save(user);
        result.add(linkTo(methodOn(UserController.class)
                .add(user))
                .withSelfRel());
        result.add(linkTo(methodOn(UserController.class)
                .getById(user.getId()))
                .withRel("users"));
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    public HttpEntity<User> update(@PathVariable long id, @RequestBody User user) throws Exception {
        user.setId(id);
        var result = this.userRepository.updateUsername(id, user.getUsername());
        user.setUsername(user.getUsername());

        user.add(linkTo(methodOn(UserController.class)
                .update(id, user))
                .withSelfRel());
        user.add(linkTo(methodOn(UserController.class)
                .getById(user.getId()))
                .withRel("users"));
        user.add(linkTo(methodOn(UserController.class)
                .add(user))
                .withRel("posts"));
        return new ResponseEntity<>(user, HttpStatus.ACCEPTED);
    }
}
