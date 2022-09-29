package SpringBoot.FinalProject.repository;

import SpringBoot.FinalProject.model.Employee;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public class EmployeeRepository {
	List<Employee> employees;

	public EmployeeRepository(){
		employees = new ArrayList<>();
		employees.add(new Employee(UUID.randomUUID(),"Alexandru", "Mohor", "mohor.alexandru@gmail.com", "1234567", 3200));
		employees.add(new Employee(UUID.randomUUID(),"Madalina", "Eleonora", "madalina.eleonora@gmail.com", "123231312", 10000));
		employees.add(new Employee(UUID.randomUUID(),"Mugurel", "Maftei", "mugurel.maftei@gmail.com", "141414153", 7000));
	}

	public void addEmployee(Employee employee){
		this.employees.add(employee);
	}

	public List<Employee> getEmployees(){
		List<Employee> listOfEmployees = new ArrayList<>();
		listOfEmployees.addAll(employees);
		return listOfEmployees;
	}

	public void deleteEmployeeById(UUID employeeId){
		Iterator<Employee> employeeIterator = employees.iterator();
		while(employeeIterator.hasNext()){
			Employee employee = employeeIterator.next();
			if (employee.getId().equals(employeeId)){
				employeeIterator.remove();
			}
		}
	}

	public Employee findEmployeeById(UUID employeeId){
		for (Employee employee : employees){
			if (employee.getId().equals(employeeId)){
				return employee;
			}
		}
		throw new IllegalArgumentException("No employee found with ID" + employeeId);
	}
}
