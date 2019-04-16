package salariati.Repository;

import salariati.Domain.Employee;

import java.util.List;

public interface EmployeeRepositoryInterface {
	
	boolean addEmployee(Employee employee);
	boolean modifyEmployee(Employee oldEmployee, String function);
	List<Employee> getEmployeeList();
	Employee getEmployeeByCNP(String CNP);
	List<Employee> getEmployeeByCriteria();


}
