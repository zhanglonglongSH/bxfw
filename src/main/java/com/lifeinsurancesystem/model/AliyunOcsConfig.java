/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.model.AliyunOcsConfig
* @描          述: 阿里云配置信息
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.model;

public class AliyunOcsConfig extends BaseConfig{
	private String ocsEnable;
	private String ocsHost;
	private String ocsPort;
	private String ocsUserName;
	private String ocsPassword;
	public String getOcsEnable() {
		return ocsEnable;
	}
	public void setOcsEnable(String ocsEnable) {
		this.ocsEnable = ocsEnable;
	}
	public String getOcsHost() {
		return ocsHost;
	}
	public void setOcsHost(String ocsHost) {
		this.ocsHost = ocsHost;
	}
	public String getOcsPort() {
		return ocsPort;
	}
	public void setOcsPort(String ocsPort) {
		this.ocsPort = ocsPort;
	}
	public String getOcsUserName() {
		return ocsUserName;
	}
	public void setOcsUserName(String ocsUserName) {
		this.ocsUserName = ocsUserName;
	}
	public String getOcsPassword() {
		return ocsPassword;
	}
	public void setOcsPassword(String ocsPassword) {
		this.ocsPassword = ocsPassword;
	}
	@Override
	public String toString() {
		return "AliyunOcsConfig [ocsEnable=" + ocsEnable + ", ocsHost=" + ocsHost + ", ocsPort=" + ocsPort
				+ ", ocsUserName=" + ocsUserName + ", ocsPassword=" + ocsPassword + "]";
	}
	
}
