/** 
* @公司名称: xxxxxxxxxxxx
* @版权信息: 版权归属15202125130@163.com
* @包          名: com.lifeinsurancesystem.util.MailService
* @描          述: 邮件发送工具类
* @作          者: yaojj 
* @邮          箱: 15202125130@163.com 
* @创建日期: 2016年4月10日 下午9:54:38 
* @修改日期: 2016年4月10日 下午9:54:38
* @版权序号: V0.0.1
*/
package com.lifeinsurancesystem.util;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Address;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
/**
 * @类          名: MailService 
 * @描          述: 
 * @作          者: yaojj 
 * @邮          箱: 15202125130@163.com 
 * @创建日期: 2016年6月7日 下午3:36:45 
 * @修改日期: 2016年6月7日 下午3:36:45
 */
public class MailService {
	protected final static Logger log = LoggerFactory.getLogger(MailService.class);
    private MimeMessage mimeMsg; //MIME邮件对象   
    private Session session; //邮件会话对象   
    private Properties props; //系统属性   
    //smtp认证用户名和密码     
    private Multipart mp; //Multipart对象,邮件内容,标题,附件等内容均添加到其中后再生成MimeMessage对象   
    public static String smtp;  
    public static String from;  
    public static String to;  
    public static String copyto;  
    public static String username;  
    public static String password; 
    
	public MailService(){

    }
    /** 
     * Constructor 
     * @param smtp 邮件发送服务器 
     */  
    public MailService(String smtp){   
        setSmtpHost(smtp);   
        createMimeMessage();   
    }   
  
    /** 
     * 设置邮件发送服务器 
     * @param hostName String  
     */  
    public void setSmtpHost(String hostName) {   
    	log.info("设置系统属性：mail.smtp.host = "+hostName);   
        if(props == null)  
            props = System.getProperties(); //获得系统属性对象    
        props.put("mail.smtp.host",hostName); //设置SMTP主机   
    }   
  
  
    /** 
     * 创建MIME邮件对象   
     * @return 
     */  
    public boolean createMimeMessage()   
    {   
        try {   
        	log.info("准备获取邮件会话对象！");   
            session = Session.getDefaultInstance(props,null); //获得邮件会话对象   
        }   
        catch(Exception e){   
        	log.error("获取邮件会话对象时发生错误！"+e);   
            return false;   
        }   
      
        log.info("准备创建MIME邮件对象！");   
        try {   
            mimeMsg = new MimeMessage(session); //创建MIME邮件对象   
            mp = new MimeMultipart();   
          
            return true;   
        } catch(Exception e){   
        	log.error("创建MIME邮件对象失败！"+e);   
            return false;   
        }   
    }     
      
    /** 
     * 设置SMTP是否需要验证 
     * @param need 
     */  
    public void setNeedAuth(boolean need) {   
    	log.info("设置smtp身份认证：mail.smtp.auth = "+need);   
        if(props == null) props = System.getProperties();   
        if(need){   
            props.put("mail.smtp.auth","true");   
        }else{   
            props.put("mail.smtp.auth","false");   
        }   
    }   
  
    /** 
     * 设置用户名和密码 
     * @param name 
     * @param pass 
     */  
    public void setNamePass(String name,String pass) {   
        username = name;   
        password = pass;   
    }   
  
    /** 
     * 设置邮件主题 
     * @param mailSubject 
     * @return 
     */  
    private boolean setSubject(String mailSubject) {   
    	log.info("设置邮件主题！");   
        try{   
            mimeMsg.setSubject(mailSubject);   
            return true;   
        }   
        catch(Exception e) {   
        	log.error("设置邮件主题发生错误！");   
            return false;   
        }   
    }  
      
    /**  
     * 设置邮件正文 
     * @param mailBody String  
     */   
    private boolean setBody(String mailBody) {   
        try{   
            BodyPart bp = new MimeBodyPart();   
            bp.setContent(""+mailBody,"text/html;charset=GBK");   
            mp.addBodyPart(bp);   
          
            return true;   
        } catch(Exception e){   
        	log.error("设置邮件正文时发生错误！"+e);   
        return false;   
        }   
    }   
    /**  
     * 添加附件 
     * @param filename String  
     */   
    private boolean addFileAffix(String filename) {   
      
    	log.info("增加邮件附件："+filename);   
        try{   
            BodyPart bp = new MimeBodyPart();   
            FileDataSource fileds = new FileDataSource(filename);   
            bp.setDataHandler(new DataHandler(fileds));   
            bp.setFileName(fileds.getName());   
              
            mp.addBodyPart(bp);   
              
            return true;   
        } catch(Exception e){   
        	log.error("增加邮件附件："+filename+"发生错误！"+e);   
            return false;   
        }   
    }   
      
    /**  
     * 设置发信人 
     * @param from String  
     */   
    private boolean setFrom(String from) {   
    	log.info("设置发信人！");   
        try{   
            mimeMsg.setFrom(new InternetAddress(from)); //设置发信人   
            return true;   
        } catch(Exception e) {   
            return false;   
        }   
    }   
    /**  
     * 设置收信人 
     * @param to String  
     */   
    private boolean setTo(String to){   
        if(to == null)return false;   
        try{   
            mimeMsg.setRecipients(Message.RecipientType.TO,InternetAddress.parse(to));   
            return true;   
        } catch(Exception e) {   
            return false;   
        }     
    }   
      
    /**  
     * 设置抄送人 
     * @param copyto String   
     */   
    private boolean setCopyTo(String copyto)   
    {   
        if(copyto == null)return false;   
        try{   
        mimeMsg.setRecipients(Message.RecipientType.CC,(Address[])InternetAddress.parse(copyto));   
        return true;   
        }   
        catch(Exception e)   
        { return false; }   
    }   
      
    /**  
     * 发送邮件 
     */   
    private boolean sendOut()   
    {   
        try{   
            mimeMsg.setContent(mp);   
            mimeMsg.saveChanges();   
            log.info("正在发送邮件....");   
              
            Session mailSession = Session.getInstance(props,null);   
            Transport transport = mailSession.getTransport("smtp");   
            transport.connect((String)props.get("mail.smtp.host"),username,password);   
            transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.TO));   
            transport.sendMessage(mimeMsg,mimeMsg.getRecipients(Message.RecipientType.CC));   
            //transport.send(mimeMsg);   
              
            log.info("发送邮件成功！");   
            transport.close();   
              
            return true;   
        } catch(Exception e) {   
        	log.error("邮件发送失败！"+e);   
            return false;   
        }   
    }   
  
    /** 
     * 调用sendOut方法完成邮件发送 
     * @param smtp 
     * @param from 
     * @param to 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @return boolean 
     */  
    @SuppressWarnings("unused")
    private static boolean send(String smtp,String from,String to,String subject,String content,String username,String password) {  
        MailService theMail = new MailService(smtp);  
        theMail.setNeedAuth(true); //需要验证  
          
        if(!theMail.setSubject(subject)) return false;  
        if(!theMail.setBody(content)) return false;  
        if(!theMail.setTo(to)) return false;  
        if(!theMail.setFrom(from)) return false;  
        theMail.setNamePass(username,password);  
          
        if(!theMail.sendOut()) return false;  
        return true;  
    }  
      
    /** 
     * 调用sendOut方法完成邮件发送,带抄送 
     * @param smtp 
     * @param from 
     * @param to 
     * @param copyto 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @return boolean 
     */  
    private static boolean sendAndCc(String smtp,String from,String to,String copyto,String subject,String content,String username,String password) {  
        MailService theMail = new MailService(smtp);  
        theMail.setNeedAuth(true); //需要验证  
          
        if(!theMail.setSubject(subject)) return false;  
        if(!theMail.setBody(content)) return false;  
        if(!theMail.setTo(to)) return false;  
        if(!theMail.setCopyTo(copyto)) return false;  
        if(!theMail.setFrom(from)) return false;  
        theMail.setNamePass(username,password);  
          
        if(!theMail.sendOut()) return false;  
        return true;  
    }  
      
    /** 
     * 调用sendOut方法完成邮件发送,带附件 
     * @param smtp 
     * @param from 
     * @param to 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @param filename 附件路径 
     * @return 
     */  
    @SuppressWarnings("unused")
    private static boolean send(String smtp,String from,String to,String subject,String content,String username,String password,String filename) {  
        MailService theMail = new MailService(smtp);  
        theMail.setNeedAuth(true); //需要验证  
          
        if(!theMail.setSubject(subject)) return false;  
        if(!theMail.setBody(content)) return false;  
        if(!theMail.addFileAffix(filename)) return false;   
        if(!theMail.setTo(to)) return false;  
        if(!theMail.setFrom(from)) return false;  
        theMail.setNamePass(username,password);  
          
        if(!theMail.sendOut()) return false;  
        return true;  
    }  
      
    /** 
     * 调用sendOut方法完成邮件发送,带附件和抄送 
     * @param smtp 
     * @param from 
     * @param to 
     * @param copyto 
     * @param subject 
     * @param content 
     * @param username 
     * @param password 
     * @param filename 
     * @return 
     */  
    @SuppressWarnings("unused")
	private static boolean sendAndCc(String smtp,String from,String to,String copyto,String subject,String content,String username,String password,String filename) {  
        MailService theMail = new MailService(smtp);  
        theMail.setNeedAuth(true); //需要验证  
          
        if(!theMail.setSubject(subject)) return false;  
        if(!theMail.setBody(content)) return false;  
        if(!theMail.addFileAffix(filename)) return false;   
        if(!theMail.setTo(to)) return false;  
        if(!theMail.setCopyTo(copyto)) return false;  
        if(!theMail.setFrom(from)) return false;  
        theMail.setNamePass(username,password);  
          
        if(!theMail.sendOut()) return false;  
        return true;  
    } 
    
    /**
     * 
      * @Description: TODO描述 
      * @param @param text  参数说明 
      * @return void    返回类型 
      * @throws
     */
    public static void sendTextMail(String title,String text){
    	MailService.sendAndCc(smtp, from,to, copyto, title, text, username, password);
    }
}   
