package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OtherInsectTest {

	public OtherInsect other;
	public OtherInsect other1;

	@BeforeEach
	public void setUp() {
		other = other.ChincheFoliada;
		other1 = other.PhtiaChinche;
		
	}
	
	@Test
	void testInstanceOfEnumAreEquals() {
		OtherInsect other2 = OtherInsect.ChincheFoliada;
		assertEquals(other2,other); 
	}
	
	@Test
	void testDiferentsInstanceOfEnumAreNotEquals() {
		assertNotEquals(other1,other);
	}
}
