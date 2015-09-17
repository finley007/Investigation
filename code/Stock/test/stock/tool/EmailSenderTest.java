package stock.tool;

import static org.junit.Assert.*;

import org.junit.Test;

public class EmailSenderTest {

	@Test
	public void testSendTextMail() {
		EmailSender sender = new EmailSender();
		try {
			sender.sendTextMail("hi", "This is test", "finley916@sohu.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testSendHtmlMail() {
		EmailSender sender = new EmailSender();
		try {
			sender.sendHtmlMail("hi", "This is test", "finley916@sohu.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
