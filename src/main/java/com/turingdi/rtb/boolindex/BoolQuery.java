package com.turingdi.rtb.boolindex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.turingdi.rtb.boolindex.entity.Activity;
import com.turingdi.rtb.boolindex.entity.Assignment;
import com.turingdi.rtb.boolindex.entity.Conjunction;
import com.turingdi.rtb.boolindex.entity.Posting;
import com.turingdi.rtb.boolindex.entity.Query;

public class BoolQuery {
	public List<Activity> boolQuery(Map<Conjunction, List<Activity>> primaryIndex,
			Map<Integer, Map<Assignment, List<Posting>>> secondaryIndex, Query bidQuery){
		List<Activity> result = new ArrayList<Activity>();
		
		return result;
	}
}
