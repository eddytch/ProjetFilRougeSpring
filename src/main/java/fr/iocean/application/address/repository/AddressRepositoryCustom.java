package fr.iocean.application.address.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import fr.iocean.application.address.model.Address;
import org.springframework.stereotype.Repository;


public interface AddressRepositoryCustom {

	public PageImpl<Address> search(Pageable pageable, String numStreet, String nameStreet, String pcTown, String town, String country);

}
