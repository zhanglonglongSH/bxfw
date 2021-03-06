/** 
 * @公司名称: xxxxxxxxxxxx
 * @版权信息: 版权归属15202125130@163.com
 * @包          名: com.lifeinsurancesystem.config.WebConfigurer
 * @描          述: web配置文件
 * @作          者: yaojj 
 * @邮          箱: 15202125130@163.com 
 * @创建日期: 2016年4月10日 下午9:54:38 
 * @修改日期: 2016年4月10日 下午9:54:38
 * @版权序号: V0.0.1
 */
package com.lifeinsurancesystem.config;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.boot.context.embedded.ConfigurableEmbeddedServletContainer;
import org.springframework.boot.context.embedded.EmbeddedServletContainerCustomizer;
import org.springframework.boot.context.embedded.MimeMappings;
import org.springframework.boot.context.embedded.ServletContextInitializer;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import com.lifeinsurancesystem.web.CachingHttpHeadersFilter;
import com.lifeinsurancesystem.web.StaticResourcesProductionFilter;
import com.lifeinsurancesystem.web.gzip.GZipServletFilter;

/**
 * Configuration of web application with Servlet 3.0 APIs.
 */
@Configuration
@AutoConfigureAfter(CacheConfiguration.class)
public class WebConfigurer implements ServletContextInitializer, EmbeddedServletContainerCustomizer {

	private final Logger log = LoggerFactory.getLogger(WebConfigurer.class);

	@Inject
	private Environment env;

	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		log.info("Web application configuration, using profiles: {}", Arrays.toString(env.getActiveProfiles()));

		log.info("Web application fully configured");
	}

	/**
	 * Set up Mime types.
	 */
	@Override
	public void customize(ConfigurableEmbeddedServletContainer container) {
		MimeMappings mappings = new MimeMappings(MimeMappings.DEFAULT);
		mappings.add("html", "text/html;charset=utf-8");
		mappings.add("json", "text/html;charset=utf-8");
		container.setMimeMappings(mappings);
	}

	/**
	 * Initializes the GZip filter.
	 */
	private void initGzipFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
		log.debug("Registering GZip Filter");
		FilterRegistration.Dynamic compressingFilter = servletContext.addFilter("gzipFilter", new GZipServletFilter());
		Map<String, String> parameters = new HashMap<>();
		compressingFilter.setInitParameters(parameters);
		compressingFilter.addMappingForUrlPatterns(disps, true, "*.css");
		compressingFilter.addMappingForUrlPatterns(disps, true, "*.json");
		compressingFilter.addMappingForUrlPatterns(disps, true, "*.html");
		compressingFilter.addMappingForUrlPatterns(disps, true, "*.js");
		compressingFilter.setAsyncSupported(true);
	}

	/**
	 * Initializes the static resources production Filter.
	 */
	private void initStaticResourcesProductionFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
		log.debug("Registering static resources production Filter");
		FilterRegistration.Dynamic staticResourcesProductionFilter = servletContext.addFilter("staticResourcesProductionFilter", new StaticResourcesProductionFilter());
		staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/");
		staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/index.html");
		staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/assets/*");
		staticResourcesProductionFilter.addMappingForUrlPatterns(disps, true, "/scripts/*");
		staticResourcesProductionFilter.setAsyncSupported(true);
	}

	/**
	 * Initializes the cachig HTTP Headers Filter.
	 */
	private void initCachingHttpHeadersFilter(ServletContext servletContext, EnumSet<DispatcherType> disps) {
		log.debug("Registering Caching HTTP Headers Filter");
		FilterRegistration.Dynamic cachingHttpHeadersFilter = servletContext.addFilter("cachingHttpHeadersFilter", new CachingHttpHeadersFilter());
		cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/assets/*");
		cachingHttpHeadersFilter.addMappingForUrlPatterns(disps, true, "/scripts/*");
		cachingHttpHeadersFilter.setAsyncSupported(true);
	}
}
