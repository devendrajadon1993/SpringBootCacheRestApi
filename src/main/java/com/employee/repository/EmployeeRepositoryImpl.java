package com.employee.repository;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import com.employee.entity.Employee;
import com.employee.view.model.ViewEmployee;

@Repository
@CacheConfig(cacheNames = "employeeCache")
public class EmployeeRepositoryImpl implements EmployeeRepository {

	@Autowired
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	@CachePut(cacheNames = "employeeCache")
	public int insertEmployee(Employee employee) {
		String sql = "INSERT INTO employee (first_name,last_name, email, phone) VALUES (:firstName, :lastName,:email, :phoneNo)";
		BeanPropertySqlParameterSource paramSource = new BeanPropertySqlParameterSource(employee);
		return namedParameterJdbcTemplate.update(sql, paramSource);
	}

	@Override
	public int chkEmailAlreadyExist(String email) {
		String sql = "SELECT count(*) from employee where email= ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { email }, Integer.class);
	}

	@Override
	public int findEmployeeById(int empId) {
		String sql = "SELECT count(*) from employee where id= ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { empId }, Integer.class);
	}

	@Caching(evict = @CacheEvict(value = "employeeCache", allEntries = true))
	@Override
	public int deleteEmployeeById(int empId) {
		String sql = "delete from employee where id= ?";
		return jdbcTemplate.update(sql, empId);
	}

	@Override
	public Long getTotalEmployeeCounts(String searchTerm) {
		String sql = "select count(*) from employee where first_name like ?";
		return jdbcTemplate.queryForObject(sql, new Object[] { "%" + searchTerm + "%" }, Long.class);
	}

	@Override
	@Cacheable(cacheNames = "employeeCache")
	public List<ViewEmployee> getEmployeeList(String searchTerm, int pageNo, int pageSize) {
		String sql = "select id as empId,first_name,last_name,email,phone as phoneNumber from employee where first_name like ? order by first_name limit "
				+ pageSize + " offset " + pageNo * pageSize + "";
		return jdbcTemplate.query(sql, new Object[] { "%" + searchTerm + "%" },
				new BeanPropertyRowMapper<ViewEmployee>(ViewEmployee.class));
	}

	@CachePut(cacheNames = "employeeCache")
	@Override
	public int updateEmployeeById(int empId, @Valid Employee emp) {
		String sql = "update employee set first_name =? , last_name=? ,phone=? where id=?";
		return jdbcTemplate.update(sql,
				new Object[] { emp.getFirstName(), emp.getLastName(), emp.getPhoneNo(), empId });
	}

}
