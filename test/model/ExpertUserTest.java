package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExpertUserTest {
	
	private ExpertUser expert;
	
	@BeforeEach
	public void setUp() {
		expert = new ExpertUser();
	}

	@Test
	void testIsExpert() {
		assertTrue(expert.isExpert());
	}
	
	@Test
	void testEquals() {
		assertEquals(new ExpertUser(), new ExpertUser());
	}

}
