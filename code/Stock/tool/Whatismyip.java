import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class Whatismyip {

	private static String host = "smtp.163.com"; 
	private static String user = "finley007@163.com"; 
	private static String pwd = "!WWL&KMW&LL)"; 
	private static String from = "finley007@163.com"; 
	private static String to = "finley007@163.com"; 
	private static String subject = "What is my ip"; 

	public static void main(String[] args) throws Exception {
		sendMail(getMyIp());
	}

	private static String getMyIp() throws Exception {
		String result = "";
		URL getUrl = new URL("https://www.whatismyip.com/");
		HttpURLConnection connection = (HttpURLConnection) getUrl
				.openConnection();
		connection.connect();
		BufferedReader reader = new BufferedReader(new InputStreamReader(
				connection.getInputStream()));
		String lines;
		while ((lines = reader.readLine()) != null) {
			if (lines.indexOf("Your IP Address Is") > 0) {
				result = parseMyIp(lines);
				break;
			}
		}
		reader.close();
		connection.disconnect();
		return result;
	}

	private static String parseMyIp(String str) throws Exception {
		String regEx = "&#[0-9]{2}\\;";
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			String code = matcher.group();
			char aa = (char) Integer.parseInt(code.substring(2,
					code.length() - 1));
			sb.append(aa);
		}
		return sb.toString();
	}

	private static void sendMail(String txt) throws Exception {
		Properties props = new Properties();
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.auth", "true");
		Session session = Session.getDefaultInstance(props);
		session.setDebug(true);
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress(from));
		message.addRecipient(Message.RecipientType.TO, new InternetAddress(
				to));
		message.setSubject(subject);
		Multipart multipart = new MimeMultipart();
		BodyPart contentPart = new MimeBodyPart();
		contentPart.setText(txt);
		multipart.addBodyPart(contentPart);
		message.setContent(multipart);
		message.saveChanges();
		Transport transport = session.getTransport("smtp");
		transport.connect(host, user, pwd);
		transport.sendMessage(message, message.getAllRecipients());
		transport.close();
	}

}
