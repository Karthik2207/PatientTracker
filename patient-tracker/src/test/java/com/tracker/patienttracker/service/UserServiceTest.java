package com.tracker.patienttracker.service;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import static org.junit.Assert.assertNotNull;
import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(OrderAnnotation.class)
public class UserServiceTest {
	@Autowired
	UserService userService;
	
	@Test
	@Order(1)
	void testGetPendingApprovalRequest()
	{
		assertNotNull(userService.userApprovalPendingList());
	}
}
