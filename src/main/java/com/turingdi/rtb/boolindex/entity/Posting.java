package com.turingdi.rtb.boolindex.entity;

public class Posting {
	private Conjunction conj;	//原来所属的conjunction
	private boolean belong;	//该字段是属于/不属于的标识
	
	@Override
	public String toString() {
		return "Posting [conj=" + conj + ", belong=" + belong + "]";
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
