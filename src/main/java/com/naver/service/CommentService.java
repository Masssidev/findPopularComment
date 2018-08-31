package com.naver.service;

import java.util.List;

import com.naver.dto.Comment;

public interface CommentService {
	public abstract List<Comment> findCommentsByPostId(int postId, int pg);
}
