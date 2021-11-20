package com.blog.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostBasicDTO {

    private Long id;
    private String title;
    private String image;
    private String category;
    private String creationDate;
}
