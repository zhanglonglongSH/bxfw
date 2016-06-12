/** 
 * @公司名称: xxxxxxxxxxxx
 * @版权信息: 版权归属15202125130@163.com
 * @包          名: com.lifeinsurancesystem.controller
 * @描          述: 文件描述
 * @作          者: yaojj 
 * @邮          箱: 15202125130@163.com 
 * @创建日期: 2016年6月7日 下午4:48:53 
 * @修改日期: 2016年6月7日 下午4:48:53
 * @版权序号: V0.0.1
 */
package com.lifeinsurancesystem.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.lifeinsurancesystem.enums.BxfwMessageCode;
//import com.lifeinsurancesystem.model.InsuranceType;
import com.lifeinsurancesystem.model.MessageData;
import com.lifeinsurancesystem.service.IProductManageService;
import com.lifeinsurancesystem.util.CommonUtil;

/**
 * @类          名: ProductManageController 
 * @描          述: 险种管理、保险公司、险种分类
 * @作          者: yaojj 
 * @邮          箱: 15202125130@163.com 
 * @创建日期: 2016年6月7日 下午4:48:56 
 * @修改日期: 2016年6月7日 下午4:48:56
 */
@Controller
@RequestMapping("/productManage")
public class ProductManageController {
//	@Autowired
//	private IProductManageService productManageServiceImpl;
//	
//	@RequestMapping(value = "getInsuranceTypeList/{parmData}", method = { RequestMethod.GET })
//	public @ResponseBody MessageData getInsuranceTypeList(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
//		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
//		InsuranceType insuranceType =new InsuranceType(map);
//		List<InsuranceType> list = productManageServiceImpl.getInsuranceTypeList(insuranceType);
//		MessageData rs=new MessageData();
//		if(list == null){
//			rs.setCode(BxfwMessageCode.DATANULL.getCode());
//			rs.setMessagestr("获取省数据为null");
//			return rs;
//		}
//		rs.setCode(BxfwMessageCode.SUCCESS.getCode());
//		rs.setMessagestr("险种分类列表，数据获取成功");
//		rs.setData(list);
//		return rs;
//	}
//	
//	@RequestMapping(value = "saveInsuranceType/{parmData}", method = { RequestMethod.POST })
//	public @ResponseBody MessageData saveInsuranceType(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
//		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
//		InsuranceType insuranceType =new InsuranceType(map);
//		MessageData rs=new MessageData();
//		boolean state=productManageServiceImpl.saveInsuranceType(insuranceType);
//		rs.setCode(state==true?BxfwMessageCode.SUCCESS.getCode():BxfwMessageCode.FAILURE.getCode());
//		rs.setMessagestr((state==true?BxfwMessageCode.SUCCESS.getMessage():BxfwMessageCode.FAILURE.getMessage()));
//		return rs;
//	}
//	
//	@RequestMapping(value = "delInsuranceType/{parmData}", method = { RequestMethod.DELETE })
//	public @ResponseBody MessageData delInsuranceType(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
//		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
//		InsuranceType insuranceType =new InsuranceType(map);
//		MessageData rs=new MessageData();
//		boolean state=productManageServiceImpl.delInsuranceType(insuranceType);
//		rs.setCode(state==true?BxfwMessageCode.SUCCESS.getCode():BxfwMessageCode.FAILURE.getCode());
//		rs.setMessagestr((state==true?BxfwMessageCode.SUCCESS.getMessage():BxfwMessageCode.FAILURE.getMessage()));
//		return rs;
//	}

}

