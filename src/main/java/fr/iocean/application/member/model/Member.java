package fr.iocean.application.member.model;

import java.util.Date;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import fr.iocean.application.address.model.Address;
import fr.iocean.application.persistence.Identifiable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Member implements Identifiable {
	
	private static final long serialVersionUID = -9156107858932394127L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotEmpty
	private String lastname;
	@NotEmpty
	private String name;
	@NotEmpty
	private String email;

	@NotNull
	private Date birthday;
	
	@Transient
	private String age;

	private Date paymentDate;
	private double amount;
	
	@Transient
	private Date endSubscription;
	
	@Embedded
	private Address address;


}

