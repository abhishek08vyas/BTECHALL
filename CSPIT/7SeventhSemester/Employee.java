import java.util.ArrayList;
import java.util.Scanner;

public class Employee extends Login {
	private Employee m_Emp = null;
	private int nLeave = 0;
	private int nTotalLeave = 10;
	private ArrayList<Employee> lstEmpInfo = null;
	private int nAcceptedLeave = 0;
	private int nRejectedLeave = 0;
	private int nApplyLeave = 0;
	private String UnderRA = "";
	boolean bLeaveFlag = false;

	public Employee() {
	}

	public Employee(ArrayList<Employee> lstEmpInfo) {
		this.lstEmpInfo = lstEmpInfo;
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
		return super.GetUserId();
	}

	// Set password
	public void SetPassword(String sPassword) {
		super.SetPassword(sPassword);
	}

	// Get password
	public String GetPassword() {
		return super.GetPassword();
	}

	public int GetLeaveTotal() {
		return this.nTotalLeave;
	}

	// Set leave
	public void SetLeave(int nLeave) {
		this.nLeave = nLeave;
	}

	// Get leave
	public int GetLeave() {
		return this.nLeave;
	}

	// Set leave
	public void SetApplyLeave(int nLeave) {
		this.nApplyLeave = nLeave;
	}

	// Get leave
	public int GetApplyLeave() {
		return this.nApplyLeave;
	}

	// Set accepted leave
	public void SetAcceptedLeave(int nLeave) {
		this.nAcceptedLeave = nLeave;
	}

	// Get accepted leave
	public int GetAcceptedLeave() {
		return this.nAcceptedLeave;
	}

	// Set accepted leave
	public void SetRejectedLeave(int nLeave) {
		this.nRejectedLeave = nLeave;
	}

	// Get accepted leave
	public int GetRejectedLeave() {
		return this.nRejectedLeave;
	}

	// Set RA
	public void SetRA(String sRAUnder) {
		this.UnderRA = sRAUnder;
	}

	// Get RA
	public String GetRA() {
		return this.UnderRA;
	}

	// Set leave flag
	public void SetLeaveFlag(boolean bLeaveFlag) {
		this.bLeaveFlag = bLeaveFlag;
	}

	// Get leave flag
	public boolean GetLeaveFlag() {
		return this.bLeaveFlag;
	}

	// Employee Login
	public void EmployeeLogin() {

		if (lstEmpInfo == null || lstEmpInfo.size() == 0) {
			System.out.println("\nEMPLOYEE USER LIST IS EMPTY.\nCONTACT ADMINISTRATOR TO CREATE NEW EMPLOYEE USER.\n");
			return;
		}
		boolean bIsValidUser = false;
		do {
			String sUserid = getValidUserId();
			String sPassword = getValidPassword();
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
				DisplayLeaveReport();
				bIsValidChoice = false;
				break;
			case "2":
				ApplyLeave();
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

	// Apply leave for employee
	public void ApplyLeave() {
		Scanner sUserInput = new Scanner(System.in);
		for (int i = 0; i < lstEmpInfo.size(); i++) {
			if (lstEmpInfo.get(i).GetUserId().equals(m_Emp.GetUserId()) == true) {
				System.out.println("TOTAL REMAINING LEAVE(S) : " + lstEmpInfo.get(i).GetLeave());
				System.out.println("NO. OF LEAVE(S) YOU WANT : ");
				// Store user choice
				String sLeaveApply = sUserInput.nextLine();
				boolean bIsValidInput = false;
				do {
					try {
						int nLeaveApply = Integer.parseInt(sLeaveApply);
						if (lstEmpInfo.get(i).GetLeave() == 0) {
							System.out.println("\n------------------------------------------------------");
							System.out.println("YOU CAN'T APPLY FOR MORE LEAVE(S).\nYOU HAVE 0 LEAVES LEFT.");
							System.out.println("------------------------------------------------------\n");
							break;
						}
						if (lstEmpInfo.get(i).GetLeave() < nLeaveApply) {
							System.out.println("\n------------------------------------------------------");
							System.out.println("YOU CAN'T APPLY FOR MORE THAN "+ lstEmpInfo.get(i).GetLeave() + " LEAVE(S).");
							System.out.println("------------------------------------------------------\n");
							break;
						}
						if (nLeaveApply < 0) {
							System.out.println("\n------------------------------------------------------");
							System.out.println("APPLIED LEAVE(S) SHOULD BE POSITIVE.");
							System.out.println("------------------------------------------------------\n");
							break;
						}
						if (nLeaveApply == 0) {
							System.out.println("\n------------------------------------------------------");
							System.out.println("APPLIED LEAVE(S) SHOULD BE MORE THAN 0.");
							System.out.println("------------------------------------------------------\n");
							break;
						}
						lstEmpInfo.get(i).SetApplyLeave(nLeaveApply);
						lstEmpInfo.get(i).SetLeaveFlag(true);
						System.out.println("----------------- SUCCESSFULLY APPLIED ---------------");
						bIsValidInput = true;
					} catch (Exception ex) {
						System.out.println("---------- ONLY INTEGER VALUE IS ACCEPTABLE ----------");
						bIsValidInput = false;
						//continue;
						break;
					}

				} while (bIsValidInput != true);
				break;
			}
		}
	}

	// Display employee leave report
	private void DisplayLeaveReport() {

		System.out.println("\n------------------------------------------------------");
		for (int i = 0; i < lstEmpInfo.size(); i++) {
			if (lstEmpInfo.get(i).GetUserId().equals(m_Emp.GetUserId()) == true) {
				System.out.println("NO. OF ACCEPTED LEAVE(S) : " + lstEmpInfo.get(i).GetAcceptedLeave());
				System.out.println("NO. OF REJECTED LEAVE(S) : " + lstEmpInfo.get(i).GetRejectedLeave());
				System.out.println("TOTAL REMAINING LEAVE(S) : " + lstEmpInfo.get(i).GetLeave());
				break;
			}
		}
		System.out.println("------------------------------------------------------\n");

	}

	private String getValidUserId() {

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