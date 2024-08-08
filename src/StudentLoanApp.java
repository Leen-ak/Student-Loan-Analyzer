/**
 * Program Name: StudentLoanApp
 * Purpose:     A GUI application to calculate student loan payments, including validation and 
 * 				navigation through student records.
 * Author:      Leen Aboukhalil
 * Date:        April 07, 2024
 */

import java.awt.*; 
import java.awt.event.*; 
import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;
public class StudentLoanApp extends JFrame implements  Leen_LoanPayable{
 
	//ArrayList from type Student to added or removed as required 
	private ArrayList<Student> studentList; 
	
	//class wide scope area
	private JLabel titleLabel, studentIDLab, surnameLab, middleNameLab, firstNameLab, aptNumberLab, streetNumberLab, streetNameLab, cityLab, postalCodeLab, cslLoanLab, 
		oslLoanLab, cslMonthlyPaymentOutLab,oslMonthlyPaymentOutLab, totalPaymentOutLab, interestTotalOutLab, borrowedAmountOutLab, totalInterestPaidOutLab ; //max period is 120 month 
	private JLabel studentIDOutLab, surnameOutLab, middleNameOutLab, firstNameOutLab, aptNumberOutLab, streetNumberOutLab, streetNameOutLab, cityOutLab, provinceOutLab, postalCodeOutLab, cslLoanOutLab, 
	oslLoanOutLab, intrestRateOutLab, monthOutLab; //max period is 120 month 
	private JTextField studentIDTxt, surnameTxt, middleNameTxt, firstNameTxt, aptNumberTxt, streetNumberTxt, streetNameTxt, cityTxt, 
		provinceTxt, postalCodeTxt, cslLoanTxt, oslLoanTxt;
	private JTextField studentIDOutTxt, surnameOutTxt, middleNameOutTxt, firstNameOutTxt, aptNumberOutTxt, streetNumberOutTxt, streetNameOutTxt, 
	cityOutTxt, provinceOutTxt, postalCodeOutTxt, cslLoanOutTxt, oslLoanOutTxt,cslMonthlyPaymentOutTxt,oslMonthlyPaymentOutTxt, totalPaymentOutTxt, interestTotalOutTxt, borrowedAmountOutTxt,
	totalInterestPaidOutTxt  ;  
	private JMenuBar menubar;
	private JMenu  provinceMenu;
	private JMenuItem 	AB, BC, MB, NB, NF, NT, NS, NU, ON, PE, PQ, SK, YT; 
	private JPanel titlePanel, studentInfoLeft, studentOutput; 
	private JButton submitBtn, clearBtn, backBtn,nextBtn, calculateBtn; 
	private JSpinner annualRate, monthNumber;  
	//private int currentIndex = -1; 
	private int size,monthNumberVal;
	private String studentId, firstName, middleName, surname,  streetName, streetNumber, aptNumber, city, postalCode,province; 
	private  double cslLoan, oslLoan, annualRateVar; 
	private String [] provinceItems = {"AB", "BC", "MB", "NB", "NF", "NT", "NS", "NU", "ON", "PE", "PQ", "SK", "YT"}; 
	JMenuItem [] provinceMenuItems = new JMenuItem[provinceItems.length];
	Font font = new Font("Times New Roman", Font.BOLD, 16);
	int currentIndex;
	
	
public StudentLoanApp(){
	//the object of the actionListener class
	SetAction listen = new SetAction();    
	
	//calling the method to design the window 
	CreateJPanel();
	CreateInputSection(); 
	CreateProvinceMenu(listen); 
	CreateCalculationInfoSection();
	CreateOutputSection(); 
	CreateSpinnerModel();
	CreateButtons(); 
	CalculationOutputSection(); 
		
	//adding listener
	submitBtn.addActionListener(listen);
	clearBtn.addActionListener(listen);
	nextBtn.addActionListener(listen);
	backBtn.addActionListener(listen);
	calculateBtn.addActionListener(listen);
	this.setVisible(true);
}    
    
    
private class SetAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent ev) {
		Container contentPane = getContentPane();
		
		//checking if the user press any of the menu items to set the text to the JTextField
		for(int i = 0 ; i < provinceMenuItems.length ; ++i)
			if(ev.getSource() == provinceMenuItems[i]) 
				provinceTxt.setText(provinceItems[i]);	
			
		try {
				
			if(ev.getSource() == submitBtn) {
				StudentIDCheck(contentPane); 			//calling the method to check the user wrote 7 numbers for the studentID not more than 7 or less 
				CollectUserInput(contentPane); 			//calling the method to check if all the data the user enter is correct and collect the data if everything is right 
			          
		        //try-catch to validate the input is not a negative number and if it is, it will convert it to positive number and continue calculation
				  try {
					  if(oslLoan < 0 && cslLoan < 0){ 					  //if both of the numbers are negative then convert the numbers to positive value and throw an error 
							oslLoan = Math.abs(oslLoan); 
							Double.parseDouble(oslLoanTxt.getText());  
							cslLoan = Math.abs(cslLoan); 
							Double.parseDouble(cslLoanTxt.getText());
							throw new L_A_NegativeValueException(cslLoan , oslLoan);
					 }else if(oslLoan < 0 || cslLoan < 0){			 	  //if one of the numbers is a negative value the convert it to positive and continue 
							  
						  if(oslLoan < 0){
							  oslLoan = Math.abs(oslLoan);
							  Double.parseDouble(oslLoanTxt.getText()); 
							  throw new L_A_NegativeValueException(oslLoan); 
					 }else if(cslLoan < 0){
							   cslLoan = Math.abs(cslLoan); 
							   Double.parseDouble(cslLoanTxt.getText());
							   throw new L_A_NegativeValueException(cslLoan); 
							   }
					  }
					  
				 }catch(L_A_NegativeValueException ex){
					  JOptionPane.showMessageDialog(contentPane, "The input value should be a postive number, the negativ value will convert to the postive value and continue with the calculations","Error", JOptionPane.ERROR_MESSAGE); 
				 }
					
			Student student1 = new Student (studentId, surname, middleName, firstName, aptNumber,  
					streetNumber, streetName, city, province, postalCode, cslLoan, oslLoan); 	 //call the object from the Student class and populate all the text values in the constructor 

			//converting the text values to Int and double to calculate the formulas
			  monthNumberVal = (int) monthNumber.getValue();
			  annualRateVar = (double) annualRate.getValue();
			  
			  //add the input of the user into ArrayList after checking all the input is good 
			  studentList.add(student1);
	          updateFormWithStudentData(student1);
	          currentIndex = studentList.size()-1;		//assign currentIndex for the size of the arrayList to keep tracking the current index size
		}
				
		 if(ev.getSource() == clearBtn){
			ClearButton(); 	     		  //clear the text field and return do not add it to the arrayList 
 		    return;
		 }
	      	  
		 if (ev.getSource() == backBtn) {
		    if (currentIndex > 0) {			//if currentIndex is more than 0 that's mean there is data in the ArrayList and we can go back 
		    	currentIndex--; 	
				updateFormWithStudentData(studentList.get(currentIndex));	//after we increment the currentIndex by one we will update the JTextField by the currentIndex of the ArrayList
			    }
		}
				  
		 if (ev.getSource() == nextBtn) {
			 if (currentIndex < studentList.size() -1) {
		        currentIndex++;
		        updateFormWithStudentData(studentList.get(currentIndex));
		    }
		}
				  		          
		if(ev.getSource() == calculateBtn) {
	        	 	  	        
		     double cslLoanMethod = calculateLoanPayment(cslLoan,annualRateVar,monthNumberVal);			//calling the calculateLoanPayment() method
		     double oslLoanMethod = calculateLoanPayment(oslLoan,annualRateVar,monthNumberVal);  		//calling the calculateLoanPayment() method
		     if(ev.getSource() == calculateBtn) {
		 	       cslLoanMethod = calculateLoanPayment(cslLoan,annualRateVar,monthNumberVal);
	 			   oslLoanMethod = calculateLoanPayment(oslLoan,annualRateVar,monthNumberVal); 
	  			   CalculateOutput(cslLoanMethod, oslLoanMethod); 
	  			   }
		    }
		}catch(NumberFormatException e){
	    	 JOptionPane.showMessageDialog(contentPane, "Your input should be numeric and not empty!","erro", JOptionPane.ERROR_MESSAGE); 
		}
	}
}
    
public static void main(String [] args) {
	new StudentLoanApp();
}
/**
 * Method Name: CreateJPanel
 * Purpose:     Method to create and set up the main JPanel components
 * Accepts:     None
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void CreateJPanel() {
	setTitle("Leen Aboukhalil");
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
	this.setSize(800,870);
	this.setLocationRelativeTo(null);
	this.setLayout(new BorderLayout());
	
	studentList = new ArrayList();
	
	//Display the title of the page
	titlePanel = new JPanel(new FlowLayout()); 
	titleLabel = new JLabel("This is Leen's Student Loan Calculator");
	titlePanel.add(titleLabel); 
	titlePanel.setFont(font); 
	this.add(titlePanel, BorderLayout.NORTH);

	//Panel for the student info
	studentInfoLeft = new JPanel(new GridLayout(7, 4, 10,10)); //10 60
	studentInfoLeft.setPreferredSize(new Dimension(200,300)); 
	studentInfoLeft.setBorder(BorderFactory.createEmptyBorder(10, 10, 0, 10));
	this.add(studentInfoLeft, BorderLayout.CENTER); 
	
	//Panel for the student result
	studentOutput = new JPanel(new GridLayout(22,4));
	studentOutput.setPreferredSize(new Dimension(200,570)); 
	this.add(studentOutput, BorderLayout.SOUTH);
}

/**
 * Method Name: CreateInputSection
 * Purpose:     Method to create and set up the input section components
 * Accepts:     None
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void CreateInputSection() {
	//initialize the labels
	studentIDLab = new JLabel("Enter Student ID"); 
	studentIDTxt = new JTextField(); 
	firstNameLab = new JLabel("Enter First Name: ");
	firstNameTxt = new JTextField();
	middleNameLab = new JLabel("Enter Middle Name: ");
	middleNameTxt = new JTextField();
	surnameLab = new JLabel("Enter surname: ");
	surnameTxt = new JTextField();
	streetNameLab = new JLabel("Enter Street Name: "); 
	streetNameTxt = new JTextField();
	streetNumberLab = new JLabel("Enter street Number");
	streetNumberTxt = new JTextField();
	aptNumberLab = new JLabel("Enter Apartment Number: ");
	aptNumberTxt = new JTextField();
	cityLab = new JLabel("Enter City: ");
	cityTxt = new JTextField();
	postalCodeLab = new JLabel("Enter Postal Code: ");
	postalCodeTxt = new JTextField();

	JLabel [] label = {studentIDLab, firstNameLab, middleNameLab, surnameLab, streetNameLab,
			streetNumberLab, aptNumberLab, cityLab, postalCodeLab};
	JTextField [] text = {studentIDTxt, firstNameTxt, middleNameTxt, surnameTxt, streetNameTxt, streetNumberTxt,
			aptNumberTxt, cityTxt, postalCodeTxt};
	
	for(int i = 0; i < text.length ; ++i) {
		JLabel lableText = label[i];
		studentInfoLeft.add(lableText);
		
		JTextField userText = text[i];
		studentInfoLeft.add(userText);
	}
}

/**
 * Method Name: CreateProvinceMenu
 * Purpose:     Method to create and set up the province menu
 * Accepts:     SetAction (ActionListener for menu items)
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void CreateProvinceMenu(SetAction listen){
	//create JMenu 
	menubar = new JMenuBar();
	studentInfoLeft.add(menubar);
	provinceTxt = new JTextField();
	provinceMenu = new JMenu("Choose province"); 
	studentInfoLeft.add(provinceTxt);
	menubar.add(provinceMenu);
	provinceTxt.setEditable(false);
	menubar.add(provinceMenu);
			
	for(int i = 0; i < provinceItems.length ; ++i) {
		provinceMenuItems[i] = new JMenuItem(provinceItems[i]);
		provinceMenuItems[i].addActionListener(listen);
		provinceMenu.add(provinceMenuItems[i]); 
	}
}

/**
 * Method Name: CreateCalculationInfoSection
 * Purpose:     Method to create and set up the calculation info section
 * Accepts:     None
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void CreateCalculationInfoSection() {
	cslLoanLab = new JLabel("Enter CSL Loan Amount: ");
	studentInfoLeft.add(cslLoanLab); 
	cslLoanTxt = new JTextField(); 
	studentInfoLeft.add(cslLoanTxt);
	oslLoanLab = new JLabel("Enter OSL LoanAmount: ");
	studentInfoLeft.add(oslLoanLab);
	oslLoanTxt = new JTextField();
	studentInfoLeft.add(oslLoanTxt); 
	submitBtn = new JButton("Submit");
	studentInfoLeft.add(submitBtn);
	clearBtn = new JButton("Clear");
	studentInfoLeft.add(clearBtn); 
}


/**
 * Method Name: CreateOutputSection
 * Purpose:     Method to create and set up the output section components
 * Accepts:     None
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void CreateOutputSection() {
	studentIDOutLab = new JLabel("Your Student ID is: ");
	studentIDOutTxt = new JTextField();
	firstNameOutLab = new JLabel("Your First Name is: ");
	firstNameOutTxt = new JTextField();
	middleNameOutLab = new JLabel("Your Middle Name is: ");
	middleNameOutTxt = new JTextField();
	surnameOutLab = new JLabel("Your surname is: ");
	surnameOutTxt = new JTextField(); 
	streetNameOutLab = new JLabel("Your Street Name is: ");
	streetNameOutTxt = new JTextField(); 
	streetNumberOutLab = new JLabel("Your Street Number is: ");
	streetNumberOutTxt = new JTextField();
	aptNumberOutLab = new JLabel("Your Apartment Number is: ");
	aptNumberOutTxt = new JTextField(); 
	cityOutLab = new JLabel("Your city is: ");
	cityOutTxt = new JTextField(); 
	provinceOutLab = new JLabel("Your province is: ");
	provinceOutTxt = new JTextField(); 
	postalCodeOutLab = new JLabel("Your Postal Code is: ");
	postalCodeOutTxt = new JTextField();
	cslLoanOutLab = new JLabel("Your CSL Loan Amount is: ");
	cslLoanOutTxt = new JTextField();
	oslLoanOutLab = new JLabel("Your OSL Loan Amount is: ");
	oslLoanOutTxt = new JTextField();
	JLabel [] outLabel = {studentIDOutLab, firstNameOutLab, middleNameOutLab, surnameOutLab,
			streetNameOutLab, streetNumberOutLab, aptNumberOutLab, cityOutLab, provinceOutLab,
			postalCodeOutLab, cslLoanOutLab, oslLoanOutLab};
	JTextField [] outText = {studentIDOutTxt, firstNameOutTxt, middleNameOutTxt, surnameOutTxt,
			streetNameOutTxt, streetNumberOutTxt, aptNumberOutTxt, cityOutTxt, provinceOutTxt,
			postalCodeOutTxt, cslLoanOutTxt, oslLoanOutTxt}; 
	

	for(int i = 0; i < outText.length ; ++i) {
		JLabel lab = outLabel[i];
		studentOutput.add(lab); 
		
		JTextField txt = outText[i];
		txt.setEditable(false);
		studentOutput.add(txt);
	}
}

/**
 * Method Name: CreateSpinnerModel
 * Purpose:     Method to create and set up the spinner models for rate and month
 * Accepts:     None
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void CreateSpinnerModel() {
	intrestRateOutLab = new JLabel("Select the annual rate");
	studentOutput.add(intrestRateOutLab);
	
	//create an object from SpinnerNumberModel class for the annual rate
	SpinnerNumberModel spinnerModel = new SpinnerNumberModel(0,0,10.0,0.25); 
	 
	//create JSpinner 
	annualRate = new JSpinner(spinnerModel); 
	annualRate.setPreferredSize(new Dimension(10,10));
	studentOutput.add(annualRate); 
	
	// label for month
	monthOutLab = new JLabel("Select amortization period in months(1 - 120)");
	studentOutput.add(monthOutLab); 
	
	//create JSpinner for the months numbers
	SpinnerNumberModel SpinnerModel2 = new SpinnerNumberModel(1,1,120,1);  
	monthNumber = new JSpinner(SpinnerModel2);
	monthNumber.setPreferredSize(new Dimension(10,10));
	studentOutput.add(monthNumber); 
}

/**
 * Method Name: CreateButtons
 * Purpose:     Method to create and set up the buttons for navigation and actions
 * Accepts:     None
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void CreateButtons() {
	//creating back button to get to the previous elements in the arrayList
	backBtn = new JButton("<- Back");
	studentOutput.setBorder(BorderFactory.createEmptyBorder(10, 10,10,10)); 
	studentOutput.add(backBtn);
	
	//creating back button to get to the next elements in the arrayList
	nextBtn = new JButton("Next ->");
	studentOutput.add(nextBtn);
}

/**
 * Method Name: CalculationOutputSection
 * Purpose:     Method to create and set up the calculation output section
 * Accepts:     None
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void CalculationOutputSection() {
	//Add CSL month payment to the window 
	cslMonthlyPaymentOutLab = new JLabel("CSL Monthly Payment:");
	studentOutput.add(cslMonthlyPaymentOutLab);
	cslMonthlyPaymentOutTxt = new JTextField(); 
	studentOutput.add(cslMonthlyPaymentOutTxt);
	cslMonthlyPaymentOutTxt.setEditable(false);
			
	//Add OSL month payment to the window
	oslMonthlyPaymentOutLab = new JLabel("OSL Monthly Payment:");
	oslMonthlyPaymentOutTxt = new JTextField(); 
	totalPaymentOutLab = new JLabel("The Total Monthly Payment:");
	totalPaymentOutTxt = new JTextField();
	interestTotalOutLab = new JLabel("The Total Amount that will be Repaid with Interest:");
	interestTotalOutTxt = new JTextField();
	borrowedAmountOutLab = new JLabel("Total Borrowed Amount:");
	borrowedAmountOutTxt = new JTextField();
	totalInterestPaidOutLab = new JLabel("Total Interest paid is:");
	totalInterestPaidOutTxt = new JTextField();

	JLabel [] oslLab = {oslMonthlyPaymentOutLab, totalPaymentOutLab, interestTotalOutLab,
			borrowedAmountOutLab, totalInterestPaidOutLab};
	JTextField [] oslTxt = {oslMonthlyPaymentOutTxt, totalPaymentOutTxt, interestTotalOutTxt,
			borrowedAmountOutTxt, totalInterestPaidOutTxt};
			
	for(int i = 0; i < oslTxt.length; ++i) {
		JLabel outLab  = oslLab[i];
		studentOutput.add(outLab); 
				
		JTextField outTxt = oslTxt[i];
		studentOutput.add(outTxt);
	}
			
	//Add the JButton calculate to the window 
	calculateBtn = new JButton("Calculate");
	studentOutput.add(calculateBtn);
}

/**
 * Method Name: StudentIDCheck
 * Purpose:     Method to validate the student ID entered by the user
 * Accepts:     Container (contentPane to show messages)
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void StudentIDCheck(Container contentPane) {
	Integer.parseInt(studentIDTxt.getText());
	//take the values form text field and if the numbers did not parse then will catch the error and show up an error message for the user 
	if(studentIDTxt.getText().length() != 7) {
		JOptionPane.showMessageDialog(contentPane, "Your Student ID should be 7 numbers.", "Invalid Student ID", JOptionPane.ERROR_MESSAGE);
		studentIDTxt.requestFocus();
		return; 		                   //return until the user type 7 numbers long
	}
}

/**
 * Method Name: CollectUserInput
 * Purpose:     Method to collect user input from text fields and validate the data
 * Accepts:     Container (contentPane to show messages)
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void CollectUserInput(Container contentPane){
    //if there is no error take the rest of the text field and store it in variables 
	studentId = studentIDTxt.getText(); 
    firstName = firstNameTxt.getText();  
    middleName = middleNameTxt.getText();
    surname = surnameTxt.getText();
    streetName = streetNameTxt.getText();
    streetNumber = streetNumberTxt.getText();
    aptNumber = aptNumberTxt.getText();
    city = cityTxt.getText();
    postalCode = postalCodeTxt.getText();
    province = provinceTxt.getText(); 
    annualRate.getValue();
    monthNumber.getValue(); 
     
    //if the input was nun numeric or empty it will show up a message error and focus the mouse on that field for cslLoan
    cslLoan = Double.parseDouble(cslLoanTxt.getText());
    if(cslLoanTxt.getText().isEmpty()){
   	JOptionPane.showMessageDialog(contentPane, "Enter a number please.", "Invalid cslLoan", JOptionPane.ERROR_MESSAGE);
  	  	cslLoanTxt.requestFocus();
    }
    
     //if the input was nun numeric or empty it will show up a message error and focus the mouse on that field for oslLoan
     oslLoan = Double.parseDouble(oslLoanTxt.getText());
     if(oslLoanTxt.getText().isEmpty()){
   	  JOptionPane.showMessageDialog(contentPane, "Enter a number please.", "Invalid oslLoan", JOptionPane.ERROR_MESSAGE);
   	  oslLoanTxt.requestFocus();
     }
}

/**
 * Method Name: ClearButton
 * Purpose:     Method to clear all input text fields
 * Accepts:     None
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void ClearButton() {
	studentIDTxt.setText("");
	firstNameTxt.setText(""); 
	middleNameTxt.setText("");
	surnameTxt.setText("");
	streetNameTxt.setText("");
	streetNumberTxt.setText("");
	aptNumberTxt.setText("");
	cityTxt.setText("");
	postalCodeTxt.setText("");
	provinceTxt.setText("");
	cslLoanTxt.setText("");
	oslLoanTxt.setText("");
}

/**
 * Method Name: updateFormWithStudentData
 * Purpose:     Method to update the form with student data
 * Accepts:     Student (student object with data to display)
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void updateFormWithStudentData(Student student){
    studentIDOutTxt.setText(student.getStudentID());
    firstNameOutTxt.setText(student.getFirstName());
    middleNameOutTxt.setText(student.getMiddleName());
    surnameOutTxt.setText(student.getSurname());
    streetNumberOutTxt.setText(student.getStreetNumber());
    streetNameOutTxt.setText(student.getStreetName());
    aptNumberOutTxt.setText(student.getAptNumber());
    cityOutTxt.setText(student.getCity());
    provinceOutTxt.setText(student.getProvince());
    postalCodeOutTxt.setText(student.getPostalCode());
    cslLoanOutTxt.setText(String.valueOf(student.getCslLoanAmount()));
    oslLoanOutTxt.setText(String.valueOf(student.getOslLoanAmount()));
}

/**
 * Method Name: calculateLoanPayment
 * Purpose:     Method to calculate the loan payment
 * Accepts:     double (loan amount), double (annual interest rate), int (amortization period in months)
 * Returns:     double (calculated monthly payment)
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
public double calculateLoanPayment(double loanAmount, double annualIntersetRate, int amortizationPeriodMont) {
	
	//interest rate on a CSL is prime rate + 2.5 
	double csl = annualIntersetRate + 2.5;
	//interest rate on OSL is prime rate + 1.0 
	double osl = annualIntersetRate + 1.0; 
	
	//A is the loan amount that the user input 
	double A = loanAmount;
	
	//P is for storing monthly payment 
	double P;
	
	//to convert to a monthly interest rate 
	double Icsl = csl * Leen_LoanPayable.ANNUAL_RATE_TO_MONTHLY_RATE;
	double Iosl = osl * Leen_LoanPayable.ANNUAL_RATE_TO_MONTHLY_RATE;		
	
	//Calculating the CSL amount
	if(loanAmount == cslLoan)
	{
	    P = A * Icsl * Math.pow((1 + Icsl), amortizationPeriodMont) / ((Math.pow((1 + Icsl), amortizationPeriodMont)-1.0));
	}
	else //calculating the OSL amount 
	{
	//Calculating the OSL amount
		P = A * Iosl * Math.pow((1 + Iosl), amortizationPeriodMont) / ((Math.pow((1 + Iosl), amortizationPeriodMont)-1.0));
	}
	return P; 
}

/**
 * Method Name: CalculateOutput
 * Purpose:     Method to calculate and display the loan payment outputs
 * Accepts:     double (CSL loan method result), double (OSL loan method result)
 * Returns:     Nothing
 * Coder:       LEEN
 * Date:        April 5, 2024
 */
private void CalculateOutput(double cslLoanMethod, double oslLoanMethod){

	cslLoanMethod = calculateLoanPayment(cslLoan,annualRateVar,monthNumberVal);
         
	//calling the calculateLoanPayment() method
	oslLoanMethod = calculateLoanPayment(oslLoan,annualRateVar,monthNumberVal); 
		  
	//print and calculate the calculation 
	cslMonthlyPaymentOutTxt.setText(String.valueOf(String.format("$%.2f per month", cslLoanMethod)));
	oslMonthlyPaymentOutTxt.setText(String.valueOf(String.format("$%.2f per month",oslLoanMethod))); 
	totalPaymentOutTxt.setText(String.valueOf(String.format("$%.2f on an amorization period of %d", cslLoanMethod + oslLoanMethod,monthNumberVal ))); 
	interestTotalOutTxt.setText(String.valueOf(String.format("$%.2f", monthNumberVal *( cslLoanMethod + oslLoanMethod))));
	borrowedAmountOutTxt.setText(String.valueOf(String.format("$%.2f", cslLoan + oslLoan)));	  totalInterestPaidOutTxt.setText(String.valueOf(String.format("$%.2f",(monthNumberVal *( cslLoanMethod + oslLoanMethod) - (cslLoan + oslLoan)) )));
}

}
