package com.bytebpm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import com.bytebpm.mongotest.entity.SampleEntity;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ByteBootApplicationTests {

	@Autowired
	private MongoTemplate mongoDao;
	@Autowired
	private RedisTemplate redisTemplate;
	@Test
	public void redisTest() {
		redisTemplate.boundValueOps("name").set("zhanlang");
	}
	@Test
	public void redisGet(){
		String name = (String) redisTemplate.boundValueOps("name").get();
		System.err.println(name);
	}
	@Test
	public void mongoTest(){
		SampleEntity sampleEntity = new SampleEntity();
		sampleEntity.setAge(25);
		sampleEntity.setName("haochangjing");
		mongoDao.save(sampleEntity);
	}
	
}
