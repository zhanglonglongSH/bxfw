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
import java.io.OutputStream;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.google.zxing.WriterException;
import com.lifeinsurancesystem.Application;
import com.lifeinsurancesystem.enums.BxfwJavaCommonEnum;
import com.lifeinsurancesystem.enums.BxfwMessageCode;
import com.lifeinsurancesystem.model.CompanyInfo;
import com.lifeinsurancesystem.model.ConstantBean;
import com.lifeinsurancesystem.model.DeptInfo;
import com.lifeinsurancesystem.model.MessageData;
import com.lifeinsurancesystem.model.OfficeStaffUserBean;
import com.lifeinsurancesystem.model.PositionInfo;
import com.lifeinsurancesystem.service.IRedisService;
import com.lifeinsurancesystem.service.ISystemService;
import com.lifeinsurancesystem.util.CommonUtil;
import com.lifeinsurancesystem.util.DateUtil;
import com.lifeinsurancesystem.util.OSSUnit;
import com.lifeinsurancesystem.util.ValidateCode;

@Controller
@RequestMapping("/system")
public class SystemController {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(Application.class);
	@Autowired
	private ISystemService systemServiceImpl;
	@Autowired
	private IRedisService redisServiceImpl;
	private final static String VERIFYCODE= "verifyCode";
	/**
	 * 
	 * @方法描述: verifycode
	 * @作          者: zhangll
	 * @邮          箱: 792963711@qq.com 
	 * @参数描述: @param request
	 * @参数描述: @param response
	 * @参数描述: @throws WriterException
	 * @参数描述: @throws IOException
	 * @异常处理:
	 */
	@RequestMapping(value = "getRestPasValidateCode/{parmData}", method = {RequestMethod.GET,RequestMethod.POST})
	public void  getRestPasValidateCode(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response)
			throws WriterException, IOException {
		response.setHeader("Pragma","No-cache");
                response.setHeader("Cache-Control","no-cache"); 
                response.setDateHeader("Expires", 0);
                response.setContentType("image/jpeg") ;
                Map<String,Object> map = CommonUtil.jsonToObject(parmData);
                ValidateCode idCode = new ValidateCode();  
                String code=idCode.createCalCode();
                 // save in redis   
                if(StringUtils.isNotEmpty(MapUtils.getString(map,"username"))){
                    map.put(VERIFYCODE,code );
                    setRedisInfo(BxfwJavaCommonEnum.LOGIN_IN_REDIS_VERIFYCODE.getData()+MapUtils.getString(map,"username"),map.toString(),1800);
                }
                //输出流生成图片
                OutputStream output = response.getOutputStream();
		ImageIO.write(idCode.getBuffImg(), "JPEG", output);
		output.flush();
		output.close();
	}
	
	@RequestMapping(value = "login/{parmData}", method = { RequestMethod.POST,RequestMethod.GET})
	public void login(@PathVariable("parmData") String parmData,HttpServletRequest request,HttpServletResponse response) throws IOException {
		OfficeStaffUserBean ret = new OfficeStaffUserBean();
		MessageData rs=new MessageData();
		Map<String, Object> redisSaveMap = new HashMap<String, Object>();
		try {
			Map<String,Object> map = CommonUtil.jsonToObject(parmData);
			OfficeStaffUserBean officeStaffUserBean = new OfficeStaffUserBean();
			officeStaffUserBean.setUserName(MapUtils.getString(map,"username"));
			officeStaffUserBean.setUserPassword(MapUtils.getString(map,"userpassword"));
//			查询redis中验证码
			String mapTemp = redisServiceImpl.get(BxfwJavaCommonEnum.LOGIN_IN_REDIS_VERIFYCODE.getData()+MapUtils.getString(map,"username"));
			if(StringUtils.isNotEmpty(mapTemp)){
			    Map<String, Object> vertifyMap = JSONObject.fromObject(mapTemp);
			    String verifyCodeInRedis = MapUtils.getString(vertifyMap, VERIFYCODE);
			    if(!StringUtils.equals(MapUtils.getString(map, "verifyCode", "@X@"),verifyCodeInRedis)){
				rs.setCode(BxfwMessageCode.VERIFYCODE_ERROR.getCode());
				request.setAttribute("data", rs);
                    		request.getRequestDispatcher("/login.html").forward(request, response);
			    }
			}
			
			List<OfficeStaffUserBean> list = systemServiceImpl.getOfficeStaffUser(officeStaffUserBean);
			if(CollectionUtils.isNotEmpty(list)){
			    	//如果redis中含有该客户信息，则删除掉，因为登录成功了
			    	setRedisInfo(BxfwJavaCommonEnum.LOGIN_IN_REDIS_FLAG.getData()+MapUtils.getString(map,"username"),map.toString(),1);
				request.setAttribute("data", list.get(0));
				request.getRequestDispatcher("/app/app.html").forward(request, response);
			}else{
			    	
                    		rs.setCode(BxfwMessageCode.FAILURE.getCode());
                    		//如果失败，则在redis中记录失败次数,1分钟登录3次弹出验证码
                    		if(StringUtils.isNotEmpty(MapUtils.getString(map,"username"))){
                    		    String time = redisServiceImpl.get(BxfwJavaCommonEnum.LOGIN_IN_REDIS_FLAG.getData()+MapUtils.getString(map,"username"));
                            	    if(StringUtils.isEmpty(time)){
                            		redisSaveMap.put("loginErrorTime",0 );
                            	    }else{
                            		Map<String, Object> temp = JSONObject.fromObject(time);
                            		int count = MapUtils.getIntValue(temp, "loginErrorTime", 0)+1;
                            		redisSaveMap.put("loginErrorTime", count);
                            		if(count>(int)BxfwJavaCommonEnum.LOGIN_VERIFYCODE_OPENFLAG.getData()){
                            		    rs.setCode(BxfwMessageCode.VERIFYCODE_SHOW.getCode());
                            		}
                            	    }
                            	    setRedisInfo(BxfwJavaCommonEnum.LOGIN_IN_REDIS_FLAG.getData()+MapUtils.getString(map,"username"),redisSaveMap.toString(),(int)BxfwJavaCommonEnum.LOGIN_IN_REDIS_SAVETIME.getData()); 
                    		}
                    		response.getWriter().print(rs.toString());
				response.getWriter().flush();
				response.getWriter().close();
//                    		request.getRequestDispatcher("/login.html").forward(request, response);
			    	
			}
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
		    log.error("登录异常",e);
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
	/**
	 * 
	 * @方法描述: 查询redis储存之前的数据并且放入新数据
	 * @作          者: zhangll
	 * @邮          箱: 792963711@qq.com 
	 * @参数描述: @param key
	 * @参数描述: @param value
	 * @参数描述: @param expireTime
	 * @参数描述: @return
	 * @异常处理:
	 */
	private void setRedisInfo(String key,String value,int  expireTime){
	    Calendar calendar=Calendar.getInstance();
	    String endTime = DateUtil.getSecondsDate(DateUtil.dateToStr(calendar.getTime(), "yyyy-MM-dd HH:mm:ss"), expireTime, "yyyy-MM-dd HH:mm:ss");
	    redisServiceImpl.saveValue(key,value,DateUtil.stringToDate(endTime)); 
	}
	
}
