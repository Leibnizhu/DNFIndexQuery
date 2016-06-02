package com.turingdi.rtb.boolindex;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.turingdi.rtb.boolindex.entity.Activity;
import com.turingdi.rtb.boolindex.entity.AssgPostlistEntryComparator;
import com.turingdi.rtb.boolindex.entity.Assignment;
import com.turingdi.rtb.boolindex.entity.Conjunction;
import com.turingdi.rtb.boolindex.entity.PostList;
import com.turingdi.rtb.boolindex.entity.Posting;

public class MakeIndex {
	/**
	 * 将一个活动追加到索引中 TODO: 还没考虑到index放在redis中，因为再Redis中存储太麻烦啦，Key是个复杂对象的时候
	 * 
	 * @param primaryIndex
	 * @param act
	 */
	public void appendIndex(Map<Conjunction, List<Activity>> primaryIndex,
			Map<Integer, LinkedHashMap<Assignment, PostList>> secondaryIndex, Activity act) {
		// 一级索引
		List<Conjunction> conjList = analysisConjunction(act);
		for (Conjunction conj : conjList) {
			if (primaryIndex.containsKey(conj)) {
				// 已经包含conjunction，加进list
				primaryIndex.get(conj).add(act);
			} else {
				// 新conjunction，建立list并加进去
				List<Activity> newActList = new ArrayList<Activity>();
				newActList.add(act);
				conj.setId(primaryIndex.size());// conjunction的ID单调增，index中的conjunction永不删
				primaryIndex.put(conj, newActList);
			}
		}

		// 二级索引
		for (Conjunction conj : conjList) {
			int size = conj.size();
			LinkedHashMap<Assignment, PostList> newAssgMap = analysisAssignment(conj);
			if (secondaryIndex.containsKey(size)) {
				// 往Assignment对应的Posting的List中追加即可
				LinkedHashMap<Assignment, PostList> storedAssgMap = secondaryIndex.get(size);
				for (Assignment assg : newAssgMap.keySet()) {
					if (storedAssgMap.containsKey(assg)) {
						// 原来已存储这个Assignment，则将新的Posting的List加入到原有的List中去
						// curEntry忽略掉因为已经在storedAssgMap第一次put这个assg的时候已经放进去了
						storedAssgMap.get(assg).getPostingList().addAll(newAssgMap.get(assg).getPostingList());
					} else {
						// 原来没有存储，则直接put
						// 这时候放进去的PostList里面curEntry就是0
						storedAssgMap.put(assg, newAssgMap.get(assg));
					}
				}
				secondaryIndex.put(size, storedAssgMap);
			} else {
				// 需要新建Assignment的List
				secondaryIndex.put(size, newAssgMap);
			}
		}

		// 二级索引排序，遍历所有Conjunction的Size
		for (LinkedHashMap<Assignment, PostList> assgPostMap : secondaryIndex.values()) {
			// ——每个PostingList内部排序
			for (PostList postList : assgPostMap.values()) {
				Collections.sort(postList.getPostingList());
			}
			// ——Map<Assignment,
			// PostList>中根据PostList的第一个Posting的conjunctionID来排序
			List<Entry<Assignment, PostList>> tempList = new ArrayList<Entry<Assignment, PostList>>(
					assgPostMap.entrySet());
			Collections.sort(tempList, new AssgPostlistEntryComparator());
			// System.out.println(tempList);
			assgPostMap = new LinkedHashMap<Assignment, PostList>();
			for (Entry<Assignment, PostList> entry : tempList) {
				assgPostMap.put(entry.getKey(), entry.getValue());
			}
		}
	}

	/**
	 * 解析Conjunction，得到Assignment的List
	 * 
	 * @param conj
	 * @return
	 */
	private LinkedHashMap<Assignment, PostList> analysisAssignment(Conjunction conj) {
		LinkedHashMap<Assignment, PostList> assgMap = new LinkedHashMap<Assignment, PostList>();
		int size = conj.size();
		try{
			Assignment assg = null;
			List<Posting> belongPostList ;
			List<Posting> nobelongPostList = new ArrayList<Posting>();
			PostList postList;
			nobelongPostList.add(new Posting(conj, false));
			//对各个属性分别创建键值对
			//startDate
			/*assg = new Assignment(size, "startDate", new SimpleDateFormat("yyyy-MM-dd").format(conj.getStartDate()));
			belongPostList = new ArrayList<Posting>();
			belongPostList.add(new Posting(conj, true));
			postList = new PostList();
			postList.setPostingList(belongPostList);
			postList.setCurEntry(0);
			postList.setCurPost(belongPostList.get(0));
			assgMap.put(assg, postList);
			
			//stopDate
			if(null != conj.getStopDate()){
				assg = new Assignment(size, "stopDate", new SimpleDateFormat("yyyy-MM-dd").format(conj.getStopDate()));
				belongPostList = new ArrayList<Posting>();
				belongPostList.add(new Posting(conj, true));
				postList = new PostList();
				postList.setPostingList(belongPostList);
				postList.setCurEntry(0);
				postList.setCurPost(belongPostList.get(0));
				assgMap.put(assg, postList);
			}*/
			/*
			 * 对于startDate和StopDate应该是将未来N天（假设一周更新一次，就是7天，假设每天更新索引，就是1天）中可投放的日期（size<=N）
			 * 单独作为一个actdate创建一个Assignment
			 */
			Calendar cal = Calendar.getInstance();
			Date date = new Date();
			for(int i = 0; i < 7; i++){
				if(date.after(conj.getStartDate())){
					if(date.before(conj.getStopDate())){
						//当前date在活动规定的范围之内，增加Assignment
						assg = new Assignment(size, "actdate", new SimpleDateFormat("yyyy-MM-dd").format(date));
						belongPostList = new ArrayList<Posting>();
						belongPostList.add(new Posting(conj, true));
						postList = new PostList();
						postList.setPostingList(belongPostList);
						postList.setCurEntry(0);
						postList.setCurPost(belongPostList.get(0));
						assgMap.put(assg, postList);
					} else{
						//在開始時間後，也在結束時間後，則往後也是超出，直接跳出
						break;
					}
				}
				//即使是再开始时间之前，也要往前走一天，因为开始时间可能在此之后
				//日期增加一天
				cal.setTime(date);
				cal.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
				date=cal.getTime();
			}
			
			
			
			
			//area
			if(null != conj.getArea() && conj.getArea().size() > 0){
				for(String area : conj.getArea()){
					//每个地区分别建一个Assignment
					assg = new Assignment(size, "area", area);
					belongPostList = new ArrayList<Posting>();
					belongPostList.add(new Posting(conj, true));
					postList = new PostList();
					postList.setPostingList(belongPostList);
					postList.setCurEntry(0);
					postList.setCurPost(belongPostList.get(0));
					assgMap.put(assg, postList);
				}
			}
			
			//adx
			if(null != conj.getAdx() && conj.getAdx().size() > 0){
				for(String adx : conj.getAdx()){
					//每个adx分别建一个Assignment
					assg = new Assignment(size, "adx", adx);
					belongPostList = new ArrayList<Posting>();
					belongPostList.add(new Posting(conj, true));
					postList = new PostList();
					postList.setPostingList(belongPostList);
					postList.setCurEntry(0);
					postList.setCurPost(belongPostList.get(0));
					assgMap.put(assg, postList);
				}
			}
			
			//term
			if(null != conj.getTerm() && conj.getTerm().size() > 0){
				for(String term : conj.getTerm()){
					//每个终端分别建一个Assignment
					assg = new Assignment(size, "term", term);
					belongPostList = new ArrayList<Posting>();
					belongPostList.add(new Posting(conj, true));
					postList = new PostList();
					postList.setPostingList(belongPostList);
					postList.setCurEntry(0);
					postList.setCurPost(belongPostList.get(0));
					assgMap.put(assg, postList);
				}
			}
			
			//blacklist，是“不属于“的字段
			if(null != conj.getBlacklist() && conj.getBlacklist().size() > 0){
				for(String blackitem : conj.getBlacklist()){
					//每个终端分别建一个Assignment
					assg = new Assignment(size, "blacklist", blackitem);
					belongPostList = new ArrayList<Posting>();
					belongPostList.add(new Posting(conj, false));
					postList = new PostList();
					postList.setPostingList(belongPostList);
					postList.setCurEntry(0);
					postList.setCurPost(belongPostList.get(0));
					assgMap.put(assg, postList);
				}
			}
			
			//week和hours，必须同时出现
			if(null != conj.getWeek()){
				//week星期几创建一个Assignment
				assg = new Assignment(size, "week", String.valueOf(conj.getWeek()));
				belongPostList = new ArrayList<Posting>();
				belongPostList.add(new Posting(conj, true));
				postList = new PostList();
				postList.setPostingList(belongPostList);
				postList.setCurEntry(0);
				postList.setCurPost(belongPostList.get(0));
				assgMap.put(assg, postList);
				//一天内哪些时间段全部要逐个创建Assignment
				for(Integer hour : conj.getHours()){
					assg = new Assignment(size, "hours", String.valueOf(hour));
					belongPostList = new ArrayList<Posting>();
					belongPostList.add(new Posting(conj, true));
					postList = new PostList();
					postList.setPostingList(belongPostList);
					postList.setCurEntry(0);
					postList.setCurPost(belongPostList.get(0));
					assgMap.put(assg, postList);
				}
			}
		} catch(Exception e){
			e.printStackTrace();
		}
		return assgMap;
	}

	/**
	 * 将活动分解为Conjunction列表
	 * 
	 * @param act
	 * @return
	 */
	public List<Conjunction> analysisConjunction(Activity act) {
		List<Conjunction> conjList = new ArrayList<Conjunction>();

		Conjunction conj;
		List<ArrayList<Integer>> hoursList = act.getHours();
		for (int i = 0; i < 7; i++) {
			conj = new Conjunction();
			cloneConj(conj, act);
			conj.setHours(hoursList.get(i));
			conj.setWeek(i + 1);
			conjList.add(conj);
		}
		return conjList;
	}

	/**
	 * 将活动的属性复制到conjunciton中 除去ID（查索引的时候赋值）、Week和Hours（根据星期不同而赋值）
	 * 
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
