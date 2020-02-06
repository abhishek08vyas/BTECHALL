import java.util.ArrayList;
import java.util.Scanner;

public class Admin extends Login {

	private Admin m_Admin = null;
	private Employee m_Emp = null;
	private ReportingAuthority m_RA = null;
	private ArrayList<Employee> lstEmpInfo = null;
	private ArrayList<ReportingAuthority> lstRAInfo = null;
	private int nTotalLeave = 10;

	public Admin() {
		this.lstEmpInfo = new ArrayList<Employee>();
		this.lstRAInfo = new ArrayList<ReportingAuthority>();
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

	public ArrayList<ReportingAuthority> GetRAInfo() {
		return this.lstRAInfo;
	}

	// Admin login
	public void AdminLogin() {
		boolean bIsValidInput = false;
		String sUserId = "";
		String sPassword = "";
		do {
			sUserId = getValidUserId("");
			sPassword = getValidPassword();
			if (sUserId.equals("admin") == false || sPassword.equals("admin") == false) {
				System.out.println("---------------- INVALID CREDENTIALS -----------------");
				continue;
			}
			bIsValidInput = true;
		} while (bIsValidInput != true);
		m_Admin = new Admin();
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
				if (AddUser() == true) {
					//System.out.println("------------ USER IS ADDED SUCCESSFULLY --------------");
					continue;
				}
				break;
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

	// Add user type
	private boolean AddUser() {
		try {
			System.out.println("\n1. " + "NORMAL EMPLOYEE");
			System.out.println("2. " + "REPORTING AUTHORITY\n");
			System.out.print("OPT FOR : ");

			Scanner sUserInput = new Scanner(System.in);
			// Store user choice
			String sChoice = sUserInput.nextLine();
			boolean bIsValidChoice = false;
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
					bIsValidChoice = true;
					break;
				case "2":
					enLoginUser = EnLoginUserType.RA;
					bIsValidChoice = true;
					break;
				default:
				System.out.println("---------- THERE'S NO SUCH OPTION AVAILABLE ----------");
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

		System.out.println("\nENTER THE LIST OF EMPLOYEES WORKING UNDER " + sUserId
				+ "\nNOTE : IF MORE THAN ONE EMPLOYEE , PLEASE SEPARATE IT USING COMMA.\n");
		String sUsers = getValidUserId("");
		String[] arrUsers = sUsers.split(",");

		for (int i = 0; i < arrUsers.length; i++) {
			if (IsUseridUnique(arrUsers[i], EnLoginUserType.Employee.toString()) == false) {
				System.out.println("HE/SHE CAN'T BE ASSIGNED TO YOU.\n" + arrUsers[i] + " IS ALREADY WORKING UNDER ANOTHER RA.\n");
				arrUsers[i] = null;
			}
		}

		Employee TempEmp = null;
		if (lstEmpInfo.size() == 0) {
			if (arrUsers[0] != null) {
				//System.out.println("CURRENTLY " + arrUsers[0] + " IS NOT AVAILABLE.");
				String sEmpPassword = getValidPassword();
				TempEmp = new Employee();
				TempEmp.SetLeave(nTotalLeave);
				TempEmp.SetUserId(arrUsers[0]);
				TempEmp.SetPassword(sEmpPassword);
				TempEmp.SetRA(sUserId);
				lstEmpInfo.add(TempEmp);
			}
		}
		if (lstEmpInfo.size() > 0) {
			for (int j = 0; j < arrUsers.length; j++) {
				for (int i = 0; i < lstEmpInfo.size(); i++) {
					if (lstEmpInfo.get(i).GetUserId() != null && arrUsers[j] != null
							&& arrUsers[j].equals(lstEmpInfo.get(i).GetUserId()) == false) {
						//System.out.println(arrUsers[j] + " NAME ALREADY EXISTS.");
						String sEmpPassword = getValidPassword();
						TempEmp = new Employee();
						TempEmp.SetLeave(nTotalLeave);
						TempEmp.SetUserId(arrUsers[j]);
						TempEmp.SetPassword(sEmpPassword);
						TempEmp.SetRA(sUserId);
						lstEmpInfo.add(TempEmp);
						break;
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

		System.out.println("\nENTER CREDENTIALS OF RA : ");
		String sRA = getValidUserId("");
		if (IsUseridUnique(sRA, EnLoginUserType.RA.toString()) == true) {
			System.out.println("\nRA WITH THIS NAME DOES NOT EXIST.\nWILL CREATE A NEW RA.\n");
			String sRAPassword = getValidPassword();
			ReportingAuthority TempRA = null;
			if (lstRAInfo.size() == 0) {
				TempRA = new ReportingAuthority();
				TempRA.SetUserId(sRA);
				TempRA.SetPassword(sRAPassword);
				lstRAInfo.add(TempRA);
			} else {
				for (int i = 0; i < lstRAInfo.size(); i++) {
					if (lstRAInfo.get(i).GetUserId() != null || sRA.equals(lstRAInfo.get(i).GetUserId()) == false) {
						TempRA = new ReportingAuthority();
						TempRA.SetUserId(sRA);
						TempRA.SetPassword(sRAPassword);
						lstRAInfo.add(TempRA);
						break;
					}
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

	private boolean IsUseridUnique(String sUserid, String sUserType) {
		if (sUserid == null) {
			return false;
		}

		if (sUserType.equals(EnLoginUserType.Employee.toString()) == true) {
			for (int i = 0; i < lstEmpInfo.size(); i++) {
				if (lstEmpInfo.get(i).GetUserId() != null) {
					if (lstEmpInfo.get(i).GetUserId().equals(sUserid) == true) {
						return false;
					}
				}
			}
		} else if (sUserType.equals(EnLoginUserType.RA.toString()) == true) {
			for (int i = 0; i < lstRAInfo.size(); i++) {
				if (lstRAInfo.get(i).GetUserId() != null) {
					if (lstRAInfo.get(i).GetUserId().equals(sUserid) == true) {
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
				System.out.print("\nUSERNAME : ");
				sUserId = sUserInput.nextLine();
				if (sUserId == null) {
					System.out.println("----------- USERNAME SHOULD NOT BE EMPTY -------------");
					bIsValidInput = false;
					continue;
				}

				if (sUserId.length() == 0) {
					System.out.println("----------- USERNAME SHOULD NOT BE EMPTY -------------");
					bIsValidInput = false;
					continue;
				}
				if (IsUseridUnique(sUserId, sLoginUserType) == false) {
					System.out.println("------------- USERNAME ALREADY EXISTS ----------------");
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
				System.out.print("PASSWORD : ");
				sPassword = sUserInput.nextLine();
				System.out.print("\n");
				if (sPassword == null) {
					System.out.println("----------- PASSWORD SHOULD NOT BE EMPTY -------------");
					bIsValidInput = false;
					continue;
				}

				if (sPassword.length() == 0) {
					System.out.println("----------- PASSWORD SHOULD NOT BE EMPTY -------------");
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