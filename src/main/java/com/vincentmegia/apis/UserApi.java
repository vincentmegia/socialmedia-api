package com.vincentmegia.apis;

import com.vincentmegia.models.User;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.*;

public interface UserApi {
    @GetMapping("/{id}")
    HttpEntity<User> getById(@PathVariable long id) throws Exception;

    @PostMapping
    HttpEntity<User> add(@RequestBody User user) throws Exception;

    @PatchMapping("/{id}")
    HttpEntity<User> update(@PathVariable long id, @RequestBody User user) throws Exception;
}
