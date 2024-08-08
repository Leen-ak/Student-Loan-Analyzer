 /**
  * Program Name: StudentLoanApp.java
  * Purpose: creating a custom exception class for thrown the negative value 
  * @author Leen Aboukhalil
  * Date: April 07, 2024
 */
public class L_A_NegativeValueException extends Exception{

	//data members
	private double cslLoan;
	private double oslLoan;
	private double loan;
	private static String messageString = "Invalid Value";
	
	//constructore with one arg
	public L_A_NegativeValueException(double loan){
		super(messageString + "should be a postive value" + loan);
		this.loan = loan;
	}	
	
	//constructore with two arg
	public L_A_NegativeValueException(double cslLoan, double oslLoan){
		super(messageString + "Invalid value, should be a postive value" + cslLoan + oslLoan);
		this.cslLoan = cslLoan;
		this.oslLoan = oslLoan; 
	}	
	
	
	
}
