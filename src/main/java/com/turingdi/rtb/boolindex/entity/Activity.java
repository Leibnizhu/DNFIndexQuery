package com.turingdi.rtb.boolindex.entity;

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
	private List<Integer> monHours;
	private List<Integer> tueHours;
	private List<Integer> wedHours;
	private List<Integer> thuHours;
	private List<Integer> friHours;
	private List<Integer> satHours;
	private List<Integer> sunHours;
	
	@Override
	public String toString() {
		return "Activity [id=" + id + ", name=" + name + ", mode=" + mode + ", cpc=" + cpc + ", total=" + total
				+ ", everyday=" + everyday + ", object=" + object + ", unit=" + unit + ", count=" + count + ", crowd="
				+ crowd + ", startDate=" + startDate + ", stopDate=" + stopDate + ", Area=" + Area + ", adx=" + adx
				+ ", term=" + term + ", blacklist=" + blacklist + ", monHours=" + monHours + ", tueHours=" + tueHours
				+ ", wedHours=" + wedHours + ", thuHours=" + thuHours + ", friHours=" + friHours + ", satHours="
				+ satHours + ", sunHours=" + sunHours + "]";
	}
	public Activity() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public List<Integer> getMonHours() {
		return monHours;
	}
	public void setMonHours(List<Integer> monHours) {
		this.monHours = monHours;
	}
	public List<Integer> getTueHours() {
		return tueHours;
	}
	public void setTueHours(List<Integer> tueHours) {
		this.tueHours = tueHours;
	}
	public List<Integer> getWedHours() {
		return wedHours;
	}
	public void setWedHours(List<Integer> wedHours) {
		this.wedHours = wedHours;
	}
	public List<Integer> getThuHours() {
		return thuHours;
	}
	public void setThuHours(List<Integer> thuHours) {
		this.thuHours = thuHours;
	}
	public List<Integer> getFriHours() {
		return friHours;
	}
	public void setFriHours(List<Integer> friHours) {
		this.friHours = friHours;
	}
	public List<Integer> getSatHours() {
		return satHours;
	}
	public void setSatHours(List<Integer> satHours) {
		this.satHours = satHours;
	}
	public List<Integer> getSunHours() {
		return sunHours;
	}
	public void setSunHours(List<Integer> sunHours) {
		this.sunHours = sunHours;
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
