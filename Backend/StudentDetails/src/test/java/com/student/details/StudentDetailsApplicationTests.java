package com.student.details;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StudentDetailsApplicationTests {

	@Test
	void contextLoads() {
		for (int i = 0; i < 5; i++) {
			System.out.println("Count: " + i);
		}
	}

}
