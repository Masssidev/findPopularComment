package com.naver.service;

import java.util.List;

import com.naver.dto.Comment;

/**
 * CommentService interface
 */
public interface CommentService {
	
	/**
	 * @return the list of all comments by post ID
	 */
	public abstract List<Comment> findCommentsByPostId(int postId, int pg);
}
