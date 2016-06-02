package com.turingdi.rtb.boolindex.entity;

/**
 * Posting List中每个元素的实体，对应论文里的(3, ∈)或(6,∉)等
 * @author leibniz
 *
 */
public class Posting implements Comparable<Posting>{
	private Conjunction conj;	//原来所属的conjunction
	private boolean belong;	//该字段是属于/不属于的标识
	
	public Posting(Conjunction conj, boolean belong) {
		super();
		this.conj = conj;
		this.belong = belong;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (belong ? 1231 : 1237);
		result = prime * result + ((conj == null) ? 0 : conj.hashCode());
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
		Posting other = (Posting) obj;
		if (belong != other.belong)
			return false;
		if (conj == null) {
			if (other.conj != null)
				return false;
		} else if (!conj.equals(other.conj))
			return false;
		return true;
	}

	/* 
	 * 比较的方法，来自Comparable接口
	 * 优先判断conjunction的ID，如果ID相等，则∈关系的比∉关系的更大
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Posting o) {
		int diff = this.getConj().getId() - o.getConj().getId();
		if(0 != diff){
			return diff;
		} else {
			if(this.isBelong()){
				return o.isBelong()?0:1;
			} else {
				return o.isBelong()?-1:0;
			}
		}
	}
	
	@Override
	public String toString() {
		return "Posting [conj=" + conj.getId() + ", belong=" + (belong?"∈":"不属于") + "]";
	}
	public Conjunction getConj() {
		return conj;
	}
	public void setConj(Conjunction conj) {
		this.conj = conj;
	}
	public boolean isBelong() {
		return belong;
	}
	public void setBelong(boolean belong) {
		this.belong = belong;
	}
}
