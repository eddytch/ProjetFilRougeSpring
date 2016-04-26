package fr.iocean.application.user.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

import fr.iocean.application.persistence.Identifiable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="user_")
public class User implements Identifiable {
	
	private static final long serialVersionUID = 2972210068123124759L;
	
	@Id
    @GeneratedValue
	private Long id;
	@NotEmpty
	private String login;
	@NotEmpty
	private String password;

}
