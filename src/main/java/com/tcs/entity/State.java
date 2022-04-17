package com.tcs.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="STATE")
@Data
@NoArgsConstructor
public class State {

	@Id
	@Column(name="STATE_ID")
	private int stateId;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@Column(name="COUNTRY_ID")
	private int countryId;

	public State(int stateId, String stateName,int countryId) {
		this.stateId=stateId;
		this.stateName=stateName;
		this.countryId=countryId;
	}
	
	
}
