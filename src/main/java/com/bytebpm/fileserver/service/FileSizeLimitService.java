package com.bytebpm.fileserver.service;

import com.bytebpm.base.dto.PageModel;
import com.bytebpm.fileserver.entity.FileSizeLimitEntity;

public interface FileSizeLimitService {

	/**
	 * 添加文件业务大小限制信息
	 * @param entity
	 */
	public void saveFileSizeLimit(FileSizeLimitEntity entity);
	
	/**
	 * 根据id删除文件业务大小限制信息
	 * @param id
	 */
	public void deleteFileSizeLimit(String id);
	
	/**
	 * 根据id修改文件限制大小
	 * @param entity
	 */
	public void updateFileSizeLimit(FileSizeLimitEntity entity);
	
	/**
	 * 根据id查询文件业务大小限制信息
	 * @param id
	 * @return
	 */
	public FileSizeLimitEntity findFileSizeLimitById(String id);
	
	/**
	 * 分页查询
	 * @param start
	 * @param count
	 * @return
	 */
	public PageModel<FileSizeLimitEntity> findFileSizeLimitByCount(int page,int size,String name);
	
	/**
	 * 根据业务名查询
	 * @param name
	 * @return
	 */
	public FileSizeLimitEntity findFileSizeLimitByName(String name);
	
}
