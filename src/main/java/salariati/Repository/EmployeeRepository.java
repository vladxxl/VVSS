package salariati.Repository;

import salariati.Domain.DidacticFunction;
import salariati.Domain.Employee;
import salariati.Domain.EmployeeValidator;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class EmployeeRepository implements EmployeeRepositoryInterface {

	private List<Employee> employeeList;
	private EmployeeValidator employeeValidator;
	
	public EmployeeRepository() {
		employeeValidator = new EmployeeValidator();
		employeeList = new ArrayList<>();
	}
	
	@Override
	public boolean addEmployee(Employee employee) {
		if ( employeeValidator.isValid(employee)) {
			employeeList.add(employee);
			return true;
		}
		return false;
	}

	@Override
	public boolean modifyEmployee(Employee oldEmployee, String function) {
		if (function.equals("ASISTENT") || function.equals("LECTURER") || function.equals("TEACHER")) {
			employeeList.remove(oldEmployee);
			Employee newEmployee = new Employee(oldEmployee.getLastName(), oldEmployee.getFirstName(), oldEmployee.getCnp(), DidacticFunction.valueOf(function), oldEmployee.getSalary());
			employeeList.add(newEmployee);
			return true;
		}
		return false;
	}

	@Override
	public List<Employee> getEmployeeList() {
		return employeeList;
	}

	@Override
	public List<Employee> getEmployeeByCriteria() {
        List<Employee> employeeList = getEmployeeList();
        for (Employee employee : employeeList) {
            String dateOfBirth = employee.getCnp().substring(1, 8);
            Integer year, month, day;
            if (employee.getCnp().substring(0, 1).equals("1") || employee.getCnp().substring(0, 1).equals("2"))
                year = Integer.parseInt("19" + dateOfBirth.substring(0, 2));
            else
                year = Integer.parseInt("20" + dateOfBirth.substring(0, 2));
            month = Integer.parseInt(dateOfBirth.substring(2, 4));
            day = Integer.parseInt(dateOfBirth.substring(4, 6));
            LocalDate dateOfBirthh = LocalDate.of(year, month, day);
            LocalDate now = LocalDate.now();
            Integer ageOfEmployee = Period.between(dateOfBirthh, now).getYears();
            employee.setCnp(ageOfEmployee.toString());
        }
        employeeList.sort((p1, p2) -> {
            if (p2.getSalary().compareTo(p1.getSalary()) == 0) {
                return p1.getCnp().compareTo(p2.getCnp());
            } else {
                return p2.getSalary().compareTo(p1.getSalary());
            }
        });
        return employeeList;
    }
}
