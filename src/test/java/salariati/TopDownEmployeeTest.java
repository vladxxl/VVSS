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


public class TopDownEmployeeTest {

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
		int size=controller.getEmployeesList().size();
		Employee newEmployee3 = new Employee("ValidLastName", "1910509111157", DidacticFunction.ASISTENT, 3000);
		assertTrue(employeeRepository.addEmployee(newEmployee3));
		assertEquals(size+1,controller.getEmployeesList().size() );
	}

	@Test
	public void testBigBangEmployee1() {
		int size=controller.getEmployeesList().size();

		assertTrue(controller.getEmployeesList().isEmpty());
		Employee newEmployee = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 5000);
		controller.addEmployee(newEmployee);
		Employee newEmployee2 = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 4000);
		controller.addEmployee(newEmployee2);

		assertEquals(size+2,controller.getEmployeesList().size() );
	}

	@Test
	public void testBigBangEmployee2() {
		int size=controller.getEmployeesList().size();

		assertTrue(controller.getEmployeesList().isEmpty());
		Employee newEmployee = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 5000);
		controller.addEmployee(newEmployee);
		Employee newEmployee2 = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 4000);
		controller.addEmployee(newEmployee2);

		String function = "LECTURER";
		assertTrue(employeeRepository.modifyEmployee(newEmployee,function));
	}

	@Test
	public void testBigBangEmployee3() {
		int size=controller.getEmployeesList().size();

		Employee newEmployee = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 5000);
		controller.addEmployee(newEmployee);
		Employee newEmployee2 = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, 4000);
		controller.addEmployee(newEmployee2);

		String function = "LECTURER";
		employeeRepository.modifyEmployee(newEmployee,function);

		List<Employee> sortedList = controller.getEmployeesSorted();

		assertEquals(sortedList.get(0).getFunction(),DidacticFunction.valueOf(function));
	}

}
