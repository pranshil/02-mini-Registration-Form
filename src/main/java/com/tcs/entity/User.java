package com.tcs.entity;

import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name="USER_DTLS")
public class User {

	@Id
	@GeneratedValue
	@Column(name="USER_ID")
	private int userId;
	
	@Column(name="FIRST_NAME")
	private String firstName;
	
	@Column(name="LAST_NAME")
	private String lastName;
	
	@Column(name="EMAIL")
	private String userEmail;
	
	@Column(name="PHONE_NUM")
	private long phoneNum;
	
	@Column(name="DOB")
	private LocalDateTime dob;
	
	@Column(name="GENDER")
	private String gender;
	
	@Column(name="COUNTRY_NAME")
	private String countryName;
	
	@Column(name="STATE_NAME")
	private String stateName;
	
	@Column(name="CITY_NAME")
	private String cityName;
	
	@Column(name="ACC_PASSWORD")
	private String accStatus;
	
	@Column(name="PASSWORD")
	private String password;
	
	@Column(name="INSERT_DATE", updatable=false)
	@CreationTimestamp
	private LocalDateTime insertDate;
	
	@Column(name="UPDATE_DATE", insertable=false)
	@UpdateTimestamp
	private LocalDateTime updateDate;
}
