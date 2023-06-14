package model;

import java.util.List;

public class Location {

	private System 			system = System.getInstance("systemLEM");
	private float 			latitude;
	private float 			longitude;

	public Location(float aLatitude, float aLongitude) {
		this.latitude 	= aLatitude;
		this.longitude	= aLongitude;
	}

	public float getLatitude() 	{ return latitude; }
	public float getLongitude() { return longitude; }

	public double distance(Location aLocation1, Location aLocation2) {
		// Taken from: https://stackoverflow.com/questions/14431032/i-want-to-calculate-the-distance-between-two-points-in-java
		return Math.hypot( (aLocation1.getLatitude()  - aLocation2.getLatitude())
						 , (aLocation1.getLongitude() - aLocation2.getLongitude()) );
		// Taken from: https://javatutoring.com/distance-between-two-points-java-program/
//		return Math.sqrt((aLocation1.latitude - aLocation2.latitude) +
//				(aLocation1.longitude - aLocation2.longitude) );
	}

	public double distance(Location aLocation) {
		return this.distance(this, aLocation);
	}

	public List<Location> closestLocations(List<Location> locations, double maxDistance) {
		return locations.stream()
				.filter(location -> this.distance(location) <= maxDistance)
				.toList();
	}

	public List<Sample> closestSamples(Sample aSample, double maxDistance) {
		return this.system.getSamples().stream()
				.filter(sample -> this.distance(aSample.getLocation(), sample.getLocation()) <= maxDistance)
				.toList();
	}


}
