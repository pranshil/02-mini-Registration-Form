package com.tcs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.binding.UnlockForm;
import com.tcs.service.UserService;

@RestController
public class UnlockController {

	@Autowired
	public UserService userService;
	
	@PostMapping("/unlock")
	public ResponseEntity<String> unlockUser(@RequestBody UnlockForm unlockForm){
		userService.unLockUser(unlockForm);
		
		return new ResponseEntity<String>("Account Unlocked", HttpStatus.OK);
	}
	
}
