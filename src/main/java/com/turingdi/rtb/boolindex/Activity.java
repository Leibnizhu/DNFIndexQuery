package com.turingdi.rtb.boolindex;

import java.util.Date;
import java.util.Set;

public class Activity {
	private String name;
	private String mode;
	private int cpc;
	private int total;
	private int everyday;
	private String object;
	private String unit;
	private int count;
	private Set<String> crowd;
	private Date startDate;
	private Date stopDate;
	private Set<String> Area;
	private Set<String> adx;
	private Set<String> term;
	private Set<String> blacklist;
	
	@Override
	public String toString() {
		return "Activity [name=" + name + ", mode=" + mode + ", cpc=" + cpc + ", total=" + total + ", everyday="
				+ everyday + ", object=" + object + ", unit=" + unit + ", count=" + count + ", crowd=" + crowd
				+ ", startDate=" + startDate + ", stopDate=" + stopDate + ", Area=" + Area + ", adx=" + adx + ", term="
				+ term + ", blacklist=" + blacklist + "]";
	}
	public Activity() {
		super();
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMode() {
		return mode;
	}
	public void setMode(String mode) {
		this.mode = mode;
	}
	public int getCpc() {
		return cpc;
	}
	public void setCpc(int cpc) {
		this.cpc = cpc;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public int getEveryday() {
		return everyday;
	}
	public void setEveryday(int everyday) {
		this.everyday = everyday;
	}
	public String getObject() {
		return object;
	}
	public void setObject(String object) {
		this.object = object;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public Set<String> getCrowd() {
		return crowd;
	}
	public void setCrowd(Set<String> crowd) {
		this.crowd = crowd;
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
}
