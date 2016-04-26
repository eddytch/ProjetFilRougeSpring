package fr.iocean.application.loan.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import fr.iocean.application.loan.model.Loan;

public interface LoanRepository extends JpaRepository<Loan, Long>, LoanRepositoryCustom {

}
