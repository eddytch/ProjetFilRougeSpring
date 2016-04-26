package fr.iocean.application.author.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import fr.iocean.application.media.model.Media;
import fr.iocean.application.persistence.Identifiable;

import java.util.List;

@Entity
@Getter
@Setter
public class Author implements Identifiable {

    private static final long serialVersionUID = -3201785286202614503L;

	@Id
    @GeneratedValue
    private Long id ;

    @Column
	@NotEmpty
    private String lastName;

    @Column
    private String firstName;

    @OneToMany(mappedBy = "author")
    private List<Media> listMedia;
}