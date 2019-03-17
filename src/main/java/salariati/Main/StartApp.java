package salariati.Main;

//functionalitati
//F01.	 adaugarea unui nou angajat (nume, prenume, CNP, functia didactica, salariul de incadrare);
//F02.	 modificarea functiei didactice (asistent/lector/conferentiar/profesor) a unui angajat;
//F03.	 afisarea salariatilor ordonati descrescator dupa salariu si crescator dupa varsta (CNP).

import salariati.Controller.EmployeeController;
import salariati.Domain.DidacticFunction;
import salariati.Domain.Employee;
import salariati.Repository.EmployeeFileRepository;
import salariati.Repository.EmployeeRepository;
import salariati.Repository.EmployeeRepositoryInterface;

public class StartApp {
	
	public static void main(String[] args) {
		
		EmployeeRepositoryInterface employeesRepository = new EmployeeRepository();
        EmployeeRepositoryInterface employeesFileRepository = new EmployeeFileRepository();
		EmployeeController employeeController = new EmployeeController(employeesRepository);
        EmployeeController employeeFileController = new EmployeeController(employeesFileRepository);

		Employee employee1   = new Employee("Bocsa", "Mirela", "2980221010738", DidacticFunction.ASISTENT, 25000);
		Employee employee2   = new Employee("Birsan", "Vlad", "1970928111738", DidacticFunction.LECTURER, 2500);
		Employee employee3  = new Employee("Pop", "Ioana", "5020221701278", DidacticFunction.LECTURER, 2500);

		employeeController.addEmployee( employee1 );
		employeeController.addEmployee( employee2 );
		employeeController.addEmployee( employee3 );

        employeeFileController.addEmployee( employee1 );
        employeeFileController.addEmployee( employee2 );
        employeeFileController.addEmployee( employee3 );

        System.out.println("Consola");

		for(Employee _employee : employeeController.getEmployeesList())
			System.out.println(_employee.toString());
		System.out.println("-----------------------------------------");

        System.out.println("Fisier");

        for(Employee _employee : employeeFileController.getEmployeesList())
            System.out.println(_employee.toString());
        System.out.println("-----------------------------------------");

		employeeController.modifyEmployee(employee3, "TEACHER");
		employeeFileController.modifyEmployee(employee3, "TEACHER");

        System.out.println("Consola");

        for(Employee _employee : employeeController.getEmployeesList())
            System.out.println(_employee.toString());
        System.out.println("-----------------------------------------");

		for(Employee employee : employeeController.getEmployeesSorted())
		    System.out.println(employee.toString());
	}

}
