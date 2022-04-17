package com.tcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.binding.SignInForm;
import com.tcs.service.UserService;

@RestController
public class SignInController {

	@Autowired
	public UserService userService;
	
	@PostMapping("/login")
	public ResponseEntity<?> userLogin(@RequestBody SignInForm signInForm){

		userService.signIn(signInForm);
		
		return new ResponseEntity<String>("Login",HttpStatus.OK);
	}
}
