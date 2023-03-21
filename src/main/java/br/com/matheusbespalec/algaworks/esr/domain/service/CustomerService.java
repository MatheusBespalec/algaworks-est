package br.com.matheusbespalec.algaworks.esr.domain.service;

import br.com.matheusbespalec.algaworks.esr.api.exception.model.BadRequestException;
import br.com.matheusbespalec.algaworks.esr.domain.model.Customer;
import br.com.matheusbespalec.algaworks.esr.domain.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CustomerService {
    private CustomerRepository customerRepository;

    public List<Customer> list() {
        return customerRepository.findAll();
    }

    public Customer findById(Long customerId) {
        return customerRepository.findById(customerId).orElse(null);
    }
    public Customer save(Customer customer) {
        if (customer.getId() == null && customerRepository.existsByEmail(customer.getEmail())) {
            throw new BadRequestException("this email is already in use");
        }

        return customerRepository.save(customer);
    }


    public void delete(Long customerId) {
        customerRepository.deleteById(customerId);
    }
}
