package com.turingdi.rtb.boolindex;

import java.io.File;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;

import com.turingdi.rtb.boolindex.entity.Activity;
import com.turingdi.rtb.boolindex.entity.Assignment;
import com.turingdi.rtb.boolindex.entity.Conjunction;
import com.turingdi.rtb.boolindex.entity.Posting;

public class BoolIndexTest {
	@Test
	public void getSampleTest(){
		Map<Conjunction, List<Activity>> primaryIndex = new HashMap<Conjunction, List<Activity>>();
		Map<Integer, Map<Assignment, List<Posting>>> secondaryIndex = new HashMap<Integer, Map<Assignment, List<Posting>>>();
		try {
			for(int i = 1; i >= 0; i--){
				long start = System.nanoTime();
				Activity act = new GetSampleData().getOneRedisSample("Activity::" + (i+1));
				System.out.println("活动：\n" + act);
				//System.out.println("分conjunction结果：\n" + new MakeIndex().analysisConjunction(act));
				new MakeIndex().appendIndex(primaryIndex, secondaryIndex, act);
				System.out.println("Indexing for Activity::" + (i+1) + " Cost: " + (System.nanoTime()-start)/1000/1000 + " ms...");
			}
			
			/*for(Assignment assg : secondaryIndex.get(8).keySet()){
				System.out.println(assg + "---" + secondaryIndex.get(8).get(assg));
			}*/
			new FileWriter(new File("log.log"),false).write("\n建立索引结果：\n一级索引：\n" + primaryIndex + "\n二级索引：\n" + secondaryIndex);
			//System.out.println("\n建立索引结果：\n一级索引：\n" + primaryIndex + "\n二级索引：\n" + secondaryIndex);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
