package model;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class UserTest {
	
	private User user;
	private User user2;
	private User user3;
	private BasicUser stateBasic;
	private ExpertUser stateExpert;
	private System system;
	private List<Sample> samples = new ArrayList<> ();
	private List<Opinion> opinions = new ArrayList<> ();
	private Sample sample;
	private Opinion opinion;
	
	
	@BeforeEach
	public void setUp() {
		stateBasic = mock(BasicUser.class);
		stateExpert = mock(ExpertUser.class);
		system = mock(System.class);
		user = new User(stateBasic,system);
		user2 = new User(stateBasic,system);
		user3 = new User(stateExpert);
		sample = mock(Sample.class);
	    opinion = mock(Opinion.class);
		
	}

	@Test
	void testGetState() {
		assertEquals(stateBasic, user.getState());
	}
	
	void testGetStateConstructorOnlyOneParameter() {
		assertEquals(stateExpert, user3.getState());
	}
	
	
	@Test
	void testGetSystem() {
		assertEquals(system, user.getSystem());
	}
	
	@Test
	void verifyStateWhenMustChange() {
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);
		opinions.add(opinion);
		when(system.getSamples()).thenReturn(samples);
		when(sample.getUser()).thenReturn(user);
		when(sample.getCreationDate()).thenReturn(LocalDate.now());
		when(sample.getOpinions()).thenReturn((ArrayList<Opinion>) opinions);
		when(opinion.getUser()).thenReturn(user);
		when(opinion.getDate()).thenReturn(LocalDate.now());
		user.verifyState();
		assertEquals(new ExpertUser(), user.getState());
		
	}
	
	@Test
	void verifyStateWhenNotMustChangeReturnAnotherUser() {
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);
		opinions.add(opinion);
		when(system.getSamples()).thenReturn(samples);
		when(sample.getUser()).thenReturn(user2);
		when(sample.getCreationDate()).thenReturn(LocalDate.now());
		when(sample.getOpinions()).thenReturn((ArrayList<Opinion>) opinions);
		when(opinion.getUser()).thenReturn(user);
		when(opinion.getDate()).thenReturn(LocalDate.now());
		user.verifyState();
		assertEquals(new BasicUser(), user.getState());
		
	}
	
	@Test
	void verifyStateWhenNotMustChangeDateAntiguos() {
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);samples.add(sample);
		samples.add(sample);
		opinions.add(opinion);
		when(system.getSamples()).thenReturn(samples);
		when(sample.getUser()).thenReturn(user);
		when(sample.getCreationDate()).thenReturn(LocalDate.now());
		when(sample.getOpinions()).thenReturn((ArrayList<Opinion>) opinions);
		when(opinion.getUser()).thenReturn(user);
		when(opinion.getDate()).thenReturn(LocalDate.now().minusDays(60));
		user.verifyState();
		assertEquals(new BasicUser(), user.getState());
		
	}
	

}
