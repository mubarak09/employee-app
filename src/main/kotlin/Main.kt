import java.math.BigDecimal
import java.math.RoundingMode

import Employee

var employee =  Employee("Joe", "Soap", 'm', 6143, 67543.21, 38.5, 5.2, 1450.50, 54.33)


fun getFullName() = when (employee.gender){
    'm', 'M' -> "Mr. ${employee.firstName} ${employee.surName}"
    'f', 'F' -> "Ms.  ${employee.firstName} ${employee.surName}"
    else ->  "${employee.firstName} ${employee.surName}"
}


fun main(args: Array<String>){
    // Add new employee
    add()
    var input : Int

    do {
        input = menu()
        when(input) {
            1 -> println("Monthly Salary: ${getMonthlySalary()}")
            2 -> println("Monthly PRSI: ${getMonthlyPRSI()}")
            3 ->println("Monthly PAYE: ${getMonthlyPAYE()}")
            4 -> println("Monthly Gross Pay: ${getGrossMonthlyPay()}")
            5 -> println("Monthly Total Deductions: ${getTotalMonthlyDeductions()}")
            6 -> println("Monthly Net Pay: ${getMonthlySalary()}")
            7 -> println(getPaySlipRounding())
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
}


fun menu() : Int {
    print("""
         Employee Menu for ${getFullName()}
           1. Monthly Salary
           2. Monthly PRSI
           3. Monthly PAYE
           4. Monthly Gross Pay
           5. Monthly Total Deductions
           6. Monthly Net Pay
           7. Full Payslip
          -1. Exit
         Enter Option : """)
    return readLine()!!.toInt()
}


// Functions for calculations
fun getMonthlySalary() = employee.grossSalary/12
fun getMonthlyPAYE() = (getMonthlySalary() * (employee.payePercentage/100))
fun getMonthlyPRSI() = (getMonthlySalary() * (employee.prsiPercentage/100))
fun getGrossMonthlyPay() = (getMonthlySalary() + (employee.annualBonus /12))
fun getTotalMonthlyDeductions() = (getMonthlyPRSI() + getMonthlyPAYE() + employee.cycleToWorkSchemeMonthly)

// Function to format decimal places to 2.
fun formatted(doubleToFormat: Double): BigDecimal? {
    return BigDecimal(doubleToFormat).setScale(2, RoundingMode.HALF_EVEN)
}

fun getPaySlipRounding(): String {
    return(
        """
        +____________________________________________________________________+
         Monthly Payslip:             ${getFullName().uppercase()} (${employee.gender.uppercase()}), ID: $employee.employeeID                  
        +____________________________________________________________________+    
              PAYMENT DETAILS (gross pay: ${getGrossMonthlyPay()})                                                                    
        +____________________________________________________________________+
                   Salary: ${formatted(employee.grossSalary/12)}
                   Bonus:  ${formatted(employee.annualBonus/12)}             
        +____________________________________________________________________+
              DEDUCTION DETAILS (total Deductions: ${formatted(getTotalMonthlyDeductions())})      
        +____________________________________________________________________+
                   PAYE: ${formatted(getMonthlyPAYE())}               
                   PRSI: ${formatted(getMonthlyPRSI())} 
                   Cycle To Work: $employee.cycleToWorkSchemeMonthly        
        +____________________________________________________________________+
             NET PAY: ${formatted((getGrossMonthlyPay() - ((getMonthlySalary() * (employee.prsiPercentage / 100)) + employee.cycleToWorkSchemeMonthly)))}
        +____________________________________________________________________+"""
    )
}

fun add(){
    print("Enter first name: ")
    val firstName = readLine().toString()
    print("Enter surname: ")
    val surname = readLine().toString()
    print("Enter gender (m/f): ")
    val gender = readLine()!!.toCharArray()[0]
    print("Enter employee ID: ")
    val employeeID = readLine()!!.toInt()
    print("Enter gross salary: ")
    val grossSalary = readLine()!!.toDouble()
    print("Enter PAYE %: ")
    val payePercentage = readLine()!!.toDouble()
    print("Enter PRSI %: ")
    val prsiPercentage = readLine()!!.toDouble()
    print("Enter Annual Bonus: ")
    val annualBonus= readLine()!!.toDouble()
    print("Enter Cycle to Work Deduction: ")
    val cycleToWorkMonthlyDeduction= readLine()!!.toDouble()

    employee = Employee(firstName, surname, gender, employeeID, grossSalary, payePercentage, prsiPercentage, annualBonus, cycleToWorkMonthlyDeduction)
}

