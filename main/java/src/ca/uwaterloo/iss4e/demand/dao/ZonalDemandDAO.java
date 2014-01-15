package ca.uwaterloo.iss4e.demand.dao;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

import ca.uwaterloo.iss4e.demand.dao.mapper.ZonalDemandMapper;
import ca.uwaterloo.iss4e.demand.model.ZonalDemand;

public class ZonalDemandDAO {
	private static DataSource iesoDataSource;
	public static final String ZONAL_DEMAND_TIMEZONE = "EST";

	public ZonalDemandDAO(DataSource dataSource) {
		this.iesoDataSource = dataSource;
	}

	public void insertZonalDemand(ZonalDemand zonalDemand) {
		// MySQL's storage of timezone is annoying
		Calendar observationCalendar = Calendar.getInstance();
		observationCalendar.setTime(zonalDemand.getObservationDate());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		df.setCalendar(observationCalendar);
		df.setTimeZone(TimeZone.getTimeZone(ZONAL_DEMAND_TIMEZONE));
		String observationEstDatetime = df
				.format(observationCalendar.getTime());

		String sql = "insert into zonal_demand (demand_datetime_dst, "
				+ "demand_datetime_standard, demand_timezone, hour, total_ontario, "
				+ "total_zones, difference, northwest, northeast, ottawa, east, "
				+ "toronto, essa, bruce, southwest, niagara, west) "
				+ "values (?, STR_TO_DATE(?,'%Y-%m-%d %H:%i:%s'), ?, ?, ?, ?, ?, ?, "
				+ "?, ?, ?, ?, ?, ?, ?, ?, ?)";
		JdbcTemplate template = new JdbcTemplate(iesoDataSource);
		template.update(
				sql,
				new Object[] { zonalDemand.getObservationDate(),
						observationEstDatetime, ZONAL_DEMAND_TIMEZONE,
						zonalDemand.getHourNum(),
						zonalDemand.getTotalOntario(),
						zonalDemand.getTotalZones(),
						zonalDemand.getDifference(),
						zonalDemand.getNorthWest(), zonalDemand.getNorthEast(),
						zonalDemand.getOttawa(), zonalDemand.getEast(),
						zonalDemand.getToronto(), zonalDemand.getEssa(),
						zonalDemand.getBruce(), zonalDemand.getSouthWest(),
						zonalDemand.getNiagara(), zonalDemand.getWest() });
	}

	public List<ZonalDemand> getZonalDemandRange(Date startDate, Date endDate) {
		String sql = "select concat(date_format(demand_datetime_standard, '%Y-%m-%d %H:%i:%s'), ' ', demand_timezone) as demand_datetime_with_timezone, "
				+ "hour, total_ontario, total_zones, difference, northwest, northeast, "
				+ "ottawa, east, toronto, essa, bruce, southwest, niagara, west "
				+ "from zonal_demand where demand_datetime_standard >= ? and "
				+ "demand_datetime_standard <= ?";
		JdbcTemplate template = new JdbcTemplate(iesoDataSource);
		List<ZonalDemand> zonalDemands = template.query(sql, new Object[] {
				startDate, endDate }, new ZonalDemandMapper());
		return zonalDemands;
	}
}
