package com.sabin.app.blogapi.controller;

import com.sabin.app.blogapi.config.AppConstants;
import com.sabin.app.blogapi.payload.ApiResponse;
import com.sabin.app.blogapi.payload.PostDTO;
import com.sabin.app.blogapi.payload.PostResponse;
import com.sabin.app.blogapi.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    public ResponseEntity<PostDTO> createPost(@RequestBody PostDTO postDTO, @PathVariable("userId") Integer userId,@PathVariable("categoryId") Integer categoryId){
        PostDTO createPostDto = this.postService.createPost(postDTO,userId,categoryId);
        return new ResponseEntity<>(createPostDto, HttpStatus.CREATED);
    }

    @GetMapping("/category/{categoryId}/posts")
    public ResponseEntity<List<PostDTO>> getByCategory(@PathVariable("categoryId") Integer categoryId){
        List<PostDTO> postDTOS = postService.getByCategory(categoryId);
        return ResponseEntity.ok(postDTOS);
    }
    @GetMapping("/user/{userId}/posts")
    public ResponseEntity<List<PostDTO>> getByUser(@PathVariable Integer userId){
        List<PostDTO> postDTOS = postService.getByUser(userId);
        return ResponseEntity.ok(postDTOS);
    }
    @GetMapping("/post/{postId}")
    public ResponseEntity<PostDTO> getPost(@PathVariable("postId") Integer postId){
        PostDTO postDTO = postService.getPost(postId);
        return  ResponseEntity.ok(postDTO);
    }
    @GetMapping("/post/allpost")
    public ResponseEntity<PostResponse> getAllPost(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false)String sortBy,
            @RequestParam(value = "sortDir",defaultValue = AppConstants.SORT_DIR,required = false) String sortDir){
        PostResponse postResponse = postService.getAllPost(pageNumber,pageSize,sortBy,sortDir);
        return ResponseEntity.ok(postResponse);
    }
    @DeleteMapping("/post/{postId}")
    public ResponseEntity<ApiResponse> deletePost(@PathVariable("postId") Integer postId){
        postService.deletePost(postId);
        return new ResponseEntity<>(new ApiResponse("Deleted successfully",true),HttpStatus.OK);
    }
    @PutMapping("/post/{postId}")
    public ResponseEntity<PostDTO> updatePost(@RequestBody PostDTO postDTO,@PathVariable("postId") Integer postId){
        PostDTO createPostDto = postService.updatePost(postDTO,postId);
        return ResponseEntity.ok(createPostDto);
    }

    @GetMapping("/post/search/{keyword}")
    public ResponseEntity<List<PostDTO>> searchPost(@PathVariable("keyword") String keyword){
        List<PostDTO> postDTOS = postService.searchPost(keyword);
        return ResponseEntity.ok(postDTOS);
    }

}
