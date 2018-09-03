package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.naver.dto.Comment;
import com.naver.mapper.CommentMapper;

/**
 * CommentService Implementation
 */
@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;

	@Override
	@Cacheable(value = "comments", key = "#pg")
	public List<Comment> findCommentsByPostId(int postId, int pg) {
		return commentMapper.findCommentsByPostId(postId, pg);
	}
}
