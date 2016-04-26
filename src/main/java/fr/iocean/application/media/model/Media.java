package fr.iocean.application.media.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import fr.iocean.application.author.model.Author;
import fr.iocean.application.loan.model.Loan;
import fr.iocean.application.persistence.Identifiable;

@Entity
@Getter
@Setter
public class Media implements Identifiable {    

    private static final long serialVersionUID = -3769487681096006056L;

	@Id
    @GeneratedValue
    private Long id ;

    @Column
	@NotEmpty
    private String title;

	@NotNull
    @Enumerated(EnumType.STRING)
    private MediaType type ;

	@NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

	@OneToMany(mappedBy = "media")
	private List<Loan> mediaLoan;
}
