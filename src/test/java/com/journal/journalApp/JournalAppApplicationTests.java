package com.journal.journalApp;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class JournalAppApplicationTests {

	

	@Test
	void contextLoads() {
		assertEquals(4, 2+2);
		assertEquals(6, 10-4);
	}

}
