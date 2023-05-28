package model;

import java.lang.Math.*;
import java.util.List;

public class Location {

	private SystemSingleton system;	
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
		return distance(this, aLocation);
	}

	public List<Location> closestLocations(List<Location> Locations) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Sample> closestSamples(Sample aSample, Integer maxDistance) {
		// TODO Auto-generated method stub
		return null;
	}


}
