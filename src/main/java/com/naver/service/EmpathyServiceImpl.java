package com.naver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.naver.dao.EmpathyDao;

/**
 * EmpathyService Implementation
 */
@Service
public class EmpathyServiceImpl implements EmpathyService {
	@Autowired
	private EmpathyDao empathyDao;

	@Override
	public void insertEmpathy(int postId, int commentId) {
		empathyDao.insertEmpathy(postId, commentId);
	}
}
