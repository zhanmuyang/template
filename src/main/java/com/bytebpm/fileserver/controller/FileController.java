package com.bytebpm.fileserver.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bytebpm.base.dto.APIResult;
import com.bytebpm.fileserver.entity.FileEntity;
import com.bytebpm.fileserver.entity.FileSizeLimitEntity;
import com.bytebpm.fileserver.service.FileService;
import com.bytebpm.fileserver.service.FileSizeLimitService;

@RestController
@RequestMapping("/api/file")
public class FileController {

	@Resource
	private FileService fileServiceImpl;
	@Resource
	private FileSizeLimitService fileSizeLimitServiceImpl;
	
	@RequestMapping(value="/upload", method=RequestMethod.POST)
	public APIResult upload(String serviceId,@RequestParam("file")CommonsMultipartFile file,HttpServletRequest request) {
		
		FileSizeLimitEntity entity = fileSizeLimitServiceImpl.findFileSizeLimitById(serviceId);
		if(entity != null) {
			if(file.getSize() <= entity.getMaxSize()) {
				return fileServiceImpl.saveFileEntity(file, serviceId, request.getServletContext().getRealPath("/fileserver/"));
			}else {
				return APIResult.failure().setMessage("文件过大");
			}
		}else {
			return APIResult.failure().setMessage("业务不存在");
		}
		
		
	}
	
	@RequestMapping(value="/download", method=RequestMethod.GET)
	public APIResult download(String id,HttpServletRequest request,HttpServletResponse response) {
		FileEntity fileEntity = fileServiceImpl.findFileEntity(id);
		
		if(fileEntity != null) {
			String path = request.getServletContext().getRealPath("/fileserver/")+fileEntity.getServiceId()+"/"+fileEntity.getNumber()+"/";
			
			File filepath = new File(path,fileEntity.getSaveName());
		    //判断路径是否存在,如果不存在就创建文件路径
		    if (!filepath.exists()) { 
		        return APIResult.failure().setMessage("资源已被删除");
		    }
		   
		    try {
		 		response.addHeader("Content-Disposition", "attachment;fileName=" + new String(fileEntity.getRealName().getBytes(),"iso-8859-1"));//iso-8859-1
		 	    response.setContentType("multipart/form-data");
		 	    
		    	InputStream in = new FileInputStream(filepath);
				OutputStream out = response.getOutputStream();
				
				byte b[] = new byte[1024];
				while(in.read(b)!=-1) {
					out.write(b);
				}
				
				in.close();
				out.flush();
				out.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		    fileEntity.setDownloadNumber(fileEntity.getDownloadNumber()+1);
		    fileServiceImpl.updateFileEntity(fileEntity);
		}else {
			return APIResult.failure().setMessage("资源不存在");
		}
	    return null;
	}
	
	
	@RequestMapping(value="/del",method=RequestMethod.GET)
	public APIResult fileDelete(String id, HttpServletRequest request) {
		
		boolean boo = fileServiceImpl.deleteFileEntity(id,request.getServletContext().getRealPath("/fileserver/"));
		if(boo) {
			return APIResult.success().setMessage("删除成功");
		}else {
			return APIResult.failure().setMessage("文件不存在");
		}
	}

}
