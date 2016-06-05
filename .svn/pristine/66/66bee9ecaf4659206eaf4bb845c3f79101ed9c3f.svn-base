/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.model
* @描          述: 文件描述
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月28日 下午4:36:10 
* @修改日期: 2016年5月28日 下午4:36:10
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.model;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

/**
* @类          名: PositionInfo 
* @描          述: TODO 职位信息
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月28日 下午4:36:14 
* @修改日期: 2016年5月28日 下午4:36:14
 */
public class PositionInfo extends BaseConfig {
    private Long positionCode;
    private String positionName;
    private Long deptCode;
    private String deptName;
    private String orgName;
    private String note;
    
	public Long getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(Long positionCode) {
		this.positionCode = positionCode;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public Long getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(Long deptCode) {
		this.deptCode = deptCode;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
    
	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public PositionInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public PositionInfo(Map<String,Object> map) {
		// TODO Auto-generated constructor stub
		this.setDeptCode(MapUtils.getLong(map, "deptCode",null));
		this.setOrgName(MapUtils.getString(map, "orgName",""));
		this.setOrgType(MapUtils.getIntValue(map, "orgType",1));
		this.setDeptName(MapUtils.getString(map, "deptName",null));
		this.setPositionCode(MapUtils.getLong(map, "positionCode",null));
		this.setPositionName(MapUtils.getString(map, "positionName",""));
		this.setNote(MapUtils.getString(map, "note",""));
		this.setOperatorCode(MapUtils.getString(map, "operatorCode",null));
	}

}

