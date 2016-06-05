/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.service.SystemServiceImpl
* @描          述: 保险服务服务平台系统设置Service实现
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.service;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.MapUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lifeinsurancesystem.dao.ISystemDao;
import com.lifeinsurancesystem.model.CompanyInfo;
import com.lifeinsurancesystem.model.ConstantBean;
import com.lifeinsurancesystem.model.DeptInfo;
import com.lifeinsurancesystem.model.PositionInfo;

@Service
public class SystemServiceImpl implements ISystemService {
	@Autowired
	private ISystemDao systemDaoImpl;
	protected final Logger log = LoggerFactory.getLogger(SystemServiceImpl.class);

	@Override
	public boolean saveCompanyInfo(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		if(companyInfo.getId()==null || companyInfo.getId() == 0){
			return systemDaoImpl.saveCompanyInfo(companyInfo);
		}else{
			return systemDaoImpl.updateCompanyInfo(companyInfo);
		}
	}

	@Override
	public List<CompanyInfo> getCompanyInfoList(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		List<CompanyInfo> list=systemDaoImpl.getCompanyInfoList(companyInfo);
		return list;
	}

	@Override
	public List<ConstantBean> getSysConstant(Map<String, Object> map) {
		// TODO Auto-generated method stub
		if(MapUtils.getInteger(map, "dataType") == 1){
			List<ConstantBean> list=systemDaoImpl.getSysConstant(map);
			return list;
		}else{
			CompanyInfo companyInfo=new CompanyInfo();
			companyInfo.setOrgType(MapUtils.getIntValue(map, "constanType",0));
			companyInfo.setCompanyCode(MapUtils.getLong(map, "companyCode",0l));
			List<ConstantBean> list=new ArrayList<ConstantBean>();
			List<CompanyInfo> companyList=systemDaoImpl.getCompanyInfoList(companyInfo);
			ConstantBean obj=null;
			if(companyList != null){
				for(CompanyInfo companyobj:companyList){
					obj=new ConstantBean();
					if(companyInfo.getOrgType() == 1){
						obj.setConstanName(companyobj.getOrgName());
						obj.setConstantCode(companyobj.getOrgCode());
					}if(companyInfo.getOrgType()  == 2){
						obj.setConstanName(companyobj.getCityOrgName());
						obj.setConstantCode(companyobj.getCityOrgCode());
					}if(companyInfo.getOrgType()  == 3){
						obj.setConstanName(companyobj.getCountyOrgName());
						obj.setConstantCode(companyobj.getCountyOrgCode());
					}if(companyInfo.getOrgType()  == 4){
						obj.setConstanName(companyobj.getHallOrgName());
						obj.setConstantCode(companyobj.getHallOrgCode());
					}
					list.add(obj);
				}
			}
			return list;
		}
	}

	@Override
	public boolean delCompanyInfo(CompanyInfo companyInfo) {
		// TODO Auto-generated method stub
		return systemDaoImpl.delCompanyInfo(companyInfo);
	}

	@Override
	public boolean saveDeptInfo(DeptInfo deptInfo) {
		// TODO Auto-generated method stub
		if(deptInfo.getDeptCode()==null ||deptInfo.getDeptCode() == 0){
			deptInfo.setDeptCode(systemDaoImpl.getDefaultInnerCode());
			return systemDaoImpl.saveDeptInfo(deptInfo);
		}else{
			return systemDaoImpl.updateDeptInfo(deptInfo);
		}
	}

	@Override
	public boolean delDeptInfo(Long deptCode) {
		// TODO Auto-generated method stub
		return systemDaoImpl.delDeptInfo(deptCode);
	}

	@Override
	public List<DeptInfo> getDeptInfoList(DeptInfo deptInfo) {
		// TODO Auto-generated method stub
		return systemDaoImpl.getDeptInfoList(deptInfo);
	}

	@Override
	public boolean savePositionInfo(PositionInfo positionInfo) {
		// TODO Auto-generated method stub
		if(positionInfo.getPositionCode()==null ||positionInfo.getPositionCode() == 0){
			positionInfo.setPositionCode(systemDaoImpl.getDefaultInnerCode());
			return systemDaoImpl.savePositionInfo(positionInfo);
		}else{
			return systemDaoImpl.updatePositionInfo(positionInfo);
		}
	}

	@Override
	public boolean delPositionInfo(Long positionCode) {
		// TODO Auto-generated method stub
		return systemDaoImpl.delPositionInfo(positionCode);
	}

	@Override
	public List<PositionInfo> getPositionInfoList(PositionInfo positionInfo) {
		// TODO Auto-generated method stub
		return systemDaoImpl.getPositionInfoList(positionInfo);
	}

	@Override
	public String getSystemNum(String dataType, String companyPix) {
		// TODO Auto-generated method stub 
		Long systemNum=systemDaoImpl.getSystemNum(dataType, companyPix);
		if("1".equals(dataType)){//内勤员工号,外勤员工号
			String code = String.valueOf(systemNum);
	        switch (code.length())
	        {
	            case 1:
	            	code = "0000" + code;
	                break;
	            case 2:
	            	code = "000" + code;
	                break;
	            case 3:
	            	code = "00" + code;
	                break;
	            case 4:
	            	code = "0" + code;
	                break;
	            default:code = "" + code;
	        }
	        return code;
		}
		return null;
	}
}
