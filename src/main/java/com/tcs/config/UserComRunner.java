package com.tcs.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.tcs.entity.City;
import com.tcs.entity.Country;
import com.tcs.entity.State;
import com.tcs.repository.CityRepository;
import com.tcs.repository.CountryRepository;
import com.tcs.repository.StateRepository;

@Component
public class UserComRunner implements CommandLineRunner {

	@Autowired
	public CountryRepository countryRepository;
	
	@Autowired
	public StateRepository stateRepository;
	
	@Autowired
	public CityRepository cityRepository;

	@Override
	public void run(String... args) throws Exception {

		countryRepository.saveAll(Arrays.asList(
			new Country(111,"India"),
			new Country(222,"USA")
				)
		);
		
		stateRepository.saveAll(Arrays.asList(
			new State(11, "Maharashtra",111),
			new State(22, "MP",111),
			new State(33, "Chicago",222),
			new State(44, "Texas",222)
				)
		);
		
		cityRepository.saveAll(Arrays.asList(
			new City(1,"Mumbai",11),
			new City(2,"Pune",11),
			new City(3,"Indore",22),
			new City(4,"Bhopal",22),
			new City(5,"Lehman Street",33),
			new City(6,"Pearl",33),
			new City(7,"Karl Mayer",44),
			new City(8,"Ziemer",44)
				)
		);
	}
}
