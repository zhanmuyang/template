package com.bytebpm.fileserver.entity;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection="file_size_limit")
public class FileSizeLimitEntity implements Serializable {
	
	@Transient
	private static final long serialVersionUID = 1L;

	@Id
	private String id;//业务id
	
	@Field
	private String name;//业务名
	
	private long maxSize;//业务文件最大值（一个字节）

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public long getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(long maxSize) {
		this.maxSize = maxSize;
	}
	
}
