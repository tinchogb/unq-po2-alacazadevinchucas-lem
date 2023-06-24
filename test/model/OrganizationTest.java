package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;




import java.util.List;
import java.util.ArrayList;


public class OrganizationTest {

	private Organization			org;
	private Organization			orgMock;

	private OrganizationTypeTest 		type;
	private List<ZoneCoverage> 		registeredZones = new ArrayList<>();
	private List<Empleado>			empleados = new ArrayList<>();
	private Location 				location;
	private ExternalFunctionality 	pluginSample;
	private ExternalFunctionality 	pluginValidation;

	private ZoneCoverage			zone1;
	private Sample 					sample;

	@SuppressWarnings("unchecked")
	@BeforeEach
	public void setUp() {
	// DOC (Depended-On-Component): nuestros doubles
//		this.type				= mock(OrganizationType.class);
		this.registeredZones	= (List<ZoneCoverage>) mock(List.class);
		this.empleados			= (List<Empleado>) mock(List.class);
		this.location			= mock(Location.class);
		this.pluginSample		= mock(ExternalFunctionality.class);
		this.pluginValidation	= mock(ExternalFunctionality.class);
		
		this.zone1				= mock(ZoneCoverage.class);
		this.sample				= mock(Sample.class);

		// SUT (System Under Test): objeto a testear
		this.org = new Organization(type, registeredZones, location, pluginSample, pluginValidation, empleados);
	}

	@Test
	public void testConstructor() {
		assertNotNull(this.org);
	}

	@Test
	public void testGetType() {
		assertEquals(this.type, this.org.getType());
	}

	@Test
	public void testGetRegisteredZones() {
		assertEquals(this.registeredZones, this.org.getRegisteredZones());
	}

	@Test
	public void testGetLocation() {
		assertEquals(this.location, this.org.getLocation());
	}

	@Test
	public void testUpdate() {
		when(sample.isValidated()).thenReturn(true);
		this.org.Update(zone1, sample);
		verify(sample,times(1)).isValidated();
		Sample sample2	= mock(Sample.class);
		ZoneCoverage zone2	= mock(ZoneCoverage.class);
		this.orgMock = new Organization(type, registeredZones, location, pluginSample, pluginValidation, empleados);
		this.orgMock = spy(this.orgMock);
		this.orgMock.Update(zone2, sample2);	
		verify(orgMock,times(1)).Update(zone2, sample2);
	}

	@Test
	public void testNbCurrentWorkers() {
		this.empleados = spy(new ArrayList<Empleado>());
		when(this.empleados.size()).thenReturn(0);
		this.org = new Organization(type, registeredZones, location, pluginSample, pluginValidation, empleados);
		assertEquals(0, this.org.nbCurrentWorkers());
	}

	@Test
	public void testNewSampleExtFunc() {
	}

	@Test
	public void testNewValidationExtFunc() {
		// org.newSampleExtFunc(zone1, sample); 	// No es public !
		// org.newValidationExtFunc(zone1, sample);	// No es public !
	}
}
