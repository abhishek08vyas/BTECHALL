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
public class ReportingAuthority extends Login {
	private ReportingAuthority m_RA = null;
	private ArrayList<Employee> lstEmpInfo = new ArrayList<Employee>();
	private ArrayList<ReportingAuthority> lstRAInfo = null;

	public ReportingAuthority() {
	}

	public ReportingAuthority(ArrayList<Employee> lstEmpInfo, ArrayList<ReportingAuthority> lstRAInfo) {
		this.lstEmpInfo = new ArrayList<Employee>();
		this.lstEmpInfo = lstEmpInfo;
		this.lstRAInfo = new ArrayList<ReportingAuthority>();
		this.lstRAInfo = lstRAInfo;
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

	// RA Login
	public void RALogin() {

		if (lstRAInfo == null || lstRAInfo.size() == 0) {
			System.out.println("Currently RA users not available!!Please contact administrator.");
			return;
		}
		boolean bIsValidUser = false;
		do {
			String sUserid = getValidUserId();
			String sPassword = getValidPassword();
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
					System.out.println("Invalid credintials!!");
					bIsValidUser = false;
				}

			}
		} while (bIsValidUser != true);

		System.out.println("-------------------------------------------------");
		System.out.println("REPORTING AUTHORITY");
		System.out.println("-------------------------------------------------" + "\n");

		boolean bIsValidChoice = false;
		do {
			System.out.println("1." + "Report");
			System.out.println("2." + "Accept/Reject Leave");
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
				DisplayEmployeeReport();
				bIsValidChoice = false;
				break;
			case "2":
				EmployeeLeaveReqAcceptReject();
				bIsValidChoice = false;
				break;
			case "3":
				m_RA.SetLoginFlag(false);
				m_RA = null;
				lstEmpInfo = null;
				return;
			default:
				System.out.println("Invalid choice!!");
				bIsValidChoice = false;
				continue;
			}

		} while (bIsValidChoice != true);
	}

	// Employee leave request accept/reject
	public void EmployeeLeaveReqAcceptReject() {
		if (lstEmpInfo == null) {
			System.out.println("Employee list is empty!!");
			return;
		}

		if (lstEmpInfo.size() == 0) {
			System.out.println("Employee list is empty!!");
			return;
		}

		int nCount = 0;
		boolean bApplyLeaveFlag = false;
		ArrayList<Integer> StoreIndex = new ArrayList<Integer>();
		for (int i = 0; i < lstEmpInfo.size(); i++) {
			if (lstEmpInfo.get(i).GetApplyLeave() > 0 && (lstEmpInfo.get(i).GetRA().equals(m_RA.GetUserId()) == true)
					&& lstEmpInfo.get(i).GetLeaveFlag() != false) {
				System.out.println("\n");
				System.out.println("-------------------------------------------------");
				nCount = nCount + 1;
				System.out.print(nCount + ") ");
				System.out.println("Employee userid: " + lstEmpInfo.get(i).GetUserId());
				System.out.println("No. of applied leaves: " + lstEmpInfo.get(i).GetApplyLeave());
				System.out.println("-------------------------------------------------");
				StoreIndex.add(i);
				bApplyLeaveFlag = true;
			}
		}

		if (bApplyLeaveFlag == false) {
			System.out.println("Currently not apply leave of any employee.");
			return;
		}

		Scanner sUserInput = new Scanner(System.in);
		System.out.print("Select employee id from above list: ");
		// Store user choice
		String sSelectEmployee = sUserInput.nextLine();
		boolean bIsValidSelectEmployee = false;
		do {
			try {
				int nSelectEmployee = Integer.parseInt(sSelectEmployee);
				if (nCount < nSelectEmployee) {
					System.out.print("Selected input not in list!!");
					continue;
				}

				System.out.print("Enter accept leave: ");
				// Store user choice
				int nAcceptLeave = sUserInput.nextInt();
				lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).SetAcceptedLeave(nAcceptLeave);
				int nGetCurrentLeave = lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).GetLeave();
				int nAfterAcceptLeave = nGetCurrentLeave - nAcceptLeave;
				int nApplyLeave = lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).GetApplyLeave();
				lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).SetRejectedLeave(nApplyLeave - nAcceptLeave);
				lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).SetLeave(nAfterAcceptLeave);
				lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).SetLeaveFlag(false);
				StoreIndex = null;
				bIsValidSelectEmployee = true;
			} catch (Exception ex) {
				System.out.print("Only integer value is accept!!");
				bIsValidSelectEmployee = false;
				continue;
			}

		} while (bIsValidSelectEmployee != true);
		return;
	}

	// Display employee report
	public void DisplayEmployeeReport() {
		if (lstEmpInfo == null) {
			System.out.println("Employee list is empty!!");
			return;
		}

		if (lstEmpInfo.size() == 0) {
			System.out.println("Employee list is empty!!");
			return;
		}

		int nCount = 0;
		for (int i = 0; i < lstEmpInfo.size(); i++) {
			if (lstEmpInfo.get(i).GetRA().equals(m_RA.GetUserId()) == true) {
				System.out.println("\n");
				System.out.println("-------------------------------------------------");
				nCount = nCount + 1;
				System.out.println(nCount + ")");
				System.out.println("Employee userid: " + lstEmpInfo.get(i).GetUserId());
				System.out.println("No. remaining leaves: " + lstEmpInfo.get(i).GetLeave());
				System.out.println("-------------------------------------------------");
			}
		}
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