package tpIntegrador;

import static org.junit.Assert.assertFalse;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.mockito.Mockito.*;

import tpIntegrador.Sample;

class SampleTest{
	
	public Sample sample;
	public SpecieVinchuca specie;
	public Picture picture;
	public Location location;
	public IdUsuario idCreator;
	public ArrayList<Opinion> opinions;
	public SampleState state;
	public Opinion opinion;
	
	@BeforeEach
	public void setUp() {
		specie    = mock(SpecieVinchuca.class);
		picture   = mock(Picture.class);
		location  = mock(Location.class);
		idCreator = mock(IdUsuario.class);
		opinion   = mock(Opinion.class);
		state     = new UnverifiedSample();
		sample    = new Sample(specie,picture,location,idCreator,Arrays.asList(opinion),state); 
	}
	
	@Test
	void testConstructor() {
		assertFalse(sample==null);
	}

}
