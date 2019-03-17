package salariati.Domain;

public class Employee {

    /**
     * The last name of the employee
     */
    private String lastName;


    /**
     * The last name of the employee
     */
    private String firstName;

    /**
     * The unique id of the employee
     */
    private String cnp;

    /**
     * The didactic function of the employee inside the university
     */
    private DidacticFunction function;

    /**
     * The salary of the employee
     */
    private Integer salary;

    /**
     * Default constructor for employee
     */
    public Employee() {
        this.lastName = "";
        this.firstName = "";
        this.cnp = "";
        this.function = DidacticFunction.ASISTENT;
        this.salary = null;
    }

    /**
     * Constructor with fields for employee
     */
    public Employee(String lastName, String firstName, String cnp, DidacticFunction function, Integer salary) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.cnp = cnp;
        this.function = function;
        this.salary = salary;
    }

    public Employee(String name, String cnp, DidacticFunction function, Integer salary) {
        this.lastName = name;
        this.firstName = name;
        this.cnp = cnp;
        this.function = function;
        this.salary = salary;
    }

    public Employee(String name, String cnp, DidacticFunction function, String salary) {
        this.lastName = name;
        this.firstName = name;
        this.cnp = cnp;
        this.function = function;
        this.salary = Integer.parseInt(salary);
    }

    /**
     * Getter for the employee last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Setter for the employee last name
     *
     * @param lastName the last name to be set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Getter for the employee first name
     */

    public String getFirstName() {
        return firstName;
    }

    /**
     * Setter for the employee first name
     *
     * @param firstName the first name to be set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Getter for the employee CNP
     */
    public String getCnp() {
        return cnp;
    }

    /**
     * Setter for the employee CNP
     *
     * @param cnp the CNP to be set
     */
    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    /**
     * Getter for the employee didactic function
     */
    public DidacticFunction getFunction() {
        return function;
    }

    /**
     * Setter for the employee function
     *
     * @param function the function to be set
     */
    public void setFunction(DidacticFunction function) {
        this.function = function;
    }

    /**
     * Getter for the employee salary
     */
    public Integer getSalary() {
        return salary;
    }

    /**
     * Setter for the salary
     *
     * @param salary the salary to be set
     */
    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    /**
     * Setter for the salary
     *
     * @param salary the salary to be set
     */
    public void setSalary(String salary) {
        this.salary = Integer.parseInt(salary);
    }


    /**
     * toString function for employee
     */
    @Override
    public String toString() {
        String employee = "";

        employee += lastName + ";";
        employee += firstName + ";";
        employee += cnp + ";";
        employee += function.toString() + ";";
        employee += salary.toString();

        return employee;
    }

    /**
     * equals function for employee
     */
    public boolean equals(Employee comparableEmployee) {
        boolean hasSameLastName = this.lastName.equals(comparableEmployee.getLastName()),
                hasSameFirstName = this.firstName.equals(comparableEmployee.getFirstName()),
                hasSameCNP = this.cnp.equals(comparableEmployee.getCnp()),
                hasSameFunction = this.function.equals(comparableEmployee.getFunction()),
                hasSameSalary = this.salary.equals(comparableEmployee.getSalary());
        return hasSameLastName && hasSameFirstName && hasSameCNP && hasSameFunction && hasSameSalary;
    }

    /**
     * Returns the Employee after parsing the given line
     *
     * @param _employee the employee given as String from the input file
     * @param line      the current line in the file
     * @return if the given line is valid returns the corresponding Employee
     * @throws EmployeeException
     */
    public static Employee getEmployeeFromString(String _employee, int line) throws EmployeeException {
        Employee employee = new Employee();

        String[] attributes = _employee.split("[;]");

        if (attributes.length != 5) {
            throw new EmployeeException("Invalid line at: " + line);
        } else {
            EmployeeValidator validator = new EmployeeValidator();
            employee.setLastName(attributes[0]);
            employee.setFirstName(attributes[1]);
            employee.setCnp(attributes[2]);

            if (attributes[3].equals("ASISTENT"))
                employee.setFunction(DidacticFunction.ASISTENT);
            if (attributes[3].equals("LECTURER"))
                employee.setFunction(DidacticFunction.LECTURER);
            if (attributes[3].equals("TEACHER"))
                employee.setFunction(DidacticFunction.TEACHER);

            employee.setSalary(Integer.parseInt(attributes[4]));

            if (!validator.isValid(employee)) {
                throw new EmployeeException("Invalid line at: " + line);
            }
        }

        return employee;
    }

}
