package com.blog.blog.controller;


import com.blog.blog.dto.PostBasicDTO;
import com.blog.blog.dto.PostDTO;
import com.blog.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity<PostDTO> save (@RequestBody PostDTO post){
        PostDTO postSaved = postService.save(post);
        return ResponseEntity.status(HttpStatus.CREATED).body(postSaved);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PostDTO> getPostById(@PathVariable Long id) {
        PostDTO post = postService.getPost(id);
        return ResponseEntity.ok().body(post);
    }

    @GetMapping
    public ResponseEntity<List<PostBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam (required = false) String category
    ){
        List<PostBasicDTO> posts = postService.getByFilters(title,category);
        return ResponseEntity.ok(posts);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PostDTO> update(@PathVariable Long id, @RequestBody PostDTO postDTO){
        PostDTO result = postService.update(id, postDTO);
        return ResponseEntity.ok().body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id){
        postService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
