package ca.uwaterloo.iss4e.demand.csv;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import au.com.bytecode.opencsv.CSVReader;
import ca.uwaterloo.iss4e.demand.model.ZonalDemand;

public class ZonalDemandParser {
	public ArrayList<ZonalDemand> parseAnnualZonalDemand(
			CSVReader reader) {
		ArrayList<ZonalDemand> observations = new ArrayList<ZonalDemand>();

		String[] nextLine;
		try {
			while ((nextLine = reader.readNext()) != null) {
				if (nextLine[0].equalsIgnoreCase("Date")) {
					System.out.println("Skipping row of data");
				} else {
					ZonalDemand zdo = new ZonalDemand();
					zdo.setObservationDate(this.parseObservationDate(nextLine));
					zdo.setHourNum(Integer.parseInt(nextLine[1]));
					zdo.setTotalOntario(Double.parseDouble(nextLine[2]));
					zdo.setNorthWest(Double.parseDouble(nextLine[3]));
					zdo.setNorthEast(Double.parseDouble(nextLine[4]));
					zdo.setOttawa(Double.parseDouble(nextLine[5]));
					zdo.setEast(Double.parseDouble(nextLine[6]));
					zdo.setToronto(Double.parseDouble(nextLine[7]));
					zdo.setEssa(Double.parseDouble(nextLine[8]));
					zdo.setBruce(Double.parseDouble(nextLine[9]));
					zdo.setSouthWest(Double.parseDouble(nextLine[10]));
					zdo.setNiagara(Double.parseDouble(nextLine[11]));
					zdo.setWest(Double.parseDouble(nextLine[12]));
					zdo.setTotalZones(Double.parseDouble(nextLine[13]));
					zdo.setDifference(Double.parseDouble(this.stripNumber(nextLine[14])));
					observations.add(zdo);
				}
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return observations;
	}

	/**
	 * Strip commas out of numbers before parsing
	 * @param numString
	 * @return stripped numString
	 */
	private String stripNumber(String numString) {
		return numString.replace(",","");
	}

	private Date parseObservationDate(String[] line) throws ParseException {
		SimpleDateFormat zonalDemandDatFormat = null;

		if (line[0].contains("-")) {
			// Years 2002-2009 date format
			zonalDemandDatFormat = new SimpleDateFormat("dd-MMM-yy H z");
		} else if (line[0].contains("/")) {
			// Years 2009-2012 date format
			zonalDemandDatFormat = new SimpleDateFormat("yyyy/MM/dd H z");
		}

		// CSV starts with hour 1 = 00:00:00 through 00:59:59.
		// For some reason SimpleDateFormat "k" does not reflect proper value.
		int hour = Integer.parseInt(line[1]) - 1;

		// All times are EST, as per 1/9/2014 email conversation with IESO
		// representative
		String combinedDate = line[0] + " " + hour + " EST";
		return zonalDemandDatFormat.parse(combinedDate);
	}
}
