package model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Opinion;
import model.OpinionType;
import model.Sample;
import model.Undefined;
import model.UnverifiedSample;
import model.VerifiedPartialSample;

import static org.mockito.Mockito.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class VerifiedPartialSampleTest {

	public UnverifiedSample unverified;
	public Opinion opinion;
	public Opinion opinion1;
	public Sample sample;
	public VerifiedPartialSample partial;
	public OpinionType type;
	public ArrayList<Opinion> opinions;
	public List<OpinionType> types;
	public OpinionType type1;
	
	@BeforeEach
	public void setUp() {
		partial    = new VerifiedPartialSample();
		unverified = mock(UnverifiedSample.class);
		opinion    = mock(Opinion.class);
		opinion1   = mock(Opinion.class);
		sample     = mock(Sample.class);
		type       = mock(OpinionType.class);
		type1      = mock(OpinionType.class);
        types      = Arrays.asList(type,type1);
		opinions   = new ArrayList();
       
	}
	
	
	@Test
	void testSaveOpinionWithBasicOpinionThorwsException() {
		when(opinion.getIsExpertOpinion()).thenReturn(false);
		Exception exception = assertThrows(RuntimeException.class, 
				() -> {
					partial.saveOpinion(opinion,sample);
			    });		
		assertEquals("El usuario no está habilitado a comentar en esta muestra", exception.getMessage());
		verify(sample,never()).addOpinion(opinion);
	}
	
	@Test
	void testSaveOpinionWithExpertOpinion() {
		when(opinion.getIsExpertOpinion()).thenReturn(true);
		partial.saveOpinion(opinion, sample);
		verify(sample,times(1)).addOpinion(opinion);
	}
	
	@Test
	void testVerifyStatusChange() {
		when(sample.calculateResult(sample.expertsOpinions())).thenReturn(type);
		when(opinion.getType()).thenReturn(type);
		partial.verifyStatusChange(opinion,sample);
		verify(sample,times(1)).changeSampleState(partial.getSiguiente());
	}
	
	@Test
	void testVerifyStatusChangeWithCurrentResultUndefined() {
		opinions.add(opinion);
		opinions.add(opinion1);
		when(sample.getOpinions()).thenReturn(opinions);
		when(sample.calculateResult(sample.expertsOpinions())).thenReturn(new Undefined());
		when(opinion.getIsExpertOpinion()).thenReturn(true);
		when(opinion1.getIsExpertOpinion()).thenReturn(true);
		when(opinion.getType()).thenReturn(type);
		when(opinion1.getType()).thenReturn(type1);
		partial.verifyStatusChange(opinion,sample);
		verify(sample,times(1)).changeSampleState(partial.nextState);
	}
	
	@Test
	void testGetCurrentResult() {
		partial.getCurrrentResult(sample);
		verify(sample,times(1)).calculateResult(sample.expertsOpinions());	
	}

}
