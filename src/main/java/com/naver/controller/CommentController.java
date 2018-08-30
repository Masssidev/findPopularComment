package com.naver.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.naver.dto.Comment;

@RestController
public class CommentController {
	@Autowired 
	private CommnetService commentService;

	@GetMapping("{postId}")
	public ResponseEntity<List<Comment>> findCommentsByPostId(@PathVariable int postId) {

		final List<Comment> commentsByPostId = commentService.findByPostId(postId);

		if (commentsByPostId.isEmpty()) {
			return new ResponseEntity<List<Comment>>(HttpStatus.NO_CONTENT);
		}

		return new ResponseEntity<List<Comment>>(commentsByPostId, HttpStatus.OK);
	}
}
