package com.happyhour.dao;

import java.util.List;

import com.happyhour.model.Customer;

/**
 * Interface for Customer interaction with DB
 * 
 * @author Mohit Bansal
 */
public interface CustomerDAO {

	public int insertCustomer(Customer customer);

	public List<Customer> getRecipes();

}
