package com.vincentmegia.repositories;

import com.vincentmegia.models.Post;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends PagingAndSortingRepository<Post, Long> {
    @Query(nativeQuery = true, value = "SELECT * FROM Posts p WHERE p.user_id = :userid")
    List<Post> findByUserId(@Param("userid") long userid);
}
