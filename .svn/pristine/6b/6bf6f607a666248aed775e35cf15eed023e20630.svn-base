/** 
 * @公司名称: xxxxxxxxxxxx
 * @版权信息: 版权归属15202125130@163.com
 * @包          名: com.lifeinsurancesystem.dao
 * @描          述: 文件描述
 * @作          者: yaojj 
 * @邮          箱: 15202125130@163.com 
 * @创建日期: 2016年6月7日 下午3:17:27 
 * @修改日期: 2016年6月7日 下午3:17:27
 * @版权序号: V0.0.1
 */
package com.lifeinsurancesystem.dao;

import java.util.Date;
import java.util.Map;

/**
 * @类 名: IRedisService
 * @描 述: redis数据库封装类
 * @作 者: yaojj
 * @邮 箱: 15202125130@163.com
 * @创建日期: 2016年6月7日 下午3:17:32
 * @修改日期: 2016年6月7日 下午3:17:32
 */
public interface IRedisDao {
	/**
	 * @方法描述: 根据key获取redis对象
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param key
	 * @参数描述: @return
	 * @异常处理:
	 */
	public String get(String key);

	/**
	 * @方法描述: 根据key和字段获取value值
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param key
	 * @参数描述: @param field
	 * @参数描述: @return
	 * @异常处理:
	 */
	public Object hget(String key, String field);

	/**
	 * @方法描述: 存储值
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param key
	 * @参数描述: @param field
	 * @参数描述: @param value
	 * @参数描述: @return
	 * @异常处理:
	 */
	public long hincrBy(String key, String field, long value);

	/**
	 * @方法描述: 获取key对应的所有属性
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param key
	 * @参数描述: @return
	 * @异常处理:
	 */
	public Map<Object, Object> hgetAll(String key);

	/**
	 * @方法描述: 存储map对象
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param key
	 * @参数描述: @param value
	 * @异常处理:
	 */
	public void setHashMap(String key, Map<String, Object> value);

	/**
	 * @方法描述: key-value存储
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param hashKey
	 * @参数描述: @param key
	 * @参数描述: @param value
	 * @异常处理:
	 */
	public void setHashValue(String hashKey, String key, Object value);

	/**
	 * @方法描述: 根据key删除对象
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param hashkey
	 * @参数描述: @param key
	 * @异常处理:
	 */
	public void deleteHashValue(String hashkey, String key);

	/**
	 * @方法描述: 设置key的过期时间
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param key
	 * @参数描述: @param expire
	 * @异常处理:
	 */
	public void setKeyExpire(String key, int expire);

	/**
	 * @方法描述: 存储对象，带过期参数
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param key
	 * @参数描述: @param value
	 * @参数描述: @param expire
	 * @异常处理:
	 */
	public void saveValue(String key, String value, Date expire);

	/**
	 * @方法描述: 存储key-value
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param key
	 * @参数描述: @param value
	 * @异常处理:
	 */
	public void setValue(String key, String value);

	/**
	 * @方法描述: 删除对象
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param key
	 * @参数描述: @return
	 * @异常处理:
	 */
	public int delete(String key);

	/**
	 * @方法描述: 判断对象是否存在
	 * @作 者: yaojj
	 * @邮 箱: 15202125130@163.com
	 * @参数描述: @param key
	 * @参数描述: @return
	 * @异常处理:
	 */
	public boolean containKey(String key);
}
