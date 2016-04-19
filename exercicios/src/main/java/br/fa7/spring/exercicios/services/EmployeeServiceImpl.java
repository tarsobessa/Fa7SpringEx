package br.fa7.spring.exercicios.services;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.fa7.spring.exercicios.entity.Employee;
import br.fa7.spring.exercicios.exception.EmployeeNotFoundException;

@Service
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired
	private EntityManager entityManager;

	@Override
	public List<Employee> findAll() {
		return entityManager.createQuery("From Employee e", Employee.class).getResultList();
	}

	@Override
	@Transactional
	public void insert(Employee emp) {
		entityManager.persist(emp);
	}

	@Override
	@Transactional (rollbackFor={EmployeeNotFoundException.class})
	public Employee update(Employee emp) {
		Employee employeeDb = findById(emp.getId());
		if(employeeDb!=null){
			employeeDb.setName(emp.getName());
			return entityManager.merge(employeeDb);
		}
		throw new EmployeeNotFoundException(String.format("Empregado de id '%d' não encontrado", emp.getId()));
	}

	@Override
	@Transactional
	public void remove(Long id) {
		Employee employee = findById(id);
		if(employee!=null){
			entityManager.remove(employee);
		}else{
			throw new EmployeeNotFoundException(String.format("Empregado de id '%d' não encontrado", id));
		}
	}

	@Override
	public Employee findById(Long id) {
		return entityManager.find(Employee.class, id);
	}

}
