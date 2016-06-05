/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.controller.SystemController
* @描          述: web配置文件
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.controller;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.lifeinsurancesystem.enums.BxfwMessageCode;
import com.lifeinsurancesystem.model.CompanyInfo;
import com.lifeinsurancesystem.model.ConstantBean;
import com.lifeinsurancesystem.model.DeptInfo;
import com.lifeinsurancesystem.model.MessageData;
import com.lifeinsurancesystem.model.PositionInfo;
import com.lifeinsurancesystem.service.ISystemService;
import com.lifeinsurancesystem.util.CommonUtil;
import com.lifeinsurancesystem.util.OSSUnit;

@Controller
@RequestMapping("/system")
public class SystemController {
	private Logger log = LoggerFactory.getLogger(SystemController.class);
	@Autowired
	private ISystemService systemServiceImpl;
	
	@RequestMapping(value = "login/{parmData}", method = { RequestMethod.GET })
	public void login(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		try {
			request.getRequestDispatcher("/app/app.html").forward(request, response);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value = "saveCompanyInfo/{parmData}", method = { RequestMethod.POST })
	public @ResponseBody MessageData saveCompanyInfo(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
		CompanyInfo companyInfo =new CompanyInfo(map);
		companyInfo.setCompanyCode(new Long(1));
		MessageData rs=new MessageData();
		boolean state=systemServiceImpl.saveCompanyInfo(companyInfo);
		rs.setCode(state==true?BxfwMessageCode.SUCCESS.getCode():BxfwMessageCode.FAILURE.getCode());
		rs.setMessagestr((state==true?BxfwMessageCode.SUCCESS.getMessage():BxfwMessageCode.FAILURE.getMessage()));
		return rs;
	}
	
	@RequestMapping(value = "delCompanyInfo/{parmData}", method = { RequestMethod.DELETE })
	public @ResponseBody MessageData delCompanyInfo(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
		CompanyInfo companyInfo =new CompanyInfo(map);
		MessageData rs=new MessageData();
		boolean state=systemServiceImpl.delCompanyInfo(companyInfo);
		rs.setCode(state==true?BxfwMessageCode.SUCCESS.getCode():BxfwMessageCode.FAILURE.getCode());
		rs.setMessagestr((state==true?BxfwMessageCode.SUCCESS.getMessage():BxfwMessageCode.FAILURE.getMessage()));
		return rs;
	}
	
	@RequestMapping(value = "getCompanyInfoList/{parmData}", method = { RequestMethod.GET })
	public @ResponseBody MessageData getCompanyInfoList(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
		CompanyInfo companyInfo =new CompanyInfo(map);
		companyInfo.setCompanyCode(new Long(1));
		List<CompanyInfo> list=systemServiceImpl.getCompanyInfoList(companyInfo);
		MessageData rs=new MessageData();
		if(list == null){
			rs.setCode(BxfwMessageCode.DATANULL.getCode());
			rs.setMessagestr("获取省数据为null");
			return rs;
		}
		rs.setCode(BxfwMessageCode.SUCCESS.getCode());
		rs.setMessagestr("数据获取成功");
		rs.setData(list);
		return rs;
	}
	
	@RequestMapping(value = "uploadFile", method = { RequestMethod.POST })
	public @ResponseBody MessageData uploadFile(@RequestParam(value = "file", required = false) MultipartFile file,HttpServletRequest request,HttpServletResponse response) throws IOException {
        String path = request.getSession().getServletContext().getRealPath("upload");  
        String fileName = new Date().getTime()+".png";  
        File targetFile = new File(path, fileName);  
        if(!targetFile.exists()){  
            targetFile.mkdirs();  
        }  
        //上传阿里云 
        try {  
            file.transferTo(targetFile);  
            OSSUnit.uploadObject2OSS(targetFile);
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
		MessageData rs=new MessageData();
		return rs;
	}
	
	@RequestMapping(value = "getSysConstant/{parmData}", method = { RequestMethod.GET })
	public @ResponseBody MessageData getSysConstant(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
		List<ConstantBean> list=systemServiceImpl.getSysConstant(map);
		MessageData rs=new MessageData();
		if(list == null){
			rs.setCode(BxfwMessageCode.DATANULL.getCode());
			rs.setMessagestr("获取系统常量数据为null");
			return rs;
		}
		rs.setCode(BxfwMessageCode.SUCCESS.getCode());
		rs.setMessagestr("数据获取成功");
		rs.setData(list);
		return rs;
	}
	
	@RequestMapping(value = "saveDeptInfo/{parmData}", method = { RequestMethod.POST })
	public @ResponseBody MessageData saveDeptInfo(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
		DeptInfo deptInfo =new DeptInfo(map);
		MessageData rs=new MessageData();
		boolean state=systemServiceImpl.saveDeptInfo(deptInfo);
		rs.setCode(state==true?BxfwMessageCode.SUCCESS.getCode():BxfwMessageCode.FAILURE.getCode());
		rs.setMessagestr((state==true?BxfwMessageCode.SUCCESS.getMessage():BxfwMessageCode.FAILURE.getMessage()));
		return rs;
	}
	
	@RequestMapping(value = "delDeptInfo/{parmData}", method = { RequestMethod.DELETE })
	public @ResponseBody MessageData delDeptInfo(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
		DeptInfo deptInfo =new DeptInfo(map);
		MessageData rs=new MessageData();
		boolean state=systemServiceImpl.delDeptInfo(deptInfo.getDeptCode());
		rs.setCode(state==true?BxfwMessageCode.SUCCESS.getCode():BxfwMessageCode.FAILURE.getCode());
		rs.setMessagestr((state==true?BxfwMessageCode.SUCCESS.getMessage():BxfwMessageCode.FAILURE.getMessage()));
		return rs;
	}
	
	@RequestMapping(value = "getDeptInfoList/{parmData}", method = { RequestMethod.GET })
	public @ResponseBody MessageData getDeptInfoList(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
		DeptInfo deptInfo =new DeptInfo(map);
		List<DeptInfo> list=systemServiceImpl.getDeptInfoList(deptInfo);
		MessageData rs=new MessageData();
		if(list == null){
			rs.setCode(BxfwMessageCode.DATANULL.getCode());
			rs.setMessagestr("获取部门数据为null");
			return rs;
		}
		rs.setCode(BxfwMessageCode.SUCCESS.getCode());
		rs.setMessagestr("数据获取成功");
		rs.setData(list);
		return rs;
	}
	
	@RequestMapping(value = "savePositionInfo/{parmData}", method = { RequestMethod.POST })
	public @ResponseBody MessageData savePositionInfo(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
		PositionInfo positionInfo =new PositionInfo(map);
		MessageData rs=new MessageData();
		boolean state=systemServiceImpl.savePositionInfo(positionInfo);
		rs.setCode(state==true?BxfwMessageCode.SUCCESS.getCode():BxfwMessageCode.FAILURE.getCode());
		rs.setMessagestr((state==true?BxfwMessageCode.SUCCESS.getMessage():BxfwMessageCode.FAILURE.getMessage()));
		return rs;
	}
	
	@RequestMapping(value = "delPositionInfo/{parmData}", method = { RequestMethod.DELETE })
	public @ResponseBody MessageData delPositionInfo(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
		PositionInfo positionInfo =new PositionInfo(map);
		MessageData rs=new MessageData();
		boolean state=systemServiceImpl.delPositionInfo(positionInfo.getPositionCode());
		rs.setCode(state==true?BxfwMessageCode.SUCCESS.getCode():BxfwMessageCode.FAILURE.getCode());
		rs.setMessagestr((state==true?BxfwMessageCode.SUCCESS.getMessage():BxfwMessageCode.FAILURE.getMessage()));
		return rs;
	}
	
	@RequestMapping(value = "getPositionInfoList/{parmData}", method = { RequestMethod.GET })
	public @ResponseBody MessageData getPositionInfoList(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		Map<String,Object> map=CommonUtil.jsonToObject(parmData);
		PositionInfo positionInfo =new PositionInfo(map);
		List<PositionInfo> list=systemServiceImpl.getPositionInfoList(positionInfo);
		MessageData rs=new MessageData();
		if(list == null){
			rs.setCode(BxfwMessageCode.DATANULL.getCode());
			rs.setMessagestr("获取职位数据为null");
			return rs;
		}
		rs.setCode(BxfwMessageCode.SUCCESS.getCode());
		rs.setMessagestr("数据获取成功");
		rs.setData(list);
		return rs;
	}
	
}
