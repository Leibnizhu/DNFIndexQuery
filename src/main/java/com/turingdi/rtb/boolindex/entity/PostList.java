package com.turingdi.rtb.boolindex.entity;

import java.util.List;

public class PostList {
	private List<Posting> postingList;
	private Integer curEntry;
	
	@Override
	public String toString() {
		return "PostList [postingList=" + postingList + ", curEntry=" + curEntry + "]";
	}
	public List<Posting> getPostingList() {
		return postingList;
	}
	public void setPostingList(List<Posting> postingList) {
		this.postingList = postingList;
	}
	public Integer getCurEntry() {
		return curEntry;
	}
	public void setCurEntry(Integer curEntry) {
		this.curEntry = curEntry;
	}
}
