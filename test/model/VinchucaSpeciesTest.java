package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class VinchucaSpeciesTest {

	public VinchucaSpecies vinchuca;
	public VinchucaSpecies vinchuca1;
	public VinchucaSpecies vinchuca2;

	@BeforeEach
	public void setUp() {
		vinchuca  = VinchucaSpecies.Infestans;
		vinchuca1 = VinchucaSpecies.Sordida;
		vinchuca2 = VinchucaSpecies.Gusayana;
		
	}
	
	@Test
	void testInstanceOfEnumAreEquals() {
		VinchucaSpecies vinchuca3 = VinchucaSpecies.Infestans;
		assertEquals(vinchuca3,vinchuca); 
	}
	
	@Test
	void testDiferentsInstanceOfEnumAreNotEquals() {
		assertNotEquals(vinchuca1,vinchuca2);
	}
}
