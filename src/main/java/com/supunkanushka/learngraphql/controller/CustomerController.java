package com.supunkanushka.learngraphql.controller;

import com.supunkanushka.learngraphql.model.Customer;
import com.supunkanushka.learngraphql.model.CustomerInput;
import com.supunkanushka.learngraphql.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@AllArgsConstructor(onConstructor = @__({@Autowired}))
@Controller
public class CustomerController {

	private final CustomerRepository customerRepository;

	@QueryMapping
	public List<Customer> customers(){
		return  customerRepository.findAll();
	}

	@QueryMapping
	public Customer customerById(@Argument Long id){
		return customerRepository.findById(id).orElseThrow();
	}

	@QueryMapping
	public Customer customerByEmail(@Argument String emaill){
		return customerRepository.findCustomerByEmail(emaill);
	}

	@MutationMapping
	public Customer addCustomer(@Argument(name = "input") CustomerInput customerInput) {
		return this.customerRepository.save(customerInput.getCustomerEntity());
	}
}
