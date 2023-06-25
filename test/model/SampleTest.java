package model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.*;

class SampleTest{ 
	
	public Sample sample;
	public VinchucaSpecies specie;
	public String picture;
	public Location location;
	public ArrayList<Opinion> opinions;
	public SampleState stateUnverified;
	public SampleState stateVerified;
	public SampleState statePartial;
	public Opinion opinion;
	public Opinion opinion1;
	public BasicUser basicUser;
	public ExpertUser usuarioExperto;
	public List<OpinionType> opinionsType = new ArrayList<>();
	public OpinionType type;
	public OpinionType type1;
	public User user;
	public System system;
	
	@BeforeEach
	public void setUp() {
		//specie          = mock(VinchucaSpecies.class);
		picture         = "foto";
		location        = mock(Location.class);
		opinion         = mock(Opinion.class);
		opinion1        = mock(Opinion.class);
		stateUnverified = mock(UnverifiedSample.class);
		stateVerified   = mock(VerifiedSample.class);
		statePartial    = mock(VerifiedPartialSample.class);
		type            = mock(OpinionType.class);
		type1           = mock(OpinionType.class);
		user            = mock(User.class);
		system          = mock(System.class);
		opinionsType.add(type);
		
		
		sample          = new Sample(specie,picture,location,user,opinion,stateUnverified,system); 
	}
	
	@Test
	void testConstructor() {
		assertFalse(sample==null); 
	}
	
	@Test
	void testCreationDate() {
		assertEquals(sample.getCreationDate(),LocalDate.now());
	}
	
	@Test
	void testLastOpinionInitial() {
		assertEquals(opinion,sample.getLastOpinion());
	}
	
	@Test
	void testLastOpinionAfterAddNewOpinion() {
		sample.addOpinion(opinion1);
		assertEquals(opinion1,sample.getLastOpinion());
	}
	
	@Test
	void testGetOpinions() {
		sample.addOpinion(opinion1);
		sample.getOpinions();
		//verify(opinion, times(2));
		assertEquals(2, sample.getOpinions().size());
	}
	@Test
	void testGetPicture() {
		sample.getPicture();
		assertEquals(picture, sample.getPicture());
	}
	@Test
	void testGetSpecie() {
		sample.getSpecie();
		assertEquals(specie, sample.getSpecie());
	}
	@Test
	void testGetLocation() {
		sample.getLocation();
		assertEquals(location, sample.getLocation());
	}
	@Test
	void testUser() {
		assertEquals(user,sample.getUser());
	}
	@Test
	void testSaveOpinionUnverifiedSample() {
		sample.saveOpinion(opinion);
		verify(stateUnverified, times(1)).saveOpinion(opinion,sample);
	}
	
	@Test
	void testAddOpinionUnverifiedSample() {
		sample.addOpinion(opinion);
		assertEquals(2, sample.getOpinions().size());	
	}
	
	@Test
	void testChangeSampleState() {
		sample.changeSampleState(statePartial);
		assertEquals(statePartial, sample.getState());	
	}
	
	@Test
	void testAddOpinionPartialVerifiedSampleConUsuarioBasico() {
		sample.changeSampleState(statePartial);
		sample.saveOpinion(opinion);
		verify(statePartial,times(1)).saveOpinion(opinion, sample);
	}
	
	@Test
	void testGetCurrentResult() {
		sample.getCurrentResult();
		verify(stateUnverified,times(1)).getCurrentResult(sample);
		
	}
	
	@Test
	void testRepeatUserOpinionWhenIsRepeat() {
		when(opinion1.isRepeatUser(sample.getOpinions())).thenReturn(true);	
		
		Exception exception = assertThrows(RuntimeException.class, 
				() -> {
					sample.userVerificator(opinion1);
			    });		
		assertEquals("El usuario ya opinó en esta muestra", exception.getMessage());
	}
	 
	@Test
	void testRepeatUserOpinionWhenIsNotRepeat() {
		when(opinion1.isRepeatUser(sample.getOpinions())).thenReturn(false);	
		sample.userVerificator(opinion1);
	} 
	
	@Test
	void testCalculateResultWithOneOpinion() {
		assertEquals(type,sample.calculateResult(opinionsType));
		
	}
	
	@Test
	void testCalculateResultWithTwoEqualsOpinions() {
		opinionsType.add(type);
		assertEquals(type,sample.calculateResult(opinionsType));
		
	}
	
	@Test
	void testCalculateResultWithTwoDifferentOpinionsButOneMoreRepeated() {
		opinionsType.add(type1);
		opinionsType.add(type1);
		assertEquals(type1,sample.calculateResult(opinionsType));
		
	}
	
	@Test
	void testCalculateResultWithTwoDifferentOpinionsShouldReturnUndefined() {
		opinionsType.add(type1);
		assertNotEquals(type,sample.calculateResult(opinionsType));
		assertNotEquals(type1,sample.calculateResult(opinionsType));
		assertEquals(new Undefined(),sample.calculateResult(opinionsType));
	}
	
	@Test
	void testBasicOpinionsWithOneOpinion() {
		assertEquals(1,sample.basicOpinions().size());
	}
	
	@Test
	void testBasicOpinionsTwoOneOpinion() {
		sample.getOpinions().add(opinion);
		assertEquals(2,sample.basicOpinions().size());
	}
	
	@Test
	void testExpertsOpinionsDontSaveABasicOpinion() {
		sample.changeSampleState(statePartial);
		when(opinion.getIsExpertOpinion()).thenReturn(false);
		sample.addOpinion(opinion);
		assertEquals(0,sample.expertsOpinions().size());
	}
	
	@Test
	void testExpertsOpinionsSaveAExpertOpinion() {
		sample.changeSampleState(statePartial);
		when(opinion.getIsExpertOpinion()).thenReturn(true);
		sample.addOpinion(opinion);
		assertEquals(2,sample.expertsOpinions().size());
	} 
	
	@Test
	void testIsValidated() {
		sample.isValidated();
		verify(stateUnverified,times(1)).isValidated();
	}
	
	@Test
	void testIsValidatedInStateUnverified() {
		when(stateUnverified.isValidated()).thenReturn(false);
		sample.isValidated();
		verify(stateUnverified,times(1)).isValidated();
		verify(system,times(0)).Notify(sample);
	}
	
	@Test
	void testIsValidatedInStateVerified() {
		when(stateVerified.isValidated()).thenReturn(true);
		sample.changeSampleState(stateVerified);
		verify(stateVerified,times(1)).isValidated();
		assertTrue(sample.getState().isValidated());
		verify(sample.getSystem(),times(1)).Notify(sample);
	} 

	@Test
	void testChangeSampleStateWithUnvalidatedSample() {
		when(stateUnverified.isValidated()).thenReturn(false);
		sample.changeSampleState(stateUnverified);
		verify(system, times(0)).Notify(sample);
	}
	
	@Test
	void equals() {
		assertEquals(new Sample(specie,picture,location,user,opinion,stateUnverified,system),new Sample(null,null,null,null,null,null,null));
		assertNotEquals(sample,opinion);
	}
	
}
