package com.tcs.binding;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class UserForm {

	private String firstName;
	private String lastName;
	private String userEmail;
	private long phoneNum;
	private LocalDateTime dob;
	private String gender;
	private String password;
	private Integer countryId;
	private Integer stateId;
	private Integer cityId;
}
