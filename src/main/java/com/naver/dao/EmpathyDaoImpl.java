package com.naver.dao;

import javax.annotation.Resource;

import org.springframework.data.redis.core.ListOperations;
import org.springframework.data.redis.core.SetOperations;
import org.springframework.stereotype.Repository;

/**
 * EmpathyDao Implementation
 */
@Repository
public class EmpathyDaoImpl implements EmpathyDao {
	@Resource(name = "redisTemplate")
	private ListOperations<String, Integer> listOperations;

	@Resource(name = "redisTemplate")
	private SetOperations<String, Integer> setOperations;

	@Override
	public void insertEmpathy(int userId, int commentId) {
		if (setOperations.isMember("empathyUsers:" + userId, commentId))
			// TODO logger로 변경
			System.out.println("이미 공감한 댓글입니다.");
		else {
			listOperations.rightPush("empathyComments", commentId);
			setOperations.add("empathyUsers:" + userId, commentId);
		}
	}

}
