package salariati;

import org.junit.Before;
import org.junit.Test;
import salariati.Controller.EmployeeController;
import salariati.Domain.DidacticFunction;
import salariati.Domain.Employee;
import salariati.Domain.EmployeeValidator;
import salariati.Repository.EmployeeRepository;
import salariati.Repository.EmployeeRepositoryInterface;

import static org.junit.Assert.*;


public class AddEmployeeTest {

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
		assertNotNull(controller);
		assertTrue(controller.getEmployeesList().isEmpty());
		Employee newEmployee = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 3000);
		assertTrue(employeeRepository.addEmployee(newEmployee));
		assertFalse(controller.getEmployeesList().isEmpty());
		assertEquals(1, controller.getEmployeesList().size());
		Employee newEmployee2 = new Employee("ValidLastName", "191050asd057", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeRepository.addEmployee(newEmployee2));
		assertFalse(controller.getEmployeesList().isEmpty());
		assertNotEquals(2, controller.getEmployeesList().size());

		Employee newEmployee3 = new Employee("ValidLastName", "1910509111157", DidacticFunction.ASISTENT, 3000);
		assertTrue(employeeRepository.addEmployee(newEmployee3));
		assertEquals(2, controller.getEmployeesList().size());
		Employee newEmployee4 = new Employee("ValidLastName", "1910512345657", DidacticFunction.ASISTENT, 3000);
		assertTrue(employeeRepository.addEmployee(newEmployee4));
		assertEquals(3, controller.getEmployeesList().size());
		Employee newEmployee5 = new Employee("ValidLastName", "1910509111asd", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeRepository.addEmployee(newEmployee5));
		assertNotEquals(4, controller.getEmployeesList().size());
		Employee newEmployee6 = new Employee("ValidLastName", "1.,657", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeRepository.addEmployee(newEmployee6));
		assertNotEquals(4, controller.getEmployeesList().size());

		Employee newEmployee7 = new Employee("ValidLastName", "1910512345657", DidacticFunction.ASISTENT, 3000);
		assertTrue(employeeRepository.addEmployee(newEmployee7));
		assertEquals(4, controller.getEmployeesList().size());
		Employee newEmployee8 = new Employee("ValidLastName", "1910512345657", DidacticFunction.ASISTENT, 300000000);
		assertTrue(employeeRepository.addEmployee(newEmployee8));
		assertEquals(5, controller.getEmployeesList().size());
		Employee newEmployee9 = new Employee("ValidLastName", "1910512345657", DidacticFunction.ASISTENT, 0);
		assertFalse(employeeRepository.addEmployee(newEmployee9));
		assertNotEquals(6, controller.getEmployeesList().size());
		Employee newEmployee10 = new Employee("ValidLastName", "1910512345657", DidacticFunction.ASISTENT, -1);
		assertFalse(employeeRepository.addEmployee(newEmployee10));
		assertNotEquals(6, controller.getEmployeesList().size());


		controller=null;
		assertNull(controller);
	}

	@Test
	public void testAddNewEmployee() {
		assertTrue(controller.getEmployeesList().isEmpty());
		Employee newEmployee = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 3000);
		assertTrue(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee);
		assertEquals(1, controller.getEmployeesList().size());
		Employee newEmployee2 = new Employee("ValidLastName", "191050asd057", DidacticFunction.ASISTENT, 3000);
		assertTrue(employeeValidator.isValid(newEmployee));
		controller.addEmployee(newEmployee2);
		assertEquals(1, controller.getEmployeesList().size());
		controller.addEmployee(newEmployee);
		assertEquals(2, controller.getEmployeesList().size());
		assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
	}

}
