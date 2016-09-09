package com.bimt.thesisquery.common.oss;

import java.io.IOException;
import java.util.Properties;

import com.bimt.thesisquery.common.util.PropertiesUtils;

/**
 * 阿里云配置对象
 * 
 * @author xufeng
 *
 */
public class OSSConfigure {

	private String endpoint;
	private String accessKeyId;
	private String accessKeySecret;
	private String bucketName;
	private String accessUrl;

	private static class LazyHolder {
		private static final OSSConfigure INSTANCE = new OSSConfigure();
	}

	private OSSConfigure() {
		endpoint = PropertiesUtils.getInstance().getProperty("oss.endpoint").trim();
		accessKeyId = PropertiesUtils.getInstance().getProperty("oss.accessKeyId").trim();
		accessKeySecret = PropertiesUtils.getInstance().getProperty("oss.accessKeySecret").trim();
		bucketName = PropertiesUtils.getInstance().getProperty("oss.bucketName").trim();
		accessUrl = PropertiesUtils.getInstance().getProperty("oss.accessUrl").trim();
	}

	public static final OSSConfigure getInstance() {
		return LazyHolder.INSTANCE;
	}

	/**
	 * 通过配置文件.properties文件获取，这几项内容。
	 * 
	 * @param storageConfName
	 * @throws IOException
	 */
	public OSSConfigure(String storageConfName) throws IOException {

		Properties prop = new Properties();
		prop.load(this.getClass().getClassLoader().getResourceAsStream("conf/" + storageConfName));

		endpoint = prop.getProperty("oss.endpoint").trim();
		accessKeyId = prop.getProperty("oss.accessKeyId").trim();
		accessKeySecret = prop.getProperty("oss.accessKeySecret").trim();
		bucketName = prop.getProperty("oss.bucketName").trim();
		accessUrl = prop.getProperty("oss.accessUrl").trim();

	}

	public OSSConfigure(String endpoint, String accessKeyId, String accessKeySecret, String bucketName,
			String accessUrl) {

		this.endpoint = endpoint;
		this.accessKeyId = accessKeyId;
		this.accessKeySecret = accessKeySecret;
		this.bucketName = bucketName;
		this.accessUrl = accessUrl;
	}

	public String getEndpoint() {
		return endpoint;
	}

	public void setEndpoint(String endpoint) {
		this.endpoint = endpoint;
	}

	public String getAccessKeyId() {
		return accessKeyId;
	}

	public void setAccessKeyId(String accessKeyId) {
		this.accessKeyId = accessKeyId;
	}

	public String getAccessKeySecret() {
		return accessKeySecret;
	}

	public void setAccessKeySecret(String accessKeySecret) {
		this.accessKeySecret = accessKeySecret;
	}

	public String getBucketName() {
		return bucketName;
	}

	public void setBucketName(String bucketName) {
		this.bucketName = bucketName;
	}

	public String getAccessUrl() {
		return accessUrl;
	}

	public void setAccessUrl(String accessUrl) {
		this.accessUrl = accessUrl;
	}

}
