/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.service.ISystemService
* @描          述: 保险服务服务平台系统设置Service接口
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.service;

import java.util.List;
import java.util.Map;

import com.lifeinsurancesystem.model.CompanyInfo;
import com.lifeinsurancesystem.model.ConstantBean;
import com.lifeinsurancesystem.model.DeptInfo;
import com.lifeinsurancesystem.model.PositionInfo;

/**
* @类          名: ISystemService 
* @描          述: 系统管理模块
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年5月20日 上午8:58:22 
* @修改日期: 2016年5月20日 上午8:58:22
 */
public interface ISystemService {
  /**
   * @方法描述: 保存公司信息、省公司、市公司、营业部、业务部
   * @作          者: yaojj
   * @邮          箱: 15202125130@163.com 
   * @参数描述: @param companyInfo
   * @参数描述: @return
   * @异常处理:
   */
  public boolean saveCompanyInfo(CompanyInfo companyInfo);
   /**
    * @方法描述: 获取公司列表
    * @作          者: yaojj
    * @邮          箱: 15202125130@163.com 
    * @参数描述: @param companyInfo
    * @参数描述: @return
    * @异常处理:
    */
   public List<CompanyInfo> getCompanyInfoList(CompanyInfo companyInfo);
   /**
    * @方法描述: TODO
    * @作          者: yaojj
    * @邮          箱: 15202125130@163.com 
    * @参数描述: @param map
    * @参数描述: @return
    * @异常处理:
    */
   public List<ConstantBean> getSysConstant(Map<String,Object> map);
   /**
    * @方法描述: TODO 删除公司
    * @作          者: yaojj 
    * @邮          箱: 15202125130@163.com 
    * @参数描述: @param companyInfo
    * @参数描述: @return
    * @异常处理:
    */
   public boolean delCompanyInfo(CompanyInfo companyInfo);
   /**
    * @方法描述: TODO 保存部门
    * @作          者: yaojj
    * @邮          箱: 15202125130@163.com 
    * @参数描述: @param deptInfo
    * @参数描述: @return
    * @异常处理:
    */
   public boolean saveDeptInfo(DeptInfo deptInfo);
   /**
    * @方法描述: TODO 保存部门
    * @作          者: yaojj
    * @邮          箱: 15202125130@163.com 
    * @参数描述: @param deptCode
    * @参数描述: @return
    * @异常处理:
   */
   public boolean delDeptInfo(Long deptCode);
   /**
    * @方法描述: TODO
    * @作          者: yaojj
    * @邮          箱: 15202125130@163.com 
    * @参数描述: @param deptInfo
    * @参数描述: @return
    * @异常处理:
   */
   public List<DeptInfo> getDeptInfoList(DeptInfo deptInfo);
   /**
    * @方法描述: 保存职位信息
    * @作          者: yaojj
    * @邮          箱: 15202125130@163.com 
    * @参数描述: @param positionInfo
    * @参数描述: @return
    * @异常处理:
    */
    public boolean savePositionInfo(PositionInfo positionInfo);
    /** 
    * @方法描述: 删除职位信息
    * @作          者: yaojj
    * @邮          箱: 15202125130@163.com 
    * @参数描述: @param positionCode
    * @参数描述: @return
    * @异常处理:
    */
    public boolean delPositionInfo(Long positionCode);
    /**
     * @方法描述: 查询职信息
     * @作          者: yaojj
     * @邮          箱: 15202125130@163.com 
     * @参数描述: @param positionInfo
     * @参数描述: @return
     * @异常处理:
     */
    public List<PositionInfo> getPositionInfoList(PositionInfo positionInfo);
    /**
     * @方法描述: 获取系统自定义代码
     * @作          者: yaojj
     * @邮          箱: 15202125130@163.com 
     * @参数描述: @param dataType
     * @参数描述: @param companyPix
     * @参数描述: @return
     * @异常处理:
     */
	public String getSystemNum(String dataType,String companyPix);
    
}
