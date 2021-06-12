package com.micah.demo.repositories;

import com.micah.demo.entities.Loan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface LoanRepository extends JpaRepository<Loan, String> {
    @Query("SELECT n FROM Loan n WHERE n.borrower.username = ?1 OR n.lender.username = ?1")
    List<Loan> findByUsername(String username);

    @Query("SELECT n FROM Loan n WHERE n.borrower.username = ?1 ORDER BY n.amount")
    List<Loan> findByBorrowerName(String username);

    @Query("SELECT n FROM Loan n WHERE n.borrower.username = ?1 AND n.lender.username = ?2")
    Optional<Loan> findByBorrowerNameAndLenderName(String borrowerName, String lenderName);
}
