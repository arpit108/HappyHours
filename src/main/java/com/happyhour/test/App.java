package com.happyhour.test;

import java.util.Date;
import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.happyhour.dao.CustomerDAO;
import com.happyhour.model.Customer;

public class App {

	public static void main(String[] args) {

		ConfigurableApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		CustomerDAO customerDAO = (CustomerDAO) context.getBean("customerDAO");

		Customer customer = new Customer();
		customer.setAddress("A");
		customer.setCreatedDate(new Date());
		customer.setEmail("m");
		customer.setPassword("abc");
		customer.setPhoneNum(123);
		customer.setPincode(234);
		customer.setUserId("Mohit");
		customer.setUserName("Mohit");

		customerDAO.insertCustomer(customer);

		List<Customer> list = customerDAO.getRecipes();

		System.out.println("Batch inserted :" + list.size());

		context.close();
	}
}