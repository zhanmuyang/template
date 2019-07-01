package com.bytebpm.fileserver.dao.impl;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.bytebpm.base.dao.BaseMongoDaoImpl;
import com.bytebpm.base.dto.PageModel;
import com.bytebpm.fileserver.dao.FileSizeLimitDao;
import com.bytebpm.fileserver.entity.FileSizeLimitEntity;

@Repository
public class FIleSizeLimitDaoImpl extends BaseMongoDaoImpl<FileSizeLimitEntity> implements FileSizeLimitDao{

	@Override
	protected Class<FileSizeLimitEntity> getEntityClass() {
		return FileSizeLimitEntity.class;
	}

	public PageModel<FileSizeLimitEntity> findFileSizeLimitByCount(int page,int size,String name){
		PageModel<FileSizeLimitEntity> pages = new PageModel<>(page, size);
		
		pages.setTotalCount((int) this.mgt.count(new Query(Criteria.where("name").regex(name)), FileSizeLimitEntity.class));
		pages.setList(this.mgt.find(new Query(Criteria.where("name").regex(name)), FileSizeLimitEntity.class));
		
		return pages;
	}
}
