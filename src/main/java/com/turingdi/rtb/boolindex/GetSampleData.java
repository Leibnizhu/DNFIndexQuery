package com.turingdi.rtb.boolindex;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
		act.setStopDate(new SimpleDateFormat("yyyy-MM-dd").parse(timeMap.get("EndDate")));
		act.setArea(RedisUtils.getJedis().sinter(sampleMap.get("Area")));
		act.setAdx(RedisUtils.getJedis().sinter(sampleMap.get("ADX")));
		act.setTerm(RedisUtils.getJedis().sinter(sampleMap.get("Terminal")));
		act.setBlacklist(RedisUtils.getJedis().sinter(sampleMap.get("Blacklist")));
		
		List<String> tmp = RedisUtils.getJedis().lrange(sampleMap.get("MonHours"), 0, -1);
		List<Integer> out = new ArrayList<Integer>();
		for(String str : tmp){
			out.add(Integer.parseInt(str));
		}
		act.setMonHours(out);
		
		out.clear();
		tmp = RedisUtils.getJedis().lrange(sampleMap.get("TueHours"), 0, -1);
		for(String str : tmp){
			out.add(Integer.parseInt(str));
		}
		act.setTueHours(out);
		
		out.clear();
		tmp = RedisUtils.getJedis().lrange(sampleMap.get("WedHours"), 0, -1);
		for(String str : tmp){
			out.add(Integer.parseInt(str));
		}
		act.setWedHours(out);
		
		out.clear();
		tmp = RedisUtils.getJedis().lrange(sampleMap.get("ThuHours"), 0, -1);
		for(String str : tmp){
			out.add(Integer.parseInt(str));
		}
		act.setThuHours(out);
		
		out.clear();
		tmp = RedisUtils.getJedis().lrange(sampleMap.get("FriHours"), 0, -1);
		for(String str : tmp){
			out.add(Integer.parseInt(str));
		}
		act.setFriHours(out);
		
		out.clear();
		tmp = RedisUtils.getJedis().lrange(sampleMap.get("SatHours"), 0, -1);
		for(String str : tmp){
			out.add(Integer.parseInt(str));
		}
		act.setSatHours(out);
		
		out.clear();
		tmp = RedisUtils.getJedis().lrange(sampleMap.get("SunHours"), 0, -1);
		for(String str : tmp){
			out.add(Integer.parseInt(str));
		}
		act.setSunHours(out);
		
		return act;
	}
}
