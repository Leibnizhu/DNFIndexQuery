package com.turingdi.rtb.dnfindex.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/*
 * Activity [name=DI_品牌粉丝_tanx(hd)4, 
 * mode=CPC优化, 
 * cpc=200, 
 * total=0,
everyday=500, 
object=创意, 
unit=每天, 
count=7, 
crowd=[x9100cx9000c-KD-65X9000C_oridVuew2,…………], 
startDate=Thu Mar 31 00:00:00 CST 2016, 
stopDate=Thu Mar 31 00:00:00 CST 2016, 
Area=[松江区, 阿…………], 
adx=[Tanx], 
term=[PC], 
blacklist=[Adsense::1, Adsense::2], 
monHours=[10, 11, 12, 13, 14, 14, 15, 16, 17, 18, 19, 20, 21, 22], 
tueHours=[10, 11, 12, 13, 14, 14, 15, 16, 17, 18, 19, 20, 21, 22], 
wedHours=[10, 11, 12, 13, 14, 14, 15, 16, 17, 18, 19, 20, 21, 22],
 thuHours=[10, 11, 12, 13, 14, 14, 15, 16, 17, 18, 19, 20, 21, 22],
  friHours=[10, 11, 12, 13, 14, 14, 15, 16, 17, 18, 19, 20, 21, 22], 
  satHours=[10, 11, 12, 13, 14, 14, 15, 16, 17, 18, 19, 20, 21, 22],
   sunHours=[10, 11, 12, 13, 14, 14, 15, 16, 17, 18, 19, 20, 21, 22]]
 * */
public class Activity {
	private String id;
	private String name;
	private String mode;
	private int cpc;
	private int total;
	private int everyday;
	private String object;	//频次策略名，创意/活动等
	private String unit;	//频次控制单位
	private int count;	//频次控制值
	private Set<String> crowd;
	private Date startDate;
	private Date stopDate;
	private Set<String> Area;
	private Set<String> adx;
	private Set<String> term;
	private Set<String> blacklist;
	private List<ArrayList<Integer>> Hours;
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		Activity other = (Activity) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", mode=" + mode + ", cpc=" + cpc + ", total=" + total
				+ ", everyday=" + everyday + ", object=" + object + ", unit=" + unit + ", count=" + count + /*", crowd="
				+ crowd +*/ ", startDate=" + startDate + ", stopDate=" + stopDate + ", Area.size=" + ((null==Area)?0:Area.size()) + ", adx=" + adx
				+ ", term=" + term + ", blacklist=" + blacklist + ", Hours=" + Hours + "]";
	}
	public List<ArrayList<Integer>> getHours() {
		return Hours;
	}
	public void setHours(List<ArrayList<Integer>> hours) {
		Hours = hours;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
