package com.jdbc.bank;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;
import java.util.Scanner;

public class BankDAOImp implements BankDAO{
    

	@Override
	public void debit() {
		// TODO Auto-generated method stub
		
		String url="jdbc:mysql://localhost:3306/varna6?user=root&password=root";
		String select="select * from bank where useraccountno=? and password=?";
		
		Scanner sc=new Scanner(System.in);
		System.out.println("enter ur Account Number:");
		String acnumber=sc.next();
		System.out.println("enter ur password:");
		String password=sc.next();
		
		try {
			Connection con=DriverManager.getConnection(url);
			PreparedStatement ps=con.prepareStatement(select);
			ps.setString(1, acnumber);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				
				System.out.println("enter user amount:");
				double uamount=sc.nextDouble();
				if(uamount>=0) {
					double damount=rs.getDouble(7);
					if(damount>=uamount) {
						double sub=damount-uamount;
						String update="update bank set amount=? where useraccountno=? and password=?";
						PreparedStatement ps1=con.prepareStatement(update);
						ps1.setDouble(1, sub);
						ps1.setString(2, acnumber);
						ps1.setString(3, password);
						int result=ps1.executeUpdate();
						if(result!=0) {
							System.out.println("Amount Debited Succesfully..........");
							
						}else {
							System.out.println("server busy");
						}
					
					
					}
					else {
						System.out.println("insufficient amount");
					}
				}
				
				else {
                   System.out.println("invalid amount...");
				}
			}
			else {
				System.out.println("Invali Details");
			}
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	@Override
	public void Mobiletransaction() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter ur Mobile  Number:");
		String smobilenumber=sc.next();
		System.out.println("enter ur password:");
		String password=sc.next();
		String url="jdbc:mysql://localhost:3306/varna6?user=root&password=root";
		String select="select * from bank where usermobileno=? and password=?";
		try {
			Connection con=DriverManager.getConnection(url);
			PreparedStatement ps=con.prepareStatement(select);
			ps.setString(1, smobilenumber);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			
			if(rs.next()) {
				Random random=new Random();
				int otp=random.nextInt(10000);
				if(otp<=1000) {
					otp+=1000;
					
				}
				System.out.println("your otp is:"+otp);
				System.out.println("Enter your otp:");
				int uotp=sc.nextInt();
				if(otp==uotp) {
				System.out.println("Enter REcievers mobile no:");
				String rmobileno=sc.next();
				String select1="select * from bank where usermobileno=?";
				PreparedStatement receiversdata=con.prepareStatement(select1);
				receiversdata.setString(1, rmobileno);
				ResultSet receiversdetails=receiversdata.executeQuery();
				if(receiversdetails.next()) {
					System.out.println("Enter your Amount:");
					double amount=sc.nextDouble();
					if(amount>=0) {
						double senderdatabaseamount=rs.getDouble(7);
						if(senderdatabaseamount>=amount) {
							double sub=senderdatabaseamount-amount;
							String senderamountupdate="update bank set amount=? where usermobileno=?";
							PreparedStatement platformforupdatingsenderamount=con.prepareStatement(senderamountupdate);
							platformforupdatingsenderamount.setDouble(1, sub);
							platformforupdatingsenderamount.setString(2,smobilenumber );
							int result=platformforupdatingsenderamount.executeUpdate();
							if(result!=0) {
								double recieversdatabaseamount=receiversdetails.getDouble(7);
								double add=recieversdatabaseamount+amount;
								String updater="update bank set amount=? where usermobileno=?";
								PreparedStatement platformforupdatingreciveramount=con.prepareStatement(updater);
								platformforupdatingreciveramount.setDouble(1, add);
								platformforupdatingreciveramount.setString(2, rmobileno);
								int resultr=platformforupdatingreciveramount.executeUpdate();
								if(resultr!=0) {
								System.out.println("Amount received succesfully😀😀😀😀");
								
							}
								else {
									System.out.println("server busy for reciver");
								}
							
						}
						else {
							System.out.println("server busy");
						}
					}
					else {
						System.out.println("insufficient balance");
					}
					
				}
					else {
						System.out.println("inavalid amount");
					}
				}
				else {
					System.out.println("Register varna6 bank applications");
				}
			}
				else {
					System.out.println("Invalid otp");
				}
			}
			else {
				System.out.println("InvaliD Details");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
	}

	@Override
	public void changingPassword() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("enter your mobile number:");
		String mobilenum=sc.next();
		System.out.println("enter your pasword:");
		String password=sc.next();
		System.out.println("Enter your confirm Password:");
		String cpassword=sc.next();
		
			
		if(password.equals(cpassword))
	    {
			String url="jdbc:mysql://localhost:3306/varna6?user=root&password=root";
			String updation="update bank set password=? where usermobileno=?";
			try {
				Connection con=DriverManager.getConnection(url);
				PreparedStatement ps=con.prepareStatement(updation);
				ps.setString(1,password);
				ps.setString(2, mobilenum);
				int result=ps.executeUpdate();
				
				
				if (result!=0) {
					System.out.println("Password update Succesfully😀😀😀😀😀😀😀😀😀😀");
					
				} else {
                     System.out.println("invalid ");
				}
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			
		}
		else {
			System.out.println("invalid password");
			
			
		}
		}

	@Override
	public void checkBalance() {
		// TODO Auto-generated method stub
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter Your mobile  number:");
		String mobilenum=sc.next();
		System.out.println("Enter your password:");
		String password=sc.next();
		
			
		String url="jdbc:mysql://localhost:3306/varna6?user=root&password=root";
		String select="select * from bank where usermobileno=?";
		String selectpassword="select * from bank where password=?";
		String selectmp="select * from bank usermobileno=? and password=?";
		try {
			Connection con=DriverManager.getConnection(url);
			PreparedStatement ps=con.prepareStatement(select);
			ps.setString(1,mobilenum);
			ResultSet rs= ps.executeQuery();
			if(rs.next()) {
				
				PreparedStatement ps1=con.prepareStatement(selectpassword);
				ps1.setString(1,password);
				ResultSet rs1= ps.executeQuery();
				if(rs1.next()) {
					PreparedStatement ps2=con.prepareStatement(selectmp);
					ps2.setString(1,password);
					ResultSet rs3= ps.executeQuery();
					String mb=rs3.getString(3);
					for(int i=0;i<mb.length();i++){
					if(i>2 && i<8){
					System.out.print("*");
					}
					else {
					System.out.println(mb.charAt(i));
					}
					}
					if (rs3.next()) {
						System.out.println("id      :"+"SBI"+rs3.getInt(1));
						System.out.println("Nmae    :" +rs3.getString(2));
						System.out.println("Balance   :"+rs3.getDouble(7));
						System.out.println("mobile no   :"+rs3.getString(3));
					} else {
                        System.out.println("invalid details");
					}
				}
				else {
					System.out.println("invalid password");
				}
			}
			else {
				System.out.println("invalid mobile number");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void credit() {
		// TODO Auto-generated method stub
		String url="jdbc:mysql://localhost:3306/varna6?user=root&password=root";
		String select="select * from bank where useraccountno=? and password=?";
		
		Scanner sc=new Scanner(System.in);
		System.out.println("enter ur Account Number:");
		String acnumber=sc.next();
		System.out.println("enter ur password:");
		String password=sc.next();
		
		try {
			Connection con=DriverManager.getConnection(url);
			PreparedStatement ps=con.prepareStatement(select);
			ps.setString(1, acnumber);
			ps.setString(2, password);
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				
				System.out.println("enter user amount:");
				double uamount=sc.nextDouble();
				if(uamount>=0) {
					double damount=rs.getDouble(7);
					if(damount>=uamount) {
						double sub=damount+uamount;
						String update="update bank set amount=? where useraccountno=? and password=?";
						PreparedStatement ps1=con.prepareStatement(update);
						ps1.setDouble(1, sub);
						ps1.setString(2, acnumber);
						ps1.setString(3, password);
						int result=ps1.executeUpdate();
						if(result!=0) {
							System.out.println("Amount Credited Succesfully..........");
							
						}else {
							System.out.println("server busy");
						}
					
					
					}
					else {
						System.out.println("insufficient amount");
					}
				}
				
				else {
                   System.out.println("invalid amount...");
				}
			}
			else {
				System.out.println("Invali Details");
			}
				
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
		
	}

	
	
		
	}

	
				
	

