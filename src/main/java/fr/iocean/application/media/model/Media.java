package fr.iocean.application.media.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import fr.iocean.application.author.model.Author;
import fr.iocean.application.loan.model.Loan;
import fr.iocean.application.persistence.Identifiable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Media implements Identifiable {    

    private static final long serialVersionUID = -3769487681096006056L;

	@Id
    @GeneratedValue
    private Long id ;

	@NotEmpty
    private String title;

	@NotNull
    @Enumerated(EnumType.STRING)
    private MediaType type ;

	@NotNull
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Author author;

	@OneToOne
	@JsonIgnoreProperties("media")
	private Loan mediaLoan;

}
