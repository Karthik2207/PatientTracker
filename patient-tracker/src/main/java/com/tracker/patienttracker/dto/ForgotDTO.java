package com.tracker.patienttracker.dto;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Component
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ForgotDTO {

	private int userId;
	private String mobileNo;
	@JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
	private String dateOfBirth;
}
