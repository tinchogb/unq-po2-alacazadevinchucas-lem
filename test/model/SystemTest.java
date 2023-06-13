package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class SystemTest {

	public static System system;

	public static System instance;
	private List<ZoneCoverage> listenerZones;
	public  String				name;

	private  List<User>			users     = new ArrayList<User>(); 
	private  List<Sample>		samples   = new ArrayList<Sample>(); 
	private  List<Location>		locations = new ArrayList<Location>();


	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles
		this.instance		 = mock(System.class);
		when(this.instance.getInstance("systemLEM")).thenCallRealMethod();

		this.name        = "systemLEM";

//		this.organization = mock(Organization.class);
		this.listenerZones = mock(List<ZoneCoverage.class>);
		this.user1 	   	= mock(User.class);
		this.user2 	   	= mock(User.class);
		this.sample1 	= mock(Sample.class);
		this.sample2 	= mock(Sample.class);
		this.location1	= mock(Location.class);
		this.location2	= mock(Location.class);
		this.zone1	 	= mock(ZoneCoverage.class);
		this.zone2	 	= mock(ZoneCoverage.class);

		
		// SUT (System Under Test): objeto a testear
		this.system     = new System("systemLEM");
		this.system.add(sample1);
		this.system.add(sample2);
	}

	@Test
	void testConstructor() {
		assertFalse(this.system==null);
	}
	
	@Test
	void testGetInstancee() {
		assertEquals("foo", Foo.method());
		try (MockedStatic mocked = mockStatic(Foo.class)) {
			mocked.when(Foo::method).thenReturn("bar");
			assertEquals("bar", Foo.method());
			mocked.verify(Foo::method);
		}
		assertEquals("foo", Foo.method());
	}
}
