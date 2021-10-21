package com.areeba.challenge.customer.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.areeba.challenge.customer.exception.CustomerNotFoundException;
import com.areeba.challenge.customer.exception.InvalidMobileException;
import com.areeba.challenge.customer.exception.InvalidRequestException;
import com.areeba.challenge.customer.model.Customer;
import com.areeba.challenge.customer.repository.CustomerRepository;

/**
 * Customer service(in service layer) that expose CRUD operations for customer
 * 
 * @author Hussein Zaraket
 */
@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private MobileNumberService mobileService;

	/**
	 * Return all customers from DB by repository
	 * @return
	 */
	public List<Customer> getAllCustomers() {
		return customerRepository.findAll();
	}

	/**
	 * Return customer by id
	 * @param id
	 * @return
	 * @throws CustomerNotFoundException in case there is not customer with such id
	 * @throws InvalidRequestException in case the id is less than or equal 0(invalid)
	 */
	public Customer getCustomerById(Long id) throws CustomerNotFoundException, InvalidRequestException {
		if(null == id || id < 1) 
		{
			throw new InvalidRequestException("Id cannot be null.");
		}
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isPresent()) {
			return customer.get();
		} else {
			throw new CustomerNotFoundException(id);
		}
	}

	/**
	 * Create a new customer and return it
	 * @param customer
	 * @return
	 * @throws InvalidMobileException in case mobileNumber is not valid 
	 */
	public Customer createCustomer(Customer customer) throws InvalidMobileException {
		// check if mobile valid before save
		mobileService.validateMobileNumber(customer.getMobileNumber());
		return customerRepository.save(customer);
	}

	/**
	 * Update specif customer data
	 * @param id
	 * @param customer
	 * @return
	 * @throws InvalidMobileException in case mobileNumber is not valid
	 * @throws CustomerNotFoundException in case there is no such customer with the provided id
	 * @throws InvalidRequestException in case the id is less than or equal 0(invalid)
	 */
	public Customer updateCustomer(Long id, Customer customer) throws InvalidMobileException, CustomerNotFoundException, InvalidRequestException {
		if(null == id || id < 1) 
		{
			throw new InvalidRequestException("Id cannot be null.");
		}
		// check if mobile valid before save
		mobileService.validateMobileNumber(customer.getMobileNumber());
		Optional<Customer> retrievedCust = customerRepository.findById(id);
		if (retrievedCust.isEmpty()) {
			throw new CustomerNotFoundException(id);
		}
		Customer _customer = retrievedCust.get();
		_customer.setName(customer.getName());
		_customer.setAddress(customer.getAddress());
		_customer.setMobileNumber(customer.getMobileNumber());
		return customerRepository.save(_customer);
	}

	/**
	 * Delete customer by it's id
	 * @param id
	 * @throws CustomerNotFoundException in case there is no such customer with the provided id
	 * @throws InvalidRequestException in case the id is less than or equal 0(invalid)
	 */
	public void deleteCustomer(Long id) throws CustomerNotFoundException, InvalidRequestException {
		if(null == id || id < 1) 
		{
			throw new InvalidRequestException("Id cannot be null.");
		}
		Optional<Customer> customer = customerRepository.findById(id);
		if (customer.isEmpty()) {
			throw new CustomerNotFoundException(id);
		}
		customerRepository.deleteById(id);
	}

}
