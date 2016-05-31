package com.turingdi.rtb.boolindex.entity;

public class Posting implements Comparable<Posting>{
	private Conjunction conj;	//原来所属的conjunction
	private boolean belong;	//该字段是属于/不属于的标识
	
	public Posting(Conjunction conj, boolean belong) {
		super();
		this.conj = conj;
		this.belong = belong;
	}

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
		return "Posting [conj=" + conj.getId() + ", belong=" + belong + "]";
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
