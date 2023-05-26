package tpIntegrador;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

import java.util.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

class SampleTest{
	
	public Sample sample;
	public SpecieVinchuca specie;
	public Picture picture;
	public Location location;
	public IdUsuario idCreator;
	public ArrayList<Opinion> opinions;
	public SampleState stateUnverified;
	public SampleState stateVerified;
	public SampleState statePartial;
	public Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		specie          = mock(SpecieVinchuca.class);
		picture         = mock(Picture.class);
		location        = mock(Location.class);
		idCreator       = mock(IdUsuario.class);
		opinion         = mock(Opinion.class);
		stateUnverified = new UnverifiedSample();
		stateVerified   = new VerifiedSample();
		statePartial    = new VerifiedPartialSample();
		
		sample          = new Sample(specie,picture,location,idCreator,opinion,stateUnverified); 
	}
	
	@Test
	void testConstructor() {
		assertFalse(sample==null);
	}
	
	@Test
	void testGetOpinions() {
		sample.getOpinions();
		verify(opinion, times(1));
	}
	@Test
	void testGetPicture() {
		sample.getPicture();
		verify(picture, times(1));
	}
	@Test
	void testGetSpecie() {
		sample.getSpecie();
		verify(specie, times(1));
	}
	@Test
	void testGetLocation() {
		sample.getLocation();
		verify(location, times(1));
	}
	@Test
	void testGetIdCreator() {
		sample.getIdCreator();
		verify(idCreator, times(1));
	}
	@Test
	void testSaveOpinion() {
		sample.saveOpinion(opinion);
		verify(stateUnverified, times(1));
	}
	
	@Test
	void testAddOpinion() {
		sample.addOpinion(opinion);
		assertEquals(2, sample.getOpinions().size());	
	}
	@Test
	void testChangeSampleState() {
			
	}

}
