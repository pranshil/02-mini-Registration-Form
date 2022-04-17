package com.tcs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.entity.State;

public interface StateRepository extends JpaRepository<State, Integer>{

	public List<State> findByCountryId(Integer countryId);

}
