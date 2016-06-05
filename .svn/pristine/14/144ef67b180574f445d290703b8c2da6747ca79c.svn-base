/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.util
* @描          述: 文件描述
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月23日 下午6:05:37 
* @修改日期: 2016年5月23日 下午6:05:37
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.util;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.aliyun.oss.OSSClient;
import com.aliyun.oss.model.Bucket;
import com.aliyun.oss.model.OSSObject;
import com.aliyun.oss.model.ObjectMetadata;
import com.aliyun.oss.model.PutObjectResult;

/**
* @类          名: OSSUnit 
* @描          述: 阿里云图片上传
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月23日 下午6:05:42 
* @修改日期: 2016年5月23日 下午6:05:42
 */
public class OSSUnit {
	    private static Logger log = LoggerFactory.getLogger(OSSUnit.class);
		//阿里云API的内或外网域名
		public static String ENDPOINT;
		//阿里云API的密钥Access Key ID
		public static String ACCESS_KEY_ID;
		//阿里云API的密钥Access Key Secret
		public static String ACCESS_KEY_SECRET;
		//阿里云API的主目录 bucketName 
		public static String BUCKET_NAME;
		
		private static  OSSClient oSSClient=null;
		
		private static String PIX="bxfw-dev/userfile/";//存储文件前缀
		
		private static String imgUrl="http://www.sxfwxt.com/bxfw-dev/userfile/";
		
		public OSSUnit() {
			// TODO Auto-generated constructor stub
		}
		/**
		 * 获取阿里云OSS客户端对象
		 * */
		public static OSSClient getOSSClient(){
			if(oSSClient == null){
				oSSClient=new OSSClient(ENDPOINT,ACCESS_KEY_ID, ACCESS_KEY_SECRET);
			}
			return oSSClient;
		}
		
		/**
		 * 新建Bucket  --Bucket权限:私有
		 * @param bucketName bucket名称
		 * @return true 新建Bucket成功
		 * */
		public static boolean createBucket(OSSClient client, String bucketName){
			Bucket bucket = client.createBucket(bucketName); 
			return bucketName.equals(bucket.getName());
		}
		
		/**
		 * 删除Bucket 
		 * @param bucketName bucket名称
		 * */
		public static void deleteBucket(OSSClient client, String bucketName){
			client.deleteBucket(bucketName); 
			log.info("删除" + bucketName + "Bucket成功");
		}
		
		/**
		 * 向阿里云的OSS存储中存储文件  --file也可以用InputStream替代
		 * @param client OSS客户端
		 * @param file 上传文件
		 * @param bucketName bucket名称
		 * @param diskName 上传文件的目录  --bucket下文件的路径
		 * @return String 唯一MD5数字签名
		 * */
		public static String uploadObject2OSS(File file, String bucketName, String diskName) {
			String resultStr = null;
			try {
				InputStream is = new FileInputStream(file);
				String fileName = file.getName();
				Long fileSize = file.length();
				//创建上传Object的Metadata
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(is.available());
				metadata.setCacheControl("no-cache");
				metadata.setHeader("Pragma", "no-cache");
				metadata.setContentEncoding("utf-8");
				metadata.setContentType(getContentType(fileName));
				metadata.setContentDisposition("filesize="+ fileSize + "Byte.");
				//上传文件 
				PutObjectResult putResult = OSSUnit.getOSSClient().putObject(bucketName, diskName + fileName, is, metadata);
				//解析结果
				resultStr = putResult.getETag();
			} catch (Exception e) {
				log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
			}
			return resultStr;
		}
		
		public static String uploadObject2OSS(File file) {
			String resultStr = null;
			try {
				InputStream is = new FileInputStream(file);
				String fileName = file.getName();
				Long fileSize = file.length();
				//创建上传Object的Metadata
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(is.available());
				metadata.setCacheControl("no-cache");
				metadata.setHeader("Pragma", "no-cache");
				metadata.setContentEncoding("utf-8");
				metadata.setContentType(getContentType(fileName));
				metadata.setContentDisposition("filename/filesize=" + fileName + "/" + fileSize + "Byte.");
				//上传文件 
				PutObjectResult putResult = OSSUnit.getOSSClient().putObject(BUCKET_NAME, PIX + fileName, is, metadata);
				//解析结果
				resultStr = putResult.getETag();
			} catch (Exception e) {
				log.error("上传阿里云OSS服务器异常." + e.getMessage(), e);
			}
			return resultStr;
		}
		
	    /** 
	     * 根据key获取OSS服务器上的文件输入流
	 	 * @param client OSS客户端
	 	 * @param bucketName bucket名称
	 	 * @param diskName 文件路径
	 	 * @param key Bucket下的文件的路径名+文件名
	     */  
	     public static InputStream getOSS2InputStream(OSSClient client, String bucketName, String diskName, String key){ 
			OSSObject ossObj = client.getObject(bucketName, diskName + key);
			return ossObj.getObjectContent();	
	     }  
		
	   /** 
	    * 根据key删除OSS服务器上的文件 
	  	* @param client OSS客户端
	  	* @param bucketName bucket名称
	  	* @param diskName 文件路径
	  	* @param key Bucket下的文件的路径名+文件名
	    */  
		  public static void deleteFile(OSSClient client, String bucketName, String diskName, String key){  
		  	client.deleteObject(bucketName, diskName + key); 
		  	log.info("删除" + bucketName + "下的文件" + diskName + key + "成功");
		  }  
	     
	    /** 
	     * 通过文件名判断并获取OSS服务文件上传时文件的contentType 
	     * @param fileName 文件名
	     * @return 文件的contentType   
	     */  
	     public static String getContentType(String fileName){  
	    	String fileExtension = fileName.substring(fileName.lastIndexOf("."));
	    	if("bmp".equalsIgnoreCase(fileExtension)) return "image/bmp";
	    	if("gif".equalsIgnoreCase(fileExtension)) return "image/gif";
	    	if("jpeg".equalsIgnoreCase(fileExtension) || "jpg".equalsIgnoreCase(fileExtension)  || "png".equalsIgnoreCase(fileExtension) ) return "image/jpeg";
	    	if("html".equalsIgnoreCase(fileExtension)) return "text/html";
	    	if("txt".equalsIgnoreCase(fileExtension)) return "text/plain";
	    	if("vsd".equalsIgnoreCase(fileExtension)) return "application/vnd.visio";
	    	if("ppt".equalsIgnoreCase(fileExtension) || "pptx".equalsIgnoreCase(fileExtension)) return "application/vnd.ms-powerpoint";
	    	if("doc".equalsIgnoreCase(fileExtension) || "docx".equalsIgnoreCase(fileExtension)) return "application/msword";
	    	if("xml".equalsIgnoreCase(fileExtension)) return "text/xml";
	        return "text/html";  
	     }  
	     public static void main(String[] args) {
	    	 File d=new File("f://1.png");
			OSSUnit.uploadObject2OSS(d,BUCKET_NAME, "sxfwimg/1.png");
		}
}

