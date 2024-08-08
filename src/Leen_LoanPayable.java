 /**
  * Program Name: Leen_LoanPayable.java
  * Purpose: class to hold a constant value to use the value in StudentLoanApp to convert whatever annual prime interest rate the user enters to the equivalents monthly rate 
  * @author Leen Aboukhalil
  * Date: Mar 31, 2024
 */

public interface Leen_LoanPayable {

	//To convert annual prime interest rate the user enters to the monthly rate  
	public double ANNUAL_RATE_TO_MONTHLY_RATE = 1.0 / 1200.0;
	
	/**
	  
	Method Name:	calculateLoanPayment
	Purpose:        this method will represent the loan payment amount 
	Accepts:        double OSL, double annualPrimeIntersetRate, int amortizationPeriodMonth
	Returns:        double, double, int
	Coder:          LEEN
	Date:           April 5, 2024
	*/
	abstract double calculateLoanPayment(double LoanAmount, double annualIntersetRate, int amortizationPeriodMont);
}
