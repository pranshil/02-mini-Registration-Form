package com.tcs.service;

import java.util.Map;

import com.tcs.binding.ForgotForm;
import com.tcs.binding.SignInForm;
import com.tcs.binding.UnlockForm;
import com.tcs.binding.UserForm;

public interface UserService {

	public String signIn(SignInForm signInForm);
	
	
	public String checkEmail(String email);
	public Map<Integer, String> loadCountry();
	public Map<Integer, String> loadState(Integer countryId);
	public Map<Integer, String> loadCity(Integer cityId);
	public String newUser(UserForm userForm);
	
	public String unLockUser(UnlockForm unlockForm);
	
	public String forgotPassword(ForgotForm forgotForm);
	
}
