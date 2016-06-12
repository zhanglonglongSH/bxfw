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
package com.lifeinsurancesystem.service;

import java.util.Date;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.lifeinsurancesystem.dao.IRedisDao;

/**
 * @类 名: RedisServiceImpl
 * @描 述: redis数据库封装类
 * @作 者: yaojj
 * @邮 箱: 15202125130@163.com
 * @创建日期: 2016年6月7日 下午3:18:42
 * @修改日期: 2016年6月7日 下午3:18:42
 */
@Repository
public class RedisServiceImpl implements IRedisService {
	@Autowired
	private IRedisDao redisDaoImpl;

	@Override
	public String get(String key) {
		return redisDaoImpl.get(key);
	}

	@Override
	public Object hget(String key, String field) {
		return redisDaoImpl.hget(key, field);
	}

	@Override
	public long hincrBy(String key, String field, long value) {
		return redisDaoImpl.hincrBy(key, field, value);
	}

	@Override
	public Map<Object, Object> hgetAll(String key) {
		return redisDaoImpl.hgetAll(key);
	}

	@Override
	public void setHashMap(String key, Map<String, Object> value) {
		redisDaoImpl.setHashMap(key, value);
	}

	@Override
	public void setHashValue(String hashKey, String key, Object value) {
		redisDaoImpl.setHashValue(hashKey, key, value);
	}

	@Override
	public void deleteHashValue(String hashkey, String key) {
		redisDaoImpl.deleteHashValue(hashkey, key);
	}

	@Override
	public void setKeyExpire(String key, int expire) {
		redisDaoImpl.setKeyExpire(key, expire);
	}

	@Override
	public void saveValue(String key, String value, Date expire) {
		redisDaoImpl.saveValue(key, value, expire);
	}

	@Override
	public void setValue(String key, String value) {
		redisDaoImpl.setValue(key, value);
	}

	@Override
	public int delete(String key) {
		return redisDaoImpl.delete(key);
	}

	@Override
	public boolean containKey(String key) {
		return redisDaoImpl.containKey(key);
	}
}
