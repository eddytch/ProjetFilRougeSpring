package fr.iocean.application.loan.repository;

import java.util.Date;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import fr.iocean.application.loan.model.Loan;

public interface LoanRepositoryCustom {

	public PageImpl<Loan> search(Pageable pageable, Date dateLoan);

}
