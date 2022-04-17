package com.tcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.binding.ForgotForm;
import com.tcs.service.UserService;

@RestController
public class ForgotController {

	@Autowired
	public UserService userService;
	
	@PostMapping("/forgot")
	public ResponseEntity<?> forgotPassword(@RequestBody ForgotForm forgotForm){
		userService.forgotPassword(forgotForm);
		
		return new ResponseEntity<String>("Email sent", HttpStatus.OK);
	}
}