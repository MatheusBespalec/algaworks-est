package br.com.matheusbespalec.algaworks.esr.domain.repository;

import br.com.matheusbespalec.algaworks.esr.domain.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
    boolean existsByEmail(String email);
}
