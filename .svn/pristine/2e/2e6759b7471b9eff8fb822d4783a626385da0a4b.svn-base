/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.model
* @描          述: 文件描述
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月20日 上午9:03:48 
* @修改日期: 2016年5月20日 上午9:03:48
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.model;

import java.util.Map;

import org.apache.commons.collections.MapUtils;

/**
* @类          名: CompanyInfo 
* @描          述: 公司信息
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月20日 上午9:13:33 
* @修改日期: 2016年5月20日 上午9:13:33
 */
public class CompanyInfo extends BaseConfig {
    private Long id;
    private Long companyCode;
    private String orgCode;
    private String parentCode;
    private String orgNameAttr;
    private String orgName;
    private String cityOrgCode;
    private String cityOrgName;
    private String countyOrgCode;
    private String countyOrgName;
    private String hallOrgCode;
    private String hallOrgName;
    private String note;
    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCompanyCode() {
		return companyCode;
	}

	public void setCompanyCode(Long companyCode) {
		this.companyCode = companyCode;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgNameAttr() {
		return orgNameAttr;
	}

	public void setOrgNameAttr(String orgNameAttr) {
		this.orgNameAttr = orgNameAttr;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
    
	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}
	
	public CompanyInfo() {
		// TODO Auto-generated constructor stub
	}
	
    public String getCityOrgCode() {
		return cityOrgCode;
	}

	public void setCityOrgCode(String cityOrgCode) {
		this.cityOrgCode = cityOrgCode;
	}

	public String getCityOrgName() {
		return cityOrgName;
	}

	public void setCityOrgName(String cityOrgName) {
		this.cityOrgName = cityOrgName;
	}

	public String getCountyOrgCode() {
		return countyOrgCode;
	}

	public void setCountyOrgCode(String countyOrgCode) {
		this.countyOrgCode = countyOrgCode;
	}

	public String getCountyOrgName() {
		return countyOrgName;
	}

	public void setCountyOrgName(String countyOrgName) {
		this.countyOrgName = countyOrgName;
	}

	public String getHallOrgCode() {
		return hallOrgCode;
	}

	public void setHallOrgCode(String hallOrgCode) {
		this.hallOrgCode = hallOrgCode;
	}

	public String getHallOrgName() {
		return hallOrgName;
	}

	public void setHallOrgName(String hallOrgName) {
		this.hallOrgName = hallOrgName;
	}

	public String getParentCode() {
		return parentCode;
	}

	public void setParentCode(String parentCode) {
		this.parentCode = parentCode;
	}

	//数据转换
	public CompanyInfo(Map<String,Object> map) {
		// TODO Auto-generated constructor stub
		this.setId(MapUtils.getLong(map, "orgId",null));
		this.setCompanyCode(MapUtils.getLong(map, "companyCode",0l));
		this.setOrgType(MapUtils.getIntValue(map, "orgType",1));
		this.setParentCode(MapUtils.getString(map, "parentCode","0"));
		this.setOrgCode(MapUtils.getString(map, "orgCode",null));
		this.setOrgName(MapUtils.getString(map, "orgName",null));
		this.setOrgNameAttr(MapUtils.getString(map, "orgName",null));
		this.setNote(MapUtils.getString(map, "note",null));
		this.setOperatorCode(MapUtils.getString(map, "operatorCode",null));
	    this.setOperatorIP(MapUtils.getString(map, "operatorIP",null));
	}

}

