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
package com.lifeinsurancesystem.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

//import com.lifeinsurancesystem.model.InsuranceType;

/**
 * @类 名: ProductManageDaoImpl
 * @描 述: 险种管理、保险公司、险种分类
 * @作 者: lf
 * @邮 箱: 
 * @创建日期: 2016年06月07日 下午4:43:14
 * @修改日期: 
 */
public class ProductManageDaoImpl implements IProductManageDao {
	/*protected final Logger log = LoggerFactory.getLogger(ProductManageDaoImpl.class);
	@Autowired
	private SqlSessionTemplate sqlSessionTemplate;

	@Override
	public List<InsuranceType> getInsuranceTypeList(InsuranceType insuranceType) {
		// TODO Auto-generated method stub
		List<InsuranceType> list=sqlSessionTemplate.selectList("sql.com.lifeInsuranceSystem.mapper.productManageMap.getInsuranceTypeList", insuranceType);
		if(list == null ||  list.size() == 0) return null;
		return list;
	}
	
	@Override
	public boolean saveInsuranceType(InsuranceType insuranceType) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.insert("sql.com.lifeInsuranceSystem.mapper.productManageMap.saveInsuranceType", insuranceType);
		if(count == 1) return true;
		return false;
	}
	
	@Override
	public boolean updateInsuranceType(InsuranceType insuranceType) {
		int count=sqlSessionTemplate.update("sql.com.lifeInsuranceSystem.mapper.productManageMap.updateInsuranceType", insuranceType);
		if(count == 1) return true;
		return false;
	}
	
	@Override
	public boolean delInsuranceType(InsuranceType insuranceType) {
		// TODO Auto-generated method stub
		int count=sqlSessionTemplate.insert("sql.com.lifeInsuranceSystem.mapper.productManageMap.delInsuranceType", insuranceType);
		if(count == 1) return true;
		return false;
	}*/

}
