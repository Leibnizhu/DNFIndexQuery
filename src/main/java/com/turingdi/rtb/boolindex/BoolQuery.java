package com.turingdi.rtb.boolindex;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.turingdi.rtb.boolindex.entity.Activity;
import com.turingdi.rtb.boolindex.entity.Assignment;
import com.turingdi.rtb.boolindex.entity.Conjunction;
import com.turingdi.rtb.boolindex.entity.PostList;
import com.turingdi.rtb.boolindex.entity.Query;

public class BoolQuery {
	/**
	 * @param primaryIndex
	 *            一级索引
	 * @param secondaryIndex
	 *            二级索引
	 * @param bidQuery
	 *            查询
	 * @return 当前查询所满足的所有活动
	 */
	public Set<Activity> boolQuery(Map<Conjunction, List<Activity>> primaryIndex,
			Map<Integer, LinkedHashMap<Assignment, PostList>> secondaryIndex, Query bidQuery) {
		Set<Conjunction> conjResult = secondaryIndexQuery(secondaryIndex, bidQuery);
		System.out.println("\n二级检索结果：");
		for(Conjunction conj : conjResult){
			System.out.println(conj);
		}
		Set<Activity> result = primaryIndexQuery(primaryIndex, conjResult);
		return result;
	}

	/**
	 * 根据二级索引，找到当前query所满足的conjunction
	 * 
	 * @param secondaryIndex
	 * @param query
	 * @return
	 */
	private Set<Conjunction> secondaryIndexQuery(Map<Integer, LinkedHashMap<Assignment, PostList>> secondaryIndex,
			Query query) {
		Set<Conjunction> result = new HashSet<Conjunction>();
		for (int K = Math.min(query.size(), Collections.max(secondaryIndex.keySet())); K >= 0; K--) {
			if (null == secondaryIndex.get(K)) {
				// K没有对应的Map的话就直接继续下一个K
				continue;
			}
			List<PostList> poLists = getPostingLists(query, K, secondaryIndex.get(K));
			initializeCurrentEntries(poLists);
			// K=0和K=1的处理一样
			if (0 == K) {
				K = 1;
			}
			/*
			 * 如果筛选出来query满足的条件个数小于K 那么这个query肯定在当前K中不能完全满足
			 * 至少有K-poLists.size()个条件无法满足 所以忽略掉，继续下一个K，由此简化计算步骤
			 */
			if (poLists.size() < K) {
				continue;
			}
			// 这里就能看出为什么我要把curEntry设置为Integer而不是int，因为可以为null啊科科
			while (null != poLists.get(K - 1).getCurEntry()) {
				sortByCurEntries(poLists);
				int nextID = 0;
				// 判断poList第一个的conjunction和第K个的是否一致
				if(null ==poLists.get(K - 1).getCurPost()){
					continue;
				}
				if (poLists.get(0).getCurPost().getConj().getId() == poLists.get(K - 1).getCurPost().getConj().getId()) {
					if (!poLists.get(0).getCurPost().isBelong()) {
						// 如果是“不属于”的posting
						int rejectID = poLists.get(0).getCurPost().getConj().getId();
						for (int L = K; L < poLists.size(); L++) {
							// System.out.println("reject = " + rejectID);
							if (null != poLists.get(L).getCurPost() && rejectID == poLists.get(L).getCurPost().getConj().getId()) {
								postListSkip(poLists.get(L), rejectID + 1);
							} else {
								//第K-1个条件已经fail了已经没得选的话，让他重新排序
								continue;
							}
						}
					} else {
						// 则当前query可以满足curEntry的conjunction，放入result中
						result.add(poLists.get(K - 1).getCurPost().getConj());
					}
					// curEntry之后最小的可能的ID
					nextID = poLists.get(K - 1).getCurPost().getConj().getId() + 1;
				} else {
					// 跳过前面K-1个PostList
					nextID = poLists.get(K - 1).getCurPost().getConj().getId();
				}
				for (int L = 0; L < K; L++) {
					postListSkip(poLists.get(L), nextID);
				}
			}
		}
		return result;
	}

	/**
	 * 根据一级索引、以及二级索引匹配到的conjunction，合并出当前query所满足的所有活动
	 * 
	 * @param primaryIndex
	 * @param conjResult
	 * @return
	 */
	private Set<Activity> primaryIndexQuery(Map<Conjunction, List<Activity>> primaryIndex,
			Set<Conjunction> conjResult) {
		Set<Activity> result = new HashSet<Activity>();
		for (Conjunction conj : conjResult) {
			List<Activity> actList = primaryIndex.get(conj);
			for (Activity act : actList) {
				result.add(act);
			}
		}
		return result;
	}

	/*------------------------------------以下是secondaryIndexQuery用到的方法--------------------------------------*/

	/**
	 * 对指定的PostList，跳过其中List<Posting>的Posting，直至当前对应的conjunction的ID大于或等于nextID
	 * 
	 * @param postList
	 * @param nextID
	 */
	private void postListSkip(PostList postList, int nextID) {
		int index = postList.getCurEntry();
		// 相等或者大于的时候就要跳出
		// System.out.println(postList.getPostingList());
		// System.out.println(index + " " + nextID + " " +
		// postList.getPostingList().size());
		while (true) {
			if (postList.getPostingList().size() > index) {
				if (postList.getPostingList().get(index).getConj().getId() < nextID) {
					// 还没找到，继续增加index
					index++;
				} else {
					// 将找到的conjunction ID设置到curEntry和curPost
					postList.setCurEntry(index);
					postList.setCurPost(postList.getPostingList().get(index));
					break;
				}
			} else {
				// index==size，已超出界限，curEntry设为null
				postList.setCurEntry(null);
				postList.setCurPost(null);
				break;
			}
		}
	}

	/**
	 * 根据每个PostList中的curEntry，对整个poList进行排序 因为在建立索引的时候已经排序了，筛选的时候保持着大致的排序状态
	 * 所以冒泡排序效率可能更高
	 * 
	 * @param poLists
	 */
	private void sortByCurEntries(List<PostList> poLists) {
		int end;
		int flag = poLists.size();
		while (flag > 0) {
			// 每次遍历只要走到上一次最后发生交换的地方即可
			end = flag;
			flag = 0;
			for (int j = 1; j < end; j++){
				// 与临近的PostList进行比较
				// 遇到curEntry为空的应该往后排
				if (null == poLists.get(j).getCurPost()){
					continue;
				}
				if (null == poLists.get(j - 1).getCurPost()
						|| poLists.get(j - 1).getCurPost().compareTo(poLists.get(j).getCurPost()) > 0) {
					// 进行交换
					PostList temp = poLists.get(j - 1);
					poLists.set(j - 1, poLists.get(j));
					poLists.set(j, temp);
					// 记录发生交换的位置
					flag = j;
				}
			}
		}
	}

	/**
	 * 初始化PostingList，将curEntry设置为poList中的第一个元素
	 * 
	 * @param poLists
	 * @param secondaryIndex
	 */
	private void initializeCurrentEntries(List<PostList> poLists) {
		for (PostList poList : poLists) {
			poList.setCurEntry(0);
			poList.setCurPost(poList.getPostingList().get(0));
		}
	}

	/**
	 * 对当前遍历key=K的二级索引，获取当前query满足的所有Posting的List的List
	 * 
	 * @param bidQuery
	 * @param k
	 * @param secondaryIndex
	 * @return
	 */
	private List<PostList> getPostingLists(Query query, int K, LinkedHashMap<Assignment, PostList> assgPListMap) {
		List<PostList> result = new ArrayList<PostList>();
		Assignment assg;
		PostList tmpPList;
		// 反射效率低，还是直接手动按属性去生成result吧
		// area
		if (null != query.getArea()) {
			assg = new Assignment(K, "area", query.getArea());
			tmpPList = assgPListMap.get(assg);
			if (null != tmpPList) {
				// 能查询到符合条件的Assignment
				result.add(tmpPList);
			}
		}
		// adx
		if (null != query.getAdx()) {
			assg = new Assignment(K, "adx", query.getAdx());
			tmpPList = assgPListMap.get(assg);
			if (null != tmpPList) {
				// 能查询到符合条件的Assignment
				result.add(tmpPList);
			}
		}
		// term
		if (null != query.getTerm()) {
			assg = new Assignment(K, "term", query.getTerm());
			tmpPList = assgPListMap.get(assg);
			if (null != tmpPList) {
				// 能查询到符合条件的Assignment
				result.add(tmpPList);
			}
		}
		// adsense
		if (null != query.getAdsense()) {
			assg = new Assignment(K, "blacklist", query.getAdsense());
			tmpPList = assgPListMap.get(assg);
			if (null != tmpPList) {
				// 能查询到符合条件的Assignment
				result.add(tmpPList);
			}
		}
		// week
		if (null != query.getWeek()) {
			assg = new Assignment(K, "week", String.valueOf(query.getWeek()));
			tmpPList = assgPListMap.get(assg);
			if (null != tmpPList) {
				// 能查询到符合条件的Assignment
				result.add(tmpPList);
			}
		}
		// hours
		if (null != query.getHours()) {
			assg = new Assignment(K, "hours", String.valueOf(query.getHours()));
			tmpPList = assgPListMap.get(assg);
			if (null != tmpPList) {
				// 能查询到符合条件的Assignment
				result.add(tmpPList);
			}
		}
		// date
		if (null != query.getHours()) {
			assg = new Assignment(K, "actdate", new SimpleDateFormat("yyyy-MM-dd").format(query.getDate()));
			tmpPList = assgPListMap.get(assg);
			if (null != tmpPList) {
				// 能查询到符合条件的Assignment
				result.add(tmpPList);
			}
		}
		return result;
	}

	/*------------------------------------以上是secondaryIndexQuery用到的方法--------------------------------------*/

}
