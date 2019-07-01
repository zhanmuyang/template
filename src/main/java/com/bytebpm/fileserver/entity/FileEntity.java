package com.bytebpm.fileserver.entity;

import java.io.Serializable;
import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "file_info")
@CompoundIndexes({
	@CompoundIndex(name="serviceId_realName",def="{'serviceId':1,'realName':1}")
})
public class FileEntity implements Serializable {

	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	private String id;//文件id
	
	@Field
	private String realName;//文件真实名
	
	private String saveName;//文件在服务器中保存的名
	
	private long fileSize;//文件大小
	
	private String fileType;//文件类型
	
	@Indexed
	private String serviceId;//业务id
	
	private int number;//编号(对应实际位置)
	
	private Date uploadDate;//上传时间
	
	private int downloadNumber;//下载次数

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getSaveName() {
		return saveName;
	}

	public void setSaveName(String saveName) {
		this.saveName = saveName;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getServiceId() {
		return serviceId;
	}

	public void setServiceId(String serviceId) {
		this.serviceId = serviceId;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public Date getUploadDate() {
		return uploadDate;
	}

	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
	}

	public int getDownloadNumber() {
		return downloadNumber;
	}

	public void setDownloadNumber(int downloadNumber) {
		this.downloadNumber = downloadNumber;
	}

	@Override
	public String toString() {
		return "FileEntity [id=" + id + ", realName=" + realName + ", saveName=" + saveName + ", fileSize=" + fileSize
				+ ", fileType=" + fileType + ", serviceId=" + serviceId + ", number=" + number + ", uploadDate="
				+ uploadDate + ", downloadNumber=" + downloadNumber + "]";
	}
	
}
