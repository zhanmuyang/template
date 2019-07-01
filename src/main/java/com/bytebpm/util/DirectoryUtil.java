package com.bytebpm.util;

import java.io.File;

import com.bytebpm.constant.SystemConstant;


public class DirectoryUtil {

	public static String getProjectDir() {
		
		// 文件保存路径
        String curDir = Thread.currentThread().getContextClassLoader().getResource("/").getPath();
        
        curDir = curDir.replace("/WEB-INF/classes/", "");
        curDir = curDir.substring(0, curDir.lastIndexOf("/"));
        
        return curDir;
		
	}
	
	
	public static String getExportDir() {
	
		return getProjectDir() + SystemConstant.FILE_EXPORT_PATH + File.separator;
	
	}
	
}
