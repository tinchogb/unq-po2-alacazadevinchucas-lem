package model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OpinionTest {
	
	public User user;
	public User user1;
	public LocalDate date;
	public OpinionType type;
	public Opinion opinion;
	public Opinion opinion1;
	public Opinion opinion2;
	ArrayList<Opinion> opinions = new ArrayList<Opinion>();
	
	
	@BeforeEach
	public void setUp() {

		type    = mock(OpinionType.class); 
		user    = mock(User.class);
		user1   = mock(User.class);
		opinion = new Opinion(user,type);
		opinion1= new Opinion(user1,type);
		opinion2= new Opinion(user,type);
		
	} 

	@Test
	void testConstructor() {
		assertEquals(opinion.getUser(), user);
		assertEquals(opinion.getType(),type);
		assertEquals(opinion.getDate(),LocalDate.now());
	}
	
	@Test
	void testIsRepeatUserWithOneRepeatedUser() {
		opinions.add(opinion);
		assertTrue(opinion.isRepeatUser(opinions));
	}
	
	@Test
	void testIsRepeatUserWithTwoUsersOneOfThemIsRepeated() {
		opinions.add(opinion1);
		opinions.add(opinion2);
		assertTrue(opinion.isRepeatUser(opinions));
	}
	
	@Test
	void testIsRepeatUserWithTwoDiferentsUsers() {
		opinions.add(opinion1);
		opinions.add(opinion1);
		assertFalse(opinion.isRepeatUser(opinions));
	}

	@Test
	void testIsExpertOpinion() {
		opinion.isExpertOpinion();
		verify(user,times(1)).mustChangeState();
	}

	
	

}
