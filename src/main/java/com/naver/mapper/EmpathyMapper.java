package com.naver.mapper;

import java.util.ArrayList;
import java.util.HashMap;

import org.apache.ibatis.annotations.Mapper;

/**
 * Mapper for Empathy
 */
@Mapper
public interface EmpathyMapper {
	public abstract void updateEmpathyCount(ArrayList<HashMap<Integer, Integer>> empathyData);
}
