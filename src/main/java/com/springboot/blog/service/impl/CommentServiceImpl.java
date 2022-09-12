package com.springboot.blog.service.impl;

import com.springboot.blog.dto.CommentDto;
import com.springboot.blog.entity.Comment;
import com.springboot.blog.entity.Post;
import com.springboot.blog.exception.BlogAPIException;
import com.springboot.blog.exception.ResourceNotFoundException;
import com.springboot.blog.repository.CommentRepository;
import com.springboot.blog.repository.PostRepository;
import com.springboot.blog.service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper mapper;

    @Override
    public CommentDto createComment(long postId, CommentDto commentDto) {
        Comment comment = mapToEntity(commentDto);

        //retrieve post entity by their id
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

        //set post to comment entiy
        comment.setPost(post);

        //save comment entity to db
        Comment newComment = commentRepository.save(comment);

        return maptoDto(newComment);
    }

    @Override
    public List<CommentDto> getAllComments(long postId) {
        List<CommentDto> commentDtos = commentRepository.findAllByPostId(postId).stream().map(comment-> maptoDto(comment)).collect(Collectors.toList());
        return commentDtos;

    }

    @Override
    public CommentDto getCommentById(long postId, long commentId) {

        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));

        if(!comment.getPost().equals(post)){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "the comment does not belong to post.");
        }

        return maptoDto(comment);
    }

    @Override
    public CommentDto updateComment(CommentDto commentDto,long postId,  long commentId) {
        //validate the comment and post
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));

        if(!comment.getPost().equals(post)){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "the comment does not belong to post.");
        }
        //update the comment
        comment.setName(commentDto.getName());
        comment.setBody(commentDto.getBody());
        comment.setEmail(commentDto.getEmail());

        Comment newComment = commentRepository.save(comment);
        return maptoDto(newComment);
    }

    @Override
    public void deleteCommentById(long postId, long commentId) {
        //validate the comment and post
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post", "id", String.valueOf(postId)));

        Comment comment = commentRepository.findById(commentId).orElseThrow(()-> new ResourceNotFoundException("Comment", "id", String.valueOf(commentId)));

        if(!comment.getPost().equals(post)){
            throw new BlogAPIException(HttpStatus.BAD_REQUEST, "the comment does not belong to post.");
        }
        //delete comment
        commentRepository.delete(comment);
    }

    private CommentDto maptoDto(Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setId(comment.getId());
//        commentDto.setName(comment.getName());
//        commentDto.setEmail(comment.getEmail());
//        commentDto.setBody(comment.getBody());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto,Comment.class);
//        Comment comment = new Comment();
////        comment.setId(commentDto.getId());
//        comment.setName(commentDto.getName());
//        comment.setEmail(commentDto.getEmail());
//        comment.setBody(commentDto.getBody());
        return comment;
    }
}
