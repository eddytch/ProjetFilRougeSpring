package fr.iocean.application.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;

@Entity
@Getter
@Setter
public class Author {

    @Id
    @GeneratedValue
    private long id ;

    @Column
	@NotEmpty
    private String lastName;

    @Column
    private String firstName;

    @OneToMany(mappedBy = "author")
    private List<Media> listMedia;
}