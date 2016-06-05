/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.util.HttpRequestUtil
* @描          述: http访问工具类
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.util;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.ConnectException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;

import javax.activation.MimetypesFileTypeMap;

import net.sf.json.JSONObject;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpRequestUtil {
	private static Logger log=LoggerFactory.getLogger(HttpRequestUtil.class);
	/**
	 * 
	  * @Title: httpRequest 
	  * @Description: TODO发起https请求并获取结果
	  * @param @param requestUrl
	  * @param @param outputStr
	  * @param @return  参数说明 
	  * @return JSONObject    返回类型 
	  * @throws
	 */
	public static JSONObject httpRequest(String requestUrl,String outputStr) {
		return httpRequest(requestUrl,"POST", outputStr);
	}
	
	/**
	 * 发起https请求并获取结果
	 * @param requestUrl 请求地址
	 * @param requestMethod 请求方式（GET、POST）
	 * @param outputStr 提交的数据
	 * @return JSONObject(通过JSONObject.get(key)的方式获取json对象的属性值)
	 */
	public static JSONObject httpRequest(String requestUrl, String requestMethod, String outputStr) {
		
		log.debug("【发送http请求】"+requestMethod+"\toutputStr="+outputStr);
		JSONObject jsonObject = null;
		StringBuffer buffer = new StringBuffer();
		InputStream inputStream =null;
		InputStreamReader inputStreamReader = null;
		BufferedReader bufferedReader = null;
		HttpURLConnection httpUrlConn=null;
		try {
			URL url = new URL(requestUrl);
			httpUrlConn = (HttpURLConnection) url.openConnection();

			httpUrlConn.setDoOutput(true);
			httpUrlConn.setDoInput(true);
			httpUrlConn.setUseCaches(false);
			// 设置请求方式（GET/POST）
			httpUrlConn.setRequestMethod(requestMethod);

			if ("GET".equalsIgnoreCase(requestMethod))
				httpUrlConn.connect();

			// 当有数据需要提交时
			if (null != outputStr) {
				OutputStream outputStream = httpUrlConn.getOutputStream();
				// 注意编码格式，防止中文乱码
				outputStream.write(outputStr.getBytes("UTF-8"));
				outputStream.close();
			}
			// 将返回的输入流转换成字符串
			inputStream = httpUrlConn.getInputStream();
			inputStreamReader = new InputStreamReader(inputStream, "utf-8");
			bufferedReader = new BufferedReader(inputStreamReader);
			String str = null;
			while ((str = bufferedReader.readLine()) != null) {
				buffer.append(str);
			}
			bufferedReader.close();
			inputStreamReader.close();
			// 释放资源
			inputStream.close();
			httpUrlConn.disconnect();
			jsonObject = JSONObject.fromObject(buffer.toString());
		} catch (ConnectException ce) {
			log.error("网络链接失败！",ce);
		}catch (UnknownHostException uhe) {
			log.error("微信API无法访问....！",uhe);
		} catch (Exception e) {
			log.error("微信API无法访问....！",e);
		}finally {
			try {
				bufferedReader.close();
				inputStreamReader.close();
				inputStream.close();
				httpUrlConn.disconnect();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return jsonObject;
	}
	
    /**
     * @Title: getHttpJson、
     * @Description:
     * @param url
     * @return
     * @return: JSONObject
     */
    public static JSONObject getHttpJson(String url){
		CloseableHttpClient httpClient=HttpClients.createDefault(); 
		JSONObject json=null;
		byte[] responseBody=null;
		try {
			//创建GET方法的实例
			HttpGet httpget = new HttpGet(url);
			// 执行get请求.    
			CloseableHttpResponse response = httpClient.execute(httpget); 
			// 打印响应状态    
			log.debug("【http响应信息】"+response.getStatusLine());  
			// 获取响应实体    
			HttpEntity entity = response.getEntity(); 
			if(entity!=null){
				//读取内容 
				responseBody=EntityUtils.toByteArray(entity);
				json=JSONObject.fromObject(new String(responseBody));
			}
			response.close();
			return json;
		} catch (Exception e) {
			//发生致命的异常，可能是协议不对或者返回的内容有问题
			log.error(url+"请求失败:"+e.getMessage());
		} finally {
			//释放连接
			try {
				httpClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return null;
	}
    /**
     * @Title: postHttpJson、
     * @Description:post请求
     * @param url
     * @return
     * @return: JSONObject
     */
    public static JSONObject postHttpJson(String url){
    	CloseableHttpClient httpClient=HttpClients.createDefault(); 
    	JSONObject json=null;
    	byte[] responseBody=null;
    	try {
    		//创建POST方法的实例
    		HttpPost httpPost = new HttpPost(url);
    		// 执行post请求.
    		httpPost.setHeader("Cookie", "server=weixin");
    		CloseableHttpResponse response = httpClient.execute(httpPost); 
    		// 打印响应状态    
    		log.debug("【http响应信息】"+response.getStatusLine());  
    		// 获取响应实体    
    		HttpEntity entity = response.getEntity(); 
    		if(entity!=null){
    			//读取内容 
    			responseBody=EntityUtils.toByteArray(entity);
    			json=JSONObject.fromObject(new String(responseBody,"UTF-8"));
    		}
    		response.close();
    		return json;
    	} catch (Exception e) {
    		//发生致命的异常，可能是协议不对或者返回的内容有问题
    		log.error(url+"请求失败:"+e.getMessage());
    	} finally {
    		//释放连接
    		try {
    			httpClient.close();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
    	}
    	return null;
    }
    
    /**
     * @Title: formUpload、
     * @Description:上传文件
     * @param url
     * @param localFile
     * @return
     * @return: JSONObject
     */
    public static JSONObject formUpload(String url,String localFile){
    	CloseableHttpClient httpClient=HttpClients.createDefault(); 
    	CloseableHttpResponse response = null;
    	JSONObject json=null;
    	try{
    		//创建GET方法的实例
			HttpPost httpPost = new HttpPost(url);
			httpPost.addHeader("Content-Type", "multipart/form-data");
			// 把文件转换成流对象FileBody
			FileBody bin = new FileBody(new File(localFile));
			HttpEntity entity = MultipartEntityBuilder.create().addPart("media", bin).build();
		    httpPost.setEntity(entity);
            // 发起请求 并返回请求的响应
            response = httpClient.execute(httpPost);
            // 获取响应对象
            HttpEntity resEntity = response.getEntity();
            if (resEntity != null) {
            	//读取内容 
            	String reponseText= EntityUtils.toString(resEntity);
            	json=JSONObject.fromObject(reponseText);
            }
            // 销毁
            EntityUtils.consume(resEntity);  
            return json;
    	}catch(Exception e){
    		//发生致命的异常，可能是协议不对或者返回的内容有问题
    		log.error(url+"请求失败:"+e.getMessage());
    	}finally{
    		 try {
                 if(response != null){
                     response.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
             try {
                 if(httpClient != null){
                     httpClient.close();
                 }
             } catch (IOException e) {
                 e.printStackTrace();
             }
    	}
    	return null;
    }
 
    public static JSONObject formUploadUrl(String url,String imageUrl,String tempFile){
    	CloseableHttpClient httpClient=HttpClients.createDefault(); 
    	CloseableHttpResponse response = null;
    	JSONObject json=null;
    	HttpEntity resEntity=null;
    	try{
    		//创建GET方法的实例
    		HttpGet httpGet = new HttpGet(imageUrl);
    		//获取图片
    		response=httpClient.execute(httpGet);
    		resEntity=response.getEntity();
    		if (resEntity == null) {
    			return null;
    		}
    		//文件落地
    		InputStream in=resEntity.getContent();
    		FileOutputStream out=new FileOutputStream(tempFile);
    		byte[] bytes=new byte[1024];
    		int len=0;
    		while((len=in.read(bytes))>0){
    			out.write(bytes, 0, len);
    		}
    		in.close();
    		out.close();
    		//上传文件
    		FileBody bin = new FileBody(new File(tempFile));
    		HttpPost httpPost = new HttpPost(url);
    		httpPost.addHeader("Content-Type", "multipart/form-data");
    		// 把文件转换成流对象FileBody
    		HttpEntity entity = MultipartEntityBuilder.create().addPart("media", bin).build();
    		httpPost.setEntity(entity);
    		// 发起请求 并返回请求的响应
    		response = httpClient.execute(httpPost);
    		// 获取响应对象
    		resEntity = response.getEntity();
    		if (resEntity != null) {
    			//读取内容 
    			String reponseText= EntityUtils.toString(resEntity);
    			json=JSONObject.fromObject(reponseText);
    			log.info( "请求后url:"+json.toString());
    		}
    		return json;
    	}catch(Exception e){
    		//发生致命的异常，可能是协议不对或者返回的内容有问题
    		log.error(url+"请求失败:"+e.getMessage());
    	}finally{
    		try {
    			if(response != null){
    				response.close();
    			}
    			if(resEntity != null){
    				// 销毁
    				EntityUtils.consume(resEntity);  
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    		try {
    			if(httpClient != null){
    				httpClient.close();
    			}
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    	return null;
    }
    /**
     * @Title: getHttpHtml、
     * @Description:获取html内容
     * @param url
     * @return
     * @return: String
     */
    public static String getHttpHtml(String url){
  		CloseableHttpClient httpClient=HttpClients.createDefault(); 
  		String returnStr=null;
  		byte[] responseBody=null;
  		try {
  			//创建GET方法的实例
  			HttpGet httpget = new HttpGet(url);
  			// 执行get请求.    
  			CloseableHttpResponse response = httpClient.execute(httpget); 
  			// 打印响应状态    
  			log.debug("【http响应信息】"+response.getStatusLine());  
  			// 获取响应实体    
  			HttpEntity entity = response.getEntity(); 
  			if(entity!=null){
  				//读取内容 
  				responseBody=EntityUtils.toByteArray(entity);
  				returnStr=new String(responseBody,"UTF-8");
  			}
  			response.close();
  			return returnStr;
  		} catch (Exception e) {
  			//发生致命的异常，可能是协议不对或者返回的内容有问题
  			log.error(url+"请求失败:"+e.getMessage());
  		} finally {
  			//释放连接
  			try {
  				httpClient.close();
  			} catch (IOException e) {
  				e.printStackTrace();
  			}
  		}
  		return null;
  	}
     
    
    public static String formUploadMaxImg(String urlStr,String imageUrl,String tempDir,String tempFile ) {  
    	CloseableHttpClient httpClient=HttpClients.createDefault(); 
    	CloseableHttpResponse response = null;
    	HttpEntity resEntity=null;
        String res = "";  
        HttpURLConnection conn = null;  
        String BOUNDARY = "---------------------------123821742118716"; //boundary就是request头和上传文件内容的分隔符  
           String tempFileFinal=tempDir  + File.separator +tempFile;
        try {  
        	//创建GET方法的实例
    		HttpGet httpGet = new HttpGet(imageUrl);
    		//获取图片
    		response=httpClient.execute(httpGet);
    		resEntity=response.getEntity();
    		if (resEntity == null) {
    			return null;
    		}
    		//文件落地
    		InputStream in1=resEntity.getContent();
    		FileOutputStream out1=new FileOutputStream(tempFileFinal);
    		byte[] bytes1=new byte[1024];
    		int len=0;
    		while((len=in1.read(bytes1))>0){
    			out1.write(bytes1, 0, len);
    		}
    		in1.close();
    		out1.close();
    		if(httpClient != null){
				httpClient.close();
			}
    		//文件压缩
    	     String tempFile1 =  System.currentTimeMillis() + ".png";
    	     tempFile1 =tempFile;
    		
            URL url = new URL(urlStr);  
            conn = (HttpURLConnection) url.openConnection();  
            conn.setConnectTimeout(5000);  
            conn.setReadTimeout(30000);  
            conn.setDoOutput(true);  
            conn.setDoInput(true);  
            conn.setUseCaches(false);  
            conn.setRequestMethod("POST");  
            conn.setRequestProperty("Connection", "Keep-Alive");  
            conn.setRequestProperty("User-Agent",  "Mozilla/5.0 (Windows; U; Windows NT 6.1; zh-CN; rv:1.9.2.6)");  
            conn.setRequestProperty("Content-Type",   "multipart/form-data; boundary=" + BOUNDARY);  
            OutputStream out = new DataOutputStream(conn.getOutputStream());  
            File file = new File(tempDir+File.separator+tempFile1);  
            String filename = file.getName();  
            String contentType = new MimetypesFileTypeMap() .getContentType(file);  
            if (filename.endsWith(".png")) {  
                contentType = "image/png";  
            }  
            if (contentType == null || contentType.equals("")) {  
                contentType = "application/octet-stream";  
               }  
                    StringBuffer strBuf = new StringBuffer();  
             strBuf.append("\r\n").append("--").append(BOUNDARY).append( "\r\n");  
            strBuf.append("Content-Disposition: form-data; name=\"userfile\"; filename=\"" + filename  + "\"\r\n");  
            strBuf.append("Content-Type:" + contentType + "\r\n\r\n");
            out.write(strBuf.toString().getBytes());  
            DataInputStream in = new DataInputStream(new FileInputStream(file));  
            int bytes = 0;  
            byte[] bufferOut = new byte[1024];  
            while ((bytes = in.read(bufferOut)) != -1) {  
                out.write(bufferOut, 0, bytes);  
            }  
            in.close();  
            byte[] endData = ("\r\n--" + BOUNDARY + "--\r\n").getBytes();  
            out.write(endData);  
            out.flush();  
            out.close();
            // 读取返回数据  
            StringBuffer strBufs = new StringBuffer();  
            BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));  
            String line = null;  
            while ((line = reader.readLine()) != null) {  
            	strBufs.append(line).append("\n");  
            }  
            res = strBufs.toString();  
            reader.close();
        } catch (Exception e) {   
            e.printStackTrace();  
        } finally {  
            if (conn != null) {  
                conn.disconnect();  
                conn = null;  
            }  
        }  
        return res;
    }
}
