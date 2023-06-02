package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class testOther {
	
	public Other other;
	public Other other1;

	@BeforeEach
	public void setUp() {
		other = other.Ninguna;
		other1 = other.ImagenPocoClara;
		
	}
	
	@Test
	void testInstanceOfEnumAreEquals() {
		Other other2 = Other.Ninguna;
		assertEquals(other2,other); 
	}
	
	@Test
	void testDiferentsInstanceOfEnumAreNotEquals() {
		assertNotEquals(other1,other);
	}
	
}
