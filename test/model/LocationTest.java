package model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.ArrayList;


public class LocationTest {

	private Location 		location;

	private System 	system;
	private float 	latitude;
	private float 	longitude;


	private Location 				location1;
	private Location 				location2;
	private Sample 					sample1;
	private Sample 					sample2;
	private Sample 					sample3;
	private double					distance1;

	@BeforeEach
	public void setUp() {
	// DOC (Depended-On-Component): nuestros doubles
		this.latitude 	= 6;
		this.longitude	= 3;

		this.system = mock(System.class);
		this.location1 	= mock(Location.class);
		this.location2	= mock(Location.class);
		when(this.location1.getLatitude()).thenReturn((float) 4);
		when(this.location1.getLongitude()).thenReturn((float) 3);
		when(this.location2.getLatitude()).thenReturn((float) 2);
		when(this.location2.getLongitude()).thenReturn((float) 3);
		this.sample1 	= mock(Sample.class);
		this.sample2 	= mock(Sample.class);
		this.sample3 	= mock(Sample.class);
		this.distance1	= 3;

	// SUT (System Under Test): objeto a testear
	location = new Location(latitude, longitude);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.location);
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
//		when(this.location1.getLatitude()).thenReturn((float) 3);
//		when(this.location1.getLongitude()).thenReturn((float) 3);
		assertEquals(2, this.location.distance(location1));
	}

	@Test
	public void testClosestSamples() {
		when(this.sample2.getLocation()).thenReturn(location1);
		when(this.sample3.getLocation()).thenReturn(location2);
		List<Sample> givenSamples = new ArrayList<>();
		givenSamples.add(sample2);
		givenSamples.add(sample3);
		List<Sample> expSamples = new ArrayList<>();
		expSamples.add(sample2);
		this.system = spy(System.class);
		when(this.system.getSamples()).thenReturn(givenSamples);
		Location locationNew = new Location(this.latitude, this.longitude, this.system);
		when(this.sample1.getLocation()).thenReturn(locationNew);
		assertEquals(expSamples, locationNew.closestSamples(this.sample1, this.distance1));
		verify(this.system, times(1)).getSamples();
	}

	@Test
	public void testClosestLocations() {
		Location location3 = mock(Location.class);
		when(location3.getLatitude()).thenReturn((float) 7);
		when(location3.getLongitude()).thenReturn((float) 3);
		List<Location> givenLocations = new ArrayList<>();
		List<Location> expLocations = new ArrayList<>();
		givenLocations.add(this.location1);
		givenLocations.add(this.location2);
		givenLocations.add(location3);
		expLocations.add(this.location1);
		expLocations.add(location3);
		assertEquals(expLocations, this.location.closestLocations(givenLocations, distance1));
	}
}
