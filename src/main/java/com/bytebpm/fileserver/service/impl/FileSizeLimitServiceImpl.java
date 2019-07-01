package com.bytebpm.fileserver.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.bytebpm.base.dto.PageModel;
import com.bytebpm.fileserver.dao.FileSizeLimitDao;
import com.bytebpm.fileserver.entity.FileSizeLimitEntity;
import com.bytebpm.fileserver.service.FileSizeLimitService;
import com.bytebpm.util.EmptyUtil;

@Service
public class FileSizeLimitServiceImpl implements FileSizeLimitService{

	@Resource
	private FileSizeLimitDao fileSizeLimitDaoImpl;
	
	@Override
	public void saveFileSizeLimit(FileSizeLimitEntity entity) {
		fileSizeLimitDaoImpl.save(entity);
		
	}

	@Override
	public void deleteFileSizeLimit(String id) {
		fileSizeLimitDaoImpl.delete(id);
	}

	@Override
	public void updateFileSizeLimit(FileSizeLimitEntity entity) {
		fileSizeLimitDaoImpl.update(entity);
		
	}

	@Override
	public FileSizeLimitEntity findFileSizeLimitById(String id) {
		return fileSizeLimitDaoImpl.find(id);
	}

	@Override
	public PageModel<FileSizeLimitEntity> findFileSizeLimitByCount(int page, int size,String name) {

		if(EmptyUtil.isNotEmpty(name)) {
			return fileSizeLimitDaoImpl.findFileSizeLimitByCount(page, size, name);
		}else {
			return fileSizeLimitDaoImpl.pageAll(page, size);
		}
		
	}

	@Override
	public FileSizeLimitEntity findFileSizeLimitByName(String name) {
		
		return fileSizeLimitDaoImpl.uniqueByProp("name", name);
	}

}
