package com.sabin.app.blogapi.service.impl;

import com.sabin.app.blogapi.entity.Category;
import com.sabin.app.blogapi.entity.Post;
import com.sabin.app.blogapi.entity.User;
import com.sabin.app.blogapi.exception.ResourceNotFoundException;
import com.sabin.app.blogapi.payload.PostDTO;
import com.sabin.app.blogapi.payload.PostResponse;
import com.sabin.app.blogapi.repository.CategoryRepo;
import com.sabin.app.blogapi.repository.PostRepo;
import com.sabin.app.blogapi.repository.UserRepo;
import com.sabin.app.blogapi.service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CategoryRepo categoryRepo;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private ModelMapper modelMapper;


    @Override
    public PostDTO createPost(PostDTO postDTO, Integer userId, Integer categoryId) {
        User user = this.userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("user","user id",userId));
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","Category id",categoryId));

        Post post = dtoToPost(postDTO);
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        post.setUser(user);
        post.setCategory(category);
        Post newPost=this.postRepo.save(post);
//        post.
        return postToDto(newPost);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("Post","post id",postId));
        // Post post1 = postRepo.findById(postId);
        post.setTitle(postDTO.getTitle());
        post.setContent(postDTO.getContent());
        post.setAddedDate(postDTO.getAddedDate());
        post.setImageName(postDTO.getImageName());
        post.setImageName("default.png");
        post.setAddedDate(new Date());
        Post savedPost = postRepo.save(post);
        return postToDto(savedPost);
    }

    @Override
    public void deletePost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post id",postId));
        postRepo.deleteById(postId);
    }

    @Override
    public PostDTO getPost(Integer postId) {
        Post post = postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post id",postId));
        Optional<String> a2 ;
        return postToDto(post);
    }

    @Override
    public PostResponse getAllPost(Integer pageNumber, Integer pageSize,String sortBy,String sortDir) {
        Sort sort=null;
        if(sortDir.equalsIgnoreCase("asc")){
            sort=Sort.by(sortBy).ascending();
        }
        else {
            sort=Sort.by(sortBy).descending();
        }
        PageRequest p = PageRequest.of(pageNumber,pageSize, sort);
        Page<Post> pagePost = this.postRepo.findAll(p);
        List<Post> allPost = pagePost.getContent();
        List<PostDTO> postDTOS = allPost.stream().map(post->this.postToDto(post)).collect(Collectors.toList());
        PostResponse postResponse= new PostResponse();
        postResponse.setContent(postDTOS);
        postResponse.setPageNumber(pagePost.getNumber());
        postResponse.setPageSize(pagePost.getSize());
        postResponse.setTotalElements(pagePost.getTotalElements());
        postResponse.setTotalPages(pagePost.getTotalPages());
        postResponse.setLastPage(pagePost.isLast());
        return postResponse;
    }

    @Override
    public List<PostDTO> getByCategory(Integer categoryId) {
        Category category = categoryRepo.findById(categoryId).orElseThrow(()-> new ResourceNotFoundException("Category","category id",categoryId));
        List<Post> posts = this.postRepo.findByCategory(category);
        List<PostDTO> postDTOS = posts.stream().map((post)->this.postToDto(post)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> getByUser(Integer userId) {
        User user = userRepo.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User","user id",userId));
        List<Post> posts = this.postRepo.findByUser(user);
        List<PostDTO> postDTOS = posts.stream().map((post)->this.postToDto(post)).collect(Collectors.toList());
        return postDTOS;
    }

    @Override
    public List<PostDTO> searchPost(String keyword) {
        List<Post> posts = this.postRepo.findByTitleContaining(keyword);
        List<PostDTO> postDTOS = posts.stream().map((post)->this.postToDto(post)).collect(Collectors.toList());
        return postDTOS;
    }






    public Post dtoToPost(PostDTO postDTO){
        Post post = modelMapper.map(postDTO,Post.class);
        return  post;
    }
    public PostDTO postToDto(Post post){
        PostDTO postDTO = modelMapper.map(post,PostDTO.class);
        return postDTO;
    }
}
