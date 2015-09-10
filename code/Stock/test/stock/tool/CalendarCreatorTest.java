package stock.tool;

import org.junit.Test;

public class CalendarCreatorTest {

	@Test
	public void testCreateCalendar() {
		try {
			CalendarCreator.createCalendar("2015-01-01", "2015-12-31");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
