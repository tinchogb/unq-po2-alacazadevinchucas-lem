package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import model.Opinion;
import model.OpinionType;
import model.Sample;
import model.UnverifiedSample;
import model.VerifiedPartialSample;

import static org.mockito.Mockito.*;

import java.util.List;
import java.util.ArrayList;

class UnverifiedSampleTest {
	
	public UnverifiedSample unverified;
	public Opinion opinion;
	public Sample sample;
	public VerifiedPartialSample partial;
	public OpinionType type1;
	public OpinionType type2;
	public OpinionType type3;
	public List<OpinionType> opinions = new ArrayList<> ();
	public List<Opinion> opinionsSample = new ArrayList<> ();
	
	@BeforeEach
	public void setUp() {
		unverified = new UnverifiedSample();
		opinion    = mock(Opinion.class);
		sample     = mock(Sample.class);
		partial    = mock(VerifiedPartialSample.class);
		type1      = mock(OpinionType.class);
		type2      = mock(OpinionType.class);
		type3      = mock(OpinionType.class);
		opinions.add(type1);
		opinionsSample.add(opinion);
	}
	
	@Test
	void testSaveOpinion() {
		when(opinion.getIsExpertOpinion()).thenReturn(false);
		
		unverified.saveOpinion(opinion, sample);
		verify(sample, times(1)).addOpinion(opinion);
	}
	
	@Test
	void testSaveOpinionAndChangeSampleState() {
		when(opinion.getIsExpertOpinion()).thenReturn(true);
		
		unverified.saveOpinion(opinion, sample);
		verify(sample, times(1)).addOpinion(opinion);
		verify(sample, times(1)).changeSampleState(unverified.getNextState());
	}
	
	@Test
	void testGetSiguiente() {
		assertEquals(unverified.nextState,unverified.getNextState());
	}
	
	@Test
	void testGetCurrentResult() {
		unverified.getCurrrentResult(sample);
		when(sample.calculateResult(null)).thenReturn(null);
		assertEquals(null, sample.calculateResult(null));
	}
	

}
