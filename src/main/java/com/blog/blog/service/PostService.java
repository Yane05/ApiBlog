package com.blog.blog.service;

import com.blog.blog.dto.PostBasicDTO;
import com.blog.blog.dto.PostDTO;

import java.util.List;

public interface PostService {

    PostDTO getPost(Long id);

    PostDTO save(PostDTO post);

    List<PostBasicDTO> getByFilters(String title, String category);

    void delete(Long id);

    PostDTO update(Long id, PostDTO postDTO);
}
