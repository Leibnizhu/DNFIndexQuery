package com.turingdi.rtb.boolindex.entity;

import java.lang.reflect.Field;
import java.util.List;

public class Assignment {
	private int k;	//	对应conjunction的size
	private Field keyname;	//字段
	private String keyValue;	//字段属性
	private List<Posting> postList;	//包含的Posting列表
	
	@Override
	public String toString() {
		return "Assignment [k=" + k + ", keyname=" + keyname + ", keyValue=" + keyValue + ", postList=" + postList
				+ "]";
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public Field getKeyname() {
		return keyname;
	}
	public void setKeyname(Field keyname) {
		this.keyname = keyname;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
	public List<Posting> getPostList() {
		return postList;
	}
	public void setPostList(List<Posting> postList) {
		this.postList = postList;
	}
	
}
