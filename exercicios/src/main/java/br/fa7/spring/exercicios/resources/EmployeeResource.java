package br.fa7.spring.exercicios.resources;


import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.fa7.spring.exercicios.entity.Employee;
import br.fa7.spring.exercicios.exception.EmployeeNotFoundException;
import br.fa7.spring.exercicios.services.EmployeeService;

@Component
@Path("/employees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class EmployeeResource {
	
	@Autowired
	private EmployeeService employeeService;
	
	@GET
	public List<Employee> findAll(){
		return employeeService.findAll();
	}
	@GET
	@Path("{id}")
	public Response get(@PathParam("id") Long id){
		Employee employee = employeeService.findById(id);
		if(employee==null){
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(employee).build();
	}
	
	@POST
	public Employee insert(Employee employee){
		employeeService.insert(employee);
		return employee;
	}
	
	@PUT
	@Path("{id}")
	public Response update(@PathParam("id") Long id, Employee employee){
		employee.setId(id);
		try {
			Employee employeeDb  = employeeService.update(employee);
			return Response.ok(employeeDb).build();
		} catch (EmployeeNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
	}
	
	@DELETE
	@Path("{id}")
	public Response remove(@PathParam("id") Long id){
		try {
			employeeService.remove(id);
			return Response.status(Status.OK).build(); //NO_CONTENT
		} catch (EmployeeNotFoundException e) {
			return Response.status(Status.NOT_FOUND).build();
		}
		
	}
}
