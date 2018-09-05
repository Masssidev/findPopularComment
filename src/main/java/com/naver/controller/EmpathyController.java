package com.naver.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.naver.service.EmpathyService;

/**
 * EmpathyController class
 */
@RestController
public class EmpathyController {
	@Autowired
	private EmpathyService empathyService;

	/**
	 * insert a empathy
	 * 
	 * @param userId
	 * @param commentId
	 * @return ResponseEntity<Void>
	 */
	@GetMapping(value = "doEmpathy/{userId}/{commentId}")
	public ResponseEntity<Void> doEmpathy(@PathVariable final int userId, @PathVariable final int commentId) {
		empathyService.insertEmpathy(userId, commentId);

		return new ResponseEntity<Void>(HttpStatus.OK);
	}
	
	@GetMapping(value = "start")
	public void start() {
		empathyService.recordEmpathyCount();
	}
}
