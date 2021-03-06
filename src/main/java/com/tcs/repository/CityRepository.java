package com.tcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.entity.City;

public interface CityRepository extends JpaRepository<City, Integer> {

	public List<City> findByStateId(Integer cityId);

	
}