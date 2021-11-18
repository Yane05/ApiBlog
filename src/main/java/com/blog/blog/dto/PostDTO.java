package com.blog.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PostDTO {

    private Long id;
    private String title;
    private String content;
    private String image;
    private String category;
    private String creationDate;
    private Long creatorUserId;
}
