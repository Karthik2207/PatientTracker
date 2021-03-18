package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
/*
 * @author: Burre Chandu (883619)
 * 
 */
@SpringBootTest
public class ClerkTests {

	@Test
	public void testSetClerkId() throws NoSuchFieldException, IllegalAccessException  {
		
        final Clerk clerk = new Clerk();
        clerk.setClerkId(123);
        final Field field = clerk.getClass().getDeclaredField("clerkId");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(clerk), 123);
        //assertEquals("Setter failed", field.get(clerk), 124);
	}
	
	@Test
	public void testGetClerkId() throws NoSuchFieldException, IllegalAccessException  {
		
        final Clerk clerk = new Clerk();
        final Field field = clerk.getClass().getDeclaredField("clerkId");
        field.setAccessible(true);
        field.set(clerk, 123);
        assertEquals("Getter passed",clerk.getClerkId(), 123);
        //assertEquals("Getter failed",clerk.getClerkId(), 124);
	}
}
