import java.util.Calendar;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import ca.uwaterloo.iss4e.demand.dao.ZonalDemandDAO;

public class QueryZonalDemandRange {
	private static ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private static final ZonalDemandDAO zonalDemandDAO = (ZonalDemandDAO) context
			.getBean("zonalDemandDAO");

	public static void main(String[] args) {
		Calendar startCal = Calendar.getInstance();
		startCal.set(2010, Calendar.JANUARY, 1, 0, 0, 0);
		startCal.set(Calendar.MILLISECOND, 0);
		Calendar endCal = Calendar.getInstance();
		endCal.set(2010, Calendar.MARCH, 31, 23, 59, 59);
		endCal.set(Calendar.MILLISECOND, 0);

		zonalDemandDAO
				.getZonalDemandRange(startCal.getTime(), endCal.getTime());
	}
}
