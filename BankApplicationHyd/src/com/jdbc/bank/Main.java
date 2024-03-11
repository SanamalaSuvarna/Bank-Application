package com.jdbc.bank;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
	
		
	     BankDAO dao=new BankDAOImp();
////     dao.debit();
////     dao.credit();
//     dao.Mobiletransaction();
//     dao.changingPassword();
     boolean status = true;
     while(status) {
     System.out.println("Enter \n 1.for Debit \n 2.for credit \n 3.for mobile transaction \n 4.for changing password \n 5. check balance") ;
     Scanner sc=new Scanner(System.in);
     int response=sc.nextInt();
     switch(response) {
     case 1:dao.debit();
     break;
     case 2:dao.credit();
     break;
     case 3:dao.Mobiletransaction();
     break;
     case 4:dao.changingPassword();
     break;
     case 5:dao.checkBalance();
     break;
     default:System.out.println("please enter the above valid numbers");
     }
     System.out.println("DO You want to continue \n yes \n no");
     String check =sc.next();
     if(check.equalsIgnoreCase("YES")) {
    	 status=true;
    	 
     }
     else {
    	 status=false;
    	 System.out.println("thanking youu................");
     }
     }
     
	}

}

