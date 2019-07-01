package com.bytebpm.fileserver.service.impl;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.bytebpm.base.dto.APIResult;
import com.bytebpm.fileserver.dao.FileDao;
import com.bytebpm.fileserver.entity.FileEntity;
import com.bytebpm.fileserver.service.FileService;
import com.bytebpm.util.EmptyUtil;

@Service
public class FileServiceImpl implements FileService {

	@Resource
	private FileDao fileDaoImpl;

	@Override
	public APIResult saveFileEntity(CommonsMultipartFile file, String serviceId, String path) {
		// 上传文件名
		String realName = file.getOriginalFilename();
		// 做文件去重
		try {
			// 文件保持路径
			path = path + serviceId + "/";
			// 文件去重
			FileEntity fileEntity = toRepeat(serviceId, realName, file.getInputStream(), path);
			if (fileEntity == null) {

				// 获取存储编号
				int numberPath = 0;
				while (true) {
					int temp = fileDaoImpl.countByCondition(new String[] { "serviceId", "number" },
							new Object[] { serviceId, numberPath });
					if (temp < 10000) {
						break;
					}
					numberPath++;
				}

				if (numberPath < 10000) {// 判断该业务中文件数量是否小于100000000个

					path = path + numberPath + "/";
					// 给文件生成唯一保存名
					int num = 0;
					String saveName = null;
					String fileType = realName.substring(realName.lastIndexOf(".") + 1, realName.length());
					File filepath = null;
					do {// 判断是否存在该文件,如果存在,则重新生成
						num = (int) (System.currentTimeMillis() % 16);
						saveName = UUID.randomUUID().toString().substring(num, num + 16) + "." + fileType;
						filepath = new File(path, saveName);
					} while (filepath.exists());

					// 判断路径是否存在,如果不存在就创建文件路径
					if (!filepath.getParentFile().exists()) {
						filepath.getParentFile().mkdirs();
					}

					// 将上传文件保存到一个目标文件当中
					file.transferTo(new File(path + File.separator + saveName));

					fileEntity = new FileEntity();
					fileEntity.setFileSize(file.getSize());
					fileEntity.setFileType(fileType);
					fileEntity.setNumber(numberPath);
					fileEntity.setRealName(realName);
					fileEntity.setSaveName(saveName);
					fileEntity.setServiceId(serviceId);
					fileEntity.setUploadDate(new Date());

					fileDaoImpl.save(fileEntity);

				} else {
					// 文件数量超过100000000个
					return APIResult.failure().setMessage("该业务文件数量达到上限");
				}
			}
			return APIResult.success().setValue(fileEntity);
		} catch (IOException e1) {
			return APIResult.failure().setMessage("文件上传异常");
		}
	}

	@Override
	public boolean deleteFileEntity(String fileId, String fileserverPath) {
		boolean flag = true;
		FileEntity fileEntity = fileDaoImpl.find(fileId);
		if (fileEntity != null) {
			String path = fileserverPath + fileEntity.getServiceId() + "/" + fileEntity.getNumber() + "/";

			File filepath = new File(path, fileEntity.getSaveName());
			// 判断文件是否存在
			if (filepath.exists()) {
				filepath.delete();
			}
			fileDaoImpl.delete(fileId);
		} else {
			flag = false;
		}
		return flag;
	}

	@Override
	public FileEntity findFileEntity(String fileId) {
		// TODO Auto-generated method stub
		return fileDaoImpl.find(fileId);
	}

	@Override
	public void updateFileEntity(FileEntity file) {
		// TODO Auto-generated method stub
		fileDaoImpl.update(file);
	}

	@Override
	public FileEntity toRepeat(String serviceId, String fileName, InputStream in, String servicePath) {
		
		List<FileEntity> list = fileDaoImpl.findByProps(new String[] { "serviceId", "realName" },
					new String[] { serviceId, fileName });
		
		FileEntity fileEntity = null;
		InputStream fileIn = null;
		try {

			if (EmptyUtil.isNotEmpty(list)) {
				byte[] b = new byte[in.available()];
				in.read(b);
				in = new ByteArrayInputStream(b);
				in.mark(in.available());
				for (FileEntity file2 : list) {
					in.reset();
					File file = new File(servicePath + file2.getNumber() + "/", file2.getSaveName());
					if(file.exists()) {
						fileEntity = file2;
						fileIn = new FileInputStream(file);

						if (fileIn.available() == in.available()) {
							int num1 = 0, num2 = 0;

							while (num1 != -1) {
								num1 = in.read();
								num2 = fileIn.read();
								if (num1 != num2) {
									fileEntity = null;
									break;
								}
							}
						} else {
							fileEntity = null;
						}

						if (fileIn != null) {
							fileIn.close();
						}
						if (fileEntity != null) {
							break;
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return fileEntity;
	}

}
