package br.fa7.spring.exercicios.services;

import java.util.List;

import br.fa7.spring.exercicios.entity.Employee;
import br.fa7.spring.exercicios.exception.EmployeeNotFoundException;

public interface EmployeeService {

	public List<Employee> findAll();
	
	public Employee findById(Long id);
	
	public void insert(Employee emp);
	
	public Employee update(Employee emp) throws EmployeeNotFoundException;
	
	public void remove(Long id) throws EmployeeNotFoundException;
	
}
