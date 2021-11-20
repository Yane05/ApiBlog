package com.blog.blog.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PostFiltersDTO {
    private String title;
    private String category;

    public PostFiltersDTO(String title, String category) {
        this.title = title;
        this.category = category;
    }
}
