package model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import model.ZoneCoverage;
import model.Organization;
import model.Location;
import model.Sample;

public class ZoneCoverageTest {

	public SystemSingleton system;
	public ZoneCoverage zone;
	public String 		id;
	public String 		name;
	public Location 	epicenter;
	public Integer 		radiusInKm;

	public String 		OrganizationType;
	public Organization organization;
	public Sample		sample1;
	public Sample		sample2;
	public ZoneCoverage	zone2;
	
	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles
		this.system		 = mock(SystemSingleton.class);
		when(system.getInstance("lalala")).thenCallRealMethod();

		this.id       	 = "1";
		this.name        = "zone1";
		this.epicenter   = mock(Location.class);
		this.radiusInKm	 = 1;

		this.organization = mock(Organization.class);
		this.sample1 	  = mock(Sample.class);
		this.sample2 	  = mock(Sample.class);
		this.zone2	 	  = mock(ZoneCoverage.class);

		
		// SUT (System Under Test): objeto a testear
		this.zone		 = new ZoneCoverage(id,name,epicenter,radiusInKm);
		SystemSingleton system = this.zone.getSystem();
		system.addToList(sample1);
		system.addToList(sample2);
	}

	@Test
	void testConstructor() {
		assertFalse(this.zone==null);
	}
	
	@Test
	void testSamplesInZone() {
		assertEquals("zone1", this.zone.getName());
		//verify(opinion, times(2));
		assertEquals(0, this.zone.samplesInZone().size());
	}

	@Test
	public void testOneSampleRegisteredInZone() {
		// Exercise
		this.sample1.location = this.epicenter;
		List<String> samplesInZone = zone.samplesInZone();
		
		// Verify
	}

	@Test
	public void testTwoSamplesRegisteredInZone() {
	// Exercise
	}
	
	@Test
	public void intersectWithOneZone() {
		List<ZoneCoverage> interZones = zone.intersectionZones();
	}
}
