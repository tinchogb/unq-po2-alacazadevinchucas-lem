package model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class VerificationLevelTest {

	private VerificationLevel	verificationLevel;
	private List<Sample> 	samples = new ArrayList<Sample>();

	private SampleState		state1;
	private SampleState		state2;
	private SampleState		state3;
	private Sample			sample1;
	private Sample			sample2;
	private Sample			sample3;
	
	
	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles
		this.state1   = mock(SampleState.class);
		this.state2   = mock(SampleState.class);
		this.state3   = mock(SampleState.class);
		this.sample1 	= mock(Sample.class);
		this.sample2 	= mock(Sample.class);
		this.sample3 	= mock(Sample.class);
		
		this.samples.add(sample1);
		this.samples.add(sample2);
		this.samples.add(sample3);
		when(this.sample1.getState()).thenReturn(state1);
		when(this.sample2.getState()).thenReturn(state2);
		when(this.sample3.getState()).thenReturn(state3);

		// SUT (System Under Test): objeto a testear
		this.verificationLevel = new VerificationLevel(this.state1, this.samples);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.verificationLevel);
	}

	@Test
	public void testFilter() {
		List<Sample> expSamples = new ArrayList<Sample>();
		expSamples.add(sample1);
		assertEquals(expSamples, this.verificationLevel.filter());
	}
}
