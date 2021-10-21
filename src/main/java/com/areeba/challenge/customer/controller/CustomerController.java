package com.areeba.challenge.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.areeba.challenge.customer.exception.CustomerNotFoundException;
import com.areeba.challenge.customer.exception.InvalidMobileException;
import com.areeba.challenge.customer.exception.InvalidRequestException;
import com.areeba.challenge.customer.model.Customer;
import com.areeba.challenge.customer.service.CustomerService;

/**
 * Controller that expose CRUD operations APIs in order to be called when needed
 * 
 * @author Hussein Zaraket
 */
@CrossOrigin
@RestController
@RequestMapping("/api/customer-service")
public class CustomerController {

	// Inject the needed service to use it below
	@Autowired
	private CustomerService customerService;

	/**
	 * API that return all the customers in the database
	 * @return
	 */
	@GetMapping("/customers")
	public ResponseEntity<List<Customer>> getAllCustomers() {
		List<Customer> customerList = customerService.getAllCustomers();
		if (null == customerList || customerList.isEmpty()) {
			return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<>(customerList, HttpStatus.OK);
	}

	/**
	 * API that return specific customer by it's id
	 * @param id
	 * @return
	 * @throws CustomerNotFoundException
	 * @throws InvalidRequestException
	 */
	@GetMapping("/customers/{id}")
	public ResponseEntity<Customer> getCustomerById(@PathVariable("id") Long id) throws CustomerNotFoundException, InvalidRequestException {
		Customer customer = customerService.getCustomerById(id);
		return new ResponseEntity<>(customer, HttpStatus.OK);
	}

	/**
	 * API that create new customer and return it after creation
	 * @param customer
	 * @return
	 * @throws InvalidMobileException
	 */
	@PostMapping("/customers")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) throws InvalidMobileException {
		Customer newCustomer = customerService.createCustomer(customer);
		return new ResponseEntity<>(newCustomer, HttpStatus.CREATED);
	}

	/**
	 * API that update a specific customer
	 * 
	 * @param id
	 * @param customer
	 * @return
	 * @throws InvalidMobileException
	 * @throws CustomerNotFoundException
	 * @throws InvalidRequestException
	 */
	@PutMapping("/customers/{id}")
	public ResponseEntity<Customer> updateCustomer(@PathVariable("id") Long id, @RequestBody Customer customer)
			throws InvalidMobileException, CustomerNotFoundException, InvalidRequestException {
		Customer retrievedCust = customerService.updateCustomer(id, customer);
		return new ResponseEntity<>(retrievedCust, HttpStatus.OK);
	}

	/**
	 * API that delete a specific customer
	 * 
	 * @param id
	 * @return
	 * @throws CustomerNotFoundException
	 * @throws InvalidRequestException
	 */
	@DeleteMapping("/customers/{id}")
	public ResponseEntity<HttpStatus> deleteCustomer(@PathVariable("id") Long id) throws CustomerNotFoundException, InvalidRequestException {
		customerService.deleteCustomer(id);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

}
