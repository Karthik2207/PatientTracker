package com.tracker.patienttracker.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.tracker.patienttracker.dto.ForgotDTO;
import com.tracker.patienttracker.exception.UnauthorizedException;
import com.tracker.patienttracker.model.AuthResponse;
import com.tracker.patienttracker.model.UserData;
import com.tracker.patienttracker.repository.UserRepository;
import com.tracker.patienttracker.security.JwtUtil;
import com.tracker.patienttracker.util.DateUtil;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsServiceImpl implements CustomUserDetailsService {
	@Autowired
	private UserRepository userdao;
	
	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private UserService userService;
	
	public UserData serveForgotPassword(ForgotDTO dto) {
		
		com.tracker.patienttracker.model.User user = userService.getUserByUserId(dto.getUserId());
		if(user==null) {
			throw new InvalidDataAccessApiUsageException("User Not Found");
		}
		
		if(user.getContactNo().contentEquals(dto.getMobileNo())) {
			
			
		     System.out.println(user.getDateOfBirth());
			if((user.getDateOfBirth().compareTo(new DateUtil().convertToDate1(dto.getDateOfBirth()))==0)) {
				
				UserDetails userdetails = loadUserByUsername(String.valueOf(user.getUserId()));
				String role = userdetails.getAuthorities().toArray()[0].toString();
				String generateToken = jwtutil.generateTokenForReset(userdetails,role);
				return new UserData(user.getUserId(), null, generateToken, role);
			}
		}
		
		throw new InvalidDataAccessApiUsageException("Invalid Details");
	}
	
	public ResponseEntity<String>  serveForgotUserId(ForgotDTO dto) {
		com.tracker.patienttracker.model.User user =
				userService.getUserByContactAndDateOfBirth(dto.getMobileNo(), new DateUtil().convertToDate1(dto.getDateOfBirth()));
		
		if(user==null) {
			return ResponseEntity.badRequest().body("No UserId Found with given details");
		}
		
		return ResponseEntity.ok("Your UserId is "+ String.valueOf(user.getUserId()));
		
	}
	
	public void resetPassword(UserData userData) {
		System.out.println(userData);
		com.tracker.patienttracker.model.User user = userService.getUserByUserId(userData.getUserId());
		if(user==null) {
			throw new InvalidDataAccessApiUsageException("Accessed Invalid API");
		}
		AuthResponse response = getValidity(userData.getAuthToken());
		if(!response.isValid()) {
			throw new InvalidDataAccessApiUsageException("Accessed Invalid API");
		}
		
		user.setPassword(userData.getPassword());
		userService.saveUser(user);
	}
	
	@Override
	public UserDetails loadUserByUsername(String uid) {
		Optional<com.tracker.patienttracker.model.User> userData = userdao.findById(Integer.parseInt(uid));
		if (!userData.isPresent()) {
			log.error("Unauthorized exception");
			throw new UnauthorizedException("unauthorized");
		}
		com.tracker.patienttracker.model.User user = userData.get();
		List<GrantedAuthority> roles = new ArrayList<GrantedAuthority>();
		roles.add(new SimpleGrantedAuthority(user.getRole()));
		System.out.println(roles);
		return new User(String.valueOf(user.getUserId()), user.getPassword(),roles);
	}

	@Override
	public UserData login(UserData userlogincredentials) {
		System.out.println("Login Method called");
		final UserDetails userdetails = loadUserByUsername(String.valueOf(userlogincredentials.getUserId()));
		int uid=0;
		String generateToken = "";
		if (userdetails.getPassword().equals(userlogincredentials.getPassword())) {
			uid = userlogincredentials.getUserId();
			Optional<com.tracker.patienttracker.model.User> opuser = userdao.findById(uid);
			if(!opuser.isPresent())
			{
				throw new UsernameNotFoundException("Not Approved");
			}
			com.tracker.patienttracker.model.User user = opuser.get();
			if(user.getApproved()==0) {
				throw new InvalidDataAccessApiUsageException("Invalid User");
			}
			else if (user.getApproved()==-1){
				throw new InvalidDataAccessApiUsageException("Rejected User");
			}
			String role = userdetails.getAuthorities().toArray()[0].toString();
			generateToken = jwtutil.generateToken(userdetails,role);
			return new UserData(uid, null, generateToken, role);
		} else {
			log.error("Unauthorized exception");
			throw new UnauthorizedException("Unauthorized");
		}
	}

	@Override
	public AuthResponse getValidity(String token) {
		System.out.println("GET Validity Method");
		AuthResponse res = new AuthResponse();
		if (jwtutil.validateToken(token)) {
			res.setUid(jwtutil.extractUsername(token));
			res.setValid(true);
			res.setRole(userdao.findById(Integer.parseInt(jwtutil.extractUsername(token))).get().getRole());
		} else {
			res.setValid(false);
			log.info("At Validity : ");
			log.error("Token Has Expired");
		}
		return res;
	}

	

}
