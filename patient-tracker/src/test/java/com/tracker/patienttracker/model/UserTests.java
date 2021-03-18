package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.lang.reflect.Field;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.tracker.patienttracker.util.DateUtil;
/*
 * @author: Burre Chandu (883619)
 * 
 */
@SpringBootTest
public class UserTests {
	
	@Test
	public void testSetUserId() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();

        user.setUserId(777);
        final Field field = user.getClass().getDeclaredField("userId");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(user), 777);
        assertNotEquals("Setter failed", field.get(user), 771);
	}
	
	@Test
	public void testGetUserId() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("userId");
        field.setAccessible(true);
        field.set(user, 777);
        assertEquals("Getter passed",user.getUserId(), 777);
        assertNotEquals("Getter failed",user.getUserId(), 111);
	}

	@Test
	public void testSetFirstName() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        user.setFirstName("John");
        final Field field = user.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(user), "John");
        assertNotEquals("Setter failed", field.get(user), "JOhn");
	}
	
	@Test
	public void testgetFirstName() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("firstName");
        field.setAccessible(true);
        field.set(user, "John");
        assertEquals("Getter passed",user.getFirstName(), "John");
        assertNotEquals("Getter failed",user.getFirstName(), "JOhn");
	}
	
	@Test
	public void testSetLastName() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        user.setLastName("Cena");
        final Field field = user.getClass().getDeclaredField("lastName");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(user), "Cena");
        assertNotEquals("Setter failed", field.get(user), "cena");
	}
	
	@Test
	public void testGetLastName() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("lastName");
        field.setAccessible(true);
        field.set(user, "Cena");
        assertEquals("Getter passed",user.getLastName(), "Cena");
        assertNotEquals("Getter failed",user.getLastName(), "cena");
	}
	
	@Test
	public void testSetGender() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        user.setGender("male");
        final Field field = user.getClass().getDeclaredField("gender");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(user), "male");
        assertNotEquals("Setter failed", field.get(user), "female");
	}
	
	@Test
	public void testGetGender() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("gender");
        field.setAccessible(true);
        field.set(user, "male");
        assertEquals("Getter passed",user.getGender(), "male");
        assertNotEquals("Getter failed",user.getGender(), "other");
	}
	
	@Test
	public void testSetDateOfBirth() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        Date date1 = DateUtil.convertToDate("04/12/1998");
        Date date2 = DateUtil.convertToDate("12/04/1998");
        user.setDateOfBirth(date1);
        final Field field = user.getClass().getDeclaredField("dateOfBirth");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(user), date1);
        assertNotEquals("Setter failed", field.get(user), date2);
	}
	
	@Test
	public void testGetDateOfBirth() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        Date date1 = DateUtil.convertToDate("04/12/1998");
        Date date2 = DateUtil.convertToDate("12/04/1998");
        final Field field = user.getClass().getDeclaredField("dateOfBirth");
        field.setAccessible(true);
        field.set(user, date1);
        assertEquals("Getter passed",user.getDateOfBirth(), date1);
        assertNotEquals("Getter failed",user.getDateOfBirth(), date2);
	}
	
	@Test
	public void testSetContactNo() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        user.setContactNo("919381812345");
        final Field field = user.getClass().getDeclaredField("contactNo");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(user), "919381812345");
        assertNotEquals("Setter failed", field.get(user), "929381812345");
	}
	
	@Test
	public void testGetContactNo() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("contactNo");
        field.setAccessible(true);
        field.set(user, "919381812345");
        assertEquals("Getter passed",user.getContactNo(), "919381812345");
        assertNotEquals("Getter failed",user.getContactNo(), "91938181234");
	}
	
	@Test
	public void testSetPassword() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        user.setPassword("qazwsxedc");
        final Field field = user.getClass().getDeclaredField("password");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(user), "qazwsxedc");
        assertNotEquals("Setter failed", field.get(user), "feqazwsxedc");
	}
	
	@Test
	public void testGetPassword() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("password");
        field.setAccessible(true);
        field.set(user, "qazwsxedc");
        assertEquals("Getter passed",user.getPassword(), "qazwsxedc");
        assertNotEquals("Getter failed",user.getPassword(), "qzawsxedc");
	}
	
	@Test
	public void testSetAddress() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        user.setAddress("flat no 201, 4 th street, Victoria Colony, East London");
        final Field field = user.getClass().getDeclaredField("address");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(user), "flat no 201, 4 th street, Victoria Colony, East London");
        assertNotEquals("Setter failed", field.get(user), "flat no 201, 4 th street, Victoria Colony, West London");
	}
	
	@Test
	public void testGetAddress() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("address");
        field.setAccessible(true);
        field.set(user, "flat no 201, 4 th street, Victoria Colony, East London");
        assertEquals("Getter passed",user.getAddress(), "flat no 201, 4 th street, Victoria Colony, East London");
        assertNotEquals("Getter failed",user.getAddress(), "flat no 202, 4 th street, Victoria Colony, East London");
	}
	
	@Test
	public void testSetRole() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        user.setRole("ROLE_ADMIN");
        final Field field = user.getClass().getDeclaredField("role");
        field.setAccessible(true);
        assertEquals("Setter passed", field.get(user), "ROLE_ADMIN");
        assertNotEquals("Setter failed", field.get(user), "ROLE_PATIENT");
	}
	
	@Test
	public void testGetRole() throws NoSuchFieldException, IllegalAccessException  {
		
        final User user = new User();
        final Field field = user.getClass().getDeclaredField("role");
        field.setAccessible(true);
        field.set(user, "ROLE_ADMIN");
        assertEquals("Getter passed",user.getRole(), "ROLE_ADMIN");
        assertNotEquals("Getter failed",user.getRole(), "ROLE_DOCTOR");
	}
}
