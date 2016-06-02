package com.turingdi.rtb.boolindex.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.Set;

public class Conjunction{
	private int id;
	private Date startDate;
	private Date stopDate;
	private Set<String> area;
	private Set<String> adx;
	private Set<String> term;
	private Set<String> blacklist;
	private Integer week;	//周几，1-7
	private ArrayList<Integer> hours;
	
	//不含非
	public int size(){
		//date（查询时包括 startDate和stopdate了）和,adx必须有
		int size = 2;
		/*if(null != stopDate){
			size++;
		}*/
		if(null != area && area.size() > 0){
			size++;
		}
		if(null != term && term.size() > 0){
			size++;
		}
		/*blacklist为∉条件，不参与size计算
		 * if(null != blacklist && blacklist.size() > 0){
			size++;
		}*/
		if(null != week){
			size += 2;
		}
		return size;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area == null) ? 0 : area.hashCode());
		result = prime * result + ((hours == null) ? 0 : hours.hashCode());
		result = prime * result + ((adx == null) ? 0 : adx.hashCode());
		result = prime * result + ((blacklist == null) ? 0 : blacklist.hashCode());
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((stopDate == null) ? 0 : stopDate.hashCode());
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		result = prime * result + ((week == null) ? 0 : week.hashCode());;
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
		Conjunction other = (Conjunction) obj;
		if (area == null) {
			if (other.area != null)
				return false;
		} else if (!area.equals(other.area))
			return false;
		if (hours == null) {
			if (other.hours != null)
				return false;
		} else if (!hours.equals(other.hours))
			return false;
		if (adx == null) {
			if (other.adx != null)
				return false;
		} else if (!adx.equals(other.adx))
			return false;
		if (blacklist == null) {
			if (other.blacklist != null)
				return false;
		} else if (!blacklist.equals(other.blacklist))
			return false;
		if (startDate == null) {
			if (other.startDate != null)
				return false;
		} else if (!startDate.equals(other.startDate))
			return false;
		if (stopDate == null) {
			if (other.stopDate != null)
				return false;
		} else if (!stopDate.equals(other.stopDate))
			return false;
		if (term == null) {
			if (other.term != null)
				return false;
		} else if (!term.equals(other.term))
			return false;
		if (week == null) {
			if (other.week != null)
				return false;
		} else if (!week.equals(other.week))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Conjunction [id=" + id + ", startDate=" + startDate + ", stopDate=" + stopDate + ", Area=" + ((null==area)?0:area.size())
				+ ", adx=" + adx + ", term=" + term + ", blacklist=" + blacklist + ", week=" + week + ", hours=" + hours
				+ "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}
	public Date getStopDate() {
		return stopDate;
	}
	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}
	public Set<String> getArea() {
		return area;
	}
	public void setArea(Set<String> area) {
		this.area = area;
	}
	public Set<String> getAdx() {
		return adx;
	}
	public void setAdx(Set<String> adx) {
		this.adx = adx;
	}
	public Set<String> getTerm() {
		return term;
	}
	public void setTerm(Set<String> term) {
		this.term = term;
	}
	public Set<String> getBlacklist() {
		return blacklist;
	}
	public void setBlacklist(Set<String> blacklist) {
		this.blacklist = blacklist;
	}
	public Integer getWeek() {
		return week;
	}
	public void setWeek(Integer week) {
		this.week = week;
	}
	public ArrayList<Integer> getHours() {
		return hours;
	}
	public void setHours(ArrayList<Integer> hours) {
		this.hours = hours;
	}
}
