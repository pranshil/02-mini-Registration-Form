package com.tcs.serviceImpl;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tcs.binding.ForgotForm;
import com.tcs.binding.SignInForm;
import com.tcs.binding.UnlockForm;
import com.tcs.binding.UserForm;
import com.tcs.constants.AppConstants;
import com.tcs.entity.City;
import com.tcs.entity.Country;
import com.tcs.entity.State;
import com.tcs.entity.User;
import com.tcs.repository.CityRepository;
import com.tcs.repository.CountryRepository;
import com.tcs.repository.StateRepository;
import com.tcs.repository.UserRepository;
import com.tcs.service.UserService;
import com.tcs.utils.EmailUtils;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	public UserRepository userRepository;

	@Autowired
	public CountryRepository countryRepository;

	@Autowired
	public StateRepository stateRepository;

	@Autowired
	public CityRepository cityRepository;

	@Autowired
	private EmailUtils emailUtils;

	@Override
	public String checkEmail(String emailId) {
		User byEmail = userRepository.findByUserEmail(emailId);
		if (byEmail != null) {
			return AppConstants.EMAIL_UNIQUE;
		}
		return AppConstants.EMAIL_DUPLICATE;
	}

	@Override
	public Map<Integer, String> loadCountry() {
		List<Country> countries = countryRepository.findAll();
		Map<Integer, String> mapCountry = new HashMap<Integer, String>();
		if (!countries.isEmpty()) {
			countries.forEach(country -> mapCountry.put(country.getCountryId(), country.getCountryName()));
		}
		return mapCountry;
	}

	@Override
	public Map<Integer, String> loadState(Integer countryId) {
		List<State> states = stateRepository.findByCountryId(countryId);
		Map<Integer, String> mapState = new HashMap<Integer, String>();
		if (!states.isEmpty()) {
			states.forEach(state -> mapState.put(state.getStateId(), state.getStateName()));
		}
		return mapState;
	}

	@Override
	public Map<Integer, String> loadCity(Integer cityId) {
		List<City> cities = cityRepository.findByStateId(cityId);
		Map<Integer, String> mapCity = new HashMap<Integer, String>();
		if (!cities.isEmpty()) {
			cities.forEach(city -> mapCity.put(city.getCityId(), city.getCityName()));
		}
		return mapCity;
	}

	// NEW USER Registration
	@Override
	public String newUser(UserForm userForm) {

		userForm.setPassword(generateRandomPassword(5));

		User user = new User();
		user.setAccStatus(AppConstants.ACC_STATUS_LOCKED);
		BeanUtils.copyProperties(userForm, user);

		User saveUser = userRepository.save(user);

		String to = userForm.getUserEmail();
		String subject = AppConstants.NEW_USER_EMAIL_SUBJECT;
		String body = unlockAccBody(userForm);

		emailUtils.sendEmail(to, subject, body);

		return AppConstants.NEW_USER_REGIS_DONE;
	}

	// UNLOCK User
	@Override
	public String unLockUser(UnlockForm unlockForm) {
		User andPassword = userRepository.findByUserEmailAndPassword(unlockForm.getUserEmail(),
				unlockForm.getTempPassword());
		if (andPassword == null) {
			return AppConstants.INVALID_USER;
		}

		andPassword.setPassword(unlockForm.getNewPassword());
		andPassword.setAccStatus(AppConstants.UNLOCK_USER);
		userRepository.save(andPassword);
		return AppConstants.PASS_CHANGED;
	}

	// Forgot Password
	@Override
	public String forgotPassword(ForgotForm forgotForm) {
		User userEmail = userRepository.findByUserEmail(forgotForm.getUserEmail());
		if (userEmail == null) {
			return AppConstants.INVALID_EMAIL;
		}

		String to = forgotForm.getUserEmail();
		String subject = AppConstants.FORGOT_PASS_EMAIL_SUBJECT;
		String forgotbody = forgotBody(userEmail);

		emailUtils.sendEmail(to, subject, forgotbody);

		return null;
	}

	// Random Password Generator
	private String generateRandomPassword(int len) {
		String chars = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghi" + "jklmnopqrstuvwxyz!@#$%&";
		Random rnd = new Random();
		StringBuilder sb = new StringBuilder(len);
		for (int i = 0; i < len; i++)
			sb.append(chars.charAt(rnd.nextInt(chars.length())));
		return sb.toString();
	}

	// Email Generator
	private String unlockAccBody(UserForm userForm) {

		String body = "";
		StringBuffer buffer = new StringBuffer();
		Path filePath = Paths.get("emailBody.txt");

		try (Stream<String> stream = Files.lines(filePath)) {
			stream.forEach(line -> {
				buffer.append(line);
			});

			body = buffer.toString();
			body = body.replace("{FNAME}", userForm.getFirstName());
			body = body.replace("{LNAME}", userForm.getLastName());
			body = body.replace("{TEMP-PWD}", userForm.getPassword());
			body = body.replace("{EMAIL}", userForm.getUserEmail());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return body;
	}

	// Email Generator
	private String forgotBody(User user) {

		String forgotbody = "";
		StringBuffer buffer = new StringBuffer();

		try (Stream<String> stream = Files.lines(Paths.get("forgotPwd.txt"))) {

			stream.forEach(line -> {
				buffer.append(line);
			});

			forgotbody = buffer.toString();
			forgotbody = forgotbody.replace("{FNAME}", user.getFirstName());
			forgotbody = forgotbody.replace("{LNAME}", user.getLastName());
			forgotbody = forgotbody.replace("{PWD}", user.getPassword());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return forgotbody;
	}

	// LOGIN FORM
	@Override
	public String signIn(SignInForm signInForm) {
		User emailAndPassword = userRepository.findByUserEmailAndPassword(signInForm.getUserEmail(),
				signInForm.getUserPassword());
		User userEmail = userRepository.findByUserEmail(signInForm.getUserEmail());
		if (emailAndPassword == null) {
			return AppConstants.LOG_IN_CREDENTIALS_INVALID;
		}
		if (emailAndPassword.getAccStatus().equals("LOCKED")) {
			return AppConstants.LOG_IN_ACCOUNT_LOCKED;
		}
		return AppConstants.LOG_IN_SUCCESS;
	}
}
