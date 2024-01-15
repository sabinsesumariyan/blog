package com.sabin.app.blogapi.payload;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@NoArgsConstructor
@Getter
@Setter
public class PostResponse {
    private List<PostDTO> content;
    private int pageNumber;
    private  int pageSize;
    private Long totalElements;
    private int totalPages;
    private boolean lastPage;
}
