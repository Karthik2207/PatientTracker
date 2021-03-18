package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ErrorResponseTest {

	@Test
	void testStatus()
	{
		ErrorResponse error=new ErrorResponse();
		error.setStatus("Done");
		assertEquals(error.getStatus(),"Done");
	}
	@Test
	void testTimeStamp()
	{
		ErrorResponse error=new ErrorResponse();
		error.setTimeStamp(null);
		assertEquals(error.getTimeStamp(),null);
	}
	@Test
	void testMessage()
	{
		ErrorResponse error=new ErrorResponse();
		error.setMessage("Anything");
		assertEquals(error.getMessage(),"Anything");
	}
	@Test
	void testAllArgs()
	{
		ErrorResponse error=new ErrorResponse("done", null, null);
		String str=error.toString();
		assertEquals(error.toString(),str);
	}
}
