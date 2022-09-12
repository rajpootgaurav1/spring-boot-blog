package com.springboot.blog.controller;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.service.CommentService;
import com.springboot.blog.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private PostService postService;

    @PostMapping(path = "posts/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable(name = "postId") long id){
        return new ResponseEntity<>(commentService.createComment(id, commentDto), HttpStatus.CREATED);
    }

    @GetMapping(path = "posts/{postId}/comments")
    public ResponseEntity<List<CommentDto>> getAllComments(@PathVariable(name= "postId") long id) {
        return new ResponseEntity<List<CommentDto>>(commentService.getAllComments(id), HttpStatus.OK);
    }

    @GetMapping(path = "posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> getCommentById(@PathVariable(name= "postId") long postId, @PathVariable(name= "commentId") long commentId) {
        return new ResponseEntity<CommentDto>(commentService.getCommentById(postId, commentId), HttpStatus.OK);
    }

    @PutMapping(path = "posts/{postId}/comments/{commentId}")
    public ResponseEntity<CommentDto> updateComment(@RequestBody CommentDto commentDto, @PathVariable(name= "postId") long postId, @PathVariable(name= "commentId") long commentId) {
        return new ResponseEntity<CommentDto>(commentService.updateComment(commentDto, postId, commentId), HttpStatus.OK);
//        return ResponseEntity.ok(commentService.updateComment(commentDto, commentId));
    }

    //delete comment based on comment-id
    @DeleteMapping(path = "posts/{postId}/comments/{commentId}")
    public ResponseEntity<String> deleteCommentById(@PathVariable(name= "postId") long postId, @PathVariable(name= "commentId") long commentId) {
        commentService.deleteCommentById(postId, commentId);
        return new ResponseEntity<>("comment deleted successfully", HttpStatus.OK);
    }
}
