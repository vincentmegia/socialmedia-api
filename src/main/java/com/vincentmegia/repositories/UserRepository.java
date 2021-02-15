package com.vincentmegia.repositories;

import com.vincentmegia.models.User;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface UserRepository extends PagingAndSortingRepository<User, Long> {

    @Transactional
    @Modifying
    @Query(nativeQuery = true, value = "UPDATE users u SET u.username = :username WHERE u.id = :id")
    int updateUsername(@Param("id") long id, @Param("username") String username);
}
