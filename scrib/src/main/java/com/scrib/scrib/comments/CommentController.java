package com.scrib.scrib.comments;

import com.scrib.scrib.article.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/comment")
public class CommentController {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


    @GetMapping(path = "all")
    public List<Comment> getComments() {
        return commentService.getComments();
    }

    @PostMapping(path = "commenting")
    public void registerNewComment(
            @RequestBody Comment comment){
        commentService.addNewComment(comment);
    }

    @DeleteMapping(path = "{commentId}")
    public void deleteCommentById(@PathVariable("commentId") Long commentId){
        commentService.deleteComment(commentId);
    }

    @PutMapping(path = "{commentId}")
    public void updateCommentInfo(
            @PathVariable("commentId") Long commentId
            ,@RequestParam(required= false) String body){

        commentService.modifyCommentInformation(commentId,body);
    }

}
