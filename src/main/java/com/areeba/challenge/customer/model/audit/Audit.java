package com.areeba.challenge.customer.model.audit;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Audit entity used to audit the create and update of the record, it's automatically generated
 *  
 * @author Hussein Zaraket
 */
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Audit {

	// created date of the customer, that is not changed in any case 
	@JsonIgnore
	@CreatedDate
	@Column(name = "created_date", nullable = false, updatable = false)
	private Date createdDate;

	// last updated date that is changed in every updated for the record
	@JsonIgnore
	@LastModifiedDate
	@Column(name = "updated_date", nullable = false)
	private Date updatedDate;

	/**
	 * Intialize createdDate and updatedDate on creation of an instance
	 */
	@PrePersist
	public void prePersist() {
		Date newDate = new Date();
		this.createdDate = newDate;
		this.updatedDate = newDate;
	}
	
	/**
	 * Update updatedDate before every update done on the instance
	 */
	@PreUpdate
    public void preUpdate() {
		this.updatedDate = new Date();
    }

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public Date getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}

}
