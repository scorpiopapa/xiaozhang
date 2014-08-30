package com.util.mail;

public class SendEmail {
	public boolean sendEmail(String userName,String password){
		 //这个类主要是设置邮件   
	      MailSenderInfo mailInfo = new MailSenderInfo();    
	      mailInfo.setMailServerHost("smtp.163.com");    
	      mailInfo.setMailServerPort("25");    
	      mailInfo.setValidate(true);    
	      mailInfo.setUserName("xiaozhangapp@163.com");    
	      mailInfo.setPassword("xiaozhang888");//您的邮箱密码    
	      mailInfo.setFromAddress("xiaozhangapp@163.com");    
	      mailInfo.setToAddress(userName);    
	      mailInfo.setSubject("校掌系统找回密码邮件。请勿回复！谢谢！");    
	      mailInfo.setContent("如果您的密码被篡改，请修改您的密码，您可以用密码"+password+"登陆");    
	         //这个类主要来发送邮件   
	      SimpleMailSender sms = new SimpleMailSender();   
	     return  SimpleMailSender.sendHtmlMail(mailInfo);//发送html格式   
	}
}
