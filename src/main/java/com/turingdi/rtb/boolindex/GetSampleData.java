package com.turingdi.rtb.boolindex;

import java.text.SimpleDateFormat;
import java.util.Map;

public class GetSampleData {
	public Activity getOneRedisSample(String ActivityID) throws Exception{
		Map<String, String> sampleMap = RedisUtils.getJedis().hgetAll(ActivityID);
		Activity act = new Activity();
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
		return act;
	}
}
