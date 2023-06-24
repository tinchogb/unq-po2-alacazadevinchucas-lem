package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class OrganizationTypeTest {
	
	public OrganizationType educativa;
	public OrganizationType salud;
	public OrganizationType cultura;
	public OrganizationType asistencia;
	
	@BeforeEach
	public void setUp() {
		educativa    = OrganizationType.Educativa;
		salud        =OrganizationType.Salud;
		cultura     = OrganizationType.Cultura;
		asistencia  = OrganizationType.Asistencia;
	}
	
	@Test
	void testInstanceOfEnumAreEquals() {
		OrganizationType educativa2= OrganizationType.Educativa;
		assertEquals(educativa,educativa);
		assertEquals(educativa,educativa2);
	}
	
	@Test
	void testDiferentsInstanceOfEnumAreNotEquals() {
		assertNotEquals(cultura,asistencia);
		assertNotEquals(educativa,salud);
	}

}
