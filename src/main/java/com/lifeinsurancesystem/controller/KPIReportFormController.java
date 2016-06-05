/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.dao
* @描          述: 文件描述
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年6月2日 下午12:40:43 
* @修改日期: 2016年6月2日 下午12:40:43
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.lifeinsurancesystem.service.IKPIReportFormService;

/**
* @类          名: KPIReportFormController 
* @描          述: KIP报表实现类
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年6月2日 下午12:40:47 
* @修改日期: 2016年6月2日 下午12:40:47
 */
public class KPIReportFormController {
	private Logger log = LoggerFactory.getLogger(SystemController.class);
	@Autowired
	private IKPIReportFormService iKPIReportFormServiceImpl;
	public KPIReportFormController() {
		// TODO Auto-generated constructor stub
	}

}

