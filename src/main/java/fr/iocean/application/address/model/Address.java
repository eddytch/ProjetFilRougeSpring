package fr.iocean.application.address.model;

import javax.persistence.Embeddable;

import org.hibernate.validator.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter
@Setter
public class Address {
	
	@NotEmpty
	private String numStreet;
	@NotEmpty
	private String nameStreet;
	@NotEmpty
	private String pcTown;
	@NotEmpty
	private String town;
	@NotEmpty
	private String country;
}
