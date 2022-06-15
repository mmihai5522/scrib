package com.scrib.scrib.comments;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommentRepository
        extends JpaRepository<Comment,Long> {

    Optional<Comment> findByBody(String body);
}
