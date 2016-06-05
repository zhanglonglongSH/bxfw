/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.util.CommonUtil
* @描          述: 工具类
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.util;
import java.io.UnsupportedEncodingException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Random;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
* @类          名: CommonUtil 
* @描          述: TODO 工具类 
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月25日 下午2:50:50 
* @修改日期: 2016年5月25日 下午2:50:50
 */
public class CommonUtil {
	private static Logger log = LoggerFactory.getLogger(CommonUtil.class);
	/**
	 * 
	  * @Description: TODO描述  生成验证码
	  * @param @return  参数说明 
	  * @return String    返回类型 
	  * @throws
	 */
	public static int createVerificationCode(){
		  Random random = new Random();
		  int num = -1 ;
		  while(true) {
		   num = (int)(random.nextDouble()*(100000 - 10000) + 10000);
		   if(!(num+"").contains("4")) break ;
		  }
		  return num;
	}
	
	/**
	 * 
	  * @Description: TODO描述  获取随机字符串
	  * @return String   
	  * @throws
	 */
	public static String getRandomStr(){
		String str;
		try {
			str = DESSecurity.base64Encode("settingsunwechatpay"+createVerificationCode());
			return str.toUpperCase();
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 *  获取IP
	  * @Description: TODO描述 
	  * @param netAddress
	  * @return String   
	  * @throws
	 */
	public static String getHostIp(){  
		InetAddress netAddress;
		try {
			netAddress = InetAddress.getLocalHost();
			if(null == netAddress){  
	            return null;  
	        }  
	        String ip = netAddress.getHostAddress(); //get the ip address  
	        return ip;  
		}
		catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return null;
    }  
	
	/**
	 * 
	* @方法描述: json字符串转换map
	* @作          者: yaojj
	* @邮          箱: 15202125130@163.com 
	* @参数描述: @param jsonStr
	* @参数描述: @return
	* @返   回   值:
	* @异常处理:
	 */
	public static Map<String,Object> jsonToObject(String jsonStr) {
		JSONObject jsonObj;
		Map<String, Object> outMap=null;
		try {
			jsonObj = new JSONObject(java.net.URLDecoder.decode(DESSecurity.base64Decode(jsonStr), "UTF-8"));
			@SuppressWarnings("unchecked")
			Iterator<String> nameItr = jsonObj.keys();
			String name;
			outMap = new HashMap<String, Object>();
			while (nameItr.hasNext()) {
				name = nameItr.next();
				outMap.put(name, jsonObj.getString(name));
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			log.error("Json字符串转换Map异常", e);
		}
		return outMap;
	}

	
	public static void main(String[] args) {
		try {
			System.out.println(DESSecurity.base64Decode("eyJvcmdDb2RlIjoiMTIiLCJvcmdOYW1lIjoi6+vrIiwibm90ZSI6Ivn5+SJ9"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
