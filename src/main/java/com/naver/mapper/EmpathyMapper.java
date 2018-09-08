package com.naver.mapper;

import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * Mapper for Empathy
 */
@Mapper
public interface EmpathyMapper {
	public abstract void updateEmpathyCount(@Param("entries") HashMap<Integer, Integer> empathyCount);
}
