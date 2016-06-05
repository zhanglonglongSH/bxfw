/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.model
* @描          述: 文件描述
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月27日 上午11:30:43 
* @修改日期: 2016年5月27日 上午11:30:43
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.model;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

/**
* @类          名: DeptInfo 
* @描          述: TODO 部门
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月27日 上午11:30:47 
* @修改日期: 2016年5月27日 上午11:30:47
 */
public class DeptInfo extends BaseConfig {
    private Integer orgCode;
    private String orgName;
    private Long deptCode;
    private String deptName;
    private String note;
    
	public Integer getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(Integer orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public Long getDeptCode() {
		return deptCode;
	}

	public void setDeptCode(Long deptCode) {
		this.deptCode = deptCode;
	}

	public String getDeptName() {
		return deptName;
	}

	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
    
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public DeptInfo() {
		// TODO Auto-generated constructor stub
	}
	
	public DeptInfo(Map<String,Object> map) {
		// TODO Auto-generated constructor stub
		this.setDeptCode(MapUtils.getLong(map, "deptCode",null));
		this.setOrgCode(MapUtils.getIntValue(map, "orgCode",0));
		this.setOrgName(MapUtils.getString(map, "orgName",""));
		this.setDeptName(MapUtils.getString(map, "deptName",null));
		this.setOrgType(MapUtils.getIntValue(map, "orgType",1));
		this.setNote(MapUtils.getString(map, "note",""));
		this.setOperatorCode(MapUtils.getString(map, "operatorCode",null));
	    this.setOperatorIP(MapUtils.getString(map, "operatorIP",null));
	}

}

