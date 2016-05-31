package com.turingdi.rtb.boolindex.entity;

public class Assignment {
	private int k;	//	对应conjunction的size
	private String keyname;	//字段
	private String keyValue;	//字段属性
	//private List<Posting> postList;	//包含的Posting列表
	
	public Assignment(int k, String keyname, String keyValue) {
		super();
		this.k = k;
		this.keyname = keyname;
		this.keyValue = keyValue;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + k;
		result = prime * result + ((keyValue == null) ? 0 : keyValue.hashCode());
		result = prime * result + ((keyname == null) ? 0 : keyname.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Assignment other = (Assignment) obj;
		if (k != other.k)
			return false;
		if (keyValue == null) {
			if (other.keyValue != null)
				return false;
		} else if (!keyValue.equals(other.keyValue))
			return false;
		if (keyname == null) {
			if (other.keyname != null)
				return false;
		} else if (!keyname.equals(other.keyname))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Assignment [k=" + k + ", " + keyname + "=" + keyValue /*+ ", postList=" + postList */+ "]";
	}
	public int getK() {
		return k;
	}
	public void setK(int k) {
		this.k = k;
	}
	public String getKeyname() {
		return keyname;
	}
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	public String getKeyValue() {
		return keyValue;
	}
	public void setKeyValue(String keyValue) {
		this.keyValue = keyValue;
	}
/*	public List<Posting> getPostList() {
		return postList;
	}
	public void setPostList(List<Posting> postList) {
		this.postList = postList;
	}*/
	
}
