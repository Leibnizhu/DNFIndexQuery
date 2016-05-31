package com.turingdi.rtb.boolindex.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

public class Conjunction{
	private int id;
	private Date startDate;
	private Date stopDate;
	private Set<String> Area;
	private Set<String> adx;
	private Set<String> term;
	private Set<String> blacklist;
	private int week;	//周几，1-7
	private List<Integer> Hours;
	
	//需要Override hashcode和equals，以便Map进行key比较操作，id可以忽略，因为新加的conjunction没有id
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Area == null) ? 0 : Area.hashCode());
		result = prime * result + ((Hours == null) ? 0 : Hours.hashCode());
		result = prime * result + ((adx == null) ? 0 : adx.hashCode());
		result = prime * result + ((blacklist == null) ? 0 : blacklist.hashCode());
		result = prime * result + id;
		result = prime * result + ((startDate == null) ? 0 : startDate.hashCode());
		result = prime * result + ((stopDate == null) ? 0 : stopDate.hashCode());
		result = prime * result + ((term == null) ? 0 : term.hashCode());
		result = prime * result + week;
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
		if (Area == null) {
			if (other.Area != null)
				return false;
		} else if (!Area.equals(other.Area))
			return false;
		if (Hours == null) {
			if (other.Hours != null)
				return false;
		} else if (!Hours.equals(other.Hours))
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
		if (id != other.id)
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
		if (week != other.week)
			return false;
		return true;
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
		return Area;
	}
	public void setArea(Set<String> area) {
		Area = area;
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
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public List<Integer> getHours() {
		return Hours;
	}
	public void setHours(List<Integer> hours) {
		Hours = hours;
	}
}
