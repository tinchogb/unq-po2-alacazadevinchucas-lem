package model;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;


import java.lang.runtime.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.BasicUser;
import model.ExpertUser;
import model.Location;
import model.Opinion;
import model.OpinionType;
import model.Picture;
import model.Sample;
import model.SampleState;
import model.Undefined;
import model.UnverifiedSample;
import model.VerifiedPartialSample;
import model.VerifiedSample;

import static org.mockito.Mockito.*;

class SampleTest{ 
	
	public Sample sample;
	public VinchucaSpecies specie;
	public Picture picture;
	public Location location;
	public int idCreator;
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
	public Undefined undefined;
	
	@BeforeEach
	public void setUp() {
		specie          = mock(VinchucaSpecies.class);
		picture         = mock(Picture.class);
		location        = mock(Location.class);
		idCreator       = 1;
		opinion         = mock(Opinion.class);
		opinion1        = mock(Opinion.class);
		stateUnverified = mock(UnverifiedSample.class);
		stateVerified   = mock(VerifiedSample.class);
		statePartial    = mock(VerifiedPartialSample.class);
		type            = mock(OpinionType.class);
		type1           = mock(OpinionType.class);
		undefined       = mock(Undefined.class);
		opinionsType.add(type);
		
		
		sample          = new Sample(specie,picture,location,idCreator,opinion,stateUnverified); 
	}
	
	@Test
	void testConstructor() {
		assertFalse(sample==null);
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
	void testGetIdCreator() {
		sample.getIdCreator();
		assertEquals(1,idCreator);
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
		verify(stateUnverified,times(1)).getCurrrentResult(sample);
		
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
		assertEquals(sample.throwUndefined(),sample.calculateResult(opinionsType));
	}
	
	@Test
	void testBasicOpinionsWithOneOpinion() {
		assertEquals(1,sample.basicOpinions().size());
	}
	
	@Test
	void testBasicOpinionsTwoOneOpinion() {
		sample.opinions.add(opinion);
		assertEquals(2,sample.basicOpinions().size());
	}
	
	@Test
	void testExpertsOpinionsDontSaveABasicOpinion() {
		sample.changeSampleState(statePartial);
		when(opinion.mustChangeState()).thenReturn(false);
		sample.addOpinion(opinion);
		assertEquals(0,sample.expertsOpinions().size());
	}
	
	@Test
	void testExpertsOpinionsSaveAExpertOpinion() {
		sample.changeSampleState(statePartial);
		when(opinion.mustChangeState()).thenReturn(true);
		sample.addOpinion(opinion);
		assertEquals(2,sample.expertsOpinions().size());
	} 
	
}
