package com.bytebpm.fileserver.service;

import java.io.InputStream;

import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bytebpm.base.dto.APIResult;
import com.bytebpm.fileserver.entity.FileEntity;

public interface FileService {

	/**
	 * 保存file对象至mongo
	 * @param file
	 */
	public APIResult saveFileEntity(CommonsMultipartFile file, String serviceId,String path);
	
	/**
	 * 删除附件
	 * @param fileId
	 * @param fileserverPath  ==>request.getServletContext().getRealPath("/fileserver/")
	 */
	public boolean deleteFileEntity(String fileId,String fileserverPath);
	
	/**
	 * 查询附件
	 * @param fileId
	 */
	public FileEntity findFileEntity(String fileId);
	
	/**
	 * 修改文件下载次数
	 * @param downloadNumber
	 */
	public void updateFileEntity(FileEntity file);
	
	/**
	 * 判断该业务中是否有该附件
	 * 如果有该附件并且内容一样,则返回该FileEntity对象
	 * 没有则返回null
	 * @return
	 */
	public FileEntity toRepeat(String serviceId,String fileName,InputStream in, String servicePath);
}
