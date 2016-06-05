/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.config.SpringMvcConfig
* @描          述: spring配置文件
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.config;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
* @类          名: SpringMvcConfig 
* @描          述: TODO
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月24日 上午11:34:48 
* @修改日期: 2016年5月24日 上午11:34:48
 */
@Configuration
@EnableWebMvc
public class SpringMvcConfig extends WebMvcConfigurerAdapter {
	private final Logger log = LoggerFactory.getLogger(WebMvcConfigurerAdapter.class);
	
	@Autowired
	MappingJackson2HttpMessageConverter mappingJackson2HttpMessageConverter;
	
	
    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
    	log.info("configureDefaultServletHandling is enabled");
        configurer.enable();
    }	
	/**
	 * 配置拦截器
	 * 
	 * @author lance
	 * @param registry
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {

	}

	@Override
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addRedirectViewController("/", "/app/login.html");
	}

	@Override
	public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
		List<MediaType> supportedMediaTypes=new ArrayList<MediaType> ();
		supportedMediaTypes.add(MediaType.TEXT_HTML);
	    supportedMediaTypes.add(new MediaType("text", "plain",Charset.forName("UTF-8")));
	    supportedMediaTypes.add(new MediaType("application", "json",Charset.forName("UTF-8")));		
		mappingJackson2HttpMessageConverter.setSupportedMediaTypes(supportedMediaTypes);
		
		converters.add(mappingJackson2HttpMessageConverter);
	}
}