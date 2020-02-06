import java.util.ArrayList;
import java.util.Scanner;

public class Admin{

	// Store login true or false
		private boolean bIsLogin = false;
		// Store user type
		private EnLoginUserType enUserType = EnLoginUserType.Admin;
		// Store user id
		private String sUserid = "";
		// Store password
		private String sPassword = "";

	private Admin m_Admin = null;
	private Employee m_Emp = null;
	private ReportingAuthority m_RA = null;
	private ArrayList<Employee> lstEmpInfo = null;
	private ArrayList<ReportingAuthority> lstRAInfo = null;
	private int nTotalLeave = 10;

	public Admin() {
	}

	// Set Login flag
		public void SetLoginFlag(boolean IsLogin) {
			this.bIsLogin = IsLogin;
		}

		// Get Login Flag
		public boolean GetLoginFlag() {
			return this.bIsLogin;
		}

		// Set user type
		public void SetUserType(EnLoginUserType enUserType) {
			this.enUserType = enUserType;
		}

		// Get user type
		public EnLoginUserType getUserType() {
			return this.enUserType;
		}

		// Set user id
		public void SetUserId(String sUserId) {
			this.sUserid = sUserId;
		}

		// Get user id
		public String GetUserId() {
			return this.sUserid;
		}

		// Set password
		public void SetPassword(String sPassword) {
			this.sPassword = sPassword;
		}

		// Get password
		public String GetPassword() {
			return this.sPassword;
		}
		
	public void SetEmpInfo(ArrayList<Employee> lstEmpInfo) {
		this.lstEmpInfo=lstEmpInfo;
	}

	public void SetRAInfo(ArrayList<ReportingAuthority> lstRAInfo) {
		this.lstRAInfo=lstRAInfo;
	}

	// Add RA
	public ReportingAuthority AddReportingAuthority(ReportingAuthority ra) {
		String sUserId="";
		do {
			sUserId = Input.getValidUserId();
		}while(IsUseridUnique(sUserId,EnLoginUserType.RA.toString())!=true);
		String sPassword = Input.getValidPassword();

		System.out.println("\nENTER THE LIST OF EMPLOYEES WORKING UNDER " + sUserId
				+ "\nNOTE : IF MORE THAN ONE EMPLOYEE , PLEASE SEPARATE IT USING COMMA.\n");
		String sUsers = Input.getValidUserId();
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
				String sEmpPassword = Input.getValidPassword();
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
						String sEmpPassword = Input.getValidPassword();
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
		ra.SetUserId(sUserId);
		ra.SetPassword(sPassword);
		return ra;

	}

	// Add employee
	public Employee AddEmployee(Employee emp) {
		String sUserId="";
		do {
			sUserId = Input.getValidUserId();
		}while(IsUseridUnique(sUserId,EnLoginUserType.Employee.toString())!=true);
		String sPassword = Input.getValidPassword();

		System.out.println("\nENTER CREDENTIALS OF RA : ");
		String sRA = "";
		do {
			sRA=Input.getValidUserId();
		}while(IsUseridUnique(sUserId,EnLoginUserType.RA.toString())!=true);
		if (IsUseridUnique(sRA, EnLoginUserType.RA.toString()) == true) {
			System.out.println("\nRA WITH THIS NAME DOES NOT EXIST.\nWILL CREATE A NEW RA.\n");
			String sRAPassword = Input.getValidPassword();
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
		emp.SetLeave(nTotalLeave);
		emp.SetUserId(sUserId);
		emp.SetPassword(sPassword);
		emp.SetRA(sRA);
		return emp;
	}

	public boolean IsUseridUnique(String sUserid, String sUserType) {
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
}