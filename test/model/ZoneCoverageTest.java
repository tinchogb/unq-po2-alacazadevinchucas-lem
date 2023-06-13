package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.Assert.*;

import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;
import org.mockito.InOrder;

public class ZoneCoverageTest {

	private ZoneCoverage zone;

	private System 				system;
	private String 				name;
	private Location 			epicenter;
	private Integer 			radiusInKm;
	private List<IOrganization> organizations = new ArrayList<>();
	
//	public String 		OrganizationType; // no tiene que conocer a la organization
//	public Organization organization; // no tiene que conocer a la organization
	private Sample			sample1;
	private Sample			sample2;
	private IOrganization	iOrg1;
	private IOrganization	iOrg2;
	private ZoneCoverage	zone1;
	private ZoneCoverage	zone2;
	
	@SuppressWarnings("unchecked") // Al hacer click derecho en mock(List.class)
	@BeforeEach
	public void setUp() {
		
		this.name        = "zone0";
		this.radiusInKm	 = 1;
		this.epicenter   = mock(Location.class);
		// DOC (Depended-On-Component): nuestros doubles
//		this.system		 = mock(this.zone.getSystem());
//		when(System.getInstance("systemLEM")).thenCallRealMethod();

		this.organizations = (List<IOrganization>) mock(List.class);
		this.sample1 	   = mock(Sample.class);
		this.sample2 	   = mock(Sample.class);
		this.iOrg1 	   	   = mock(IOrganization.class);
		this.iOrg2 	   	   = mock(IOrganization.class);
		this.zone1	 	   = mock(ZoneCoverage.class);
		this.zone2	 	   = mock(ZoneCoverage.class);

		
		// SUT (System Under Test): objeto a testear
		this.zone     = new ZoneCoverage(name,epicenter,radiusInKm);
	}

	@Test
	void testConstructor() {
		assertFalse(this.zone==null);
	}

	@Test
	public void testGetSystem() {
		assertEquals(this.system, this.zone.getSystem());
	}

	@Test
	void testSamplesInZone() {
		assertEquals(0, this.zone.samplesInZone().size());
		assertEquals("zone0", this.zone.getName());
		//verify(opinion, times(2));
	}

	@Test
	public void testOneSampleRegisteredInZone() {
		// Exercise
		List<Sample> expSamples = new ArrayList<>();
		when(this.system.getSamples()).thenReturn(expSamples);
		this.sample1.location = this.epicenter;
		this.system.add(sample1);
		List<Sample> currSamplesInZone = zone.samplesInZone();
		assertEquals(expSamples, currSamplesInZone);
		// Verify
	}

	@Test
	public void testTwoSamplesRegisteredInZone() {
		// Exercise
		List<Sample> expSamples = new ArrayList<>();
		when(this.system.getSamples()).thenReturn(expSamples);
		this.sample1.location = this.epicenter;
		this.system.add(sample1);
		this.sample2.location = this.epicenter;
		this.system.add(sample2);
		List<Sample> currSamplesInZone = zone.samplesInZone();
		assertEquals(expSamples, currSamplesInZone);

		// Verify
	}
	
	@Test
	public void testIntersectWithOneZone() {
		when(this.zone1.getEpicenter()).thenReturn(this.epicenter);
		this.system.Attach(zone1);
		List<ZoneCoverage> currInterZones = zone.intersectionZones();
		List<ZoneCoverage> expInterZones = new ArrayList<>();
		expInterZones.add(zone1);
		assertEquals(expInterZones, currInterZones);
	}

	@Test
	public void testAttach() {
		List<IOrganization> spyIOrg = spy(this.organizations);
		// New SUT (System Under Test): objeto a testear
		ZoneCoverage zoneNew     = new ZoneCoverage(name,epicenter,radiusInKm,spyIOrg);
		zone.Attach(iOrg1);
		zone.Attach(iOrg2);

		InOrder orden = inOrder(spyIOrg);
		orden.verify(spyIOrg).add(iOrg1);
		orden.verify(spyIOrg).add(iOrg2);

		// Para saber si el método size() del spy fue llamado
//		verify(spyIOrg).size();
	}

	@Test
	public void testDetach() {
		List<IOrganization> spyIOrg = spy(this.organizations);
		// New SUT (System Under Test): objeto a testear
		ZoneCoverage zoneNew     = new ZoneCoverage(name,epicenter,radiusInKm,spyIOrg);
		zone.Attach(iOrg1);
		zone.Attach(iOrg2);
		assertEquals(2, spyIOrg.size());
		zone.Detach(iOrg1);
		zone.Detach(iOrg2);
		assertEquals(0, spyIOrg.size());
	}

	@Test
	public void testNotify() {
		this.iOrg1 	   = spy(iOrg1);
		this.iOrg2	   = spy(iOrg2);
		List<IOrganization> expOrgs = this.organizations;
		// New SUT (System Under Test): objeto a testear
		ZoneCoverage zoneNew     = new ZoneCoverage(name,epicenter,radiusInKm,expOrgs);
		zone.Attach(iOrg1);
		zone.Attach(iOrg2);
		this.zone.Notify(sample1);
		verify(iOrg1).Update(zone, sample1);
		verify(iOrg2).Update(zone, sample1);
	}

	@Test
	public void testUpdate() {}

	@Test
	public void testGetName() {
		assertEquals(name, this.zone.getName());
	}

	@Test
	public void testGetEpicenter() {
		assertEquals(epicenter, this.zone.getEpicenter());
	}

	@Test
	public void testGetRadiusInKm() {
		assertEquals(1, this.zone.getRadiusInKm());
	}
}
