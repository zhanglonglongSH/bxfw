/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.enums.BxfwMessageCode
* @描          述: 保险服务平台枚举类型
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.enums;
public enum BxfwMessageCode {
	/**
	 * 空数据{@value 1007}
	 */
	DATANULL("空数据", 1007)
	/**
	 * 操作成功 {@value 1008}
	 */
	,SUCCESS("操作成功", 1008)
	/**
	 * 通信失败 {@value 1009}
	 */
	,FAILURE("通信失败", 1009);
	/**
	 * 描述
	 */
	private String message;
	/**
	 * 返回码
	 */
	private int code;

	private BxfwMessageCode(String message, int code) {
		this.message = message;
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public String toString() {
		return "Messagecode:[message=" + getMessage() + ",code=" + getCode()
				+ "]";
	}

}
