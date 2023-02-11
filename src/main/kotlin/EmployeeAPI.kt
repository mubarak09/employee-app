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

    // Edit the employees information by the ID specified by the user
    fun editEmployee(){
        println("Please enter the ID of the employee you would like to amend: ")
        var empID = readLine()?.toInt()

        // Print the menu for editing
        println("What would you like to update?")
        println("""
        1. Name
        2. Salary
        3. annualBonus
        """.trimIndent())

        // Menu option
        var option = readLine()?.toInt()

        // When statement to filter options from menu
        when(option){
            1 -> {
                print("Enter updated first name: ")
                val firstName = readLine().toString()
                print("Enter updated surname: ")
                val surname = readLine().toString()

                val employeeToUpdate = employees.find{ it.employeeID == empID}
                employeeToUpdate?.firstName = firstName
                employeeToUpdate?.surName = surname
            }
            2 -> {
                print("Enter updated gross salary: ")
                val grossSalary = readLine()!!.toDouble()

                val employeeToUpdate = employees.find{ it.employeeID == empID}
                employeeToUpdate?.grossSalary = grossSalary
            }
            3 -> {
                print("Enter updated Annual Bonus: ")
                val annualBonus= readLine()!!.toDouble()

                val employeeToUpdate = employees.find{ it.employeeID == empID}
                employeeToUpdate?.annualBonus = annualBonus
            }
        }
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