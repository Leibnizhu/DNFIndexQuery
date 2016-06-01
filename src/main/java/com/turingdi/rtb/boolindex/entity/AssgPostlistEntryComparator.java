package com.turingdi.rtb.boolindex.entity;

import java.util.Comparator;
import java.util.Map.Entry;

public class AssgPostlistEntryComparator implements Comparator<Entry<Assignment, PostList>>{

	@Override
	public int compare(Entry<Assignment, PostList> o1, Entry<Assignment, PostList> o2) {
		//升序排序
		PostList pl1 = o1.getValue();
		PostList pl2 = o2.getValue();
		Posting po1 = pl1.getPostingList().get(pl1.getCurEntry());
		Posting po2 = pl2.getPostingList().get(pl2.getCurEntry());
		int diff = po1.getConj().getId() - po2.getConj().getId();
		if(0 != diff){
			return diff;
		} else {
			if(po1.isBelong()){
				return po2.isBelong()?0:1;
			} else {
				return po2.isBelong()?-1:0;
			}
		}
	}
}
