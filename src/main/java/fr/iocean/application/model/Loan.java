package fr.iocean.application.model;

import fr.iocean.application.member.model.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.persistence.Transient;

@Entity
@Getter
@Setter
public class Loan {
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private Date dateLoan;
	
	@ManyToOne
	private Media media;
	
	@Transient
	private Date dateRent;
	
	private Date dateEndMedia;
	
	@ManyToOne
	private Member leaser;

}