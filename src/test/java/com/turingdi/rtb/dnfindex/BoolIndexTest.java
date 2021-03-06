package com.turingdi.rtb.dnfindex;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.turingdi.rtb.dnfindex.BoolQuery;
import com.turingdi.rtb.dnfindex.GetSampleData;
import com.turingdi.rtb.dnfindex.MakeIndex;
import com.turingdi.rtb.dnfindex.RedisUtils;
import com.turingdi.rtb.dnfindex.entity.Activity;
import com.turingdi.rtb.dnfindex.entity.Assignment;
import com.turingdi.rtb.dnfindex.entity.Conjunction;
import com.turingdi.rtb.dnfindex.entity.PostList;
import com.turingdi.rtb.dnfindex.entity.Query;

public class BoolIndexTest {
	@Test
	public void getSampleTest(){
		Map<Conjunction, List<Activity>> primaryIndex = new HashMap<Conjunction, List<Activity>>();
		Map<Integer, LinkedHashMap<Assignment, PostList>> secondaryIndex = new HashMap<Integer, LinkedHashMap<Assignment, PostList>>();
		RedisUtils.getJedis().hgetAll("Activity::1");
		try {
			for(int i = 5; i >= 0; i--){
				long start = System.nanoTime();
				Activity act = new GetSampleData().getOneRedisSample("Activity::" + (i+1));
				//System.out.println("读取了活动：\n" + act);
				//System.out.println("分conjunction结果：\n" + new MakeIndex().analysisConjunction(act));
				new MakeIndex().appendIndex(primaryIndex, secondaryIndex, act);
				System.out.println("活动" + (i + 1) + "创建索引耗时: " + (System.nanoTime()-start)/1000/1000 + " ms...");
			}
			
			FileWriter fw = new FileWriter(new File("log.log"), false);
			fw.write("\n建立索引结果：\n一级索引：\n" + primaryIndex + "\n二级索引：\n" + secondaryIndex);
			fw.close();
			System.out.println("索引已写入log.log文件...");
			
			//查询测试
			//配置query，date/week/hour在new的时候已经根据当前日期时间初始化了
			Query query = new Query(); 
			query.setAdsense("Adsense::3");
			query.setAdx("Tanx");
			query.setArea("顺德");
			query.setTerm("PC");
			//执行查询
			long start = System.nanoTime();
			Set<Activity> result = new BoolQuery().boolQuery(primaryIndex, secondaryIndex, query);
			long costtime =  (System.nanoTime()-start)/1000/1000;
			System.out.println("\n最终查询结果:" + result.size() + " 个活动");
			for(Activity act : result){
				System.out.println(act);
			}
			System.out.println("查询耗时: " + costtime + " ms...");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
