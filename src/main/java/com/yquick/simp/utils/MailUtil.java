package com.yquick.simp.utils;

import com.alibaba.fastjson.JSONObject;
import com.yquick.simp.common.Constants;
import com.yquick.simp.common.ResultModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Date;
import java.util.List;
import java.util.Properties;

@PropertySource("classpath:application.properties")
@Component
public class MailUtil {
	private final Logger logger = LoggerFactory.getLogger(getClass());
	/*private static MimeMessage message;*/
	// 发件人邮箱
	@Value("${EMAIL.FROMMAIL}")
	private String FROMMAIL;

	// 用户名
	@Value("${EMAIL.USER}")
	private String USER;

	// 密码
	@Value("${EMAIL.PASSWORD}")
	private String PASSWORD;

	// 端口号
	@Value("${EMAIL.PORT}")
	private String PORT;

	// 邮件服务器
	@Value("${EMAIL.SMTPADDR}")
	private String SMTPADDR;

	public Object sendEmail(JSONObject data) {
		Date date=new Date();
		ResultModel resultModel=new ResultModel();
		logger.info("发送邮件原始数据："+data);
		try {
			String mailTitle = data.getString("title");
			String mailContent = data.getString("content");
			String priority=data.getString("priority");
			List<String> receivers = data.getJSONArray("receivers").toJavaList(String.class);
			Properties props = new Properties();
			props.put("mail.smtp.host", SMTPADDR);
			props.setProperty("mail.smtp.port", PORT);
			props.put("mail.smtp.auth", "true");
			 props.put("mail.smtp.ssl.enable", "true");//测试解开
//			props.put("mail.smtp.ssl.enable", "false");// 瑞泰邮箱解开
//			props.put("mail.smtp.ehlo", "false");// 瑞泰邮箱解开
			props.put("mail.smtp.ssl.checkserveridentity", "false");
			props.put("mail.smtp.ssl.trust", SMTPADDR);
			Session session = Session.getInstance(props);
			session.setDebug(true);
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(FROMMAIL));// 设置发件人的地址
			message.addHeader("X-Priority",priority);
			InternetAddress[] sendTo = new InternetAddress[receivers.size()];
			for (int i = 0; i < receivers.size(); i++) {
				sendTo[i] = new InternetAddress(receivers.get(i));
			}
			message.setRecipients(MimeMessage.RecipientType.TO, sendTo);
			message.setSubject(mailTitle);// 设置标题
			// 添加邮件的各个部分内容
			Multipart multipart = new MimeMultipart();
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(mailContent, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);
			message.setSentDate(new Date());
			message.setContent(multipart);
			message.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			transport.connect(USER, PASSWORD);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
			resultModel.setCode(Constants.SERVER_NORMAL_CODE);
			resultModel.setMsg("邮件发送成功");
		} catch (AuthenticationFailedException e) {
			e.printStackTrace();
			resultModel.setCode(Constants.SERVER_EXCEPTION_CODE);
			resultModel.setMsg("用户名或密码验证失败:"+data.toString());
		} catch (Exception e) {
			e.printStackTrace();
			resultModel.setCode(Constants.SERVER_EXCEPTION_CODE);
			resultModel.setMsg("服务异常，邮件发送失败:"+data.toString());
		}
		resultModel.setExecTime(System.currentTimeMillis()-date.getTime());
		logger.info("邮箱返回结果："+resultModel);
		return resultModel;
	}
}