package com.blog.blog.service.impl;

import com.blog.blog.dto.PostBasicDTO;
import com.blog.blog.dto.PostDTO;
import com.blog.blog.dto.PostFiltersDTO;
import com.blog.blog.entity.PostEntity;
import com.blog.blog.exception.ParamNotFound;
import com.blog.blog.mapper.PostMapper;
import com.blog.blog.repository.PostRepository;
import com.blog.blog.repository.specification.PostSpecification;
import com.blog.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostMapper postMapper;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostSpecification postSpecification;

    @Override
    public PostDTO getPost(Long id) {
        Optional<PostEntity> entity = postRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Invalid post id");
        }
        PostDTO result = postMapper.postEntity2DTO(entity.get());
        return result;
    }

    @Override
    public PostDTO save(PostDTO dto) {
        PostEntity entity = postMapper.postDTO2Entity(dto);
        PostEntity entitySaved = postRepository.save(entity);
        PostDTO result = postMapper.postEntity2DTO(entitySaved);
        return result;
    }

    @Override
    public List<PostBasicDTO> getByFilters(String title, String category) {
        PostFiltersDTO filtersDTO = new PostFiltersDTO(title, category);
        List<PostEntity> entities = postRepository.findAll(postSpecification.getByFilters(filtersDTO));
        List<PostBasicDTO> dtos = postMapper.postEntityList2BasicDTOList(entities);
        return dtos;
    }

    @Override
    public void delete(Long id) {
        Optional<PostEntity> entity = postRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Invalid post id");
        }
        postRepository.deleteById(id);
    }

    @Override
    public PostDTO update(Long id, PostDTO postDTO) {
        Optional<PostEntity> entity = postRepository.findById(id);
        if (!entity.isPresent()) {
            throw new ParamNotFound("Error: Invalid post id");
        }
        postMapper.postEntityRefreshValues(entity.get(), postDTO);
        PostEntity entitySaved = postRepository.save(entity.get());
        PostDTO result = postMapper.postEntity2DTO(entitySaved);
        return result;
    }
}
