package model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class CreationDateTest {

	private CreationDate	creationDate;
	private LocalDate		date;
	private List<Sample> 	samples = new ArrayList<Sample>();

	private LocalDate		date1;
	private LocalDate		date2;
	private Sample			sample1;
	private Sample			sample2;
	private Sample			sample3;
	
	
	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles
		this.date 		= LocalDate.now(); 
		this.date1		= date.plusDays(1);
		this.date2		= date1.plusDays(1);
		this.sample1 	= mock(Sample.class);
		this.sample2 	= mock(Sample.class);
		this.sample3 	= mock(Sample.class);
		
		this.samples.add(sample1);
		this.samples.add(sample2);
		this.samples.add(sample3);
		when(this.sample2.getCreationDate()).thenReturn(date);
		when(this.sample1.getCreationDate()).thenReturn(date1);
		when(this.sample3.getCreationDate()).thenReturn(date2);

		// SUT (System Under Test): objeto a testear
		this.creationDate = new CreationDate(date, this.samples);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.creationDate);
	}

	@Test
	public void testFilter() {
		List<Sample> expSamples = new ArrayList<Sample>();
		expSamples.add(sample2);
		assertEquals(expSamples, this.creationDate.filter());
	}
}
