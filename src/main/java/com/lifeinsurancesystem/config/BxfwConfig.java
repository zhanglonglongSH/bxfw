/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.config
* @描          述: 文件描述
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月24日 上午11:05:11 
* @修改日期: 2016年5月24日 上午11:05:11
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.apache.commons.lang.StringUtils;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;

import com.lifeinsurancesystem.model.BxfwConfigBean;
import com.lifeinsurancesystem.util.OSSUnit;

/**
* @类          名: bxfwConfig 
* @描          述: TODO
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月24日 上午11:05:17 
* @修改日期: 2016年5月24日 上午11:05:17
 */
@Configuration
@EnableTransactionManagement
@MapperScan("com.lifeinsurancesystem.service")
public class BxfwConfig implements EnvironmentAware {

	public BxfwConfig() {
		// TODO Auto-generated constructor stub
	}
	
	private final Logger _log = LoggerFactory.getLogger(BxfwConfig.class);
	private RelaxedPropertyResolver propertyResolver;
	private Environment env;
	
	@Override
	public void setEnvironment(Environment env) {
		this.env = env;
		this.propertyResolver = new RelaxedPropertyResolver(env,"lifeinsurancesystem.");
	}
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public BxfwConfigBean getBxfwConfigBean(){
		//初始化阿里云连接参数
		OSSUnit.ACCESS_KEY_ID=propertyResolver.getProperty("aliyun.oss.accessKeyId");
		OSSUnit.ACCESS_KEY_SECRET=propertyResolver.getProperty("aliyun.oss.accessKeySecret");
		OSSUnit.ENDPOINT=propertyResolver.getProperty("aliyun.oss.endPoint");
		OSSUnit.BUCKET_NAME=propertyResolver.getProperty("aliyun.oss.bucketName");
		BxfwConfigBean server = new BxfwConfigBean();
		//初始化阿里云对象存储参数
		server.setBxfwSys(propertyResolver.getProperty("bxfw.bxfwSys"));
		server.setOssImgUrl(propertyResolver.getProperty("bxfw.ossImgUrl"));
		//添加参数为empty检查
		String message=server.check();
		if(!StringUtils.isEmpty(StringUtils.trim(message))){
			_log.error("weixinserver error message {}",message);
		}
		return server;
	}
	
	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public CommonsMultipartResolver getCommonsMultipartResolver(){
		CommonsMultipartResolver server = new CommonsMultipartResolver();
		return server;
	}
	
}

