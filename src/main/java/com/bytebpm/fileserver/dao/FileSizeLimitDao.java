package com.bytebpm.fileserver.dao;

import com.bytebpm.base.dao.BaseMongoDao;
import com.bytebpm.base.dto.PageModel;
import com.bytebpm.fileserver.entity.FileSizeLimitEntity;

public interface FileSizeLimitDao extends BaseMongoDao<FileSizeLimitEntity>{

	public PageModel<FileSizeLimitEntity> findFileSizeLimitByCount(int page,int size,String name);
}
