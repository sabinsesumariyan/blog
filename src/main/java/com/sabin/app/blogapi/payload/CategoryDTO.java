package com.sabin.app.blogapi.payload;

import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;
import java.util.TreeMap;

@NoArgsConstructor
@Getter
@Setter
public class CategoryDTO {
    private Integer categoryId;
    @NotEmpty(message = "Give the correct title")
    private String categoryTitle;
    @NotEmpty(message = "Give the correct description")
    private String categoryDescription;

}
