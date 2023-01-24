import java.math.BigDecimal
import java.math.RoundingMode

val firstName: String = "Joe"
val surName: String = "Soap"
val gender: Char = 'm'
val employeeID: Int = 6143
val grossSalary: Double = 67_543.21
val payePercentage: Float = 38.5f
val prsiPercentage: Float = 5.2f
val annualBonus: Double = 1450.50
val cycleToWorkSchemeMonthly: Double = 54.33

fun main(args: Array<String>) {
    println("Paylist Printer")
    printPaySlip()

    println("\nPayslip with Rounding")
    printPaySlipRounding()
}

// Functions for calculations
fun getMonthlySalary() = grossSalary/12
fun getMonthlyPAYE() = (getMonthlySalary() * (payePercentage/100))
fun getMonthlyPRSI() = (getMonthlySalary() * (prsiPercentage/100))
fun getGrossMonthlyPay() = (getMonthlySalary() + (annualBonus /12))
fun getTotalMonthlyDeductions() = (getMonthlyPRSI() + getMonthlyPAYE() + cycleToWorkSchemeMonthly)
fun getNetMonthlyPay() = (getGrossMonthlyPay() - getTotalMonthlyDeductions())
fun getGrossPay() = (getMonthlySalary() + (annualBonus/12))

// Function to format decimal places to 2.
fun formatted(doubleToFormat: Double): BigDecimal? {
    return BigDecimal(doubleToFormat).setScale(2, RoundingMode.HALF_EVEN)
}

fun printPaySlip(): Unit {
    println("------------------------------------------------------------------------------")
    println("|                              Monthly Payslip                               |")
    println("|----------------------------------------------------------------------------|")
    println("|                                                                            |")
    println("|   Employee Name: ${firstName.uppercase()} ${surName.uppercase()} (${gender.uppercase()})                Employee ID: $employeeID             |")
    println("|                                                                            |")
    println("|----------------------------------------------------------------------------|")
    println("|                                                                            |")
    println("|   PAYMENT DETAILS                            DEDUCTION DETAILS             |")
    println("|                                                                            |")
    println("|----------------------------------------------------------------------------|")
    println("|   Salary: ${grossSalary/12}                  PAYE: ${getMonthlyPAYE()}       |")
    println("|   Bonus: ${annualBonus/12}                             PRSI: ${getMonthlyPRSI()}       |")
    println("|                                              Cycle To Work: $cycleToWorkSchemeMonthly          |")
    println("|----------------------------------------------------------------------------|")
    println("|   Gross: ${getGrossMonthlyPay()}             Total Deductions: ${getTotalMonthlyDeductions()}|")
    println("|----------------------------------------------------------------------------|")
    println("|                          NET PAY:    ${(getGrossPay() - ((getMonthlySalary() * (prsiPercentage / 100)) + cycleToWorkSchemeMonthly))}                     |")
    println("|----------------------------------------------------------------------------|")
}

fun printPaySlipRounding(): Unit {
    println("------------------------------------------------------------------------------")
    println("|                              Monthly Payslip                               |")
    println("|----------------------------------------------------------------------------|")
    println("|                                                                            |")
    println("|   Employee Name: ${firstName.uppercase()} ${surName.uppercase()} (${gender.uppercase()})                Employee ID: $employeeID             |")
    println("|                                                                            |")
    println("|----------------------------------------------------------------------------|")
    println("|                                                                            |")
    println("|   PAYMENT DETAILS                            DEDUCTION DETAILS             |")
    println("|                                                                            |")
    println("|----------------------------------------------------------------------------|")
    println("|   Salary: ${formatted(grossSalary/12)}                            PAYE: ${formatted(getMonthlyPAYE())}                 |")
    println("|   Bonus: ${formatted(annualBonus/12)}                              PRSI: ${formatted(getMonthlyPRSI())}                  |")
    println("|                                              Cycle To Work: ${formatted(cycleToWorkSchemeMonthly)}          |")
    println("|----------------------------------------------------------------------------|")
    println("|   Gross: ${formatted(getGrossMonthlyPay())}             Total Deductions: ${formatted(getTotalMonthlyDeductions())}                     |")
    println("|----------------------------------------------------------------------------|")
    println("|                          NET PAY:    ${formatted((getGrossPay() - ((getMonthlySalary() * (prsiPercentage / 100)) + cycleToWorkSchemeMonthly)))}                               |")
    println("|----------------------------------------------------------------------------|")
}
