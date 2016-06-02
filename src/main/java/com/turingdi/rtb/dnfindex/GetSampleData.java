package com.turingdi.rtb.dnfindex;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.turingdi.rtb.dnfindex.entity.Activity;

public class GetSampleData {
	public Activity getOneRedisSample(String ActivityID) throws Exception {
		Map<String, String> sampleMap = RedisUtils.getJedis().hgetAll(ActivityID);
		Activity act = new Activity();
		act.setId(ActivityID);
		act.setName(sampleMap.get("Name"));
		act.setMode(sampleMap.get("Mode"));
		String tmp = sampleMap.get("CPC");
		if (null != tmp) {
			act.setCpc(Integer.parseInt(tmp));
		}
		tmp = sampleMap.get("Total");
		if (null != tmp) {
			act.setTotal(Integer.parseInt(tmp));
		}
		tmp = sampleMap.get("Everyday");
		if (null != tmp) {
			act.setEveryday(Integer.parseInt(tmp));
		}
		tmp = sampleMap.get("Frequency");
		if (null != tmp) {
			Map<String, String> freqMap = RedisUtils.getJedis().hgetAll(tmp);
			act.setObject(freqMap.get("Object"));
			act.setUnit(freqMap.get("Unit"));
			act.setCount(Integer.parseInt(freqMap.get("Count")));
			act.setCrowd(RedisUtils.getJedis().sinter(sampleMap.get("Crowds")));
		}
		tmp = sampleMap.get("Time");
		if (null != tmp) {
			Map<String, String> timeMap = RedisUtils.getJedis().hgetAll(tmp);
			act.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(timeMap.get("StartDate")));
			tmp = timeMap.get("EndDate");
			if(!"不限".equals(tmp)){
				Date stopdate = new SimpleDateFormat("yyyy-MM-dd").parse(tmp);
				Calendar cal = Calendar.getInstance();
				cal.setTime(stopdate);
				cal.add(Calendar.DATE, 1);// 把日期往后增加一天.整数往后推,负数往前移动
				stopdate = cal.getTime();
				act.setStopDate(stopdate);
			}
		}
		tmp = sampleMap.get("Area");
		if (null != tmp) {
			act.setArea(RedisUtils.getJedis().sinter(tmp));
		}
		tmp = sampleMap.get("ADX");
		if (null != tmp) {
			act.setAdx(RedisUtils.getJedis().sinter(tmp));
		}
		tmp = sampleMap.get("Terminal");
		if (null != tmp) {
			act.setTerm(RedisUtils.getJedis().sinter(tmp));
		}
		tmp = sampleMap.get("Blacklist");
		if (null != tmp) {
			act.setBlacklist(RedisUtils.getJedis().sinter(tmp));
		}

		List<String> tmpList;
		List<ArrayList<Integer>> hoursList = new ArrayList<ArrayList<Integer>>();
		String[] weekName = { "MonHours", "TueHours", "WedHours", "ThuHours", "FriHours", "SatHours", "SunHours" };
		for (int i = 0; i < 7; i++) {
			tmp = sampleMap.get(weekName[i]);
			if(null != tmp){
				tmpList = RedisUtils.getJedis().lrange(tmp, 0, -1);
				ArrayList<Integer> out = new ArrayList<Integer>();
				for (String str : tmpList) {
					out.add(Integer.parseInt(str));
				}
				hoursList.add(out);
			}
		}
		act.setHours(hoursList);
		return act;
	}
}
