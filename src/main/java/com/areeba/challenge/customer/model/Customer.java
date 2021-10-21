package com.areeba.challenge.customer.model;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.areeba.challenge.customer.model.audit.Audit;

/**
 * Customer entity that contain all needed configuration to auto-create/update DB table once run and hold the needed data
 * 
 * @author Hussein Zaraket
 */
@Entity
@Table(name = "customer")
public class Customer extends Audit{

	// id that will be auto-filled by the database sequence when a new customer instance is created
	@Id
	@SequenceGenerator(name = "customer_seq", initialValue = 1, allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "customr_seq")
	@Column(name = "customer_id", length = 10)
	private Long id;

	// customer name
	@Column(nullable = false, length = 30)
	private String name;

	// customer address
	@Column(length = 300)
	private String address;

	// customer mobile number
	@Column(length = 30)
	private String mobileNumber;
	
	public Customer() {
		
	}

	public Customer(String name, String address, String mobileNumber) {
		this.name = name;
		this.address = address;
		this.mobileNumber = mobileNumber;
	}
	
	public Customer(Long id, String name, String address, String mobileNumber) {
		this(name, address, mobileNumber);
		this.id = id;
	}

	@Override
	public String toString() {
		return "Customer [ id = " + id + ", name = " + name + ", address = " + address + ", mobileNumber = "
				+ mobileNumber + " ]";
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	@Override
	public int hashCode() {
		return Objects.hash(address, id, mobileNumber, name);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		return Objects.equals(address, other.address) && Objects.equals(id, other.id)
				&& Objects.equals(mobileNumber, other.mobileNumber) && Objects.equals(name, other.name);
	}

}
