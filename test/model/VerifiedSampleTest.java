package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Opinion;
import model.Sample;
import model.UnverifiedSample;
import model.VerifiedSample;

class VerifiedSampleTest {

	public UnverifiedSample unverified;
	public Opinion opinion;
	public Sample sample;
	public VerifiedSample verified;
	
	@BeforeEach
	public void setUp() {
		verified = new VerifiedSample();
		opinion  = mock(Opinion.class);
		sample   = mock(Sample.class);
	}
	
	@Test
	void testSaveOpinion() {
		Exception exception = assertThrows(RuntimeException.class, 
				() -> {
					verified.saveOpinion(opinion,sample);
			    });	
		assertEquals("No se puede opinar, la muestra ya está verificada", exception.getMessage());
	}
	
	
	@Test
	void testGetCurrentResult() {
		verified.getCurrrentResult(sample);
		verify(sample,times(1)).calculateResult(sample.expertsOpinions());
		
	}
	
	@Test
	void isValidated() {
		assertTrue(verified.isValidated());
		
	}
}
