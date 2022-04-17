package com.tcs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tcs.binding.UserForm;
import com.tcs.service.UserService;

@RestController
public class RegistrationController {

	@Autowired
	public UserService userService;

	@GetMapping("/check/{email}")
	public ResponseEntity<String> checkEmail(@RequestParam("email") String emailId) {
		String checkEmail = userService.checkEmail(emailId);
		return new ResponseEntity<String>(checkEmail, HttpStatus.OK);
	}

	@GetMapping("/countries")
	public ResponseEntity<Map<Integer, String>> getCountry() {
		Map<Integer, String> loadCountry = userService.loadCountry();
		return new ResponseEntity<>(loadCountry, HttpStatus.OK);
	}

	@GetMapping("/states/{countryId}")
	public ResponseEntity<Map<Integer, String>> getState(@PathVariable("countryId") Integer countryId) {
		Map<Integer, String> loadState = userService.loadState(countryId);
		return new ResponseEntity<>(loadState, HttpStatus.OK);
	}

	@GetMapping("/cities/{stateId}")
	public ResponseEntity<Map<Integer, String>> getCity(@PathVariable("stateId") Integer stateId) {
		Map<Integer, String> loadCity = userService.loadCity(stateId);
		return new ResponseEntity<>(loadCity, HttpStatus.OK);
	}

	@PostMapping("/user")
	public ResponseEntity<String> saveUser(@RequestBody UserForm userForm) {
		String newUser = userService.newUser(userForm);
		return new ResponseEntity<String>(newUser, HttpStatus.OK);
	}
}