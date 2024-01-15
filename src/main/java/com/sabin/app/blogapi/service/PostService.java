package com.sabin.app.blogapi.service;

import com.sabin.app.blogapi.payload.PostDTO;
import com.sabin.app.blogapi.payload.PostResponse;

import java.util.List;

public interface PostService {

    PostDTO createPost(PostDTO postDTO,Integer userId,Integer categoryId);
    PostDTO updatePost(PostDTO postDTO,Integer postId);
    void deletePost(Integer postId);
    PostDTO getPost(Integer postId);
    PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    List<PostDTO> getByCategory(Integer categoryId);
    List<PostDTO> getByUser(Integer userId);
    List<PostDTO> searchPost(String keyword);
}
