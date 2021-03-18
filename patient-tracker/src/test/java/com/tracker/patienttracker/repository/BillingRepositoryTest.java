package com.tracker.patienttracker.repository;

import static org.junit.Assert.assertNull;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
@TestMethodOrder(OrderAnnotation.class)
class BillingRepositoryTest {
	@Autowired
	BillingRepository billingRepository;
	
	@Test
	@Order(1)
	void testGetBillingByPatientId() {
		assertNull(billingRepository.getBillingByPatientId(2000));
	}
}
