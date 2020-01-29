/**
 * 
 */
package Com.LeaveManagement;

import java.io.Console;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Yashvi
 *
 */
public class Admin extends Login {

	private Admin m_Admin = null;
	private Employee m_Emp = null;
	private ReportingAuthority m_RA = null;
	private ArrayList<Employee> lstEmpInfo = null;
	private ArrayList<ReportingAuthority> lstRAInfo = null;
	private int nTotalLeave = 10;

	public Admin() {
		this.lstEmpInfo = new ArrayList<Employee>();
		this.lstRAInfo = new ArrayList<ReportingAuthority>();;
	}

	// Set Login flag
	public void SetLoginFlag(boolean IsLogin) {
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
	public void SetUserId(String sUserId) {
		super.SetUserId(sUserId);
	}

	// Get user id
	public String GetUserId() {
		return super.GetPassword();
	}

	// Set password
	public void SetPassword(String sPassword) {
		super.SetPassword(sPassword);
	}

	// Get password
	public String GetPassword() {
		return super.GetPassword();
	}

	public ArrayList<Employee> GetEmpInfo() {
		return this.lstEmpInfo;
	}

	// Admin login
	public void AdminLogin() {
		boolean bIsValidInput = false;
		String sUserId = "";
		String sPassword = "";
		do {
			sUserId = getValidUserId("");
			sPassword = getValidPassword();
			if (sUserId.equals("Admin") == false && sPassword.equals("Admin123") == false) {
				System.out.println("Invalid credintials!!");
				continue;
			}
			bIsValidInput = true;
		} while (bIsValidInput != true);
		m_Admin = new Admin();
		m_Admin.SetLoginFlag(true);
		m_Admin.SetUserType(EnLoginUserType.Admin);

		boolean bIsValidChoice = false;
		do {
			System.out.println("-------------------------------------------------");
			System.out.println("\t\t\tADMIN");
			System.out.println("-------------------------------------------------" + "\n");

			System.out.println("1." + "Add user");
			System.out.println("2." + "Logout");
			System.out.print("Enter option:");

			Scanner sUserInput = new Scanner(System.in);
			// Store user choice
			String sChoice = sUserInput.nextLine();

			if (sChoice == null) {
				System.out.println("Choice should not be empty!!");
				bIsValidChoice = false;
				continue;
			}

			if (sChoice.length() == 0) {
				System.out.println("Choice should not be empty!!");
				bIsValidChoice = false;
				continue;
			}

			switch (sChoice) {
			case "1":
				if (AddUser() == true) {
					System.out.println("User added successfully.");
					continue;
				}
				break;
			case "2":
				m_Admin.SetLoginFlag(false);
				m_Admin = null;
				return;
			default:
				System.out.println("Invalid choice!!");
				bIsValidChoice = false;
				continue;
			}

		} while (bIsValidChoice != true);
	}

	// Add user type
	private boolean AddUser() {
		try {
			System.out.println("1." + "Employee");
			System.out.println("2." + "Reporting Authority");
			System.out.print("Enter option:");

			Scanner sUserInput = new Scanner(System.in);
			// Store user choice
			String sChoice = sUserInput.nextLine();
			boolean bIsValidChoice = false;
			EnLoginUserType enLoginUser = EnLoginUserType.Admin;
			do {
				if (sChoice == null) {
					System.out.println("Choice should not be empty!!");
					bIsValidChoice = false;
					continue;
				}

				if (sChoice.length() == 0) {
					System.out.println("Choice should not be empty!!");
					bIsValidChoice = false;
					continue;
				}

				switch (sChoice) {
				case "1":
					enLoginUser = EnLoginUserType.Employee;
					bIsValidChoice = true;
					break;
				case "2":
					enLoginUser = EnLoginUserType.RA;
					bIsValidChoice = true;
					break;
				default:
					System.out.println("Invalid choice!!");
					bIsValidChoice = false;
					continue;
				}

			} while (bIsValidChoice != true);

			if (enLoginUser.equals(EnLoginUserType.Employee) == true) {
				AddEmployee();
			}

			else {
				AddReportingAuthority();
			}

			return true;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return false;
		}
	}

	// Add RA
	public boolean AddReportingAuthority() {
		String sUserId = getValidUserId(EnLoginUserType.RA.toString());
		String sPassword = getValidPassword();

		System.out.print("Enter employee userid(More than one user separte by comma): ");
		String sUsers = getValidUserId("");
		String[] arrUsers = sUsers.split(",");
		if(lstEmpInfo.size()==0) {
			System.out.print("Enter userid: ");
			String sEmpUserId = getValidUserId(EnLoginUserType.Employee.toString());
			System.out.print("Enter password: ");
			String sEmpPassword = getValidPassword();
			Employee TempEmp = new Employee(null);
			TempEmp.SetLeave(nTotalLeave);
			TempEmp.SetUserId(sEmpUserId);
			TempEmp.SetPassword(sEmpPassword);
			lstEmpInfo.add(TempEmp);
		}
		else {
			for (int i = 0; i < lstEmpInfo.size(); i++) {
				if (lstEmpInfo.get(i).GetUserId() != null) {
					for (int j = 0; i < arrUsers.length; j++) {
						if (arrUsers[j].equals(lstEmpInfo.get(i).GetUserId()) == false) {
							System.out.println("Currently " + arrUsers[j] + "not available!!");
							System.out.print("Enter userid: ");
							String sEmpUserId = getValidUserId(EnLoginUserType.Employee.toString());
							System.out.print("Enter password: ");
							String sEmpPassword = getValidPassword();
							Employee TempEmp = new Employee();
							TempEmp.SetLeave(nTotalLeave);
							TempEmp.SetUserId(sEmpUserId);
							TempEmp.SetPassword(sEmpPassword);
							lstEmpInfo.add(TempEmp);
						}
					}
				}
			}	
		}

		m_RA = new ReportingAuthority();
		m_RA.SetUserId(sUserId);
		m_RA.SetPassword(sPassword);
		lstRAInfo.add(m_RA);
		return true;
	}

	// Add employee
	private boolean AddEmployee() {
		String sUserId = getValidUserId(EnLoginUserType.Employee.toString());
		String sPassword = getValidPassword();

		System.out.print("Enter Reporting authority userid");
		String sRA = getValidUserId(EnLoginUserType.RA.toString());
		if(lstEmpInfo.size()==0) {
			System.out.print("Enter password: ");
			String sRAPassword = getValidPassword();
			ReportingAuthority TempRA = new ReportingAuthority();
			TempRA.SetUserId(sRA);
			TempRA.SetPassword(sRAPassword);
			lstRAInfo.add(TempRA);
		}
		else {
			for (int i = 0; i < lstEmpInfo.size(); i++) {
				if (lstEmpInfo.get(i).GetUserId() != null || sRA.equals(lstEmpInfo.get(i).GetUserId()) == false) {
					System.out.println("Currently " + sRA + "not available!!");
					System.out.print("Enter password: ");
					String sRAPassword = getValidPassword();
					ReportingAuthority TempRA = new ReportingAuthority();
					TempRA.SetUserId(sRA);
					TempRA.SetPassword(sRAPassword);
					lstRAInfo.add(TempRA);
				}
		}
		}

		m_Emp = new Employee();
		m_Emp.SetLeave(nTotalLeave);
		m_Emp.SetUserId(sUserId);
		m_Emp.SetPassword(sPassword);
		m_Emp.SetRA(sRA);
		lstEmpInfo.add(m_Emp);
		m_Emp = null;
		return true;
	}
	
	private boolean IsUseridUnique(String sUserid,String sUserType) {
		if(sUserid==null) {
			return false;
		}

		if(sUserType.equals(EnLoginUserType.Employee.toString())==true) {
			for(int i=0;i<lstEmpInfo.size();i++) {
				if(lstEmpInfo.get(i).GetUserId()!=null) {
					if(lstEmpInfo.get(i).GetUserId().equals(sUserid)==true) {
						return false;
					}
				}
			}
		}
		else {
			for(int i=0;i<lstRAInfo.size();i++) {
				if(lstRAInfo.get(i).GetUserId()!=null) {
					if(lstRAInfo.get(i).GetUserId().equals(sUserid)==true) {
						return false;
					}
				}
			}
		}
		
		return true;
	}

	private String getValidUserId(String sLoginUserType) {

		try {
			Scanner sUserInput = new Scanner(System.in);
			boolean bIsValidInput = false;
			String sUserId = "";
			do {
				System.out.print("Enter userid: ");
				sUserId = sUserInput.nextLine();
				if (sUserId == null) {
					System.out.println("Userid should not be empty!!");
					bIsValidInput = false;
					continue;
				}

				if (sUserId.length() == 0) {
					System.out.println("Userid should not be empty!!");
					bIsValidInput = false;
					continue;
				}
				if(IsUseridUnique(sUserId,sLoginUserType)==false) {
					System.out.println("Userid already available!!");
					bIsValidInput = false;
					continue;
				}
				
				bIsValidInput = true;

			} while (bIsValidInput != true);

			return sUserId;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}

	// Get validate password from user input
	private String getValidPassword() {
		try {
			Scanner sUserInput = new Scanner(System.in);
			boolean bIsValidInput = false;
			String sPassword = "";
			do {
				System.out.print("Enter password: ");
				sPassword = sUserInput.nextLine();
				if (sPassword == null) {
					System.out.println("password should not be empty!!");
					bIsValidInput = false;
					continue;
				}

				if (sPassword.length() == 0) {
					System.out.println("password should not be empty!!");
					bIsValidInput = false;
					continue;
				}
				bIsValidInput = true;
			} while (bIsValidInput != true);

			return sPassword;
		} catch (Exception ex) {
			System.out.println(ex.getMessage());
			return null;
		}
	}
}
