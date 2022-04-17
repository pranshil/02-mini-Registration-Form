package com.tcs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="COUNTRY")
@Data
@NoArgsConstructor
public class Country {

	@Id
	@Column(name="COUNTRY_ID")
	private int countryId;
	
	@Column(name="COUNTRY_NAME")
	private String countryName;

	public Country(int countryId, String countryName) {
		this.countryId=countryId;
		this.countryName=countryName;
	}
}
