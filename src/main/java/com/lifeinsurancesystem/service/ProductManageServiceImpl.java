/** 
 * @公司名称: xxxxxxxxxxxx
 * @版权信息: 版权归属15202125130@163.com
 * @包          名: com.lifeinsurancesystem.dao
 * @描          述: 文件描述
 * @作          者: yaojj 
 * @邮          箱: 15202125130@163.com 
 * @创建日期: 2016年6月7日 下午4:42:54 
 * @修改日期: 2016年6月7日 下午4:42:54
 * @版权序号: V0.0.1
 */
package com.lifeinsurancesystem.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.lifeinsurancesystem.dao.IProductManageDao;
//import com.lifeinsurancesystem.model.InsuranceType;

/**
 * @类 名: ProductManageDaoImpl
 * @描 述: TODO
 * @作 者: yaojj
 * @邮 箱: 15202125130@163.com
 * @创建日期: 2016年6月7日 下午4:43:14
 * @修改日期: 2016年6月7日 下午4:43:14
 */
public class ProductManageServiceImpl implements IProductManageService {
	/*protected final Logger log = LoggerFactory.getLogger(ProductManageServiceImpl.class);
	@Autowired
	private IProductManageDao productManageDaoImpl;

	public ProductManageServiceImpl() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public List<InsuranceType> getInsuranceTypeList(InsuranceType insuranceType) {
		// TODO Auto-generated method stub
		List<InsuranceType> list = productManageDaoImpl.getInsuranceTypeList(insuranceType);
		return list;
	}
	
	@Override
	public boolean saveInsuranceType(InsuranceType insuranceType) {
		// TODO Auto-generated method stub
		if(insuranceType.getTypeCode() == null || insuranceType.getTypeCode() == 0){
			return productManageDaoImpl.saveInsuranceType(insuranceType);
		}else{
			return productManageDaoImpl.updateInsuranceType(insuranceType);
		}
	}
	
	@Override
	public boolean delInsuranceType(InsuranceType insuranceType) {
		// TODO Auto-generated method stub
		return productManageDaoImpl.delInsuranceType(insuranceType);
	}*/

}
