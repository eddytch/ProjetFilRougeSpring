package fr.iocean.application.address.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import fr.iocean.application.member.model.Member;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Getter
@Setter
public class Address {
	
	@Id
	@GeneratedValue
	private Long id;

	@NotEmpty
	private String numStreet;
	@NotEmpty
	private String nameStreet;
	@NotEmpty
	private String pcTown;
	@NotEmpty
	private String town;
	@NotEmpty
	private String country;
	
	@OneToMany(mappedBy = "address")
	private List<Member> members;
}
