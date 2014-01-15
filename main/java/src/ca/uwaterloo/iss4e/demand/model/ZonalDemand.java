package ca.uwaterloo.iss4e.demand.model;

import java.util.Date;

public class ZonalDemand {
	private Date observationDate;
	private int hourNum;
	private double totalOntario;
	private double totalZones;
	private double difference;
	private double northWest;
	private double northEast;
	private double ottawa;
	private double east;
	private double toronto;
	private double essa;
	private double bruce;
	private double southWest;
	private double niagara;
	private double west;

	public int getHourNum() {
		return hourNum;
	}

	public void setHourNum(int hourNum) {
		this.hourNum = hourNum;
	}

	public Date getObservationDate() {
		return observationDate;
	}

	public void setObservationDate(Date observationDate) {
		this.observationDate = observationDate;
	}

	public double getTotalOntario() {
		return totalOntario;
	}

	public void setTotalOntario(double totalOntario) {
		this.totalOntario = totalOntario;
	}

	public double getTotalZones() {
		return totalZones;
	}

	public void setTotalZones(double totalZones) {
		this.totalZones = totalZones;
	}

	public double getDifference() {
		return difference;
	}

	public void setDifference(double difference) {
		this.difference = difference;
	}

	public double getNorthWest() {
		return northWest;
	}

	public void setNorthWest(double northWest) {
		this.northWest = northWest;
	}

	public double getNorthEast() {
		return northEast;
	}

	public void setNorthEast(double northEast) {
		this.northEast = northEast;
	}

	public double getOttawa() {
		return ottawa;
	}

	public void setOttawa(double ottawa) {
		this.ottawa = ottawa;
	}

	public double getEast() {
		return east;
	}

	public void setEast(double east) {
		this.east = east;
	}

	public double getToronto() {
		return toronto;
	}

	public void setToronto(double toronto) {
		this.toronto = toronto;
	}

	public double getEssa() {
		return essa;
	}

	public void setEssa(double essa) {
		this.essa = essa;
	}

	public double getBruce() {
		return bruce;
	}

	public void setBruce(double bruce) {
		this.bruce = bruce;
	}

	public double getSouthWest() {
		return southWest;
	}

	public void setSouthWest(double southWest) {
		this.southWest = southWest;
	}

	public double getNiagara() {
		return niagara;
	}

	public void setNiagara(double niagara) {
		this.niagara = niagara;
	}

	public double getWest() {
		return west;
	}

	public void setWest(double west) {
		this.west = west;
	}

}
