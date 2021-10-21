package com.areeba.challenge.customer;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.areeba.challenge.customer.controller.CustomerController;
import com.areeba.challenge.customer.exception.CustomerNotFoundException;
import com.areeba.challenge.customer.exception.InvalidMobileException;
import com.areeba.challenge.customer.exception.InvalidRequestException;
import com.areeba.challenge.customer.model.Customer;
import com.areeba.challenge.customer.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Class that contain customer controller automation test cases
 * 
 * @author Hussein Zaraket
 */
@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

	// Used to perform api testing
	@Autowired
	private MockMvc mockMvc;

	// serialize needed data as in json format
	@Autowired
	private ObjectMapper mapper;

	// customerService mocked instance in order to be mock it's methods when called
	@MockBean
	private CustomerService customerService;
	
	// Test Customer instance
	private Customer customer1 = new Customer(1L, "Hussein Zaraket", "Lebanon, Beirut", "0096170745563");
	private Customer customer2 = new Customer(2L, "John Farhat", "Lebanon, Tyre", "009613556441");
	private Customer customer3 = new Customer(3L, "Mohamad Falha", "Lebanon, Tripole", "0096181447554");
	private Customer customer4 = new Customer(4L, "Tarek Mrad", "Lebanon, Beirut", "0096170444222");

	/**
	 * Test getAllCustomers method success case
	 * 
	 * @throws Exception
	 */
	@Test
	public void getAllCustomers_success() throws Exception{
		List<Customer> records = new ArrayList<>(Arrays.asList(customer1, customer2, customer3, customer4));

		// mock the getAllCustomer method and return custom data
		Mockito.when(customerService.getAllCustomers()).thenReturn(records);

		// Call the needed API and insure that the return data is the same as the one specified in above mocking functionality 
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/customer-service/customers").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", hasSize(4)))
				.andExpect(jsonPath("$[1].name", is("John Farhat")))
				.andExpect(jsonPath("$[3].mobileNumber", is("0096170444222")));
	}

	/**
	 * getCustomerById success case
	 * 
	 * @throws Exception
	 */
	@Test
	public void getCustomerById_success() throws Exception {
		
		// mock the getCustomerById method and return custom data
		Mockito.when(customerService.getCustomerById(customer3.getId())).thenReturn(customer3);

		// Call the needed API and insure that the return data is the same as the one specified in above mocking functionality
		mockMvc.perform(
				MockMvcRequestBuilders.get("/api/customer-service/customers/3").contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("Mohamad Falha")));
	}
	
	/**
	 * getCustomerById fail case, where an InvalidRequestException should be thrown in case the id is negative
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void getCustomerById_negativeId() throws Exception {
		try {
			// call getCustomerById method with negative id
			new CustomerService().getCustomerById(-1L);
			// should not reach this statement, if reached case will fail  
			Assert.fail("Exception expected");
		} 
		catch (InvalidRequestException e) {
			// insure that the InvalidRequestException is thrown and check that the message is as expected
			String message = e.getMessage();
		    assertThat(message, containsString("Id cannot be null."));
		} catch (CustomerNotFoundException e) {
			//should not reach this statement, if reached case will fail
			Assert.fail("Unexpected Exception");
		}
	}

	/**
	 * createCustomer success case
	 * 
	 * @throws Exception
	 */
	@Test
	public void createCustomer_success() throws Exception {
		Customer customer = new Customer("Hussein Zaraket", "Lebanon, Beirut", "0096170745563");

		// mock the createCustomer method and return custom data
		Mockito.when(customerService.createCustomer(customer)).thenReturn(customer);

		// RequestBuilder that prepare the API call that will be performed
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.post("/api/customer-service/customers")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(customer));

		// Call the needed API and insure that the return data is the same as the one specified in above mocking functionality
		mockMvc.perform(mockRequest).andExpect(status().isCreated()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("Hussein Zaraket")));
	}

	/**
	 * updateCustomer fail case, where an InvalidRequestException should be thrown in case the id is negative
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void updateCustomer_negativeId() 
	{
		try {
			// call updateCustomer method with negative id
			new CustomerService().updateCustomer(-1L, customer1);
			// should not reach this statement, if reached case will fail
			Assert.fail("Exception expected");
		} catch (InvalidRequestException e) {
			// insure that the InvalidRequestException is thrown and check that the message is as expected
			String message = e.getMessage();
			assertThat(message, containsString("Id cannot be null."));
		} catch (InvalidMobileException | CustomerNotFoundException e) {
			// should not reach this statement, if reached case will fail
			Assert.fail("Unexpected Exception");
		}
	}
	
	/**
	 * updateCustomer success case
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateCustomer_success() throws Exception {
		Customer updatedCustomer = new Customer(1L, "Hussein Zaraket", "Lebanon, Tyre", "0096170745563");

		// mock the updateCustomer method and return custom data
		Mockito.when(customerService.updateCustomer(updatedCustomer.getId(), updatedCustomer))
				.thenReturn(updatedCustomer);

		// RequestBuilder that prepare the API call that will be performed
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.put("/api/customer-service/customers/1")
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.content(this.mapper.writeValueAsString(updatedCustomer));

		// Call the needed API and insure that the return data is the same as the one specified in above mocking functionality
		mockMvc.perform(mockRequest).andExpect(status().isOk()).andExpect(jsonPath("$", notNullValue()))
				.andExpect(jsonPath("$.name", is("Hussein Zaraket")));
	}
	

	/**
	 * deleteCustomer fail case, where an InvalidRequestException should be thrown in case the id is negative
	 * 
	 * @throws Exception
	 */
	@SuppressWarnings("deprecation")
	@Test
	public void deleteCustomer_negativeId() 
	{
		try {
			// call deleteCustomer method with negative id
			new CustomerService().deleteCustomer(-5L);
			// should not reach this statement, if reached case will fail
			Assert.fail("Exception expected");
		} catch (InvalidRequestException e) {
			// insure that the InvalidRequestException is thrown and check that the message is as expected
			String message = e.getMessage();
		    assertThat(message, containsString("Id cannot be null."));
		} catch (CustomerNotFoundException e) {
			// should not reach this statement, if reached case will fail
			Assert.fail("Unexpected Exception");
		}
	}
	
}
