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
public class AdminTests {

	@Test
	public void testSetAdminId() throws NoSuchFieldException, IllegalAccessException  {
		
        final Admin admin = new Admin();
        admin.setAdminId(1);
        final Field field = admin.getClass().getDeclaredField("adminId");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(admin), 1);
        //assertEquals("Setter failed", field.get(admin), 01);
	}
	
	@Test
	public void testGetAdminId() throws NoSuchFieldException, IllegalAccessException  {
		
        final Admin admin = new Admin();
        final Field field = admin.getClass().getDeclaredField("adminId");
        field.setAccessible(true);
        field.set(admin, 1);
        assertEquals("Getter passed",admin.getAdminId(), 1);
        //assertEquals("Getter failed",admin.getAdminId(), 01);
	}
}
