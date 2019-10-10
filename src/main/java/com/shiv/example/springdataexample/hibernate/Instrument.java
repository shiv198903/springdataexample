package com.shiv.example.springdataexample.hibernate;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INSTRUMENT")
public class Instrument implements Serializable {

	private static final long serialVersionUID = -7728370803115827438L;

	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	public Instrument() {
		super();
	}

	public int getId() {
		return id;
	}

}
