package com.supunkanushka.learngraphql.repository;

import com.supunkanushka.learngraphql.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

	Customer findCustomerByEmail(String email);
}
