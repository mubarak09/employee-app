import java.math.BigDecimal
import java.math.RoundingMode

class Employee (var firstName: String, var surName: String, var gender: Char, var employeeID: Int, var grossSalary: Double,
                var payePercentage: Double, var prsiPercentage: Double, var annualBonus: Double, var cycleToWorkSchemeMonthly: Double){

    // Functions for calculations
    fun getMonthlySalary() = grossSalary/12
    fun getMonthlyPRSI() = (getMonthlySalary() * (prsiPercentage/100))
    fun getMonthlyPAYE() = (getMonthlySalary() * (payePercentage/100))
    fun getGrossMonthlyPay() = (getMonthlySalary() + (annualBonus /12))
    fun getTotalMonthlyDeductions() = (getMonthlyPRSI() + getMonthlyPAYE() + cycleToWorkSchemeMonthly)
    fun getNetMonthlyPay() = formatted(getGrossMonthlyPay() - getTotalMonthlyDeductions())

    // Function to format decimal places to 2.
    fun formatted(doubleToFormat: Double): BigDecimal? {
        return BigDecimal(doubleToFormat).setScale(2, RoundingMode.HALF_EVEN)
    }

    fun getPaySlipRounding(): String {
        return(
                """
        +____________________________________________________________________+
         Monthly Paysli:             ${getFullName().uppercase()} (${gender.uppercase()}), ID: $employeeID                  
        +____________________________________________________________________+    
              PAYMENT DETAILS (gross pay: ${getGrossMonthlyPay()})                                                                    
        +____________________________________________________________________+
                   Salary: ${formatted(grossSalary/12)}
                   Bonus:  ${formatted(annualBonus/12)}             
        +____________________________________________________________________+
              DEDUCTION DETAILS (total Deductions: ${formatted(getTotalMonthlyDeductions())})      
        +____________________________________________________________________+
                   PAYE: ${formatted(getMonthlyPAYE())}               
                   PRSI: ${formatted(getMonthlyPRSI())} 
                   Cycle To Work: ${cycleToWorkSchemeMonthly }       
        +____________________________________________________________________+
             NET PAY: ${formatted((getGrossMonthlyPay() - ((getMonthlySalary() * (prsiPercentage / 100)) + cycleToWorkSchemeMonthly)))}
        +____________________________________________________________________+"""
                )
    }

    fun getFullName() = when (gender) {
        'm', 'M' -> "Mr. ${firstName} $surName}"
        'f', 'F' -> "Ms.  ${firstName} ${surName}"
        else -> "${firstName} ${surName}"
    }



    override fun toString(): String {
        return "Employee(firstName='$firstName', surName='$surName', gender=$gender, employeeID=$employeeID, grossSalary=$grossSalary, payePercentage=$payePercentage, prsiPercentage=$prsiPercentage, annualBonus=$annualBonus, cycleToWorkSchemeMonthly=$cycleToWorkSchemeMonthly)"
    }

}