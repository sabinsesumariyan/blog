package com.sabin.app.blogapi.service.impl;

import com.sabin.app.blogapi.entity.Commend;
import com.sabin.app.blogapi.entity.Post;
import com.sabin.app.blogapi.exception.ResourceNotFoundException;
import com.sabin.app.blogapi.payload.CommendDTO;
import com.sabin.app.blogapi.repository.CommendRepo;
import com.sabin.app.blogapi.repository.PostRepo;
import com.sabin.app.blogapi.service.CommendService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommendServiceImpl implements CommendService {
    @Autowired
    private PostRepo postRepo;
    @Autowired
    private CommendRepo commendRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommendDTO createCommend(CommendDTO commendDTO, Integer postId) {
        Post post = this.postRepo.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post","post Id",postId));
        Commend commend = this.dtoTocommend(commendDTO);
        commend.setPost(post);
        Commend savedCommend = this.commendRepo.save(commend);
        return commendToDto(savedCommend);
    }

    @Override
    public void deleteCommend(Integer commendId) {
        Commend commend = this.commendRepo.findById(commendId).orElseThrow(()-> new ResourceNotFoundException("commend","commend Id",commendId));
        this.commendRepo.deleteById(commendId);
    }




    public Commend dtoTocommend(CommendDTO commendDTO){
        Commend commend = this.modelMapper.map(commendDTO,Commend.class);
        return commend;
    }

    public  CommendDTO commendToDto(Commend commend){
        CommendDTO commendDTO = this.modelMapper.map(commend,CommendDTO.class);
        return commendDTO;
    }

}
