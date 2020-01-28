import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.HashMap; 
import java.util.Map; 

/**
 * @author 
 *
 */
 enum EnLoginUserType {
	/*Login as a admin*/
	Admin,
	
	/* Login as a RA user*/
	RA,
	
	/* Login as a Normal user*/
	Employee
}
 
 class Employee extends Login {
	
			private int nLeave=0;
			
			// Set Login flag
			public void SetLoginFlag(boolean IsLogin){
				super.SetLoginFlag(IsLogin);
			}
			// Get Login Flag
			public boolean GetLoginFlag() {
				return super.GetLoginFlag();
			}
			
			// Set user type
			public void SetUserType(EnLoginUserType enUserType) {
				super.SetUserType(enUserType);
			}
			// Get user type
			public EnLoginUserType getUserType() {
				return super.getUserType();
			}
			
			// Set user id
			public void SetUserId(String sUserId){
				super.SetUserId(sUserId);
			}
			// Get user id
			public String GetUserId() {
				return super.GetUserId();
			}
			
			// Set password
			public void SetPassword(String sPassword){
				super.SetPassword(sPassword);
			}
			// Get password
			public String GetPassword() {
				return super.GetPassword();
			}
			
			public int GetLeaveTotal(){
				return 10;
			}
			
			// Set leave
			public void SetLeave(int nLeave) {
				this.nLeave=nLeave;
			}
			
			// Get leave
			public int GetLeave() {
				return this.nLeave;
			}
}
 
 abstract class Login {

	/*----------Member variable----------*/
	// Store login true or false
	private boolean bIsLogin = false;
	// Store user type
	private EnLoginUserType enUserType = EnLoginUserType.Admin;
	// Store user id
	private String sUserid = "";
	// Store password
	private String sPassword = "";

	/*----------Property----------*/
	
	// Set Login flag
	protected void SetLoginFlag(boolean IsLogin){
		this.bIsLogin=IsLogin;
	}
	// Get Login Flag
	protected boolean GetLoginFlag() {
		return this.bIsLogin;
	}
	
	// Set user type
	protected void SetUserType(EnLoginUserType enUserType) {
		this.enUserType = enUserType;
	}
	// Get user type
	protected EnLoginUserType getUserType() {
		return this.enUserType;
	}
	
	// Set user id
	protected void SetUserId(String sUserId){
		this.sUserid=sUserId;
	}
	// Get user id
	protected String GetUserId() {
		return this.sUserid;
	}
	
	// Set password
	protected void SetPassword(String sPassword){
		this.sPassword=sPassword;
	}
	// Get password
	protected String GetPassword() {
		return this.sPassword;
	}
}
 
 class Admin extends Login  {
	
		// Set Login flag
		public void SetLoginFlag(boolean IsLogin){
			super.SetLoginFlag(IsLogin);
		}
		// Get Login Flag
		public boolean GetLoginFlag() {
			return super.GetLoginFlag();
		}
		
		// Set user type
		public void SetUserType(EnLoginUserType enUserType) {
			super.SetUserType(enUserType);
		}
		// Get user type
		public EnLoginUserType getUserType() {
			return super.getUserType();
		}
		
		// Set user id
		public void SetUserId(String sUserId){
			super.SetUserId(sUserId);
		}
		// Get user id
		public String GetUserId() {
			return super.GetPassword();
		}
		
		// Set password
		public void SetPassword(String sPassword){
			super.SetPassword(sPassword);
		}
		// Get password
		public String GetPassword() {
			return super.GetPassword();
		}
}

 class ReportingAuthority extends Login {
	// Set Login flag
			public void SetLoginFlag(boolean IsLogin){
				super.SetLoginFlag(IsLogin);
			}
			// Get Login Flag
			public boolean GetLoginFlag() {
				return super.GetLoginFlag();
			}
			
			// Set user type
			public void SetUserType(EnLoginUserType enUserType) {
				super.SetUserType(enUserType);
			}
			// Get user type
			public EnLoginUserType getUserType() {
				return super.getUserType();
			}
			
			// Set user id
			public void SetUserId(String sUserId){
				super.SetUserId(sUserId);
			}
			// Get user id
			public String GetUserId() {
				return super.GetPassword();
			}
			
			// Set password
			public void SetPassword(String sPassword){
				super.SetPassword(sPassword);
			}
			// Get password
			public String GetPassword() {
				return super.GetPassword();
			}
}

public class LeaveManage {
	private static Admin m_adminUser=null;
	private static Employee m_empUser=null;
	private static ReportingAuthority m_RAUser=null;
	private static NotificationLeave m_LeaveNotify=null;
	private static Employee[] lstEmpInfo=new Employee[15];
	private static ReportingAuthority[] lstRAInfo=new ReportingAuthority[15];
	private static int countRA=-1; 
		private static int countEmp=-1; 

	/**
	 * @param args
	 */
	public static void main(String[] args) {
	LoginScreen();
	}
	
	public static void LoginScreen(){
	System.out.println("-------------------------------------------------");
	System.out.println("\t\tLogin");
	System.out.println("-------------------------------------------------");
	System.out.println("1.Admin");
	System.out.println("2.Employee");
	System.out.println("3.RA");
	Scanner input=new Scanner(System.in);
	System.out.print("Enter option:");
	int choice=input.nextInt();
	switch(choice){
		case 1:
		AdminScreen();
	break;
	case 2:
	EmployeeScreen();
	case 3:
	RAScreen();
	default:
	System.out.println("Invalid choice");
	}
	LoginScreen();
	}
	
	public static void RAScreen(){
		Scanner input=new Scanner(System.in);
		String userid=input.nextLine();
		String pass=input.nextLine();
		for(int i=0;i<lstRAInfo.length;i++){
			if(lstRAInfo[i].GetUserId()==userid&&lstRAInfo[i].GetPassword()==pass){
				System.out.println("-------------------------------------------------");
		System.out.println("RA HOME");
		System.out.println("-------------------------------------------------");
		System.out.println("1.Report");
		System.out.println("2.Accept/reject");
		System.out.print("Enter option:");
		int choice=input.nextInt();
		switch(choice){
					case 1:
					for(int j=0;j<lstEmpInfo.length;j++){
						userid=input.nextLine();
						if(userid==lstEmpInfo[i].GetUserId())
						System.out.println("No. of leave remaining:"+lstEmpInfo[i].GetLeave());
					}
						
					break;
				case 2:
					System.out.print("Enter userid");
					userid=input.nextLine();
					for(int j=0;j<lstEmpInfo.length;j++){
						userid=input.nextLine();
						if(userid==lstEmpInfo[i].GetUserId()){
							System.out.print("Enter userid:");
							int leave=input.nextInt();
							
							int countleave=10-leave;
							lstEmpInfo[i].SetLeave(countleave);
						}
					}
					break;
				default:
				System.out.println("Invalid choice");
				}
				break;
		}
		}
	}
	
	public static void EmployeeScreen(){
		
		Scanner input=new Scanner(System.in);
		String userid=input.nextLine();
		String pass=input.nextLine();
		for(int i=0;i<lstEmpInfo.length;i++){
			if(lstEmpInfo[i].GetUserId()==userid&&lstEmpInfo[i].GetPassword()==pass){
				System.out.println("-------------------------------------------------");
		System.out.println("\tEmployee HOME");
		System.out.println("-------------------------------------------------");
		System.out.println("1.Report");
		System.out.println("2.Apply leave");
		System.out.print("Enter option:");
		int choice=input.nextInt();
		switch(choice){
					case 1:
						System.out.println("No. of leave remaining:"+lstEmpInfo[i].GetLeave() );
					break;
				case 2:
					System.out.print("Enter no of leave taken:");
					int leave=input.nextInt();
					int countleave=lstEmpInfo[i].GetLeave()-leave;
					lstEmpInfo[i].SetLeave(countleave);
					break;
				default:
				System.out.println("Invalid choice");
				}
				break;
		}
		}
	}
	
	public static void AdminScreen(){
		Scanner input=new Scanner(System.in);
		String userid=input.nextLine();
		String pass=input.nextLine();
		
		if(userid=="Admin"&&pass=="Admin123"){
			System.out.println("-------------------------------------------------");
		System.out.println("\t\tAdmin HOME");
		System.out.println("-------------------------------------------------");
		System.out.println("1.Add employee");
		System.out.println("2.Add RA");
		System.out.print("Enter option:");
		int choice=input.nextInt();
				switch(choice){
					case 1:
					System.out.print("Enter userid:");
					userid=input.nextLine();
					System.out.print("Enter password:");
					pass=input.nextLine();
					System.out.print("Enter Reporting authority userid:");
					String RAuserid=input.nextLine();
					m_RAUser=new ReportingAuthority();
					for(int i=0;i<lstRAInfo.length;i++){
						if(lstRAInfo[i].GetUserId()!=RAuserid){
							System.out.print("RA user not exist enter Reporting authority password:");
							String RApass=input.nextLine();
							m_RAUser.SetUserId(RAuserid);
							m_RAUser.SetPassword(RApass);
						}
					}
					countRA++;
					countEmp++;
					lstRAInfo[countRA]=m_RAUser;
					m_empUser=new Employee();
					m_empUser.SetUserId(userid);
					m_empUser.SetPassword(pass);
					m_empUser.SetLeave(10);
					lstEmpInfo[countEmp]=m_empUser;
				break;
				case 2:
				System.out.print("Enter userid:");
					userid=input.nextLine();
					System.out.print("Enter password:");
					pass=input.nextLine();
					m_RAUser=new ReportingAuthority();
					m_RAUser.SetUserId(userid);
					m_RAUser.SetPassword(pass);
					System.out.print("Enter list userid of employee(Separated by comma):");
					String empuserid=input.nextLine();
					String[] arrOfStr = empuserid.split(","); 
					m_empUser=new Employee();
					for(int i=0;i<lstEmpInfo.length;i++){
						for(int j=0;j<arrOfStr.length;j++){
							if(lstEmpInfo[i].GetUserId()!=arrOfStr[j]){
							System.out.print("Emp user not exist enter Emp password:");
							String Empass=input.nextLine();
							m_empUser.SetUserId(arrOfStr[j]);
							m_empUser.SetPassword(Empass);
							m_empUser.SetLeave(10);
						}
						}
					}
					countRA++;
					countEmp++;
					lstRAInfo[countRA]=m_RAUser;
					lstEmpInfo[countEmp]=m_empUser;
					break;
				default:
				System.out.println("Invalid choice");
				}
		}
	}
}