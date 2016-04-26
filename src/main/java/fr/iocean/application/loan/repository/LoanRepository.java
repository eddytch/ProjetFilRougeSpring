package fr.iocean.application.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iocean.application.loan.model.Loan;
import org.springframework.stereotype.Repository;


public interface LoanRepository extends JpaRepository<Loan, Long>, LoanRepositoryCustom {

}
