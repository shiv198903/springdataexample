package com.shiv.example.springdataexample.jpa;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Instrument {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID")
	private Long instrument_id;

	@Column(name = "NAME")
	private String name;

	public Instrument() {
		super();
	}

	public Long getInstrument_id() {
		return instrument_id;
	}

	public void setInstrument_id(Long instrument_id) {
		this.instrument_id = instrument_id;
	}

}
