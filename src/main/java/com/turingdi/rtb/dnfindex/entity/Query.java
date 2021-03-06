package com.turingdi.rtb.dnfindex.entity;

import java.util.Calendar;
import java.util.Date;

public class Query {
	private Date date;
	private String area;
	private String adx;
	private String term;
	private String adsense;
	private Integer week;	//周几，1-7
	private Integer hours;
	
	/**
	 * 计算并返回query中包含的非∉有效条件数量
	 * @return
	 */
	public int size(){
		//date,week,hours肯定有
		int size = 3;
		if(null != area){
			size++;
		}
		
		if(null != adx){
			size++;
		}
		
		if(null != term){
			size++;
		}
		/*
		 * 对应blacklist条件，为∉条件，不参与size计算
		if(null != adsense){
			size++;
		}*/
		return size;
	}
	
	
	public Query() {
		this.date = new Date();
		this.week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
		if(Calendar.SUNDAY == this.week){
			this.week = 7;
		} else {
			this.week--;
		}
		this.hours = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getAdx() {
		return adx;
	}
	public void setAdx(String adx) {
		this.adx = adx;
	}
	public String getTerm() {
		return term;
	}
	public void setTerm(String term) {
		this.term = term;
	}
	public String getAdsense() {
		return adsense;
	}
	public void setAdsense(String adsense) {
		this.adsense = adsense;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public Integer getHours() {
		return hours;
	}
	public void setHours(Integer hours) {
		this.hours = hours;
	}
	
}
