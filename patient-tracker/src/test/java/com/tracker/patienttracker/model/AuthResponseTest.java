package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class AuthResponseTest {

	@Test
	void testUid()
	{
		AuthResponse auth=new AuthResponse();
		auth.setUid("123");
		assertEquals(auth.getUid(),"123");
	}
	@Test
	void testName()
	{
		AuthResponse auth=new AuthResponse();
		auth.setName("Jon");
		assertEquals(auth.getName(),"Jon");
	}
	@Test
	void testRole()
	{
		AuthResponse auth=new AuthResponse();
		auth.setRole("ROLE_PATIENT");
		assertEquals(auth.getRole(),"ROLE_PATIENT");
	}
	@Test
	void testIsValid()
	{
		AuthResponse auth=new AuthResponse();
		auth.setValid(false);
		assertEquals(auth.isValid(),false);
	}
	@Test
	void testAllArgs()
	{
		AuthResponse auth=new AuthResponse("s", null, false, null);
		String str=auth.toString();
		assertEquals(auth.toString(),str);
	}
	
}
