package com.turingdi.rtb.boolindex;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.turingdi.rtb.boolindex.entity.Activity;
import com.turingdi.rtb.boolindex.entity.Conjunction;

public class MakeIndex {
	/**
	 * 将一个活动追加到索引中
	 *	TODO:  还没考虑到index放在redis中
	 * @param primaryIndex
	 * @param act
	 */
	public void appendIndex(Map<Conjunction, List<Activity>> primaryIndex, Activity act){
		//第一层索引
		List<Conjunction> conjList = analysisConjunction(act);
		for(Conjunction conj : conjList){
			if(primaryIndex.containsKey(conj)){
				//已经包含conjunction，加进list
				primaryIndex.get(conj).add(act);
			} else {
				//新conjunction，建立list并加进去
				List<Activity> newActList = new ArrayList<Activity>();
				newActList.add(act);
				conj.setId(primaryIndex.size());//conjunction的ID单调增，index中的conjunction永不删
				primaryIndex.put(conj, newActList);
			}
		}
		
		//第二层索引
		
	}

	/**
	 * 将活动分解为Conjunction列表
	 * @param act
	 * @return
	 */
	private List<Conjunction> analysisConjunction(Activity act) {
		List<Conjunction> conjList = new ArrayList<Conjunction>();
		
		Conjunction conj = new Conjunction();
		cloneConj(conj, act);
		conj.setHours(act.getMonHours());
		conj.setWeek(1);
		conjList.add(conj);
		
		conj = new Conjunction();
		cloneConj(conj, act);
		conj.setHours(act.getTueHours());
		conj.setWeek(2);
		conjList.add(conj);
		
		conj = new Conjunction();
		cloneConj(conj, act);
		conj.setHours(act.getWedHours());
		conj.setWeek(3);
		conjList.add(conj);
		
		conj = new Conjunction();
		cloneConj(conj, act);
		conj.setHours(act.getThuHours());
		conj.setWeek(4);
		conjList.add(conj);
		
		conj = new Conjunction();
		cloneConj(conj, act);
		conj.setHours(act.getFriHours());
		conj.setWeek(5);
		conjList.add(conj);
		
		conj = new Conjunction();
		cloneConj(conj, act);
		conj.setHours(act.getSatHours());
		conj.setWeek(6);
		conjList.add(conj);
		
		conj = new Conjunction();
		cloneConj(conj, act);
		conj.setHours(act.getSunHours());
		conj.setWeek(7);
		conjList.add(conj);
		
		return conjList;
	}

	/**
	 * 将活动的属性复制到conjunciton中
	 * 除去ID（查索引的时候赋值）、Week和Hours（根据星期不同而赋值）
	 * @param conj
	 * @param act
	 */
	private void cloneConj(Conjunction conj, Activity act) {
		conj.setAdx(act.getAdx());
		conj.setArea(act.getArea());
		conj.setBlacklist(act.getBlacklist());
		conj.setStartDate(act.getStartDate());
		conj.setStopDate(act.getStopDate());
		conj.setTerm(act.getTerm());
	}
}
