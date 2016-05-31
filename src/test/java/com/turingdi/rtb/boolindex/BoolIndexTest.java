package com.turingdi.rtb.boolindex;

import org.junit.Test;

import com.turingdi.rtb.boolindex.entity.Activity;

public class BoolIndexTest {
	@Test
	public void getSampleTest(){
		Activity act = null;
		try {
			act = new GetSampleData().getOneRedisSample("Activity::1");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println(act);
	}
}
