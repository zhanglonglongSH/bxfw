
package com.lifeinsurancesystem.model;

import java.io.Serializable;

/**
 * 
* @类          名: OfficeStaffUserBean 
* @描          述: TODO
* @作          者: zhangll 
* @邮          箱: 792963711@qq.com 
* @创建日期: 2016年6月6日 下午8:45:42 
* @修改日期: 2016年6月6日 下午8:45:42
 */
public class OfficeStaffUserBean implements Serializable {
    
/**
   * 
   */
	private static final long serialVersionUID = 1L;
	private String userName;
    private String userPassword;
    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getUserPassword() {
        return userPassword;
    }
    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }
	
}

