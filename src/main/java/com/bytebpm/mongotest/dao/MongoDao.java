package com.bytebpm.mongotest.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.bytebpm.mongotest.entity.SampleEntity;

public interface MongoDao extends MongoRepository<SampleEntity, Integer>{
	
}
