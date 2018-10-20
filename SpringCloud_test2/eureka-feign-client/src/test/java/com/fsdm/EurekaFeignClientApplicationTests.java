package com.fsdm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EurekaFeignClientApplicationTests {

	@Test
	public void contextLoads() {
		System.out.println(TimeUnit.SECONDS.toMillis(1));
	}

}
