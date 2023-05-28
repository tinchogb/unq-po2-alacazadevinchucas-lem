package model;

import java.util.List;

public class ZoneCoverage {

	private SystemSingleton system = SystemSingleton.getInstance("lalala");
	private String 	 id;
	private String 	 name;
	private Location epicenter;
	private Integer  radiusInKm;

	// Constructor for testing purpose only 
	public ZoneCoverage(SystemSingleton aSystem, String id, String name, Location epicenter, Integer radiusInKm) {
		this.system		= aSystem;
		this.id 		= id;
		this.name		= name;
		this.epicenter	= epicenter;
		this.radiusInKm	= radiusInKm;
	}	

	// Real constructor
	public ZoneCoverage(String id, String name, Location epicenter, Integer radiusInKm) {
		// TODO Auto-generated constructor stub
		this.id 		= id;
		this.name		= name;
		this.epicenter	= epicenter;
		this.radiusInKm	= radiusInKm;
	}

	
	public SystemSingleton 	getSystem() { return system; }
	public String 	getId() 			{ return id; }
	public String 	getName() 			{ return name; }
	public Location getEpicenter() 		{ return epicenter; }
	public Integer 	getRadiusInKm() 	{ return radiusInKm; }


	public List<String> samplesInZone() {
		// TODO Auto-generated method stub
		return this.system.getSamples().stream()
					.filter(sample -> this.inZone(sample.getLocation()))
					.map(sample -> this.getId())
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
