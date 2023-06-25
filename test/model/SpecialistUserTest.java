package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SpecialistUserTest {
	
	public User user;
	public ExpertUser expert;
	public BasicUser basic;
	public SpecialistUser specialist;
	public Opinion opinion;
	public OpinionType type;
	public VinchucaSpecies specie;
	public Location location;
	public System system;
	public Sample sample;
	
	@BeforeEach
	public void setUp() {
		
		expert = mock(ExpertUser.class);
		opinion = mock(Opinion.class);
		type = mock(OpinionType.class);
		//specie = mock(VinchucaSpecies.class);
		location = mock(Location.class);
		system   = mock(System.class);
		sample = mock(Sample.class);
		user = mock(User.class);
		basic = mock(BasicUser.class);
		specialist = new SpecialistUser();
	}

	@Test
	void testGetState() {
		assertEquals(new ExpertUser(),specialist.getState());
	}

}
