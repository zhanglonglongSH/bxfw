package com.lifeinsurancesystem.enums;
/**
 * 
* @类          名: BxfwJavaCommonEnum 
* @描          述: 本产品后台使用的枚举常量
* @作          者: zhangll 
* @邮          箱: 792963711@qq.com 
* @创建日期: 2016年6月9日 下午9:57:54 
* @修改日期: 2016年6月9日 下午9:57:54
 */
public enum BxfwJavaCommonEnum {
    LOGIN_VERIFYCODE_OPENFLAG(3,"登录错误超过次数，显示验证码！"),
    LOGIN_IN_REDIS_FLAG("bxfw_login_record","存在redis中的登录时的记录标识；bxfw_login_record+【登录名】"),
    LOGIN_IN_REDIS_VERIFYCODE("bxfw_login_record_verifyCode","存在redis中的登录时的记录标识；bxfw_login_record_verifyCode+【登录名】"),
    LOGIN_IN_REDIS_SAVETIME(300,"登录错误间隔时间"),
    ;
    /**
     * 使用的数据
     */
    private Object data;
    /**
     * 该数据作用/信息
     */
    private Object information;
    
    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public Object getInformation() {
        return information;
    }

    public void setInformation(Object information) {
        this.information = information;
    }

    private BxfwJavaCommonEnum(Object data, Object information) {
	this.data = data;
	this.information = information;
    }
    
    
}
