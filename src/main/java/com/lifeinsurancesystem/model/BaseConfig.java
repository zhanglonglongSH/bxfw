/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.model.BaseConfig
* @描          述: 配置文件
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.model;

import org.apache.commons.beanutils.BeanMap;
import org.apache.commons.lang.StringUtils;

public class BaseConfig {
	private int orgType;
	private String createTime;
    private String updateTime;
    private String operatorCode;
    private String operatorIP;
    private String flag;
    
	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getOperatorCode() {
		return operatorCode;
	}

	public void setOperatorCode(String operatorCode) {
		this.operatorCode = operatorCode;
	}

	public String getOperatorIP() {
		return operatorIP;
	}

	public void setOperatorIP(String operatorIP) {
		this.operatorIP = operatorIP;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
    
	public int getOrgType() {
		return orgType;
	}

	public void setOrgType(int orgType) {
		this.orgType = orgType;
	}

	/**
	 * 检查数据的合法性
	 * 
	 * @return
	 */
	public String check() {
		StringBuffer message=new StringBuffer();
		BeanMap map = new BeanMap(this);
		for (Object key : map.keySet()) {
			if("class".equals((String)key)){
				continue;
			}

			Object value=map.get(key);// key 是属性，map.get(key) 是属性值。
			if (value==null||StringUtils.isEmpty(StringUtils.trim((String)value))){
				message.append((String) key);
				message.append(" is null ;");
			}
		}
		return message.toString();
	}
}
