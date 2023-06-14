package model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;


public class LocationTest {

	private Location 		location;

	private System 	system = System.getInstance("systemLEM");
	private float 	latitude;
	private float 	longitude;


	private Location 				location1;
	private Location 				location2;
	private Sample 					sample1;
	private Sample 					sample2;
	private Sample 					sample3;
	private Integer					distance1;

	@BeforeEach
	public void setUp() {
	// DOC (Depended-On-Component): nuestros doubles
		this.latitude 	= 5;
		this.longitude	= 5;
		
		this.location1 	= mock(Location.class);
		this.location2	= mock(Location.class);
		when(this.location1.getLatitude()).thenReturn((float) 4);
		when(this.location1.getLongitude()).thenReturn((float) 4);
		when(this.location2.getLatitude()).thenReturn((float) 2);
		when(this.location2.getLongitude()).thenReturn((float) 2);
		this.sample1 	= mock(Sample.class);
		this.sample2 	= mock(Sample.class);
		this.sample3 	= mock(Sample.class);
		this.distance1	= 2;

	// SUT (System Under Test): objeto a testear
	location = new Location(latitude, longitude);
	}

	@Test
	void testConstructor() {
		assertFalse(this.location==null);
	}

	@Test
	public void testGetLatitude() {
		assertEquals(this.latitude, this.location.getLatitude());
	}

	@Test
	public void testGetLongitude() {
		assertEquals(this.longitude, this.location.getLongitude());
	}

	@Test
	public void testDistanceTwoLocations() {
		assertEquals(2, this.location.distance(location1, location2));
	}
	
	@Test
	public void testDistanceOneLocation() {
		when(this.location1.getLatitude()).thenReturn((float) 3);
		when(this.location1.getLongitude()).thenReturn((float) 3);
		assertEquals(2, this.location.distance(location1));
	}

	@Test
	public void testClosestSamples() {
		when(this.sample1.getLocation()).thenReturn(location);
		when(this.sample2.getLocation()).thenReturn(location1);
		when(this.sample3.getLocation()).thenReturn(location2);
		this.system = spy(this.system);
		List<Sample> givenSamples = new ArrayList<>();
		givenSamples.add(sample2);
		givenSamples.add(sample3);
		when(this.system.getSamples()).thenReturn(givenSamples);
		assertEquals(givenSamples, this.location.closestSamples(sample1, this.distance1));
	}

	@Test
	public void testClosestLocations() {
		Location location3 = mock(Location.class);
		when(location3.getLatitude()).thenReturn((float) 7);
		when(location3.getLongitude()).thenReturn((float) 7);
		List<Location> givenLocations = new ArrayList<>();
		givenLocations.add(this.location1);
		givenLocations.add(this.location2);
		givenLocations.add(location3);
		assertEquals(givenLocations.remove(location3), this.location.closestLocations(givenLocations, distance1));
	}
}
