package com.sabin.app.blogapi.payload;

import com.sabin.app.blogapi.entity.Category;
import com.sabin.app.blogapi.entity.Commend;
import com.sabin.app.blogapi.entity.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
public class PostDTO {
    private Integer postId;
    private String title;
    private String content;
    private String imageName;
    private Date addedDate;
    private UserDTO user;
    private CategoryDTO category;
    private Set<CommendDTO> commends = new HashSet<>();
}
