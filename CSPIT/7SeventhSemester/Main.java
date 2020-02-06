import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static Admin m_Admin=null;
	private static Employee m_Emp=null;
	private static ReportingAuthority m_RA=null;
	public Main() {
		this.m_Admin=new Admin();
	}
	
	public static void main(String[] args) {
		LoginScreen();
	}

	public static void LoginScreen() {
		ArrayList<Employee> lstEmpInfo = new ArrayList<Employee>();
		ArrayList<ReportingAuthority> lstRAInfo=new ArrayList<ReportingAuthority>();
		System.out.println("------------------------------------------------------");
		System.out.println("\t\t\tLOGIN");
		System.out.println("------------------------------------------------------");
		Main main1=new Main();
		boolean bIsValidChoice = false;
		do {
			System.out.println("\n1. " + "ADMIN");
			System.out.println("2. " + "REPORTING AUTHORITY");
			System.out.println("3. " + "EMPLOYEE\n");
			Scanner sUserInput = new Scanner(System.in);
			// Store user choice
			String sLoginChoice = "";
			System.out.print("LOGIN AS : ");
			sLoginChoice = sUserInput.nextLine();
			System.out.print("\n");
			if (sLoginChoice == null) {
				System.out.println("------------ CHOICE SHOULD NOT BE EMPTY --------------");
				bIsValidChoice = false;
				continue;
			}

			if (sLoginChoice.length() == 0) {
				System.out.println("------------ CHOICE SHOULD NOT BE EMPTY --------------");
				bIsValidChoice = false;
				continue;
			}

			switch (sLoginChoice) {
			case "1":
				AdminLogin(lstEmpInfo,lstRAInfo);
				bIsValidChoice = false;
				break;
			case "2":
				RALogin(lstRAInfo);
				ReportingAuthority raLgn = new ReportingAuthority(lstEmpInfo, lstRAInfo);
				bIsValidChoice = false;
				break;
			case "3":
				EmployeeLogin(lstEmpInfo);
				Employee empLgn = new Employee(lstEmpInfo);
				bIsValidChoice = false;
				break;
			default:
				System.out.println("---------- THERE'S NO SUCH OPTION AVAILABLE ----------");
				bIsValidChoice = false;
				continue;
			}

		} while (bIsValidChoice != true);
	}
	
	// Admin login
	public static void AdminLogin(ArrayList<Employee> lstEmpInfo,ArrayList<ReportingAuthority> lstRAInfo) {
		boolean bIsValidInput = false;
		String sUserId = "";
		String sPassword = "";
		do {
			sUserId = Input.getValidUserId();
			sPassword = Input.getValidPassword();
			if (sUserId.equals("admin") == false || sPassword.equals("admin") == false) {
				System.out.println("---------------- INVALID CREDENTIALS -----------------");
				continue;
			}
			bIsValidInput = true;
		} while (bIsValidInput != true);
		m_Admin.SetLoginFlag(true);
		m_Admin.SetUserType(EnLoginUserType.Admin);

		boolean bIsValidChoice = false;
		do {
			System.out.println("------------------------------------------------------");
			System.out.println("\t\t\tADMIN");
			System.out.println("------------------------------------------------------");

			System.out.println("\n1. " + "ADD USER");
			System.out.println("2. " + "LOGOUT\n");
			System.out.print("OPT FOR : ");

			Scanner sUserInput = new Scanner(System.in);
			// Store user choice
			String sChoice = sUserInput.nextLine();
			
			//Some login validations
			if (sChoice == null) {
				System.out.println("------------ CHOICE SHOULD NOT BE EMPTY --------------");
				bIsValidChoice = false;
				continue;
			}

			if (sChoice.length() == 0) {
				System.out.println("------------ CHOICE SHOULD NOT BE EMPTY --------------");
				bIsValidChoice = false;
				continue;
			}

			switch (sChoice) {
			case "1":
				try {
					System.out.println("\n1. " + "NORMAL EMPLOYEE");
					System.out.println("2. " + "REPORTING AUTHORITY\n");
					System.out.print("OPT FOR : ");

					Scanner sUserInput1 = new Scanner(System.in);
					// Store user choice
					String sChoice1 = sUserInput.nextLine();
					boolean bIsValidChoice1 = false;
					EnLoginUserType enLoginUser = EnLoginUserType.Admin;
					do {
						if (sChoice == null) {
						System.out.println("------------ CHOICE SHOULD NOT BE EMPTY --------------");
							bIsValidChoice = false;
							continue;
						}

						if (sChoice.length() == 0) {
						System.out.println("------------ CHOICE SHOULD NOT BE EMPTY --------------");
							bIsValidChoice = false;
							continue;
						}

						switch (sChoice) {
						case "1":
							enLoginUser = EnLoginUserType.Employee;
							bIsValidChoice1 = true;
							break;
						case "2":
							enLoginUser = EnLoginUserType.RA;
							bIsValidChoice1 = true;
							break;
						default:
						System.out.println("---------- THERE'S NO SUCH OPTION AVAILABLE ----------");
							bIsValidChoice1 = false;
							continue;
						}

					} while (bIsValidChoice1 != true);

					if (enLoginUser.equals(EnLoginUserType.Employee) == true) {
						Employee emp=new Employee(lstEmpInfo);
						lstEmpInfo.add(m_Admin.AddEmployee(emp));
					}

					else {
						ReportingAuthority ra=new ReportingAuthority(lstEmpInfo, lstRAInfo);
						lstRAInfo.add(m_Admin.AddReportingAuthority(ra));
					}

					return;
				} catch (Exception ex) {
					System.out.println(ex.getMessage());
					return;
				}
			case "2":
				m_Admin.SetLoginFlag(false);
				m_Admin = null;
				return;
			default:
				System.out.println("---------- THERE'S NO SUCH OPTION AVAILABLE ----------");
				bIsValidChoice = false;
				continue;
			}

		} while (bIsValidChoice != true);
	}
	
	// Employee Login
	public static void EmployeeLogin(ArrayList<Employee> lstEmpInfo) {

		if (lstEmpInfo == null || lstEmpInfo.size() == 0) {
			System.out.println("\nEMPLOYEE USER LIST IS EMPTY.\nCONTACT ADMINISTRATOR TO CREATE NEW EMPLOYEE USER.\n");
			return;
		}
		boolean bIsValidUser = false;
		do {
			String sUserid = Input.getValidUserId();
			String sPassword = Input.getValidPassword();
			for (int i = 0; i < lstEmpInfo.size(); i++) {
				if (lstEmpInfo.get(i).GetUserId().equals(sUserid) == true
						&& lstEmpInfo.get(i).GetPassword().equals(sPassword) == true) {
					m_Emp = new Employee();
					m_Emp.SetLoginFlag(true);
					m_Emp.SetUserType(EnLoginUserType.Employee);
					m_Emp.SetUserId(sUserid);
					m_Emp.SetLeave(lstEmpInfo.get(i).GetLeave());
					bIsValidUser = true;
					break;
				} 
				else if (i == lstEmpInfo.size()) {
					System.out.println("---------------- INVALID CREDENTIALS -----------------");
					bIsValidUser = false;
					continue;
				}
			}
		} while (bIsValidUser != true);

		System.out.println("------------------------------------------------------");
		System.out.println("\t\t\tEMPLOYEE");
		System.out.println("------------------------------------------------------");

		boolean bIsValidChoice = false;
		do {
			System.out.println("\n1. " + "SHOW REPORT");
			System.out.println("2. " + "APPLY LEAVE");
			System.out.println("3. " + "LOGOUT\n");
			System.out.print("OPT FOR : ");

			Scanner sUserInput = new Scanner(System.in);
			// Store user choice
			String sChoice = sUserInput.nextLine();
			if (sChoice == null) {
				System.out.println("------------ CHOICE SHOULD NOT BE EMPTY --------------");
				bIsValidChoice = false;
				continue;
			}

			if (sChoice.length() == 0) {
				System.out.println("------------ CHOICE SHOULD NOT BE EMPTY --------------");
				bIsValidChoice = false;
				continue;
			}

			switch (sChoice) {
			case "1":
				m_Emp.DisplayLeaveReport();
				bIsValidChoice = false;
				break;
			case "2":
				m_Emp.ApplyLeave();
				bIsValidChoice = false;
				break;
			case "3":
				m_Emp.SetLoginFlag(false);
				m_Emp = null;
				lstEmpInfo = null;
				return;
			default:
				System.out.println("---------- THERE'S NO SUCH OPTION AVAILABLE ----------");
				bIsValidChoice = false;
				continue;
			}

		} while (bIsValidChoice != true);
	}
	
	// RA Login
		public static void RALogin(ArrayList<ReportingAuthority> lstRAInfo) {

			if (lstRAInfo == null || lstRAInfo.size() == 0) {
				System.out.println("\nRA USER LIST IS EMPTY.\nCONTACT ADMINISTRATOR TO CREATE NEW EMPLOYEE USER.\n");
				return;
			}
			boolean bIsValidUser = false;
			do {
				String sUserid = Input.getValidUserId();
				String sPassword = Input.getValidPassword();
				for (int i = 0; i < lstRAInfo.size(); i++) {
					if (lstRAInfo.get(i).GetUserId().equals(sUserid) == true
							&& lstRAInfo.get(i).GetPassword().equals(sPassword) == true) {
						m_RA = new ReportingAuthority();
						m_RA.SetLoginFlag(true);
						m_RA.SetUserType(EnLoginUserType.RA);
						m_RA.SetUserId(sUserid);
						bIsValidUser = true;
						break;
					} else if (i == lstRAInfo.size()) {
						System.out.println("---------------- INVALID CREDENTIALS -----------------");
						bIsValidUser = false;
					}

				}
			} while (bIsValidUser != true);

			System.out.println("------------------------------------------------------");
			System.out.println("\t\tREPORTING AUTHORITY");
			System.out.println("------------------------------------------------------");

			boolean bIsValidChoice = false;
			do {
				System.out.println("\n1. " + "SHOW REPORT");
				System.out.println("2. " + "ACCEPT / REJECT LEAVE");
				System.out.println("3. " + "LOGOUT\n");
				System.out.print("OPT FOR : ");

				Scanner sUserInput = new Scanner(System.in);
				System.out.print("\n");

				// Store user choice
				String sChoice = sUserInput.nextLine();
				if (sChoice == null) {
					System.out.println("------------ CHOICE SHOULD NOT BE EMPTY --------------");
					bIsValidChoice = false;
					continue;
				}

				if (sChoice.length() == 0) {
					System.out.println("------------ CHOICE SHOULD NOT BE EMPTY --------------");
					bIsValidChoice = false;
					continue;
				}

				switch (sChoice) {
				case "1":
					m_RA.DisplayEmployeeReport();
					bIsValidChoice = false;
					break;
				case "2":
					m_RA.EmployeeLeaveReqAcceptReject();
					bIsValidChoice = false;
					break;
				case "3":
					m_RA.SetLoginFlag(false);
					m_RA = null;
					return;
				default:
					System.out.println("---------- THERE'S NO SUCH OPTION AVAILABLE ----------");
					bIsValidChoice = false;
					continue;
				}

			} while (bIsValidChoice != true);
		}

}
