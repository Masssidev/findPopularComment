package com.naver.service;

import java.util.HashMap;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ListOperations;
import org.springframework.stereotype.Service;

import com.naver.dao.EmpathyDao;
import com.naver.mapper.EmpathyMapper;

/**
 * EmpathyService Implementation
 */
@Service
public class EmpathyServiceImpl implements EmpathyService {
	@Autowired
	private EmpathyDao empathyDao;
	
	@Autowired
	private EmpathyMapper empathyMapper;

	@Resource(name = "redisTemplate")
	ListOperations<String, Integer> listOperations;

	@Override
	public void insertEmpathy(int postId, int commentId) {
		empathyDao.insertEmpathy(postId, commentId);
	}

	@Override
	public void recordEmpathyCount() {
		HashMap<Integer, Integer> empathyCount = resultEmpathyCount();

		if(empathyCount.isEmpty())
			return;
		else
			empathyMapper.updateEmpathyCount(empathyCount);
	}

	private HashMap<Integer, Integer> resultEmpathyCount() {

		HashMap<Integer, Integer> empathyCount = new HashMap<Integer, Integer>();

		Integer commentId;

		for (int i = 0; i < 3000; ++i) {
			commentId = listOperations.leftPop("empathyComments");
			System.out.println(commentId);
			if (commentId == null)
				return empathyCount;

			if (empathyCount.containsKey(commentId))
				empathyCount.replace(commentId, empathyCount.get(commentId) + 1);
			else
				empathyCount.put(commentId, 1);

		}
		return empathyCount;
	}
}
