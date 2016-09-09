package com.bimt.thesisquery.common.util;

import java.util.Properties;

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
 * 发送邮件通用类
 * @author xufeng
 *
 */
public class MailUtils {
	private static final Logger logger = LoggerFactory.getLogger(MailUtils.class);
	
	/**
	 * 发送邮件
	 * @param subject	邮件标题
	 * @param to	收件人
	 * @param content	邮件内容
	 * @param isHtml  是否html格式，true是false否
	 * @return	0成功1失败
	 */
	public static int send(String subject, String to, String content, boolean isHtml) {
		return send(Const.MAIL_HOST, subject, Const.MAIL_FROM, to, Const.MAIL_USER, Const.MAIL_PWD, content, isHtml);
	}
	
	/**
	 * 发送邮件
	 * @param host	邮件服务器的属性
	 * @param subject	邮件标题
	 * @param from	发件人
	 * @param to	收件人
	 * @param username	发件人邮箱用户名
	 * @param pwd	发件人邮箱密码
	 * @param content	邮件内容
	 * @param isHtml  是否html格式，true是false否
	 * @return	0成功1失败
	 */
	public static int send(String host, String subject, String from, String to, String userName, String pwd, String content, boolean isHtml) {
		Properties props = new Properties();
		// 设置发送邮件的邮件服务器的属性（这里使用网易的smtp服务器）
		props.put("mail.smtp.host", host);
		// 需要经过授权，也就是有户名和密码的校验，这样才能通过验证（一定要有这一条）
		props.put("mail.smtp.auth", "true");
		// 用刚刚设置好的props对象构建一个session
		Session session = Session.getDefaultInstance(props);
		// 有了这句便可以在发送邮件的过程中在console处显示过程信息，供调试使
		// 用（你可以在控制台（console)上看到发送邮件的过程）
		session.setDebug(true);
		// 用session为参数定义消息对象
		Message message = new MimeMessage(session);
		try {
			// 加载发件人地址
			message.setFrom(new InternetAddress(from));
			// 加载收件人地址
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			// 加载标题
			message.setSubject(subject);
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 设置邮件的文本内容
			BodyPart contentPart = new MimeBodyPart();
			if (isHtml) {
				contentPart.setContent(content, "text/html; charset=utf-8"); //.setText(content, "");
			} else {
				contentPart.setText(content);
			}
			multipart.addBodyPart(contentPart);

			// 将multipart对象放到message中
			message.setContent(multipart);
			// 保存邮件
			message.saveChanges();
			// 发送邮件
			Transport transport = session.getTransport("smtp");
			// 连接服务器的邮箱
			transport.connect(host, userName, pwd);
			// 把邮件发送出去
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();
		} catch (Exception e) {
			logger.error(e.getMessage());
			return 1;
		}
		return 0;
	}
	
	public static void main(String[] args) {
//		MailUtils.send("smtp.163.com", "bimt注册验证码", "6680847@163.com", "arienya@126.com", "6680847", "6417327_xuf", "235678");
		MailUtils.send("bimt注册验证码", "6680847@163.com", "235672", false);
	}

}
