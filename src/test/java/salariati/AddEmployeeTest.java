//package salariati;
//
//import static org.junit.Assert.*;
//
//import salariati.Controller.EmployeeController;
//import salariati.Domain.DidacticFunction;
//import salariati.Domain.Employee;
//import salariati.Domain.EmployeeValidator;
//import salariati.Repository.EmployeeRepository;
//import salariati.Repository.EmployeeRepositoryInterface;
//
//import org.junit.Before;
//import org.junit.Test;
//
//
//public class AddEmployeeTest {
//
//	private EmployeeRepositoryInterface employeeRepository;
//	private EmployeeController controller;
//	private EmployeeValidator employeeValidator;
//
//	@Before
//	public void setUp() {
//		employeeRepository = new EmployeeRepository();
//		controller         = new EmployeeController(employeeRepository);
//		employeeValidator  = new EmployeeValidator();
//	}
//
//	@Test
//	public void testRepositoryMock() {
//		assertFalse(controller.getEmployeesList().isEmpty());
//		assertEquals(6, controller.getEmployeesList().size());
//	}
//
//	@Test
//	public void testAddNewEmployee() {
//		Employee newEmployee = new Employee("ValidLastName", "1910509055057", DidacticFunction.ASISTENT, "3000");
//		assertTrue(employeeValidator.isValid(newEmployee));
//		controller.addEmployee(newEmployee);
//		assertEquals(7, controller.getEmployeesList().size());
//		assertTrue(newEmployee.equals(controller.getEmployeesList().get(controller.getEmployeesList().size() - 1)));
//	}
//
//}
