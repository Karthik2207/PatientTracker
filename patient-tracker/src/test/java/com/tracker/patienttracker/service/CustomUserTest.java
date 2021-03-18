package com.tracker.patienttracker.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.tracker.patienttracker.exception.UnauthorizedException;
import com.tracker.patienttracker.model.AuthResponse;
import com.tracker.patienttracker.model.User;
import com.tracker.patienttracker.model.UserData;
import com.tracker.patienttracker.repository.UserRepository;
import com.tracker.patienttracker.security.JwtUtil;

@ExtendWith(MockitoExtension.class)
public class CustomUserTest {

	@Mock
	private UserRepository userdao;
	
	@Mock
	private JwtUtil jwtutil;

	@Mock
	private UserService userService;
	
	@InjectMocks
	private CustomUserDetailsServiceImpl service;
	
	@Test
	public void loadUsernameTest() {
		
		User user = new User();
		user.setUserId(111);
		user.setRole("ROLE_DOCTOR");
		user.setPassword("password@123");
		
		when(userdao.findById(111)).thenReturn(Optional.empty());
		assertThrows(UnauthorizedException.class,() -> service.loadUserByUsername("111"));
		
		when(userdao.findById(111)).thenReturn(Optional.of(user));
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(user.getRole()));
		UserDetails u=new org.springframework.security.core.userdetails.User(String.valueOf(user.getUserId()), user.getPassword(),roles);
		assertEquals(service.loadUserByUsername("111"), u);
		
	}
	
	@Test
	public void tokenValidityTest() {
		AuthResponse res = new AuthResponse();
		when(jwtutil.validateToken("token")).thenReturn(true);
		when(jwtutil.extractUsername("token")).thenReturn("111");
		res.setValid(true);
		User user = new User();
		user.setUserId(111);
		res.setUid("111");
		when(userdao.findById(111)).thenReturn(Optional.of(user));
		
		assertEquals(res,service.getValidity("token"));
		AuthResponse res2 = new AuthResponse();
		res2.setValid(false);
		when(jwtutil.validateToken("token2")).thenReturn(false);

		assertEquals(res2,service.getValidity("token2"));
	}
	
}
