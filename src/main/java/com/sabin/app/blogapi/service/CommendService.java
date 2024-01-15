package com.sabin.app.blogapi.service;

import com.sabin.app.blogapi.payload.CommendDTO;

public interface CommendService {
    CommendDTO createCommend(CommendDTO commendDTO,Integer postId);
    void deleteCommend(Integer commendId);
}
