package com.codespace.exchange.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.codespace.exchange.entity.RatePair;

/**
 * @author ugureli
 *
 */
@Repository
public interface ExchangeRateRepository extends JpaRepository<RatePair, Long> {

	Optional<RatePair> findByBaseAndSymbol(String base, String symbol);

}
