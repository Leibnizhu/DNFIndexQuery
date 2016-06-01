package com.turingdi.rtb.boolindex.entity;

import java.util.List;

public class PostList {
	private List<Posting> postingList;
	private Posting curPost;
	private Integer curEntry;
	
	@Override
	public String toString() {
		return "PostList [postingList=" + postingList + ", curPost=@" + curPost.hashCode() + ", curEntry=" + curEntry + "]";
	}
	
	public Posting getCurPost() {
		return curPost;
	}
	public void setCurPost(Posting curPost) {
		this.curPost = curPost;
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
