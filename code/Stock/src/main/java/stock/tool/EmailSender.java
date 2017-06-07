package stock.tool;

import java.util.Date;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class EmailSender {
	
	public void sendTextMail(String subject, String content, String toAddress) throws Exception {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		mailInfo.setToAddress(toAddress);
		sendTextMail(mailInfo);
	}
	
	public void sendHtmlMail(String subject, String content, String toAddress) throws Exception {
		MailSenderInfo mailInfo = new MailSenderInfo();
		mailInfo.setSubject(subject);
		mailInfo.setContent(content);
		mailInfo.setToAddress(toAddress);
		sendHtmlMail(mailInfo);
	}
	
	private void sendTextMail(MailSenderInfo mailInfo) throws Exception {
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		Message mailMessage = new MimeMessage(sendMailSession);
		Address from = new InternetAddress(mailInfo.getFromAddress());
		mailMessage.setFrom(from);
		Address to = new InternetAddress(mailInfo.getToAddress());
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		mailMessage.setSubject(mailInfo.getSubject());
		mailMessage.setSentDate(new Date());
		String mailContent = mailInfo.getContent();
		mailMessage.setText(mailContent);
		Transport.send(mailMessage);
	}

	private void sendHtmlMail(MailSenderInfo mailInfo) throws Exception {
		MyAuthenticator authenticator = null;
		Properties pro = mailInfo.getProperties();
		if (mailInfo.isValidate()) {
			authenticator = new MyAuthenticator(mailInfo.getUserName(),
					mailInfo.getPassword());
		}
		Session sendMailSession = Session
				.getDefaultInstance(pro, authenticator);
		Message mailMessage = new MimeMessage(sendMailSession);
		Address from = new InternetAddress(mailInfo.getFromAddress());
		mailMessage.setFrom(from);
		Address to = new InternetAddress(mailInfo.getToAddress());
		mailMessage.setRecipient(Message.RecipientType.TO, to);
		mailMessage.setSubject(mailInfo.getSubject());
		mailMessage.setSentDate(new Date());
		Multipart mainPart = new MimeMultipart();
		BodyPart html = new MimeBodyPart();
		html.setContent(mailInfo.getContent(), "text/html; charset=utf-8");
		mainPart.addBodyPart(html);
		mailMessage.setContent(mainPart);
		Transport.send(mailMessage);
	}

	private static class MyAuthenticator extends Authenticator {
		String userName = null;
		String password = null;
		
		public MyAuthenticator() {
		}
		
		public MyAuthenticator(String username, String password) {
			this.userName = username;
			this.password = password;
		}
		
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(userName, password);
		}
	}

	public static class MailSenderInfo {
		private String mailServerHost = "smtp.qq.com";
		private String mailServerPort = "25";
		private String fromAddress = "56897527@qq.com";
		private String toAddress = "xaliuli@cn.ibm.com";
		private String userName = "56897527@qq.com";
//		private String password = "!WWL&KMW&LL)";
		private String password = "";
		private boolean validate = true;
		private String subject;
		private String content;
		private String[] attachFileNames;

		public Properties getProperties() {
			Properties p = new Properties();
			p.put("mail.smtp.host", this.mailServerHost);
			p.put("mail.smtp.port", this.mailServerPort);
			p.put("mail.smtp.auth", validate ? "true" : "false");
			return p;
		}

		public String getMailServerHost() {
			return mailServerHost;
		}

		public void setMailServerHost(String mailServerHost) {
			this.mailServerHost = mailServerHost;
		}

		public String getMailServerPort() {
			return mailServerPort;
		}

		public void setMailServerPort(String mailServerPort) {
			this.mailServerPort = mailServerPort;
		}

		public boolean isValidate() {
			return validate;
		}

		public void setValidate(boolean validate) {
			this.validate = validate;
		}

		public String[] getAttachFileNames() {
			return attachFileNames;
		}

		public void setAttachFileNames(String[] fileNames) {
			this.attachFileNames = fileNames;
		}

		public String getFromAddress() {
			return fromAddress;
		}

		public void setFromAddress(String fromAddress) {
			this.fromAddress = fromAddress;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getToAddress() {
			return toAddress;
		}

		public void setToAddress(String toAddress) {
			this.toAddress = toAddress;
		}

		public String getUserName() {
			return userName;
		}

		public void setUserName(String userName) {
			this.userName = userName;
		}

		public String getSubject() {
			return subject;
		}

		public void setSubject(String subject) {
			this.subject = subject;
		}

		public String getContent() {
			return content;
		}

		public void setContent(String textContent) {
			this.content = textContent;
		}
	}

}
