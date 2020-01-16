package com.codespace.exchange.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespace.exchange.entity.Conversion;

@Repository
public interface ConversionRepository extends JpaRepository<Conversion, Long> {

	List<Conversion> findByTransactionIdOrTransactionDate(Long transactionId, Date transactionDate, Pageable pageable);
}
