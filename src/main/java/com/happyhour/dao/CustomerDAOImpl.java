package com.happyhour.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.happyhour.model.Customer;

/**
 * Class for Customer interaction with DB
 * 
 * @author Mohit Bansal
 */
public class CustomerDAOImpl implements CustomerDAO {

	private DataSource dataSource;
	private JdbcTemplate jdbcTemplate;
	private Logger log = Logger.getLogger("CustomerDAOImpl");

	public JdbcTemplate getJdbcTemplate() {
		if (jdbcTemplate == null) {
			jdbcTemplate = new JdbcTemplate(dataSource);
		}
		return jdbcTemplate;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
		getJdbcTemplate();
	}

	public int insertCustomer(Customer customer) {

		log.info("inserting the customer");

		String sql = "INSERT INTO Customer "
				+ "(user_id, user_name, address, password, pincode,phone_num,email,created_date) VALUES (?, ?, ?,?,?,?,?,?)";

		int count = 0;
		try {
			count = jdbcTemplate.update(sql,
					new Object[] { customer.getUserId(), customer.getUserName(), customer.getAddress(),
							customer.getPassword(), customer.getPincode(), customer.getPhoneNum(), customer.getEmail(),
							customer.getCreatedDate() });
		} catch (Exception e) {
			log.info("error in inserting customer :" + e.getMessage());
		}

		return count;
	}

	public List<Customer> getRecipes() {
		String sql = "select id,user_id from Customer";

		return jdbcTemplate.query(sql, new RowMapper() {

			public Object mapRow(ResultSet rs, int rownumber) throws SQLException {
				Customer c = new Customer();
				c.setId(rs.getInt(1));
				c.setUserId(rs.getString(2));
				return c;
			}
		});

	}

}
