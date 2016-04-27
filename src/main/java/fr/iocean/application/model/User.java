package fr.iocean.application.model;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Getter
@Setter
@Table(name="user_")
public class User {
	
    @Id
    @GeneratedValue
	private Long id;
	@NotEmpty
	private String login;
	@NotEmpty
	private String password;

}
