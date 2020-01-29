/**
 * 
 */
package Com.LeaveManagement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Yashvi
 *
 */
public class Employee extends Login {
	private Employee m_Emp = null;
	private int nLeave = 0;
	private int nTotalLeave = 10;
	private ArrayList<Employee> lstEmpInfo = null;
	private int nAcceptedLeave = 0;
	private int nRejectedLeave = 0;
	private int nApplyLeave = 0;
	private String UnderRA = "";
	
	public Employee() {
	}
	
	public Employee(ArrayList<Employee> lstEmpInfo) {
		this.lstEmpInfo=lstEmpInfo;
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

	// Employee Login
	public void EmployeeLogin() {
		String sUserid = getValidUserId();
		String sPassword = getValidPassword();
		m_Emp=new Employee();
		for (int i = 0; i < lstEmpInfo.size(); i++) {
			if (lstEmpInfo.get(i).GetUserId().equals(sUserid) == true
					&& lstEmpInfo.get(i).GetPassword().equals(sPassword) == true) {
				m_Emp.SetLoginFlag(true);
				m_Emp.SetUserType(EnLoginUserType.Employee);
				m_Emp.SetUserId(sUserid);
				m_Emp.SetLeave(lstEmpInfo.get(i).GetLeave());
				break;
			}
		}

		System.out.println("-------------------------------------------------");
		System.out.println("EMPLOYEE");
		System.out.println("-------------------------------------------------" + "\n");

		boolean bIsValidChoice = false;
		do {
			System.out.println("1." + "Leave report");
			System.out.println("2." + "Apply Leave");
			System.out.println("3." + "Logout");
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
				System.out.println("Invalid choie!!");
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
				System.out.println("No.of remaining leave: " + lstEmpInfo.get(i).GetLeave());
				System.out.print("Enter leave:");
				// Store user choice
				String sLeaveApply = sUserInput.nextLine();
				boolean bIsValidInput = false;
				do {
					try {
						int nLeaveApply = Integer.parseInt(sLeaveApply);
						if (lstEmpInfo.get(i).GetLeave() == 0) {
							System.out.print("You can't apply more leave!!Please contact reporting authority.");
							break;
						}
						if (lstEmpInfo.get(i).GetLeave() < nLeaveApply) {
							System.out.print("You can't apply more leave!!Please contact reporting authority.");
							break;
						}
						lstEmpInfo.get(i).SetApplyLeave(nLeaveApply);
						System.out.print("Leave successfully applied.");
						bIsValidInput = true;
					} catch (Exception ex) {
						System.out.print("Only integer value is accept!!");
						bIsValidInput = false;
						continue;
					}

				} while (bIsValidInput != true);
				break;
			}
		}
	}

	// Display employee leave report
	private void DisplayLeaveReport() {

		System.out.println("-------------------------------------------------");
		for (int i = 0; i < lstEmpInfo.size(); i++) {
			if (lstEmpInfo.get(i).GetUserId().equals(m_Emp.GetUserId()) == true) {
				System.out.println("No. of accepted leave: " + lstEmpInfo.get(i).GetAcceptedLeave());
				System.out.println("No. of Rejected leave: " + lstEmpInfo.get(i).GetRejectedLeave());
				System.out.println("No. of Remaining leave: " + lstEmpInfo.get(i).GetLeave());
				break;
			}
		}
		System.out.println("-------------------------------------------------");
	}

	private String getValidUserId() {

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
