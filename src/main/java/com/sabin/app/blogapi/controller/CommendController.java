package com.sabin.app.blogapi.controller;

import com.sabin.app.blogapi.payload.ApiResponse;
import com.sabin.app.blogapi.payload.CommendDTO;
import com.sabin.app.blogapi.service.CommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class CommendController {
    @Autowired
    private CommendService commendService;
    @PostMapping("/post/{postId}/commends")
    public ResponseEntity<CommendDTO> createCommend(@RequestBody CommendDTO commendDTO, @PathVariable("postId") Integer postId){
        CommendDTO createdCommandDTO = this.commendService.createCommend(commendDTO,postId);
        return new ResponseEntity<>(createdCommandDTO, HttpStatus.CREATED);
    }
    @DeleteMapping("/commends/{commendId}")
    public ResponseEntity<ApiResponse> deleteCommend(@PathVariable("commendId") Integer commendId){
        this.commendService.deleteCommend(commendId);
        return new ResponseEntity<>(new ApiResponse("Commend Deleted successfully",true),HttpStatus.OK);
    }


}
