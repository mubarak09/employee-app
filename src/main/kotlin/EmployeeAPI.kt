import java.util.*

var lastId = 0

internal fun getId(): Int {
    return lastId++
}

class EmployeeAPI {

    private val employees = ArrayList<Employee>()

    fun findAll(): List<Employee> {
        return employees
    }

    fun findOne(id: Int): Employee? {
        return employees.find { p -> p.employeeID == id }
    }

    fun create(employee: Employee) {
        employee.employeeID = getId()
        employees.add(employee)
    }

    // Assignment added functionality for highest paid employees
    fun highestPaidEmployees(): Unit {
        logger.info { "sorting employees by gross salary" }

        // Sort the Employee arraylist using lamdas
        var sortedList = employees.sortedByDescending {it.grossSalary }
        // loop through the list and print to the console
        for(emp in sortedList){
            println("""Employee ID: ${emp.employeeID}
                |   Employee Name: ${emp.firstName + " " + emp.surName}
                |   Employee Gross Salary: ${emp.grossSalary}
                |   -----------------------------------------
            """.trimMargin())
        }
    }

    fun editEmployee(){

    }

    fun deleteEmployeeByID(empID: Int){
        logger.info { "Looping through employee arraylist and removing by ID" }
        for(emp in employees){
            if(emp.employeeID == empID){
                employees.remove(emp)
                return
            }
        }
    }
}