package com.naver.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.naver.dto.Comment;

/**
 * Mapper for Comment DTO
 */
@Mapper
public interface CommentMapper {
	public abstract List<Comment> findCommentsByPostId(int postId, int pg);
}
