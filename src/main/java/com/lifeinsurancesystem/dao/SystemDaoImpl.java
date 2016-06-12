/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.dao.SystemDaoImpl
* @描          述: 保险服务服务平台系统设置DAO实现
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.dao;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeinsurancesystem.model.CompanyInfo;
import com.lifeinsurancesystem.model.ConstantBean;
import com.lifeinsurancesystem.model.DeptInfo;
import com.lifeinsurancesystem.model.OfficeStaffUserBean;
import com.lifeinsurancesystem.model.PositionInfo;

@Service
public class SystemDaoImpl implements ISystemDao {
	protected final Logger log = LoggerFactory.getLogger(SystemDaoImpl.class);
	@Autowired
    private SqlSessionTemplate sqlSessionTemplate;
	@Override
	public boolean saveCompanyInfo(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.insert("sql.com.lifeInsuranceSystem.mapper.systemMap.saveCompanyInfo", companyInfo);
		if(count == 1) return true;
		return false;
	}

	@Override
	public List<CompanyInfo> getCompanyInfoList(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		List<CompanyInfo> list=sqlSessionTemplate.selectList("sql.com.lifeInsuranceSystem.mapper.systemMap.getCompanyInfoList", companyInfo);
		if(list == null ||  list.size() == 0) return null;
		return list;
	}

	@Override
	public List<ConstantBean> getSysConstant(Map<String,Object> map) {
		// TODO Auto-generated method stub
		List<ConstantBean> list=sqlSessionTemplate.selectList("sql.com.lifeInsuranceSystem.mapper.systemMap.getSysConstant", map);
		if(list == null ||  list.size() == 0) return null;
		return list;
	}

	@Override
	public boolean updateCompanyInfo(CompanyInfo companyInfo) {
		int count=sqlSessionTemplate.update("sql.com.lifeInsuranceSystem.mapper.systemMap.updateCompanyInfo", companyInfo);
		if(count == 1) return true;
		return false;
	}

	@Override
	public boolean saveSignedCompanyInfo(Map<String, Object> companyInfo) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.insert("sql.com.lifeInsuranceSystem.mapper.systemMap.saveSignedCompanyInfo", companyInfo);
		if(count == 1) return true;
		return false;
	}

	@Override
	public boolean delCompanyInfo(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.insert("sql.com.lifeInsuranceSystem.mapper.systemMap.delCompanyInfo", companyInfo);
		if(count == 1) return true;
		return false;
	}

	@Override
	public boolean saveDeptInfo(DeptInfo deptInfo) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.insert("sql.com.lifeInsuranceSystem.mapper.systemMap.saveDeptInfo", deptInfo);
		if(count == 1) return true;
		return false;
	}

	@Override
	public boolean updateDeptInfo(DeptInfo deptInfo) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.update("sql.com.lifeInsuranceSystem.mapper.systemMap.updateDeptInfo", deptInfo);
		if(count == 1) return true;
		return false;
	}

	@Override
	public boolean delDeptInfo(Long deptCode) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.insert("sql.com.lifeInsuranceSystem.mapper.systemMap.delDeptInfo", deptCode);
		if(count == 1) return true;
		return false;
	}

	@Override
	public List<DeptInfo> getDeptInfoList(DeptInfo deptInfo) {
		// TODO Auto-generated method stub
		List<DeptInfo> list=sqlSessionTemplate.selectList("sql.com.lifeInsuranceSystem.mapper.systemMap.getDeptInfoList", deptInfo);
		if(list == null ||  list.size() == 0) return null;
		return list;
	}

	@Override
	public Long getDefaultInnerCode() {
		// TODO Auto-generated method stub
		Long code=sqlSessionTemplate.selectOne("sql.com.lifeInsuranceSystem.mapper.systemMap.getDefaultInnerCode");
		if(code == null) return 0l;
		return code;
	}

	@Override
	public boolean savePositionInfo(PositionInfo positionInfo) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.insert("sql.com.lifeInsuranceSystem.mapper.systemMap.savePositionInfo", positionInfo);
		if(count == 1) return true;
		return false;
	}

	@Override
	public boolean updatePositionInfo(PositionInfo positionInfo) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.update("sql.com.lifeInsuranceSystem.mapper.systemMap.updatePositionInfo", positionInfo);
		if(count == 1) return true;
		return false;
	}

	@Override
	public boolean delPositionInfo(Long positionCode) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.update("sql.com.lifeInsuranceSystem.mapper.systemMap.delPositionInfo", positionCode);
		if(count == 1) return true;
		return false;
	}

	@Override
	public List<PositionInfo> getPositionInfoList(PositionInfo positionInfo) {
		// TODO Auto-generated method stub
		List<PositionInfo> list=sqlSessionTemplate.selectList("sql.com.lifeInsuranceSystem.mapper.systemMap.getPositionInfoList", positionInfo);
		if(list == null ||  list.size() == 0) return null;
		return list;
	}

	@Override
	public Long getSystemNum(String dataType, String companyPix) {
		// TODO Auto-generated method stub
		Long code=sqlSessionTemplate.selectOne("sql.com.lifeInsuranceSystem.mapper.systemMap.getSystemNum");
		if(code == null) return 0l;
		return code;
	}
	@Override
	public List<OfficeStaffUserBean>  getOfficeStaffUser(OfficeStaffUserBean officeStaffUserBean) {
		// TODO Auto-generated method stub
		List<OfficeStaffUserBean>  officeStaffUserBeans=sqlSessionTemplate.selectList("sql.com.lifeInsuranceSystem.mapper.systemMap.getOfficeStaffUser", officeStaffUserBean);
		return officeStaffUserBeans;
	}
}
