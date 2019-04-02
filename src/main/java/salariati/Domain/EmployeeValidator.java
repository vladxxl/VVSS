package salariati.Domain;

public class EmployeeValidator {

    public EmployeeValidator() {
    }

    public boolean isValid(Employee employee) {
        boolean isLastNameValid = employee.getLastName().matches("[a-zA-Z]+") && (employee.getLastName().length() > 2);
        boolean isFirstNameValid = employee.getLastName().matches("[a-zA-Z]+") && (employee.getLastName().length() > 2);
        boolean isCNPValid = employee.getCnp().matches("[0-9]+") && (employee.getCnp().length() == 13);
        boolean isFunctionValid = employee.getFunction().equals(DidacticFunction.ASISTENT) ||
                employee.getFunction().equals(DidacticFunction.LECTURER) ||
                employee.getFunction().equals(DidacticFunction.TEACHER);
        boolean isSalaryValid = employee.getSalary().toString().matches("[0-9]+") && (employee.getSalary().toString().length() > 0) && (employee.getSalary() > 0);

        return isLastNameValid && isFirstNameValid && isCNPValid && isFunctionValid && isSalaryValid;
    }

}
