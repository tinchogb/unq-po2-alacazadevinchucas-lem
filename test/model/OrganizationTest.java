package model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import model.ZoneCoverage;
import model.Organization;
import model.ExternalFunctionality;

public class OrganizationTest {

	public Organization				org;
	public OrganizationType 		type;
	//public List<Activity> 			activities;
	public List<ZoneCoverage> 		registeredZones;
	public Location 				location;
	public ExternalFunctionality 	pluginSample;
	public ExternalFunctionality 	pluginValidation;

	public ZoneCoverage				zone1;
	public Sample 					sample;
	@BeforeEach
	public void setUp() {
	// DOC (Depended-On-Component): nuestros doubles
	
	// SUT (System Under Test): objeto a testear
	org = new Organization(type, registeredZones, location, pluginSample, pluginValidation);
	}
	
	@Test
	public void unTest() {
	// Exercise
		Integer nbWorkers = org.nbCurrentWorkers();
	}
	
	@Test
	public void otroTest() {
		// org.newSampleExtFunc(zone1, sample); 	// No es public !
		// org.newValidationExtFunc(zone1, sample);	// No es public !
	}
}
