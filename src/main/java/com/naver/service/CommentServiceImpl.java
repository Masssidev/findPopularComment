package com.naver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dto.Comment;
import com.naver.mapper.CommentMapper;

@Service
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentMapper commentMapper;

	@Override
	public List<Comment> findCommentsByPostId(int postId, int pg) {
		return commentMapper.findCommentsByPostId(postId, pg);
	}
}
