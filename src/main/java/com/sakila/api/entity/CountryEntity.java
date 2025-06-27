package com.sakila.api.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "country")
@DynamicInsert
@Data
@Getter
@Setter
public class CountryEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "country_id")
	private int countryId;
	
	@Column(name = "country")
	private String country;
	
	@Column(name = "last_update", nullable = true)
	private Timestamp lastUpdate;
	
}