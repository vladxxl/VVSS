package salariati;

import org.junit.Before;
import org.junit.Test;
import salariati.Controller.EmployeeController;
import salariati.Domain.DidacticFunction;
import salariati.Domain.Employee;
import salariati.Domain.EmployeeValidator;
import salariati.Repository.EmployeeRepository;
import salariati.Repository.EmployeeRepositoryInterface;

import java.util.List;

import static org.junit.Assert.*;


public class GetSortedEmployeeTest {

	private EmployeeRepositoryInterface employeeRepository;
	private EmployeeController controller;
	private EmployeeValidator employeeValidator;

	@Before
	public void setUp() {
		employeeRepository = new EmployeeRepository();
		controller         = new EmployeeController(employeeRepository);
		employeeValidator  = new EmployeeValidator();
	}

	@Test
	public void testRepositoryMock() {
		assertNotNull(employeeRepository);
		assertTrue(employeeRepository.getEmployeeList().isEmpty());

		employeeRepository=null;
		assertNull(employeeRepository);
	}

	@Test
	public void testFinalTC2() {
		assertTrue(controller.getEmployeesList().isEmpty());
		Employee newEmployee = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 5000);
		controller.addEmployee(newEmployee);
		Employee newEmployee2 = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 4000);
		controller.addEmployee(newEmployee2);

		List<Employee> sortedList = controller.getEmployeesSorted();

		assertNotEquals(newEmployee, sortedList.get(1));
		assertNotEquals(newEmployee2, sortedList.get(0));
	}

	@Test
	public void testFinalTC1() {
		assertTrue(controller.getEmployeesList().isEmpty());
		Employee newEmployee = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 5000);
		controller.addEmployee(newEmployee);
		Employee newEmployee2 = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 4000);
		controller.addEmployee(newEmployee2);
		Employee newEmployee3 = new Employee("ValidLastName", "1900509055057", DidacticFunction.ASISTENT, 3000);
		controller.addEmployee(newEmployee3);
		Employee newEmployee4 = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 3000);
		controller.addEmployee(newEmployee4);

		List<Employee> sortedList = controller.getEmployeesSorted();

		assertEquals(newEmployee, sortedList.get(0));
		assertEquals(newEmployee2, sortedList.get(1));
		assertEquals(newEmployee4, sortedList.get(2));
		assertEquals(newEmployee3, sortedList.get(3));
	}

}
