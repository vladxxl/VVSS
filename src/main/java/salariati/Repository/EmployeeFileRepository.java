package salariati.Repository;

import salariati.Domain.DidacticFunction;
import salariati.Domain.Employee;
import salariati.Domain.EmployeeException;
import salariati.Domain.EmployeeValidator;

import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;

public class EmployeeFileRepository implements EmployeeRepositoryInterface {
	
	private final String employeeDBFile = "employees.txt";
	private EmployeeValidator employeeValidator = new EmployeeValidator();

	@Override
	public boolean addEmployee(Employee employee) {
		if( employeeValidator.isValid(employee) ) {
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(new FileWriter(employeeDBFile, true));
				bw.write(employee.toString());
				bw.newLine();
				bw.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	@Override
	public boolean modifyEmployee(Employee oldEmployee, String function) {
        String employeesDBFile = "employeesfile.txt";
		if( function.equals("ASISTENT") || function.equals("LECTURER") || function.equals("TEACHER")) {
			BufferedWriter bw;
			BufferedReader br;
			try {
                br = new BufferedReader(new FileReader(employeeDBFile));
                bw = new BufferedWriter(new FileWriter(employeesDBFile));
                String line;
                while ((line = br.readLine()) != null) {
                    try {
                        if (!line.equalsIgnoreCase(oldEmployee.toString())) {
                            bw.write(line);
                            bw.newLine();
                        }
                    }
                    catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }
                }
                Employee newEmployee = new Employee(oldEmployee.getLastName(), oldEmployee.getFirstName(), oldEmployee.getCnp(), DidacticFunction.valueOf(function), oldEmployee.getSalary());
                bw.append(newEmployee.toString());
                bw.newLine();
                bw.close();
				return true;
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return false;
		
	}

	@Override
	public List<Employee> getEmployeeList() {
		List<Employee> employeeList = new ArrayList<Employee>();
		
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(employeeDBFile));
			String line;
			int counter = 0;
			while ((line = br.readLine()) != null) {
				Employee employee;
				try {
					employee = Employee.getEmployeeFromString(line, counter);
					employeeList.add(employee);
				} catch(EmployeeException ex) {
					System.err.println("Error while reading: " + ex.toString());
				}
			}
		} catch (FileNotFoundException e) {
			System.err.println("Error while reading: " + e);
		} catch (IOException e) {
			System.err.println("Error while reading: " + e);
		} finally {
			if (br != null)
				try {
					br.close();
				} catch (IOException e) {
					System.err.println("Error while closing the file: " + e);
				}
		}
		
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
