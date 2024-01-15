package com.sabin.app.blogapi.repository;

import com.sabin.app.blogapi.entity.Category;
import com.sabin.app.blogapi.entity.Post;
import com.sabin.app.blogapi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post,Integer> {
    @Query(value = "select * from post join user on post.post_id=user.id",nativeQuery = true)
    List<Post> findByUser(User user);
    @Query(value = "select * from post join category on post.post_id=category.category_id",nativeQuery = true)
    List<Post> findByCategory(Category category);

    List<Post> findByTitleContaining(String title);

}
