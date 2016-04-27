package fr.iocean.application.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Member {
	
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
	
	@ManyToOne(cascade=CascadeType.ALL)
	private Address address;
	
	@OneToMany(mappedBy = "leaser")
	private List<Loan> loans;

}

