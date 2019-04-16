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


public class UpdateEmployeeTest {

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
	public void testFinalTC3() {
		Employee newEmployee3 = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 3000);
		String function = "JANITOR";
		assertFalse(employeeRepository.modifyEmployee(newEmployee3,function));
	}

	@Test
	public void testFinalTC2() {
		Employee newEmployee3 = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 3000);
		employeeRepository.addEmployee(newEmployee3);
		assertTrue(employeeRepository.modifyEmployee(newEmployee3,"TEACHER"));
	}

	@Test
	public void testFinalTC1() {
		Employee newEmployee3 = new Employee("ValidLastName", "19105091", DidacticFunction.ASISTENT, 3000);
		assertFalse(employeeRepository.modifyEmployee(newEmployee3,"LECTURER"));
	}

	@Test
	public void testUpdateNewEmployee() {
		Employee oldEmployee = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 3000);
		employeeRepository.addEmployee(oldEmployee);
		employeeRepository.modifyEmployee(oldEmployee,"LECTURER");
		assertTrue(employeeRepository.getEmployeeByCNP("1910509055057").getFunction().equals(DidacticFunction.valueOf("LECTURER")));

	}

}
