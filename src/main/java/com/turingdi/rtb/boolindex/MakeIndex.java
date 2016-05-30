package com.turingdi.rtb.boolindex;

import java.util.List;
import java.util.Map;

public class MakeIndex {
	/**
	 * 将一个活动追加到索引中
	 * @param primaryIndex
	 * @param act
	 */
	public void appendIndex(Map<Integer, List<String>> primaryIndex, Activity act){
		List<Conjunction> conjList = analysisConjunction(act);
		StringBuffer keyBuf = new StringBuffer();
		for(int key : primaryIndex.keySet()){
		}
	}

	private List<Conjunction> analysisConjunction(Activity act) {
		// TODO Auto-generated method stub
		return null;
	}
}
