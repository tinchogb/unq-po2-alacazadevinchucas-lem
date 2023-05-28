package model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import model.ZoneCoverage;
import model.Organization;

public class LocationTest {

	public float latitude;
	public float longitude;

	public Location 				location;
	public Location 				location1;
	public Location 				location2;
	public List<Location>			givenLocations;
	public Sample 					sample;
	public Integer					distance1;
	public ZoneCoverage				zone1;

	@BeforeEach
	public void setUp() {
	// DOC (Depended-On-Component): nuestros doubles
	
	// SUT (System Under Test): objeto a testear
	location = new Location(latitude, longitude);
	}
	
	@Test
	public void unTest() {
	// Exercise
	}
	
	@Test
	public void otroTest() {
		double distance					= location.distance(location1, location2);
		List<Location> knownLocations 	= location.closestLocations(givenLocations);
		List<Sample> knownSamples 		= location.closestSamples(sample, distance1);
	}
}
