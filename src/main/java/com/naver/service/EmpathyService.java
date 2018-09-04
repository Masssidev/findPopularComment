package com.naver.service;

/**
 * EmpathyService interface
 */
public interface EmpathyService {
	/**
	 * @param postId
	 * @param commentId
	 */
	public abstract void insertEmpathy(int postId, int commentId);
	
	public abstract void recordEmpathyCount();
}
