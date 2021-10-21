package com.areeba.challenge.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.areeba.challenge.customer.model.Customer;

/**
 * Customer Repository that will expose all needed CRUD operations functionality on DB
 * 
 * @author Hussein Zaraket
 */
public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
