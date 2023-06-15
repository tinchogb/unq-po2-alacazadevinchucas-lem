package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
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
//		this.instance = mock(System.class);
//		when(this.instance.getInstance()).thenReturn(this.instance);
//		try (MockedStatic<System> instance = mockStatic(System.class)) {
//			instance.when(System::getInstance).thenReturn(this);
//			assertEquals(instance, System.getInstance());
//			instance.verify(System::getInstance);
//		}


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
		this.system     = new System();
		this.system.add(sample1);
		this.system.add(sample2);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.system);
	}
	
	@Test
	void testGetInstance() {
		try (MockedStatic<System> mocked = mockStatic(System.class)) {
			mocked.when(System::getInstance).thenReturn(this);
			assertEquals(mocked, System.getInstance());
			mocked.verify(System::getInstance);
		}
	}

	@Test
	void testGetUsers() {
	}
	
	@Test
	void testGetSamples() {
	}
	
	@Test
	void testGetLocations() {
	}
	
	@Test
	void testGetZones() {
	}

	@Test
	void testAddForUsers() {
	}

	@Test
	void testAddForSamples() {
	}

	@Test
	void testAddForLocations() {
	}

	@Test
	void testAddForZones() {
	}

	@Test
	void testIsInForUsers() {
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
	void testAttach() {
	}

	@Test
	void testDetach() {
	}

	@Test
	void testNotify() {
	}
}
