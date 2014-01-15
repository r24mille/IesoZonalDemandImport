package ca.uwaterloo.iss4e.demand.dao.mapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.jdbc.core.RowMapper;

import ca.uwaterloo.iss4e.demand.model.ZonalDemand;

public class ZonalDemandMapper implements RowMapper<ZonalDemand> {
	private DateFormat datetimeWithTimezoneFormat = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss z");

	@Override
	public ZonalDemand mapRow(ResultSet rs, int rowNum) throws SQLException {
		ZonalDemand zonalDemand = new ZonalDemand();

		try {
			Date observationDate = datetimeWithTimezoneFormat.parse(rs
					.getString("demand_datetime_with_timezone"));
			System.out.println(observationDate);
			zonalDemand.setObservationDate(observationDate);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		zonalDemand.setHourNum(rs.getInt("hour"));
		zonalDemand.setTotalOntario(rs.getDouble("total_ontario"));
		zonalDemand.setTotalZones(rs.getDouble("total_zones"));
		zonalDemand.setDifference(rs.getDouble("difference"));
		zonalDemand.setNorthWest(rs.getDouble("northwest"));
		zonalDemand.setNorthEast(rs.getDouble("northeast"));
		zonalDemand.setOttawa(rs.getDouble("ottawa"));
		zonalDemand.setEast(rs.getDouble("east"));
		zonalDemand.setToronto(rs.getDouble("toronto"));
		zonalDemand.setEssa(rs.getDouble("essa"));
		zonalDemand.setBruce(rs.getDouble("bruce"));
		zonalDemand.setSouthWest(rs.getDouble("southwest"));
		zonalDemand.setNiagara(rs.getDouble("niagara"));
		zonalDemand.setWest(rs.getDouble("west"));

		return zonalDemand;
	}

}
