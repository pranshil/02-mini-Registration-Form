package com.tcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name="CITY")
public class City {

	@Id
	@Column(name="CITY_ID")
	private int cityId;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
	@Column(name="STATE_ID")
	private int stateId;

	public City(int cityId, String cityName, int StateId) {
		this.cityId=cityId;
		this.cityName=cityName;
		this.stateId=stateId;
	}
}