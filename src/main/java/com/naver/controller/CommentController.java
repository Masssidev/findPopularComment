package com.naver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.naver.dto.Comment;
import com.naver.service.CommentService;

@RestController
public class CommentController {
	@Autowired
	private CommentService commentService;

	@GetMapping(value = "comments/{postId}/{pg}")
	public ResponseEntity<List<Comment>> findCommentsByPostId(@PathVariable int postId, @PathVariable int pg) {

		final List<Comment> commentsByPostId = commentService.findCommentsByPostId(postId, pg);

		if (commentsByPostId.isEmpty()) {
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Comment>>(commentsByPostId, HttpStatus.OK);
	}
}
