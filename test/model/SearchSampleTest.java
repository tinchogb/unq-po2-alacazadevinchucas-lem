package model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;


public class SearchSampleTest {

	private SearchSample	searchSample;
	
	private System 			system;

	private LocalDate 		date1;
	private LocalDate 		date2;
	private OpinionType 	aType;
	private SampleState 	level1;
	private Sample			sample1;
	private Sample			sample2;
	private Sample			sample3;
	private List<Sample> 	samples1 = new ArrayList<Sample>();
	private List<Sample> 	samples2 = new ArrayList<Sample>();

	@BeforeEach
	public void setUp() {
	// DOC (Depended-On-Component): nuestros doubles
		this.system = mock(System.class);
		
		this.date1 	= LocalDate.now(); 
		this.date2	= date1.plusDays(1);
		this.aType	= mock(OpinionType.class);
		this.sample1 	= mock(Sample.class);
		this.sample2 	= mock(Sample.class);
		this.sample3 	= mock(Sample.class);
		this.samples1 	= (List<Sample>) mock(List.class);
		this.samples2 	= (List<Sample>) mock(List.class);
		this.level1		= mock(SampleState.class);

		// SUT (System Under Test): objeto a testear
		this.searchSample = new SearchSample();
	}

	@Test
	void testConstructor() {
		assertNotNull(this.searchSample);
	}

//	@Test
//	public void testCreationDate() {
//		when(this.sample1.getCreationDate()).thenReturn(date1);
//		this.samples1 = spy(this.samples1);
//		this.samples1.add(sample1);
//		System systemN = spy(this.system);
//		SearchSample searchSampleNew = new SearchSample(systemN);
//		systemN.add(sample1);
//		//when(systemN.getSamples()).thenReturn(this.samples1);
//		assertEquals(this.samples1, searchSampleNew.creationDate(date1));
//		verify(systemN, times(1)).getSamples();
//	}

	@Test
	public void testInsectType() {
		// Idem a 'testCreationDate' pero cambiando los mocks
	}

	@Test
	public void testVerificationLevel() {
		// Idem a 'testCreationDate' pero cambiando los mocks
	}
	
	@Test
	public void testAnd() {
		// Hay que usar HashSet para los tests?
	}

	@Test
	public void testOr() {
		//	Hay que usar HashSet para los tests?
	}
}
