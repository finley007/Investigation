package stock.tool;

import org.junit.Test;

public class CalendarCreatorTest {

	@Test
	public void testCreateCalendar() {
		try {
			CalendarCreator.createCalendar("2016-01-01", "2020-12-31");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
