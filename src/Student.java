 /**
  * Program Name: Student.java
  * Purpose: The main class for creating a GUI student loan calculator APP to modifying their repayment and see who would affect the loan cost in terms of the amount of interest that the student would pay 
  * @author Leen Aboulhalil
  * Date: April 07, 2024
 */


public class Student{

	//Data member
	private String studentID;
	private String surname; 
	private String middleName; 
	private String firstName;
	private String aptNumber;
	private String streetNumber;
	private String streetName;
	private String city;
	private String province;
	private String postalCode;
	private double cslLoanAmount;
	private double oslLoanAmount;
	
	//constructore method 
	public Student(String studentID, String surname, String middleName, String firstName, 
			String aptNumber, String streetNumber, String streetName, String city, String province,
			String postalCode, double cslLoanAmount, double oslLoanAmount) 
			
	{
		this.studentID = studentID;
		this.surname = surname; 
		this.middleName = middleName;
		this.firstName = firstName;
		this.aptNumber = aptNumber;
		this.streetNumber = streetNumber;
		this.streetName = streetName; 
		this.city = city;
		this.province = province; 
		this.postalCode = postalCode;
		this.cslLoanAmount = cslLoanAmount;
		this.oslLoanAmount = oslLoanAmount; 
	}

	/**
	 * Gets the firstNamee of this object  
	 * @return firstNamee
	 */
	
	public String getFirstName() {
		return firstName;
	}

	/**
	 * Sets the firstNamee of this object
	 * @param firstNamee
	 */
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Gets the aptNumber of this object  
	 * @return aptNumber
	 */
	
	public String getAptNumber() {
		return aptNumber;
	}

	/**
	 * Sets the aptNumber of this object
	 * @param aptNumber
	 */
	
	public void setAptNumber(String aptNumber) {
		this.aptNumber = aptNumber;
	}

	/**
	 * Gets the city of this object  
	 * @return city
	 */
	
	public String getCity() {
		return city;
	}

	/**
	 * Sets the city of this object
	 * @param city
	 */
	
	public void setCity(String city) {
		this.city = city;
	}

	/**
	 * Gets the cslLoanAmount of this object  
	 * @return cslLoanAmount
	 */
	
	public double getCslLoanAmount(){
		return cslLoanAmount;
	}

	/**
	 * Sets the cslLoanAmount of this object
	 * @param cslLoanAmount
	 * @throws L_A_NegativeValueException 
	 */
	
	public void setCslLoanAmount(double cslLoanAmount) throws L_A_NegativeValueException
	//if the value is negative throw an error 
	{ 	if(cslLoanAmount < 0)
		{
			throw new L_A_NegativeValueException(cslLoanAmount); 
		}
		else
		{
		    //else assigned the value to sslLoanAmount
			this.cslLoanAmount = cslLoanAmount;
		}
	}

	/**
	 * Gets the surname of this object  
	 * @return surname
	 */

	public String getSurname() {
		return surname;
	}

	/**
	 * Sets the surname of this object
	 * @param surname
	 */
	
	public void setSurname(String surname) {
		this.surname = surname;
	}

	/**
	 * Gets the middleName of this object  
	 * @return middleName
	 */
	
	public String getMiddleName() {
		return middleName;
	}

	/**
	 * Sets the middleName of this object
	 * @param middleName
	 */
	
	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	/**
	 * Gets the streetNumber of this object  
	 * @return streetNumber
	 */
	
	public String getStreetNumber() {
		return streetNumber;
	}

	/**
	 * Sets the streetNumber of this object
	 * @param streetNumber
	 */
	
	public void setStreetNumber(String streetNumber) {
		this.streetNumber = streetNumber;
	}

	/**
	 * Gets the streetName of this object  
	 * @return streetName
	 */
	
	public String getStreetName() {
		return streetName;
	}

	/**
	 * Sets the streetName of this object
	 * @param streetName
	 */
	
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	/**
	 * Gets the province of this object  
	 * @return province
	 */
	
	public String getProvince() {
		return province;
	}

	/**
	 * Sets the province of this object
	 * @param province
	 */
	
	public void setProvince(String province) {
		this.province = province;
	}

	/**
	 * Gets the postalCode of this object  
	 * @return postalCode
	 */
	
	public String getPostalCode() {
		return postalCode;
	}

	/**
	 * Sets the postalCode of this object
	 * @param postalCode
	 */
	
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}

	/**
	 * Gets the oslLoanAmount of this object  
	 * @return oslLoanAmount
	 */
	
	public double getOslLoanAmount() {
		return oslLoanAmount;
	}

	/**
	 * Sets the oslLoanAmount of this object
	 * @param oslLoanAmount
	 */
	
	public void setOslLoanAmount(double oslLoanAmount)throws L_A_NegativeValueException {
		if(oslLoanAmount < 0)
		{
			throw new L_A_NegativeValueException(oslLoanAmount);
		}
		else
		{
			this.oslLoanAmount = oslLoanAmount;
		}
	}

	/**
	 * Gets the studentID of this object  
	 * @return studentID
	 */
	
	public String getStudentID() {
		return studentID; 
	}
	
	/**
	  
	Method Name:	incentives
	Purpose:        to calculate tax of the total purchases
	Accepts:        Nothing
	Returns:        double
	Coder:          LEEN
	Date:           March 1, 2024
	*/
	public String toString()
	{
		return "Student Name: " + getSurname() + ", " + getFirstName() + " " + getMiddleName() +"\n"
				+ "Student Number: " + getStudentID() + "\n" +  "CSL Amount is $" + getCslLoanAmount()
				+ "\n" + "OSL Amount is $" + getOslLoanAmount();  
	}
	
	
	
	
}
