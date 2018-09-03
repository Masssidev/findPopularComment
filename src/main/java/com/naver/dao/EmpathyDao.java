package com.naver.dao;

/**
 * EmpathyDao interface
 */
public interface EmpathyDao {

	/**
	 * @param userId
	 * @param commentId
	 */
	public abstract void insertEmpathy(int userId, int commentId);
}
