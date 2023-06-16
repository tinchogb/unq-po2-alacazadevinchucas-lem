package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InOrder;
import org.mockito.MockedStatic;


public class SystemTest {

	public System system;

	public static System instance;
	private List<ZoneCoverage> listenerZones = new ArrayList<ZoneCoverage>();

	private User user1;
	private User user2;
	private Sample sample1;
	private Sample sample2;
	private Location location1;
	private Location location2;
	private ZoneCoverage zone1;
	private ZoneCoverage zone2;
	
	private  List<User>			users     = new ArrayList<User>(); 
	private  List<Sample>		samples   = new ArrayList<Sample>(); 
	private  List<Location>		locations = new ArrayList<Location>();
	private  List<ZoneCoverage>	zones 	  = new ArrayList<ZoneCoverage>();


	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles

//		this.organization = mock(Organization.class);
		this.listenerZones = (List<ZoneCoverage>) mock(List.class);
		this.user1 	   	= mock(User.class);
		this.user2 	   	= mock(User.class);
		this.sample1 	= mock(Sample.class);
		this.sample2 	= mock(Sample.class);
		this.location1	= mock(Location.class);
		this.location2	= mock(Location.class);
		this.zone1	 	= mock(ZoneCoverage.class);
		this.zone2	 	= mock(ZoneCoverage.class);

		
		// SUT (System Under Test): objeto a testear
		this.system     = System.getInstance();
	}

	@Test
	void testConstructor() {
		assertNotNull(this.system);
	}
	
	@Test
	void testGetInstance() {
		assertEquals(system,System.getInstance());
	}

	@Test
	void testGetUsers() {
		this.system.add(user1);
		this.system.add(user2);
		List<User> expUsers = new ArrayList<>();
		expUsers.add(user1);
		expUsers.add(user2);
		assertEquals(expUsers, this.system.getUsers());
	}
	
	@Test
	void testGetSamples() {
		this.system.add(sample1);
		this.system.add(sample2);
		List<Sample> expSamples = new ArrayList<>();
		expSamples.add(sample1);
		expSamples.add(sample2);
		assertEquals(expSamples, this.system.getSamples());
	}
	
	@Test
	void testGetLocations() {
		this.system.add(location1);
		this.system.add(location2);
		List<Location> expLocations = new ArrayList<>();
		expLocations.add(location1);
		expLocations.add(location2);
		assertEquals(expLocations, this.system.getLocations());
	}
	
	@Test
	void testGetZones() {
		this.system.add(zone1);
		this.system.add(zone2);
		List<ZoneCoverage> expZones = new ArrayList<>();
		expZones.add(zone1);
		expZones.add(zone2);
		assertEquals(expZones, this.system.getZones());
	}


	@Test
	void testAddForUsers() {
		this.users  = spy(new ArrayList<User>());
		this.system = new System(this.users);
		this.system.add(user1);
		assertEquals(this.users, this.system.getUsers());
		verify(this.users, times(1)).add(user1);
	}

	@Test
	void testAddForSamples() {
		this.samples  = spy(new ArrayList<Sample>());
		this.system = new System(this.samples,0,0);
		this.system.add(sample1);
		assertEquals(this.samples, this.system.getSamples());
		verify(this.samples, times(1)).add(sample1);
	}

	@Test
	void testAddForLocations() {
		this.locations  = spy(new ArrayList<Location>());
		this.system = new System(this.locations,0,0,0);
		this.system.add(location1);
		assertEquals(this.locations, this.system.getLocations());
		verify(this.locations, times(1)).add(location1);
	}

	@Test
	void testAddForZones() {
		this.zones  = spy(new ArrayList<ZoneCoverage>());
		this.system = new System(this.zones,0,0,0,0);
		this.system.add(zone1);
		assertEquals(this.zones, this.system.getZones());
		verify(this.zones, times(1)).add(zone1);
	}

	@Test
	void testIsInForUsers() {
		// No sabemos cómo testear métodos privados
		// Al buscar en la web nos encontramos con
		// refactoring de tests o uso de bibliotecas
		// alternativas como 'PowerMock' y decoradores.
	}

	@Test
	void testIsInForSamples() {
	}

	@Test
	void testIsInForLocations() {
	}

	@Test
	void testIsInForZones() {
	}
	
	@Test
	void testIsInListeners() {
	}

	@Test
	public void testAttach() {
		this.listenerZones = spy(new ArrayList<ZoneCoverage>());
		// New SUT (System Under Test): objeto a testear
		this.system = new System(this.listenerZones,0);
		this.system.Attach(zone1);
		this.system.Attach(zone2);

		InOrder order = inOrder(this.listenerZones);
		order.verify(this.listenerZones).add(zone1);
		order.verify(this.listenerZones).add(zone2);
	}

	@Test
	void testDetach() {
		this.listenerZones = spy(new ArrayList<ZoneCoverage>());
		this.system = new System(this.listenerZones,0);
		this.system.Attach(zone1);
		this.system.Attach(zone2);
		assertEquals(2, this.listenerZones.size());
		this.system.Detach(zone1);
		this.system.Detach(zone2);
		assertEquals(0, this.listenerZones.size());
	}

	@Test
	void testNotify() {
		this.zone1 	   = spy(this.zone1);
		this.zone2	   = spy(this.zone2);
		this.listenerZones = spy(new ArrayList<ZoneCoverage>());
		// New SUT (System Under Test): objeto a testear
		this.system = new System(this.listenerZones,0);
		this.system.Attach(zone1);
		this.system.Attach(zone2);
		this.system.Notify(sample1);
		
		//verify
		verify(zone1, times(1)).Update(sample1);
		verify(zone2, times(1)).Update(sample1);
	}
}
