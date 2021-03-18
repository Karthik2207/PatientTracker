package com.tracker.patienttracker.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "help")
public class Help {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int helpId;
	
	@NotNull(message = "Issue cannot be null")
	@NotEmpty(message = "Issue cannot be empty")
	@NotBlank(message = "Issue cannot be blank or whitespace")
	String issue;
	
	@NotNull(message = "Issue cannot be null")
	@NotEmpty(message = "Issue cannot be empty")
	@NotBlank(message = "Issue cannot be blank or whitespace")
	String description;
}

