package model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class InsectTypeTest {

	private InsectType		insectType;
	private List<Sample> 	samples = new ArrayList<Sample>();

	private OpinionType     opinionType1;
	private OpinionType     opinionType2;
	private OpinionType     opinionType3;
	private Sample			sample1;
	private Sample			sample2;
	private Sample			sample3;
	
	
	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles
		this.opinionType1   = mock(OpinionType.class);
		this.opinionType2   = mock(OpinionType.class);
		this.opinionType3   = mock(OpinionType.class);
		this.sample1 	= mock(Sample.class);
		this.sample2 	= mock(Sample.class);
		this.sample3 	= mock(Sample.class);
		
		this.samples.add(sample1);
		this.samples.add(sample2);
		this.samples.add(sample3);
		when(this.sample1.getCurrentResult()).thenReturn(opinionType1);
		when(this.sample2.getCurrentResult()).thenReturn(opinionType2);
		when(this.sample3.getCurrentResult()).thenReturn(opinionType3);

		// SUT (System Under Test): objeto a testear
		this.insectType = new InsectType(this.opinionType3, this.samples);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.insectType);
	}

	@Test
	public void testFilter() {
		List<Sample> expSamples = new ArrayList<Sample>();
		expSamples.add(sample3);
		assertEquals(expSamples, this.insectType.filter());
	}
}
