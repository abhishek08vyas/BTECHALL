package com.leavemanagement;


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
public class Main {
	private static Admin m_adminUser=null;
	private static Employee m_empUser=null;
	private static ReportingAuthority m_RAUser=null;
	private static NotificationLeave m_LeaveNotify=null;
	private static List<Employee> lstEmpInfo=new ArrayList();
	private static List<ReportingAuthority> lstRAInfo=new ArrayList(); 
	private static List<NotificationLeave> lstNotify=new ArrayList();
	private static HashMap<String, String> hmInfoRA = new HashMap<>();
	private static HashMap<String, String> hmInfoEmp = new HashMap<>();

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LoginScreen();
	}
	
	public static void LoginScreen() {
		System.out.println(ConsoleDefinition.DEF_LINE);
		System.out.println(ConsoleDefinition.DEF_LOGIN_TITLE);
		System.out.println(ConsoleDefinition.DEF_LINE);
		
		boolean bIsValidChoice=false;
		do
		{
			System.out.println("1."+ConsoleDefinition.DEF_ADMIN_USER);
			System.out.println("2."+ConsoleDefinition.DEF_RA_USER);
			System.out.println("3."+ConsoleDefinition.DEF_EMPLOYEE_USER);
			Scanner sUserInput=new Scanner(System.in);
			// Store user choice
			String sLoginChoice="";
			System.out.print("Enter login option:");
			sLoginChoice=sUserInput.nextLine();
			if(sLoginChoice==null) {
				System.out.println(ConsoleDefinition.ERR_LOGIN_CHOICE_NOT_EMPTY);
				bIsValidChoice=false;
				continue;
			}
			
			if(sLoginChoice.trim()=="") {
				System.out.println(ConsoleDefinition.ERR_LOGIN_CHOICE_NOT_EMPTY);
				bIsValidChoice=false;
				continue;
			}
			
			switch(sLoginChoice)
			{
			case "1":
				AdminLogin();
				bIsValidChoice=true;
				break;
			case "2":
				RALogin();
				bIsValidChoice=true;
				break;
			case "3":
				EmployeeLogin();
				bIsValidChoice=true;
				break;
				default:
					System.out.println(ConsoleDefinition.ERR_CHOICE_INVALID);
					bIsValidChoice=false;
					continue;
			}
				
		}while(bIsValidChoice!=true);

	}
	
	// Admin login
	public static void AdminLogin() {
		ClearScreen();
		boolean bIsValidInput=false;
		String sUserId="";
		String sPassword="";
		do
		{
			sUserId=getValidUserId();
			sPassword=getValidPassword();
			if(sUserId.equals(CommonDefinition.DEF_ADMIN_USERID)==false&&
					sPassword.equals(CommonDefinition.DEF_ADMIN_PASSWORD)==false)
			{
				System.out.println(CommonDefinition.ERR_INVALID_USERID_PASSWORD);
				continue;
			}
			
			ClearScreen();
			bIsValidInput=true;
		}while(bIsValidInput!=true);
		m_adminUser=new Admin();
		m_adminUser.SetLoginFlag(true);
		m_adminUser.SetUserType(EnLoginUserType.Admin);
		DisplayHomeScreen(EnLoginUserType.Admin);
	}
	
	
	public static void DisplayHomeScreen(EnLoginUserType enLoginUserType) {
		if(enLoginUserType.equals(EnLoginUserType.Admin)==true) {
			
			boolean bIsValidChoice=false;
			do
			{
				System.out.println(ConsoleDefinition.DEF_LINE);
				System.out.println(ConsoleDefinition.DEF_ADMIN_TITLE);
				System.out.println(ConsoleDefinition.DEF_LINE+"\n");
				
				System.out.println("1."+ConsoleDefinition.DEF_ADD_USER);
				System.out.println("2."+ConsoleDefinition.DEF_LOGOUT);
				System.out.print("Enter option:");
				
				Scanner sUserInput=new Scanner(System.in);
				// Store user choice
				String sChoice=sUserInput.nextLine();

				if(sChoice==null) {
					System.out.println(ConsoleDefinition.ERR_CHOICE_NOT_EMPTY);
					bIsValidChoice=false;
					continue;
				}
				
				if(sChoice.length()==0) {
					System.out.println(ConsoleDefinition.ERR_CHOICE_NOT_EMPTY);
					bIsValidChoice=false;
					continue;
				}
				
				switch(sChoice)
				{
				case "1":
					if(AddUser()==true) {
						System.out.println("User added successfully.");
						continue;
					}
					break;
				case "2":
					Logout();
					bIsValidChoice=true;
					break;
					default:
						System.out.println(ConsoleDefinition.ERR_CHOICE_INVALID);
						bIsValidChoice=false;
						continue;
				}
					
			}while(bIsValidChoice!=true);
		}
		
		if(enLoginUserType.equals(EnLoginUserType.RA)==true) {
			System.out.println(ConsoleDefinition.DEF_LINE);
			System.out.println(ConsoleDefinition.DEF_RA_TITLE);
			System.out.println(ConsoleDefinition.DEF_LINE+"\n");
			
			System.out.println("1."+ConsoleDefinition.DEF_REPORT);
			System.out.println("2."+ConsoleDefinition.DEF_ACCEPT_REJECT);
			System.out.println("3."+ConsoleDefinition.DEF_LOGOUT);
			System.out.print("Enter option:");
			
			Scanner sUserInput=new Scanner(System.in);
			// Store user choice
			String sChoice=sUserInput.nextLine();
			boolean bIsValidChoice=false;
			do
			{
				if(sChoice==null) {
					System.out.println(ConsoleDefinition.ERR_CHOICE_NOT_EMPTY);
					bIsValidChoice=false;
					continue;
				}
				
				if(sChoice.trim()=="") {
					System.out.println(ConsoleDefinition.ERR_CHOICE_NOT_EMPTY);
					bIsValidChoice=false;
					continue;
				}
				
				switch(sChoice)
				{
				case "1":
					DisplayEmployeeReport();
					bIsValidChoice=false;
					break;
				case "2":
					EmployeeLeaveReqAcceptReject();
					bIsValidChoice=false;
					break;
				case "3":
					Logout();
					bIsValidChoice=true;
					default:
						System.out.println(ConsoleDefinition.ERR_CHOICE_INVALID);
						bIsValidChoice=false;
						continue;
				}
					
			}while(bIsValidChoice!=true);
		}
		
		if(enLoginUserType.equals(EnLoginUserType.Employee)==true) {
			System.out.println(ConsoleDefinition.DEF_LINE);
			System.out.println(ConsoleDefinition.DEF_EMPLOYEE_TITLE);
			System.out.println(ConsoleDefinition.DEF_LINE+"\n");
			
			
			boolean bIsValidChoice=false;
			do
			{
				System.out.println("1."+ConsoleDefinition.DEF_REPORT);
				System.out.println("2."+ConsoleDefinition.DEF_APPLY);
				System.out.println("3."+ConsoleDefinition.DEF_LOGOUT);
				System.out.print("Enter option:");
				
				Scanner sUserInput=new Scanner(System.in);
				// Store user choice
				String sChoice=sUserInput.nextLine();
				if(sChoice==null) {
					System.out.println(ConsoleDefinition.ERR_CHOICE_NOT_EMPTY);
					bIsValidChoice=false;
					continue;
				}
				
				if(sChoice.trim()=="") {
					System.out.println(ConsoleDefinition.ERR_CHOICE_NOT_EMPTY);
					bIsValidChoice=false;
					continue;
				}
				
				switch(sChoice)
				{
				case "1":
					DisplayLeaveReport();
					bIsValidChoice=false;
					break;
				case "2":
					ApplyLeave();
					bIsValidChoice=false;
					break;
				case "3":
					Logout();
					bIsValidChoice=true;

					default:
						System.out.println(ConsoleDefinition.ERR_CHOICE_INVALID);
						bIsValidChoice=false;
						continue;
				}
					
			}while(bIsValidChoice!=true);
		}
	}
	
	public static void ApplyLeave() {
		Scanner sUserInput=new Scanner(System.in);
		for(int i=0;i<lstEmpInfo.size();i++) {
		if(lstEmpInfo.get(i).GetUserId().equals(m_empUser.GetUserId())==true) {
			System.out.print("No.of remaining leave: "+lstEmpInfo.get(i).GetLeave());
			System.out.print("Enter leave:");
			// Store user choice
			String sLeaveApply=sUserInput.nextLine();
			boolean bIsValidInput=false;
			do
			{
				try {
					int nLeaveApply= Integer.parseInt(sLeaveApply);
					if(lstEmpInfo.get(i).GetLeave()==0) {
						System.out.print("You can't apply leave!!");
					}
					if(lstEmpInfo.get(i).GetLeave()<nLeaveApply) {
						System.out.print("You can't apply leave!!");
						break;
					}
					m_LeaveNotify=new NotificationLeave();
					m_LeaveNotify.SetApplyLeave(nLeaveApply);
					m_LeaveNotify.SetUserId(lstEmpInfo.get(i).GetUserId());
					System.out.print("Leave successfully applied.");
					
					bIsValidInput=true;
				
				}
				catch(Exception ex) {
					System.out.print("Only integer value is accept!!");
					bIsValidInput=false;
					continue;
				}
					
			}while(bIsValidInput!=true);
		}
		}
	}
	
	// Display employee leave report
	public static void DisplayLeaveReport() {
		
		System.out.println(ConsoleDefinition.DEF_LINE);
		for(int i=0;i<lstEmpInfo.size();i++) {
			if(lstEmpInfo.get(i).GetUserId().equals(m_empUser.GetUserId())==true) {
				System.out.println("Employee userid: "+lstEmpInfo.get(i).GetUserId());
				System.out.println("No. of leave remaining: "+lstEmpInfo.get(i).GetLeave());					
			}

		}
		for(int i=0;i<lstNotify.size();i++) {
			if(lstNotify.get(i).GetUserId().equals(m_empUser.GetUserId())==true) {
				System.out.println("No. of leave accepted: "+lstNotify.get(i).GetAcceptedLeave());
				System.out.println("No. of leave rejected: "+lstNotify.get(i).GetRejectedLeave());
				System.out.println("No. of leave remaining: "+lstEmpInfo.get(i).GetLeave());					
			}

		}
		System.out.println(ConsoleDefinition.DEF_LINE);
	}
	
	// Employee leave request accept/reject
	public static void EmployeeLeaveReqAcceptReject() {
		if(lstNotify==null) {
			System.out.println("Employee list is empty!!");
		}
		
		if(lstNotify.size()==0) {
			System.out.println("Employee list is empty!!");
		}
		
		for(int i=0;i<lstNotify.size();i++) {
			System.out.println("\n");
			System.out.println(ConsoleDefinition.DEF_LINE);
			System.out.print("1)"+ i++ + " ");
			System.out.println("Employee userid: "+lstNotify.get(i).GetUserId());
			System.out.println("No. of leave taken: "+lstNotify.get(i).GetApplyLeave());
			System.out.println(ConsoleDefinition.DEF_LINE);
		}
		
		Scanner sUserInput=new Scanner(System.in);
		System.out.print("Select employee from above list");
		// Store user choice
		String sSelectEmployee=sUserInput.nextLine();
		boolean bIsValidSelectEmployee=false;
		do
		{
			try {
				int nSelectEmployee= Integer.parseInt(sSelectEmployee);	
				if(lstNotify.size()<nSelectEmployee) {
					System.out.print("Selected input not in list!!");
					continue;
				}
				System.out.print("Userid: "+lstNotify.get(nSelectEmployee--).GetUserId());
				// Store user choice
				System.out.print("Enter no. of leave accept:");
				String sAcceptLeave=sUserInput.nextLine();
				int nAcceptLeave= Integer.parseInt(sAcceptLeave);
				int nLeaveGet=lstEmpInfo.get(nSelectEmployee--).GetLeave();
				lstEmpInfo.get(nSelectEmployee--).SetLeave(nLeaveGet-nAcceptLeave);
				lstNotify.get(nSelectEmployee--).SetAcceptedLeave(nAcceptLeave);
				int nApplyLeave=lstNotify.get(nSelectEmployee--).GetApplyLeave();
				lstNotify.get(nSelectEmployee--).SetRejectedLeave(nApplyLeave-nAcceptLeave);
				bIsValidSelectEmployee=true;
			}
			catch(Exception ex) {
				System.out.print("Only integer value is accept!!");
				bIsValidSelectEmployee=false;
				continue;
			}
				
		}while(bIsValidSelectEmployee!=true);
	}
	
	// Display employee report
	public static void DisplayEmployeeReport() {
		if(lstEmpInfo==null) {
			System.out.println("Employee list is empty!!");
		}
		
		if(lstEmpInfo.size()==0) {
			System.out.println("Employee list is empty!!");
		}
		
		for(int i=0;i<lstEmpInfo.size();i++) {
			System.out.println("\n");
			System.out.println(ConsoleDefinition.DEF_LINE);
			System.out.println("Employee userid: "+lstEmpInfo.get(i).GetUserId());
			System.out.println("No. remaining leaves: "+lstEmpInfo.get(i).GetLeave());
			System.out.println(ConsoleDefinition.DEF_LINE);
		}
	}
	
	// Logout
	public static void Logout() {
		if(m_adminUser!=null&&m_adminUser.GetLoginFlag()==true) {
			m_adminUser.SetLoginFlag(false);
			m_adminUser=null;
		}
		
		if(m_empUser!=null&&m_empUser.GetLoginFlag()==true) {
			m_empUser.SetLoginFlag(false);
			m_empUser=null;
		}
		
		if(m_RAUser!=null&&m_RAUser.GetLoginFlag()==true) {
			m_RAUser.SetLoginFlag(false);
			m_RAUser=null;
		}
		ClearScreen();
		LoginScreen();
	}
	
	// Add user type
	public static boolean AddUser() {
		System.out.println("1."+ConsoleDefinition.DEF_EMPLOYEE_USER);
		System.out.println("2."+ConsoleDefinition.DEF_RA_USER);
		System.out.print("Enter option:");
		
		Scanner sUserInput=new Scanner(System.in);
		// Store user choice
		String sChoice=sUserInput.nextLine();
		boolean bIsValidChoice=false;
		EnLoginUserType enLoginUser=EnLoginUserType.Admin;;
		do
		{
			if(sChoice==null) {
				System.out.println(ConsoleDefinition.ERR_CHOICE_NOT_EMPTY);
				bIsValidChoice=false;
				continue;
			}
			
			if(sChoice.trim()=="") {
				System.out.println(ConsoleDefinition.ERR_CHOICE_NOT_EMPTY);
				bIsValidChoice=false;
				continue;
			}
			
			switch(sChoice)
			{
			case "1":
				enLoginUser=EnLoginUserType.Employee;
				bIsValidChoice=true;
				break;
			case "2":
				enLoginUser=EnLoginUserType.RA;
				bIsValidChoice=true;
				break;
				default:
					System.out.println(ConsoleDefinition.ERR_CHOICE_INVALID);
					bIsValidChoice=false;
					continue;
			}
				
		}while(bIsValidChoice!=true);
		
		if(enLoginUser.equals(EnLoginUserType.Employee)==true) {
			AddEmployee();
		}
		
		else {
			AddRA();
		}	
		
		return true;
	}
	
		// Add RA
		public static boolean AddRA() {
			Scanner sUserInput=new Scanner(System.in);
			boolean bIsValidInput=false;
			
			String sUserId="";
			do {
				System.out.print(CommonDefinition.DEF_USERID_INPUT);
				sUserId=sUserInput.nextLine();
				if(hmInfoRA.containsKey(sUserId)==true) {
					System.out.println("Userid already registered!!");
					continue;
				}
				if(CommonUtlity.IsValidUserId(sUserId)==false) {
					bIsValidInput=false;
					continue;
				}	
				bIsValidInput=true;
			}while(bIsValidInput!=true);
			
			bIsValidInput=false;
			String sPassword="";
			do {
				System.out.print(CommonDefinition.DEF_PASSWORD_INPUT);
				sPassword=sUserInput.nextLine();
				if(CommonUtlity.IsValidPassword(sPassword)==false) {
					System.out.println(CommonDefinition.ERR_PASSWORD_NOT_EMPTY);
					bIsValidInput=false;
					continue;
				}
				
				bIsValidInput=true;	
				
			}while(bIsValidInput!=true);
			
			m_RAUser=new ReportingAuthority();
			m_RAUser.SetUserId(sUserId);
			m_RAUser.SetPassword(sPassword);
			lstRAInfo.add(m_RAUser);
			hmInfoRA.put(sUserId,sPassword);
			return true;
		}

	
	// Add employee
	public static boolean AddEmployee() {
		Scanner sUserInput=new Scanner(System.in);
		boolean bIsValidInput=false;
		
		String sUserId="";
		do {
			System.out.print(CommonDefinition.DEF_USERID_INPUT);
			sUserId=sUserInput.nextLine();
			if(CommonUtlity.IsValidUserId(sUserId)==false) {
				bIsValidInput=false;
				continue;
			}	
			bIsValidInput=true;
		}while(bIsValidInput!=true);
		
		bIsValidInput=false;
		System.out.print(CommonDefinition.DEF_PASSWORD_INPUT);
		String sPassword=sUserInput.nextLine();
		do {
			if(CommonUtlity.IsValidPassword(sPassword)==false) {
				System.out.println(CommonDefinition.ERR_PASSWORD_NOT_EMPTY);
				bIsValidInput=false;
				continue;
			}
			
			bIsValidInput=true;	
			
		}while(bIsValidInput!=true);
		
		m_empUser=new Employee();
		m_empUser.SetLeave(LeaveManagementDefination.nTotalLeave);
		m_empUser.SetUserId(sUserId);
		m_empUser.SetPassword(sPassword);
		lstEmpInfo.add(m_empUser);
		hmInfoEmp.put(sUserId,sPassword);
		m_empUser=null;
		return true;
	}
	
	// RA Login
	public static void RALogin() {
		if(lstRAInfo==null) {
			System.out.println("Currently RA user list is empty!!");
			return;
		}
		
		if(lstRAInfo.size()==0) {
			System.out.println("Currently RA user list is empty!!");
			return;
		}
		ClearScreen();
		String sUserid="";
		String sPassword="";
		boolean bIsValidInput=false;
		do
		{
			try {
				sUserid=getValidUserId();
				sPassword=getValidPassword();
				if(hmInfoRA.containsKey(sUserid)==false&&
						hmInfoRA.get(sUserid).equals(sPassword)==false)
				{
					System.out.println(CommonDefinition.ERR_INVALID_USERID_PASSWORD);
					continue;
				}	
			}
			catch(Exception ex) {
				System.out.println(CommonDefinition.ERR_INVALID_USERID_PASSWORD);
				continue;
			}
			
			ClearScreen();
			bIsValidInput=true;
		}while(bIsValidInput!=true);
		m_RAUser=new ReportingAuthority();
		m_RAUser.SetLoginFlag(true);
		m_RAUser.SetUserType(EnLoginUserType.RA);
		m_RAUser.SetUserId(sUserid);
		DisplayHomeScreen(EnLoginUserType.RA);
	}
	
	// Employee Login
	public static void EmployeeLogin() {
		if(lstEmpInfo==null) {
			System.out.println("Employee list is empty!!");
			return;
		}
		
		if(lstEmpInfo.size()==0) {
			System.out.println("Employee list is empty!!");
			return;
		}
		ClearScreen();
		String sUserid="";
		String sPassword="";
		boolean bIsValidInput=false;
		
		do
		{
			try
			{
				sUserid=getValidUserId();
				sPassword=getValidPassword();
				if(hmInfoEmp.containsKey(sUserid)==false&&
						hmInfoEmp.get(sUserid).equals(sPassword)==false)
				{
					System.out.println(CommonDefinition.ERR_INVALID_USERID_PASSWORD);
					continue;
				}	
			}
			catch(Exception ex) {
				System.out.println(CommonDefinition.ERR_INVALID_USERID_PASSWORD);
				continue;
			}
			
			ClearScreen();
			bIsValidInput=true;
		}while(bIsValidInput!=true);
		m_empUser=new Employee();
		for(int i=0;i<lstEmpInfo.size();i++) {
			if(lstEmpInfo.get(i).GetUserId().equals(sUserid)==true) {
				m_empUser.SetLoginFlag(true);
				m_empUser.SetUserType(EnLoginUserType.Employee);
				m_empUser.SetUserId(sUserid);
				m_empUser.SetLeave(lstEmpInfo.get(i).GetLeave());
				DisplayHomeScreen(EnLoginUserType.Employee);	
				break;
			}
		}
	}

	// Clear console screen
	public static void ClearScreen() {  
		   System.out.print("\033[H\033[2J");  
		   System.out.flush();  
		}
	
	public static String getValidUserId()
	{
		Scanner sUserInput=new Scanner(System.in);
		boolean bIsValidInput=false;
		String sUserId="";
		do {
			System.out.print(CommonDefinition.DEF_USERID_INPUT);
			sUserId=sUserInput.nextLine();
			if(sUserId==null) {
				System.out.println(CommonDefinition.ERR_USERID_NOT_EMPTY);
				bIsValidInput=false;
				continue;
			}
			
			if(sUserId.length()==0) {
				System.out.println(CommonDefinition.ERR_USERID_NOT_EMPTY);
				bIsValidInput=false;
				continue;
			}
			
			bIsValidInput=true;	
			
		}while(bIsValidInput!=true);
		
		return sUserId;
	}
	
	// Get validate password from user input
	public static String getValidPassword()
	{
		Scanner sUserInput=new Scanner(System.in);
		boolean bIsValidInput=false;
		String sPassword="";
		do {
			System.out.print(CommonDefinition.DEF_PASSWORD_INPUT);
			sPassword=sUserInput.nextLine();
			if(sPassword==null) {
				System.out.println(CommonDefinition.ERR_PASSWORD_NOT_EMPTY);
				bIsValidInput=false;
				continue;
			}
			
			if(sPassword.length()==0) {
				System.out.println(CommonDefinition.ERR_PASSWORD_NOT_EMPTY);
				bIsValidInput=false;
				continue;
			}
			
			bIsValidInput=true;	
			
		}while(bIsValidInput!=true);
		
		return sPassword;
	}
}