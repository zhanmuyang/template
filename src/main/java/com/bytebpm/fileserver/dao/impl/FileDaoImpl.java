package com.bytebpm.fileserver.dao.impl;

import org.springframework.stereotype.Repository;

import com.bytebpm.base.dao.BaseMongoDaoImpl;
import com.bytebpm.fileserver.dao.FileDao;
import com.bytebpm.fileserver.entity.FileEntity;

@Repository
public class FileDaoImpl extends BaseMongoDaoImpl<FileEntity> implements FileDao{

	@Override
	protected Class<FileEntity> getEntityClass() {
		// TODO Auto-generated method stub
		return FileEntity.class;
	}

}
