package com.example.demo.dto;

import javax.persistence.Cacheable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Version;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Indexed
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Inheritance(strategy=InheritanceType.JOINED)
public class Product {
	
	
	@GeneratedValue
	@Id
	private int id;
	
	@Field
	@NotBlank
	private String name;
	@Field
	@NotBlank
	private String companyName;
	private double price;
	private String image;
	private int quantity;
	@NotBlank
	private String category;
	
	@Version
	private int version;
	
	
	
}
