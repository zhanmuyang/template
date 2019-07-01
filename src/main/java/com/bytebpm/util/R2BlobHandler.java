package com.bytebpm.util;

import java.io.ByteArrayInputStream;
import java.io.UnsupportedEncodingException;
import java.sql.Blob;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.PreparedStatement;

public class R2BlobHandler extends BaseTypeHandler<String> {  

    private static final String DEFAULT_CHARSET = "utf-8";  
  
    @Override  
    public void setNonNullParameter(PreparedStatement ps, int i,  
            String parameter, JdbcType jdbcType) throws SQLException {  
        ByteArrayInputStream bis;  
        try {  
 
            bis = new ByteArrayInputStream(parameter.getBytes(DEFAULT_CHARSET));  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException("Blob Encoding Error!");  
        }     
        ps.setBinaryStream(i, bis, parameter.length());  
    }  
  
    @Override  
    public String getNullableResult(ResultSet rs, String columnName)  
            throws SQLException {  
        Blob blob = rs.getBlob(columnName);  
        byte[] returnValue = null;  
        if (null != blob) {  
            returnValue = blob.getBytes(1, (int) blob.length());  
        }  
        try {  

            return new String(returnValue, DEFAULT_CHARSET);  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException("Blob Encoding Error!");  
        }  
    }  
  
    @Override  
    public String getNullableResult(CallableStatement cs, int columnIndex)  
            throws SQLException {  
        Blob blob = cs.getBlob(columnIndex);  
        byte[] returnValue = null;  
        if (null != blob) {  
            returnValue = blob.getBytes(1, (int) blob.length());  
        }  
        try {  
            return new String(returnValue, DEFAULT_CHARSET);  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException("Blob Encoding Error!");  
        }  
    }

	@Override
	public String getNullableResult(ResultSet rs, int columnIndex) throws SQLException {
		Blob blob = rs.getBlob(columnIndex);  
        byte[] returnValue = null;  
        if (null != blob) {  
            returnValue = blob.getBytes(1, (int) blob.length());  
        }  
        try {  

            return new String(returnValue, DEFAULT_CHARSET);  
        } catch (UnsupportedEncodingException e) {  
            throw new RuntimeException("Blob Encoding Error!");  
        }  
	}  
}  