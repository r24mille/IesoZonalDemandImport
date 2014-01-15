import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import au.com.bytecode.opencsv.CSVReader;
import ca.uwaterloo.iss4e.demand.csv.ZonalDemandParser;
import ca.uwaterloo.iss4e.demand.dao.ZonalDemandDAO;
import ca.uwaterloo.iss4e.demand.model.ZonalDemand;

public class AnnualZonalDemandImport {
	private static ApplicationContext context = new ClassPathXmlApplicationContext(
			"applicationContext.xml");
	private static final ZonalDemandDAO zonalDemandDAO = (ZonalDemandDAO) context
			.getBean("zonalDemandDAO");

	public static void main(String[] args) {
		csvParse();
	}

	private static void csvParse() {
		ArrayList<String> files = new ArrayList<String>();
//		files.add("ZonalDemands_2003.csv");
//		files.add("ZonalDemands_2004.csv");
//		files.add("ZonalDemands_2005.csv");
//		files.add("ZonalDemands_2006.csv");
//		files.add("ZonalDemands_2007.csv");
//		files.add("ZonalDemands_2008.csv");
//		files.add("ZonalDemands_2009.csv");
//		files.add("ZonalDemands_2010.csv");
//		files.add("ZonalDemands_2011.csv");
//		files.add("ZonalDemands_2012.csv");
		files.add("ZonalDemands_2013.csv");

		CSVReader reader = null;
		try {
			for (String file : files) {
				reader = new CSVReader(new FileReader(file));
				ZonalDemandParser zonalDemandParser = new ZonalDemandParser();
				ArrayList<ZonalDemand> zonalDemands = zonalDemandParser
						.parseAnnualZonalDemand(reader);
				
				for (ZonalDemand zonalDemand : zonalDemands) {
					zonalDemandDAO.insertZonalDemand(zonalDemand);
				}
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}

	}

}
