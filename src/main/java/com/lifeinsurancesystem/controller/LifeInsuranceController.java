/** 
 * @公司名称: xxxxxxxxxxxx
 * @版权信息: 版权归属15202125130@163.com
 * @包          名: com.lifeinsurancesystem.dao
 * @描          述: 文件描述
 * @作          者: yaojj 
 * @邮          箱: 15202125130@163.com 
 * @创建日期: 2016年6月2日 下午12:37:49 
 * @修改日期: 2016年6月2日 下午12:37:49
 * @版权序号: V0.0.1
 */
package com.lifeinsurancesystem.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.lifeinsurancesystem.service.ILifeInsuranceService;

/**
 * @类 名: LifeInsuranceController
 * @描 述: 寿险实现类
 * @作 者: yaojj
 * @邮 箱: 15202125130@163.com
 * @创建日期: 2016年6月2日 下午12:37:54
 * @修改日期: 2016年6月2日 下午12:37:54
 */
@Controller
@RequestMapping("/lifeInsurance")
public class LifeInsuranceController {
	protected final Logger log = LoggerFactory.getLogger(LifeInsuranceController.class);
	@Autowired
    private ILifeInsuranceService lifeInsuranceServiceImpl;
	public LifeInsuranceController() {
		// TODO Auto-generated constructor stub
	}

}

