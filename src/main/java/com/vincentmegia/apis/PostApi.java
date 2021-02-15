package com.vincentmegia.apis;

import com.vincentmegia.models.Post;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


public interface PostApi {
    @GetMapping("/{id}")
    HttpEntity<Post> getById(@PathVariable long id) throws Exception;

    @GetMapping
    CollectionModel<Post> getAll() throws Exception;

    @PostMapping
    HttpEntity<Post> add(@RequestBody Post post);
}
