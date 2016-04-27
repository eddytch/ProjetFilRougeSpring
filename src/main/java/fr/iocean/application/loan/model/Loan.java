package fr.iocean.application.loan.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import fr.iocean.application.member.model.Member;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.persistence.Identifiable;

import javax.persistence.Transient;

@Entity
@Getter
@Setter
public class Loan implements Identifiable {
	
	private static final long serialVersionUID = 2896065020056458157L;

	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	private Date dateLoan;
	
	@ManyToOne
	@JsonIgnoreProperties("mediaLoan")
	private Media media;
	
	@Transient
	private Date dateRent;
	
	private Date dateEndMedia;
	
	@ManyToOne
	@JsonIgnoreProperties("leaser")
	private Member leaser;

}