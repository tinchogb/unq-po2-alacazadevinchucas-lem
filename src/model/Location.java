package model;

import java.lang.Math.*;
import java.util.List;

public class Location {

	private System 			system = System.getInstance("lalala");
	private float 			latitude;
	private float 			longitude;

	public Location(float aLatitude, float aLongitude) {
		// TODO Auto-generated constructor stub
		this.latitude 	= aLatitude;
		this.longitude	= aLongitude;
	}

	public float getLatitude() 	{ return latitude; }
	public float getLongitude() { return longitude; }

	public double distance(Location aLocation1, Location aLocation2) {
		// TODO Auto-generated method stub
		// Taken from: https://stackoverflow.com/questions/14431032/i-want-to-calculate-the-distance-between-two-points-in-java
		return Math.hypot( (aLocation1.getLatitude()  - aLocation2.getLatitude())
						 , (aLocation1.getLongitude() - aLocation2.getLongitude()) );
		// Taken from: https://javatutoring.com/distance-between-two-points-java-program/
//		return Math.sqrt((aLocation1.latitude - aLocation2.latitude) +
//				(aLocation1.longitude - aLocation2.longitude) );
	}

	public double distance(Location aLocation) {
		// TODO Auto-generated method stub
		return this.distance(this, aLocation);
	}

	public List<Location> closestLocations(List<Location> locations, double maxDistance) {
		// TODO Auto-generated method stub
		return locations.stream()
				.filter(location -> this.distance(location) <= maxDistance)
				.toList();
	}

	public List<Sample> closestSamples(Sample aSample, double maxDistance) {
		// TODO Auto-generated method stub
		return this.system.getSamples().stream()
				.filter(sample -> this.distance(aSample.getLocation(), sample.getLocation()) <= maxDistance)
				.toList();
	}


}
