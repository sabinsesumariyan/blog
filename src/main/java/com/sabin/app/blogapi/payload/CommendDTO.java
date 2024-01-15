package com.sabin.app.blogapi.payload;

import com.sabin.app.blogapi.entity.Post;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CommendDTO {
    private  int id;
    private String content;
}
