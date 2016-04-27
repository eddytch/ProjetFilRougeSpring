package fr.iocean.application.model;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Created by utilisateur on 23/03/2016.
 */
@Entity
@Getter
@Setter
public class Media {

    public enum Type {Livre,CD,DVD}

    @Id
    @GeneratedValue
    private long id ;

    @Column
	@NotEmpty
    private String title;

	@NotNull
    @Enumerated(EnumType.STRING)
    private Type type ;

	@NotNull
    @ManyToOne(cascade = CascadeType.ALL)
    private Author author;

	@OneToMany(mappedBy = "media")
	private List<Loan> mediaLoan;
}
