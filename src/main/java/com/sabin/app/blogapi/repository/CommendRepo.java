package com.sabin.app.blogapi.repository;

import com.sabin.app.blogapi.entity.Commend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommendRepo extends JpaRepository<Commend,Integer> {

}
