package com.blog.blog.mapper;

import com.blog.blog.dto.PostBasicDTO;
import com.blog.blog.dto.PostDTO;
import com.blog.blog.entity.PostEntity;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PostMapper {

    public PostEntity postDTO2Entity(PostDTO dto) {
        PostEntity postEntity = new PostEntity();
        postEntity.setTitle(dto.getTitle());
        postEntity.setContent(dto.getContent());
        postEntity.setImage(dto.getImage());
        postEntity.setCategory(dto.getCategory());
        postEntity.setCreationDate(string2LocalDate(dto.getCreationDate()));
        postEntity.setCreatorUserId(dto.getCreatorUserId());
        return postEntity;
    }

    public PostDTO postEntity2DTO(PostEntity entity) {
        PostDTO dto = new PostDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setImage(entity.getImage());
        dto.setCategory(entity.getCategory());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setCreatorUserId(entity.getCreatorUserId());
        return dto;
    }

    public List<PostBasicDTO> postEntityList2BasicDTOList(List<PostEntity> entities) {
        List<PostBasicDTO> dtos = new ArrayList<>();
        PostBasicDTO basicDTO;
        for (PostEntity entity : entities) {
            basicDTO = new PostBasicDTO();
            basicDTO.setId(entity.getId());
            basicDTO.setTitle(entity.getTitle());
            basicDTO.setImage(entity.getImage());
            basicDTO.setCategory(entity.getCategory());
            basicDTO.setCreationDate(entity.getCreationDate().toString());
            dtos.add(basicDTO);
        }
        return dtos;
    }

    /*public List<PostDTO> postEntityList2DTOList(List<PostEntity> entities) {
        List<PostDTO> dtos = new ArrayList<>();
        for (PostEntity entity : entities) {
            dtos.add(postEntity2DTO(entity));
        }
        return dtos;
    }*/

    public void postEntityRefreshValues(PostEntity postEntity, PostDTO dto) {
        postEntity.setTitle(dto.getTitle());
        postEntity.setContent(dto.getContent());
        postEntity.setImage(dto.getImage());
        postEntity.setCategory(dto.getCategory());
        postEntity.setCreationDate(string2LocalDate(dto.getCreationDate()));
        postEntity.setCreatorUserId(dto.getCreatorUserId());
    }

    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }
}
