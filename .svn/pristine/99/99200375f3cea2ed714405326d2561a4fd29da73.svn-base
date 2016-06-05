/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.config.DatabaseConfiguration
* @描          述: 数据库配置信息
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.config;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.ApplicationContextException;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.Scope;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.codahale.metrics.MetricRegistry;
import com.lifeinsurancesystem.util.DESSecurity;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
@Configuration
@EnableTransactionManagement
@MapperScan("com.lifeinsurancesystem.dao")
public class DatabaseConfiguration implements EnvironmentAware {

	private final Logger log = LoggerFactory.getLogger(DatabaseConfiguration.class);
	private RelaxedPropertyResolver propertyResolver;
	private Environment env;

    @Autowired
    private MetricRegistry metricRegistry;
    
	@Override
	public void setEnvironment(Environment env) {
		this.env = env;
		this.propertyResolver = new RelaxedPropertyResolver(env,"spring.datasource.");
	}

	@Bean(destroyMethod = "shutdown")
	@ConditionalOnMissingClass(name = "com.lifeinsurancesystem.config.HerokuDatabaseConfiguration")
	@Profile("!" + "cloud")
	public DataSource dataSource() {
		log.debug("Configuring Datasource");
		if (propertyResolver.getProperty("url") == null&& propertyResolver.getProperty("databaseName") == null&&propertyResolver.getProperty("secret-file") == null) {
			log.error("请设置url、database或者设置用户名密码的保存文件secret-file",Arrays.toString(env.getActiveProfiles()));

			throw new ApplicationContextException(
					"Database connection pool is not configured correctly");
		}
		HikariConfig config = new HikariConfig();
		
		config.setDataSourceClassName(propertyResolver.getProperty("dataSourceClassName"));
		if (propertyResolver.getProperty("url") == null) {
			config.addDataSourceProperty("url","");
		}else{
			config.addDataSourceProperty("url",propertyResolver.getProperty("url"));
		}
		if (propertyResolver.getProperty("databaseName") == null) {
			config.addDataSourceProperty("databaseName","");
		}else{
			config.addDataSourceProperty("databaseName",propertyResolver.getProperty("databaseName"));
		}	
		if (propertyResolver.getProperty("username") == null) {
			config.addDataSourceProperty("user","");
		} else {
			try {
				config.addDataSourceProperty("user",
						DESSecurity.decrypt(propertyResolver.getProperty("username")));
			} catch (Exception e) {
				log.error("database username decrypt error,"+e.getMessage(),e);
			}
		}
		if (propertyResolver.getProperty("password") == null) {
			config.addDataSourceProperty("password","");
		} else {
			try {
				config.addDataSourceProperty("password",
						DESSecurity.decrypt(propertyResolver.getProperty("password")));
			} catch (Exception e) {
				log.error("database password decrypt error,"+e.getMessage(),e);
			}
		}

		// 从自定义的配置文件中读取安全要求比较高的信息
		if (propertyResolver.getProperty("secret-file") != null
				&& !"".equals(propertyResolver.getProperty("secret-file"))) {
			File file = new File(propertyResolver.getProperty("secret-file"));
			if (!file.exists()) {
				throw new ApplicationContextException("Database secret file :"
						+ propertyResolver.getProperty("secret-file")
						+ " not exists");
			}
			try {
				Properties props = new Properties();
				props.load(new FileReader(file));
				
				if (props.containsKey("datasource.url")) {
					config.addDataSourceProperty("url",
							props.get("datasource.url"));
				}
				if (props.containsKey("datasource.user")) {
					try {
						config.addDataSourceProperty("user",
								DESSecurity.decrypt(props.get("datasource.user").toString()));
					} catch (Exception e) {
						log.error("database(uat or prod) username decrypt:"+e.getMessage(),e);
					}
				}
				if (props.containsKey("datasource.password")) {
					try {
						config.addDataSourceProperty("password",
								DESSecurity.decrypt(props.get("datasource.password").toString()));
					} catch (Exception e) {
						log.error("database(uat or prod) password decrypt:"+e.getMessage(),e);
					}
				}

			} catch (FileNotFoundException e) {
				log.error(e.getMessage(), e);
			} catch (IOException e) {
				log.error(e.getMessage(), e);
			}
		}

		// MySQL optimizations, see
		// https://github.com/brettwooldridge/HikariCP/wiki/MySQL-Configuration
		if ("com.mysql.jdbc.jdbc2.optional.MysqlDataSource".equals(propertyResolver.getProperty("dataSourceClassName"))) {
			config.addDataSourceProperty("cachePrepStmts",propertyResolver.getProperty("cachePrepStmts", "true"));
			config.addDataSourceProperty("prepStmtCacheSize",propertyResolver.getProperty("prepStmtCacheSize", "250"));
			config.addDataSourceProperty("prepStmtCacheSqlLimit",propertyResolver.getProperty("prepStmtCacheSqlLimit","2048"));
			config.addDataSourceProperty("useServerPrepStmts",propertyResolver.getProperty("useServerPrepStmts", "true"));
		}

		HikariDataSource hikariDataSource=new HikariDataSource(config);
		hikariDataSource.setMetricRegistry(metricRegistry);		
		return hikariDataSource;
	}

	@Bean
	@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
	public SqlSessionTemplate sqlSessionTemplate() throws Exception {
		return new SqlSessionTemplate(sqlSessionFactory());
	}

	@Bean
	public SqlSessionFactory sqlSessionFactory() throws Exception {
		final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
		Properties myBatisConfig=new Properties();
		myBatisConfig.setProperty("logImpl", "SLF4J");
		sessionFactory.setConfigurationProperties(myBatisConfig);
		sessionFactory.setDataSource(dataSource());
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		sessionFactory.setMapperLocations(resolver.getResources("classpath:/mybatismap/*.xml"));
		return sessionFactory.getObject();
	}
	
	
	public static void main(String[] args) {
		try {
			System.out.println(DESSecurity.base64Encode("root"));
		}
		catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
