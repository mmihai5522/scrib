package com.scrib.scrib.comments;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CommentService {


    private final CommentRepository commentRepository;
    @Autowired
    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public List<Comment> getComments() {
        return commentRepository.findAll();
    }

    public void addNewComment(Comment comment) {
        commentRepository.saveAndFlush(comment);
    }


    public void deleteComment(Long commentId) {
        boolean isPresent= commentRepository.existsById(commentId);

        if (!isPresent){
            throw new IllegalStateException("comment with id: " + commentId + " does not exists!");
        }else {
           commentRepository.deleteById(commentId);
        }

    }

    @Transactional
    public void modifyCommentInformation(Long commentId, String body) {
        Comment fetchedComment= commentRepository.findById(commentId)
                .orElseThrow(()->
                        new IllegalStateException("Comment with id: "+ commentId +" does not exist to modify it"));

        if (body !=null && body.length() > 0 &&
                !Objects.equals(fetchedComment.getBody(),body)){
            Optional<Comment> commentFoundByBody=commentRepository.findByBody(body);
            if (commentFoundByBody.isPresent()){
                throw new IllegalStateException(
                        "Comment with id: "+ commentId +" does not exist to be modified" );
            }
            fetchedComment.setBody(body);
        }

    }
}
