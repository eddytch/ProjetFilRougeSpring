package fr.iocean.application.credential;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.validator.constraints.NotBlank;
import fr.iocean.application.persistence.Identifiable;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Credential implements Identifiable {

	private static final long serialVersionUID = 2656075395605794189L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	private String value;
}
