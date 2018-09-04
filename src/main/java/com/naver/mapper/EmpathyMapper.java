package com.naver.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper for Empathy
 */
@Mapper
public interface EmpathyMapper {
	public abstract void updateEmpathyCount(HashMap<Integer, Integer> EmpathyCountMap);
}
