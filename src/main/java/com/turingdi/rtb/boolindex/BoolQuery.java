package com.turingdi.rtb.boolindex;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.turingdi.rtb.boolindex.entity.Activity;
import com.turingdi.rtb.boolindex.entity.Assignment;
import com.turingdi.rtb.boolindex.entity.Conjunction;
import com.turingdi.rtb.boolindex.entity.PostList;
import com.turingdi.rtb.boolindex.entity.Query;

public class BoolQuery {
	/**
	 * @param primaryIndex 一级索引
	 * @param secondaryIndex 二级索引
	 * @param bidQuery 查询
	 * @return 当前查询所满足的所有活动
	 */
	public List<Activity> boolQuery(Map<Conjunction, List<Activity>> primaryIndex,
			Map<Integer, LinkedHashMap<Assignment, PostList>> secondaryIndex, Query bidQuery){
		List<Activity> result = new ArrayList<Activity>();
		
		return result;
	}
}
