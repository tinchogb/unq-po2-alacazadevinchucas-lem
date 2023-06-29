package model;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BasicUserTest {
	
	private BasicUser basic;
	
	@BeforeEach
	public void setUp() {
		basic = new BasicUser();
	}
	

	@Test
	void testIsExpert() {
		assertFalse(basic.isExpert());
	}
	
	@Test
	void testEquals() {
		assertEquals(new BasicUser(), new BasicUser());
	}

}
