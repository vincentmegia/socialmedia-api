package com.vincentmegia.controllers;

import com.vincentmegia.apis.PostApi;
import com.vincentmegia.models.Post;
import com.vincentmegia.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;
import javax.transaction.Transactional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/posts")
@EnableTransactionManagement
public class PostController implements PostApi {

    @Autowired
    private PostRepository postRepository;

    public HttpEntity<Post> getById(@PathVariable long id) throws Exception {
        var postOptional = this.postRepository.findById(id);
        if (!postOptional.isPresent())
            throw new Exception("id-" + id);
        Post post = postOptional.get();
        post.add(linkTo(methodOn(PostController.class)
            .getById(id))
            .withSelfRel());

        post.add(linkTo(methodOn(UserController.class)
                .getById(post.getId()))
                .withRel("user"));

        return new ResponseEntity<>(post, HttpStatus.OK);
    }

    public CollectionModel<Post> getAll() throws Exception {
        var posts = this.postRepository.findAll();

        for (Post post : posts) {
            post.add(linkTo(methodOn(PostController.class)
                    .getById(post.getId()))
                    .withSelfRel());

            post.add(linkTo(methodOn(UserController.class)
                    .getById(post.getId()))
                    .withRel("user"));
        }

        return CollectionModel.of(posts)
                .add(linkTo(methodOn(PostController.class)
                        .getAll())
                        .withRel("posts"));
    }

    @Transactional
    public HttpEntity<Post> add(@RequestBody Post post) {
        var id = this.postRepository.save(post);
        return new ResponseEntity<>(new Post(), HttpStatus.CREATED);
    }
}
