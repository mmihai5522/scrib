package com.scrib.scrib.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository
        extends JpaRepository<Comment,Long> {

    @Query("SELECT c FROM Comment c WHERE c.body=?1")
    Optional<Comment> findByBody(String body);
}
