package model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AndTest {

	private And and;
	private IQuery query;
	private IQuery query2;
	private Sample sample;
	private List<Sample> samples;
	private List<Sample> empty;
	
	@BeforeEach
	public void setUp() {
		query  = mock(IQuery.class);
		query2  = mock(IQuery.class);
		sample = mock(Sample.class);
		samples = new ArrayList<Sample>();
		empty  = new ArrayList<Sample>();
		and = new And(query,query2);
	}
	@Test
	void testConstructor() {
		assertFalse(and==null); 
	}
	
	@Test
	void testFilterWhenTheSampleIsInBothQuerys() {
		samples.add(sample);
		when(query.filter()).thenReturn(samples);
		when(query2.filter()).thenReturn(samples);
		assertEquals(samples, and.filter());
	}
	
	@Test
	void testFilterWhenTheSampleIsntInBothQuerys() {
		samples.add(sample);
		when(query.filter()).thenReturn(samples);
		when(query2.filter()).thenReturn(empty);
		assertNotEquals(samples, and.filter());
	}

}
