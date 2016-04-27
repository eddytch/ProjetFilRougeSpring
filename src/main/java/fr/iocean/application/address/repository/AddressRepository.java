package fr.iocean.application.address.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iocean.application.address.model.Address;
import org.springframework.stereotype.Repository;


public interface AddressRepository extends JpaRepository<Address, Long>, AddressRepositoryCustom {

}
