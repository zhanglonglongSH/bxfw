/** 
 * @公司名称: xxxxxxxxxxxx
 * @版权信息: 版权归属15202125130@163.com
 * @包          名: com.lifeinsurancesystem.model
 * @描          述: 文件描述
 * @作          者: yaojj 
 * @邮          箱: 15202125130@163.com 
 * @创建日期: 2016年6月7日 下午4:07:14 
 * @修改日期: 2016年6月7日 下午4:07:14
 * @版权序号: V0.0.1
 */
package com.lifeinsurancesystem.model;
/**
 * @类          名: Rediskeys 
 * @描          述: 定义reids存储的key
 * @作          者: yaojj 
 * @邮          箱: 15202125130@163.com 
 * @创建日期: 2016年6月7日 下午4:07:17 
 * @修改日期: 2016年6月7日 下午4:07:17
 */
public class Rediskeys {
    private static final String REDIS_KEY="redis_key";
	public Rediskeys() {
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * @方法描述: 输出测试用
	 * @作          者: yaojj
	 * @邮          箱: 15202125130@163.com 
	 * @参数描述: 
	 * @异常处理:
	 */
	public void print(){
		System.out.println(REDIS_KEY);
	}

}

