package fr.iocean.application.user.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iocean.application.user.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
