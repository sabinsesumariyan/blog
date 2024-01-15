package com.sabin.app.blogapi.repository;

import com.sabin.app.blogapi.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepo extends JpaRepository<Category,Integer> {
    @Query(value = "select * from category join post on category_id = post.category_category_id where post_id=1;",nativeQuery = true)
    Category getExtra();
}
