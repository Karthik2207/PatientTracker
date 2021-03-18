package com.tracker.patienttracker.model;

import static org.junit.Assert.assertEquals;

import java.lang.reflect.Field;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TestTesting {
	
	@Test
	public void setTestIdTest() throws NoSuchFieldException, IllegalAccessException {
		final com.tracker.patienttracker.model.Test test = new com.tracker.patienttracker.model.Test();
		test.setTestId(1);
		final Field field = test.getClass().getDeclaredField("testId");
		field.setAccessible(true);
		assertEquals("Setter Pass Test", field.get(test), 1);
	}
	
	@Test
	public void getTestIdTest() throws NoSuchFieldException, IllegalAccessException {
		final com.tracker.patienttracker.model.Test test = new com.tracker.patienttracker.model.Test();
		final Field field = test.getClass().getDeclaredField("testId");
        field.setAccessible(true);
        field.set(test, 1);
        assertEquals("Getter Pass Test",test.getTestId(), 1);
	}
	
	@Test
	public void setTestNameTest() throws NoSuchFieldException, IllegalAccessException {
		final com.tracker.patienttracker.model.Test test = new com.tracker.patienttracker.model.Test();
		test.setTestName("BloodTest");
		final Field field = test.getClass().getDeclaredField("testName");
		field.setAccessible(true);
		assertEquals("Setter Pass Test", field.get(test), "BloodTest");
	}
	
	@Test
	public void getTestNameTest() throws NoSuchFieldException, IllegalAccessException {
		final com.tracker.patienttracker.model.Test test = new com.tracker.patienttracker.model.Test();
		final Field field = test.getClass().getDeclaredField("testName");
        field.setAccessible(true);
        field.set(test, "BloodTest");
        assertEquals("Getter Pass Test",test.getTestName(), "BloodTest");
	}
	
	@Test
	public void setTestCostTest() throws NoSuchFieldException, IllegalAccessException {
		final com.tracker.patienttracker.model.Test test = new com.tracker.patienttracker.model.Test();
		test.setTestCost(50);
		final Field field = test.getClass().getDeclaredField("testCost");
		field.setAccessible(true);
		assertEquals("Setter Pass Test", field.get(test), 50.0);
	}
	
	@Test
	public void getTestCostTest() throws NoSuchFieldException, IllegalAccessException {
		final com.tracker.patienttracker.model.Test test = new com.tracker.patienttracker.model.Test();
		final Field field = test.getClass().getDeclaredField("testCost");
        field.setAccessible(true);
        field.set(test, 50);
        assertEquals("Getter Pass Test",test.getTestCost(), 50.0, 0.001);
	}
	
	@Test
	public void setBaselineValuesTest() throws NoSuchFieldException, IllegalAccessException {
		final com.tracker.patienttracker.model.Test test = new com.tracker.patienttracker.model.Test();
		test.setBaselineValues("120/80");
		final Field field = test.getClass().getDeclaredField("baselineValues");
		field.setAccessible(true);
		assertEquals("Setter Pass Test", field.get(test), "120/80");
	}
	
	@Test
	public void getBaselineValuesTest() throws NoSuchFieldException, IllegalAccessException {
		final com.tracker.patienttracker.model.Test test = new com.tracker.patienttracker.model.Test();
		final Field field = test.getClass().getDeclaredField("baselineValues");
        field.setAccessible(true);
        field.set(test, "120/80");
        assertEquals("Getter Pass Test",test.getBaselineValues(), "120/80");
	}

}
