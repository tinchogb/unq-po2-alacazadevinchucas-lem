package model;

import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.time.LocalDate;
import java.util.ArrayList;

public class LastVerificationDateTest {

	private LastVerificationDate	lastVerifDate;
	private LocalDate		date;
	private List<Sample> 	samples = new ArrayList<Sample>();

	private Opinion         opinion1;
	private Opinion         opinion2;
	private Opinion         opinion3;
	private LocalDate		date1;
	private LocalDate		date2;
	private Sample			sample1;
	private Sample			sample2;
	private Sample			sample3;
	
	
	@BeforeEach
	public void setUp() {
		// DOC (Depended-On-Component): nuestros doubles
		this.opinion1   = mock(Opinion.class);
		this.opinion2   = mock(Opinion.class);
		this.opinion3   = mock(Opinion.class);
		this.date 		= LocalDate.now();
		this.date1		= date.plusDays(1);
		this.date2		= date1.plusDays(1);
		this.sample1 	= mock(Sample.class);
		this.sample2 	= mock(Sample.class);
		this.sample3 	= mock(Sample.class);
		
		this.samples.add(sample1);
		this.samples.add(sample2);
		this.samples.add(sample3);
		when(this.sample1.getLastOpinion()).thenReturn(opinion1);
		when(opinion1.getDate()).thenReturn(date);
		when(this.sample2.getLastOpinion()).thenReturn(opinion2);
		when(opinion2.getDate()).thenReturn(date1);
		when(this.sample3.getLastOpinion()).thenReturn(opinion3);
		when(opinion3.getDate()).thenReturn(date2);

		// SUT (System Under Test): objeto a testear
		this.lastVerifDate = new LastVerificationDate(date, this.samples);
	}

	@Test
	void testConstructor() {
		assertNotNull(this.lastVerifDate);
	}

	@Test
	public void testFilter() {
		List<Sample> expSamples = new ArrayList<Sample>();
		expSamples.add(sample2);
		expSamples.add(sample3);
		assertEquals(expSamples, this.lastVerifDate.filter());
	}
}
