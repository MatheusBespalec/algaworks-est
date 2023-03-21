package br.com.matheusbespalec.algaworks.esr.api.controller;

import br.com.matheusbespalec.algaworks.esr.domain.repository.CustomerRepository;
import br.com.matheusbespalec.algaworks.esr.domain.model.Customer;
import br.com.matheusbespalec.algaworks.esr.domain.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;

    @GetMapping
    public ResponseEntity<List<Customer>> list() {
        return ResponseEntity.ok(customerService.list());
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> findById(@PathVariable Long customerId) {
        return ResponseEntity.ok(customerService.findById(customerId));
    }

    @PostMapping
    public ResponseEntity<Customer> save(@Valid @RequestBody Customer customer) {
        return new ResponseEntity<Customer>(customerService.save(customer), HttpStatus.CREATED);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<Customer> replace(@PathVariable Long customerId, @Valid @RequestBody Customer customer) {
        Optional<Customer> customerOptional = customerRepository.findById(customerId);
        if (!customerRepository.existsById(customerId)) {
            return ResponseEntity.notFound().build();
        }

        customer.setId(customerId);
        return ResponseEntity.ok(customerRepository.save(customer));
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<Void> delete(@PathVariable Long customerId) {
        if (!customerRepository.existsById(customerId)) {
            return ResponseEntity.notFound().build();
        }

        customerService.delete(customerId);
        return ResponseEntity.noContent().build();
    }
}
