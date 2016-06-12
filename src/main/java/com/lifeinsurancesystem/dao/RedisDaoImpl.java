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
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Repository;

/**
 * @类 名: RedisServiceImpl
 * @描 述: redis数据库封装类
 * @作 者: yaojj
 * @邮 箱: 15202125130@163.com
 * @创建日期: 2016年6月7日 下午3:18:42
 * @修改日期: 2016年6月7日 下午3:18:42
 */
@Repository
public class RedisDaoImpl implements IRedisDao {
	protected final Logger log = LoggerFactory.getLogger(RedisDaoImpl.class);
	@Autowired
	private StringRedisTemplate stringRedisTemplate;

	public StringRedisTemplate getStringRedisTemplate() {
		return stringRedisTemplate;
	}

	public void setStringRedisTemplate(StringRedisTemplate stringRedisTemplate) {
		this.stringRedisTemplate = stringRedisTemplate;
	}

	@Override
	public String get(String key) {
		return stringRedisTemplate.boundValueOps(key).get();
	}

	@Override
	public Object hget(String key, String field) {
		Object o = stringRedisTemplate.boundHashOps(key).get(field);
		return o;
	}

	@Override
	public long hincrBy(String key, String field, long value) {
		return stringRedisTemplate.opsForHash().increment(key, field, value);
	}

	@Override
	public Map<Object, Object> hgetAll(String key) {
		return stringRedisTemplate.boundHashOps(key).entries();
	}

	@Override
	public void setHashMap(String key, Map<String, Object> value) {
		stringRedisTemplate.opsForHash().putAll(key, value);
	}

	@Override
	public void setHashValue(String hashKey, String key, Object value) {
		stringRedisTemplate.boundHashOps(hashKey).put(key, value);
	}

	@Override
	public void deleteHashValue(String hashkey, String key) {
		stringRedisTemplate.boundHashOps(hashkey).delete(key);
		;
	}

	@Override
	public void setKeyExpire(String key, int expire) {
		stringRedisTemplate.expire(key, expire, TimeUnit.DAYS);
	}

	@Override
	public void saveValue(String key, String value, Date expire) {
		stringRedisTemplate.boundValueOps(key).set(value);
		stringRedisTemplate.boundValueOps(key).expireAt(expire);
	}

	@Override
	public void setValue(String key, String value) {
		stringRedisTemplate.boundValueOps(key).set(value);
	}

	@Override
	public int delete(String key) {
		Set<String> rs1 = stringRedisTemplate.keys(key);
		stringRedisTemplate.delete(rs1);
		log.debug("删除成功" + rs1.size());
		return rs1.size();

	}

	@Override
	public boolean containKey(String key) {
		return stringRedisTemplate.hasKey(key);
	}
}
