package model;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrTest {

	private Or or;
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
		or = new Or(query,query2);
	}
	@Test
	void testConstructor() {
		assertFalse(or==null); 
	}
	
	@Test
	void testFilterWhenTheSampleIsInBothQuerys() {
		samples.add(sample);
		when(query.filter()).thenReturn(samples);
		when(query2.filter()).thenReturn(samples);
		assertEquals(samples, or.filter());
	}
	
	@Test
	void testFilterWhenTheSampleIsntInBothQuerys() {
		samples.add(sample);
		when(query.filter()).thenReturn(samples);
		when(query2.filter()).thenReturn(empty);
		assertEquals(samples, or.filter());
	}
	
	@Test
	void testFilterWithNoSample() {
		samples.add(sample);
		when(query.filter()).thenReturn(empty);
		when(query2.filter()).thenReturn(empty);
		assertEquals(empty, or.filter());
	}

}

