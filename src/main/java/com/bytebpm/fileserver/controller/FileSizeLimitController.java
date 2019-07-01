package com.bytebpm.fileserver.controller;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.bytebpm.base.dto.APIResult;
import com.bytebpm.fileserver.entity.FileSizeLimitEntity;
import com.bytebpm.fileserver.service.FileSizeLimitService;

@RestController
@RequestMapping("/api/file/service")
public class FileSizeLimitController {

	@Resource
	private FileSizeLimitService fileSizeLimitServiceImpl;
	
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public APIResult save(FileSizeLimitEntity entity) {
		FileSizeLimitEntity fsle = fileSizeLimitServiceImpl.findFileSizeLimitByName(entity.getName());
		if(fsle != null) {
			return APIResult.failure().setMessage("该业务已存在");
		}
		fileSizeLimitServiceImpl.saveFileSizeLimit(entity);
		
		return APIResult.success().setMessage("添加成功").setValue(entity);
	}
	
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public APIResult delete(String id) {
		FileSizeLimitEntity entity = fileSizeLimitServiceImpl.findFileSizeLimitById(id);
		if(entity!=null) {
			fileSizeLimitServiceImpl.deleteFileSizeLimit(id);
			return APIResult.success().setMessage("删除成功");
		}else {
			return APIResult.failure().setMessage("业务不存在");
		}
		
	}
	
	@RequestMapping(value="/upd",method=RequestMethod.POST)
	public APIResult update(FileSizeLimitEntity fileSizeLimitEntity) {
		
		fileSizeLimitServiceImpl.updateFileSizeLimit(fileSizeLimitEntity);
		return APIResult.success().setMessage("修改成功");
		
	}
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public APIResult find(int start, int count, String name) {
		return APIResult.success().setValue(fileSizeLimitServiceImpl.findFileSizeLimitByCount((start/count)+1, count, name));
	}
	
	
}
