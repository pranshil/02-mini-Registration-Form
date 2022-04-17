package com.tcs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tcs.entity.Country;

public interface CountryRepository extends JpaRepository<Country, Integer>{

}
