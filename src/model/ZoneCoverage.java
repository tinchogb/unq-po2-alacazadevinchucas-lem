package model;

import java.util.List;

public class ZoneCoverage {

	private System 		system = System.getInstance("lalala");
	private String 	 	name;
	private Location 	epicenter;
	private Integer  	radiusInKm;

	// Constructor for testing purpose only 
	public ZoneCoverage(System aSystem, String name, Location epicenter, Integer radiusInKm) {
		this.system		= aSystem;
		this.name		= name;
		this.epicenter	= epicenter;
		this.radiusInKm	= radiusInKm;
	}	

	// Real constructor
	public ZoneCoverage(String name, Location epicenter, Integer radiusInKm) {
		// TODO Auto-generated constructor stub
		this.name		= name;
		this.epicenter	= epicenter;
		this.radiusInKm	= radiusInKm;
	}

	
	public System 	getSystem() 		{ return system; }
	public String 	getName() 			{ return name; }
	public Location getEpicenter() 		{ return epicenter; }
	public Integer 	getRadiusInKm() 	{ return radiusInKm; }


	public List<Sample> samplesInZone() {
		// TODO Auto-generated method stub
		return this.system.getSamples().stream()
					.filter(sample -> this.inZone(sample.getLocation()))
					.toList();
	}

	private boolean inZone(Location location) {
		// TODO Auto-generated method stub
		return this.epicenter.distance(location) <= this.radiusInKm;
	}


	public List<ZoneCoverage> intersectionZones() {
		// TODO Auto-generated method stub
		return null;
	}

}
