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
		assertNotNull(employeeRepository);
		assertTrue(employeeRepository.getEmployeeList().isEmpty());

		employeeRepository=null;
		assertNull(employeeRepository);
	}

	@Test
	public void testFinalTC11() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "19105123456abc", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size,controller.getEmployeesList().size() );
	}

	@Test
	public void testFinalTC10() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "191051234565abcd", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size,controller.getEmployeesList().size() );
	}

	@Test
	public void testFinalTC9() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "191051234565ab", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size,controller.getEmployeesList().size() );
	}

	@Test
	public void testFinalTC8() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "1910512345657", DidacticFunction.ASISTENT, 3000);
		assertTrue(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size+1,controller.getEmployeesList().size() );
	}

	@Test
	public void testFinalTC7() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "19105123456578", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size,controller.getEmployeesList().size() );
	}

	@Test
	public void testFinalTC6() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "191051234565", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size,controller.getEmployeesList().size() );
	}

	@Test
	public void testFinalTC5() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "1910512345657", DidacticFunction.ASISTENT, 1);
		assertTrue(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size+1,controller.getEmployeesList().size() );
	}

	@Test
	public void testFinalTC4() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "1910512345657", DidacticFunction.ASISTENT, 0);
		assertFalse(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size,controller.getEmployeesList().size() );
	}

	@Test
	public void testFinalTC3() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "1910512345657", DidacticFunction.ASISTENT, -1);
		assertFalse(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size,controller.getEmployeesList().size() );
	}

	@Test
	public void testFinalTC2() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee8 = new Employee("ValidLastName", "1910512345657", DidacticFunction.ASISTENT, 300000000);
		assertTrue(employeeRepository.addEmployee(newEmployee8));
		assertEquals(size+1, controller.getEmployeesList().size());
	}

	@Test
	public void testFinalTC1() {
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "1910509111157", DidacticFunction.ASISTENT, 3000);
		assertTrue(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size+1,controller.getEmployeesList().size() );
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
