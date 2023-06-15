package model;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

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
		// DOC (Depended-On-Component): nuestros doubles
		
		this.name        = "zone0";
		this.radiusInKm	 = 1;
		this.epicenter   = mock(Location.class);

		this.system 	   = mock(System.class);
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
		assertNotNull(this.zone);
	}

	@Test
	public void testGetSystem() {
		ZoneCoverage zoneNew = new ZoneCoverage(name,epicenter,radiusInKm,this.system);
		assertEquals(this.system, zoneNew.getSystem());
	}

	@Test
	void testSamplesInZone() {
		assertEquals(0, this.zone.samplesInZone().size());
		assertEquals("zone0", this.zone.getName());
	}

	@Test
	public void testOneSampleRegisteredInZone() {
		// Exercise
		List<Sample> expSamples = new ArrayList<>();
		when(this.system.getSamples()).thenReturn(expSamples);
		when (sample1.getLocation()).thenReturn(this.epicenter);
		this.system.add(sample1);
		List<Sample> currSamplesInZone = zone.samplesInZone();

		// Verify
		assertEquals(expSamples, currSamplesInZone);
	}

	@Test
	public void testTwoSamplesRegisteredInZone() {
		// Exercise
		List<Sample> expSamples = new ArrayList<>();
		when(this.system.getSamples()).thenReturn(expSamples);
		when (sample1.getLocation()).thenReturn(this.epicenter);
		this.system.add(sample1);
		when (sample2.getLocation()).thenReturn(this.epicenter);
		this.system.add(sample2);
		List<Sample> currSamplesInZone = zone.samplesInZone();

		// Verify
		assertEquals(expSamples, currSamplesInZone);
	}
	
	@Test
	public void testIntersectWithOneZone() {
		this.system = spy(this.system);
		List<ZoneCoverage> givenZones = new ArrayList<>();
		givenZones.add(zone1);
		when(this.system.getZones()).thenReturn(givenZones);
		ZoneCoverage zoneNew = new ZoneCoverage(name,epicenter,radiusInKm,this.system);
		when(this.zone1.getEpicenter()).thenReturn(this.epicenter);
		List<ZoneCoverage> expInterZones = new ArrayList<>();
		expInterZones.add(zone1);
		assertEquals(expInterZones, zoneNew.intersectionZones());
	}

	@Test
	public void testAttach() {
		List<IOrganization> spyIOrg = spy(this.organizations);
		// New SUT (System Under Test): objeto a testear
		ZoneCoverage zoneNew     = new ZoneCoverage(name,epicenter,radiusInKm,spyIOrg);
		zoneNew.Attach(iOrg1);
		zoneNew.Attach(iOrg2);

		InOrder orden = inOrder(spyIOrg);
		orden.verify(spyIOrg).add(iOrg1);
		orden.verify(spyIOrg).add(iOrg2);
	}

	@Test
	public void testDetach() {
//		List<IOrganization> spyIOrg = spy(this.organizations);
		List<IOrganization> spyIOrg = spy(new ArrayList<IOrganization>());
		// New SUT (System Under Test): objeto a testear
		ZoneCoverage zoneNew = new ZoneCoverage(name,epicenter,radiusInKm,spyIOrg);
		zoneNew.Attach(iOrg1);
		zoneNew.Attach(iOrg2);
		assertEquals(2, spyIOrg.size());
		zoneNew.Detach(iOrg1);
		zoneNew.Detach(iOrg2);
		assertEquals(0, spyIOrg.size());
	}

	@Test
	public void testNotify() {
		this.iOrg1 	   = spy(this.iOrg1);
		this.iOrg2	   = spy(this.iOrg2);
		List<IOrganization> spyIOrg = spy(new ArrayList<IOrganization>());
//		List<IOrganization> expOrgs = this.organizations;
		// New SUT (System Under Test): objeto a testear
		ZoneCoverage zoneNew     = new ZoneCoverage(name,epicenter,radiusInKm,spyIOrg);
		zoneNew.Attach(iOrg1);
		zoneNew.Attach(iOrg2);
		zoneNew.Notify(sample1);
		
		//verify
		verify(iOrg1, times(1)).Update(zoneNew, sample1);
		verify(iOrg2, times(1)).Update(zoneNew, sample1);
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
