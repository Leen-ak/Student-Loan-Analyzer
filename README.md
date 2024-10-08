﻿# Student-Loan-Analyzer

## Overview
The Student Loan Calculator is a robust Java Swing application designed to assist students in managing their loan repayments. This application calculates the monthly payments for both the Canada Student Loan (CSL) and Ontario Student Loan (OSL), taking into account the interest rates and loan periods. It features comprehensive input validation to ensure accurate data entry, and custom exception handling to manage and correct negative loan values.

In addition to calculating loan repayments, the application allows users to save and navigate through multiple student records using intuitive back and next buttons. The user-friendly graphical interface ensures that students can easily input their loan details and receive immediate feedback on their repayment amounts, total interest paid, and total amount repaid over the loan period.

## Usage

1. Launch the application.
2. Enter the student details including student ID, name, address, and loan amounts.
3. Choose the province from the dropdown menu.
4. Click on the "Submit" button to save the student's information.
5. Use the "Back" and "Next" buttons to navigate the saved student records.
6. Click on the "Calculate" button to compute the monthly loan payments.

   
## Features

- Collect student information and calculate the CSL (Canada Student Loan) and OSL (Ontario Student Loan). Clear Button to clean all the input text Fields to write another student info 
![studentLoan](https://github.com/user-attachments/assets/ceae27d0-f5b6-4348-bdcc-e179e114ac92)

- Input validation for student ID.
![studentID](https://github.com/user-attachments/assets/bff0fc16-8de7-4142-8b9f-30a4446a3973)

- Input validation for CSL and OSL loans.
![emptyField](https://github.com/user-attachments/assets/fbac9613-0437-4ef2-9278-d53be806593e)

- Navigation through student records with back and next buttons.
![image](https://github.com/user-attachments/assets/aa02661a-b2a3-4349-a96a-434f64e8b19a)
![image](https://github.com/user-attachments/assets/65c92fb6-c029-47a1-8bcb-eb24f6942646)

- Custom exception handling for negative loan values.
![cslandosl](https://github.com/user-attachments/assets/51bb74de-14d3-4d1b-b54f-7ada4417bd97)
![convertNegative](https://github.com/user-attachments/assets/c76e2572-51d9-4522-baa8-deb009ac3e00)

## Challenges Faced
Implementing the back and next buttons was challenging due to several factors. Firstly, ensuring that the navigation correctly updated the displayed student records required careful management of the current index. It was essential to handle boundary conditions to prevent the index from going out of bounds when navigating to the first or last record. Additionally, updating the form fields with the correct student data based on the current index necessitated meticulous data handling and synchronization with the ArrayList holding the student records. Debugging and testing were crucial to ensure that the application provided a seamless user experience when navigating through records.

## Author
Leen Aboukhalil 
