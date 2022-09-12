package com.springboot.blog.service;

import com.springboot.blog.dto.CommentDto;

import java.util.List;

public interface CommentService {

    CommentDto createComment(long postId, CommentDto Commentdto);

    List<CommentDto> getAllComments(long id);

    CommentDto getCommentById(long postId, long commentId);

    CommentDto updateComment(CommentDto commentDto,long postId,  long commentId);

    void deleteCommentById(long postId, long commentId);
}
