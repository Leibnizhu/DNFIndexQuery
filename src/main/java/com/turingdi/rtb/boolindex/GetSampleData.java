package com.turingdi.rtb.boolindex;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.turingdi.rtb.boolindex.entity.Activity;

public class GetSampleData {
	public Activity getOneRedisSample(String ActivityID) throws Exception{
		Map<String, String> sampleMap = RedisUtils.getJedis().hgetAll(ActivityID);
		Activity act = new Activity();
		act.setId(ActivityID);
		act.setName(sampleMap.get("Name"));
		act.setMode(sampleMap.get("Mode"));
		act.setCpc(Integer.parseInt(sampleMap.get("CPC")));
		act.setTotal(Integer.parseInt(sampleMap.get("Total")));
		act.setEveryday(Integer.parseInt(sampleMap.get("Everyday")));
		Map<String, String> freqMap = RedisUtils.getJedis().hgetAll(sampleMap.get("Frequency"));
		act.setObject(freqMap.get("Object"));
		act.setUnit(freqMap.get("Unit"));
		act.setCount(Integer.parseInt(freqMap.get("Count")));
		act.setCrowd(RedisUtils.getJedis().sinter(sampleMap.get("Crowds")));
		Map<String, String> timeMap = RedisUtils.getJedis().hgetAll(sampleMap.get("Time"));
		act.setStartDate(new SimpleDateFormat("yyyy-MM-dd").parse(timeMap.get("StartDate")));
		Date stopdate = new SimpleDateFormat("yyyy-MM-dd").parse(timeMap.get("EndDate"));
		Calendar cal = Calendar.getInstance();
		cal.setTime(stopdate);
		cal.add(Calendar.DATE,1);//把日期往后增加一天.整数往后推,负数往前移动 
		stopdate=cal.getTime();
		act.setStopDate(stopdate);
		act.setArea(RedisUtils.getJedis().sinter(sampleMap.get("Area")));
		act.setAdx(RedisUtils.getJedis().sinter(sampleMap.get("ADX")));
		act.setTerm(RedisUtils.getJedis().sinter(sampleMap.get("Terminal")));
		act.setBlacklist(RedisUtils.getJedis().sinter(sampleMap.get("Blacklist")));
		
		List<String> tmp;
		List<ArrayList<Integer>> hoursList = new ArrayList<ArrayList<Integer>>();
		String[] weekName = {"MonHours", "TueHours", "WedHours", "ThuHours", "FriHours", "SatHours", "SunHours"};
		for(int i = 0; i < 7; i++){
			tmp = RedisUtils.getJedis().lrange(sampleMap.get(weekName[i]), 0, -1);
			ArrayList<Integer> out = new ArrayList<Integer>();
			for(String str : tmp){
				out.add(Integer.parseInt(str));
			}
			hoursList.add(out);
		}
		act.setHours(hoursList);
		return act;
	}
}
