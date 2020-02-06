import java.util.ArrayList;
import java.util.Scanner;

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
			System.out.println("\nRA USER LIST IS EMPTY.\nCONTACT ADMINISTRATOR TO CREATE NEW EMPLOYEE USER.\n");
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
				System.out.println("---------- THERE'S NO SUCH OPTION AVAILABLE ----------");
				bIsValidChoice = false;
				continue;
			}

		} while (bIsValidChoice != true);
	}

	// Employee leave request accept/reject
	public void EmployeeLeaveReqAcceptReject() {
		if (lstEmpInfo == null) {
			System.out.println("--------------- EMPLOYEE LIST IS EMPTY ---------------");
			return;
		}

		if (lstEmpInfo.size() == 0) {
			System.out.println("--------------- EMPLOYEE LIST IS EMPTY ---------------");
			return;
		}

		int nCount = 0;
		boolean bApplyLeaveFlag = false;
		ArrayList<Integer> StoreIndex = new ArrayList<Integer>();
		for (int i = 0; i < lstEmpInfo.size(); i++) {
			if (lstEmpInfo.get(i).GetApplyLeave() > 0 && (lstEmpInfo.get(i).GetRA().equals(m_RA.GetUserId()) == true)
					&& lstEmpInfo.get(i).GetLeaveFlag() != false) {
				System.out.println("");
				System.out.println("------------------------------------------------------");
				nCount = nCount + 1;
				System.out.println("                  ID NO. : " + nCount);
				System.out.println("USERNAME OF THE EMPLOYEE : " + lstEmpInfo.get(i).GetUserId());
				System.out.println("NO. OF APPLIED LEAVE(S)  : " + lstEmpInfo.get(i).GetApplyLeave());
				System.out.println("------------------------------------------------------\n");
				StoreIndex.add(i);
				bApplyLeaveFlag = true;
			}
		}

		if (bApplyLeaveFlag == false) {
			System.out.println("------------ CURRENTLY THE LIST IS EMPTY -------------");
			return;
		}

		Scanner sUserInput = new Scanner(System.in);
		System.out.println("\nENTER ID FROM THE ABOVE LIST (INTEGER VALUE ACCEPTABLE) : ");
		// Store user choice
		String sSelectEmployee = sUserInput.nextLine();
		boolean bIsValidSelectEmployee = false;
		do {
			try {
				int nSelectEmployee = Integer.parseInt(sSelectEmployee);
				if (nCount < nSelectEmployee) {
					System.out.println("------------ SELECTED INPUT NOT IN LIST --------------");
					//continue;
					break;
				}
				
				System.out.println("ENTER NO. OF ACCEPTED LEAVES : ");
				// Store user choice
				int nAcceptLeave = sUserInput.nextInt();
				int nApplyLeave = lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).GetApplyLeave();
				if(nApplyLeave < nAcceptLeave)
				{
					System.out.println("\n------------------------------------------------------");
					System.out.println("YOU CANNOT ACCEPT MORE THAN " + nApplyLeave + " LEAVE(S).");
					System.out.println("------------------------------------------------------\n");
					break;
				}
				else if(nAcceptLeave <= 0)
				{
					System.out.println("\n------------------------------------------------------");
					System.out.println("ACCEPTED LEAVE(S) SHOULD BE POSITIVE AND GREATER THAN 0.");
				    System.out.println("------------------------------------------------------\n");

				}
				else
				{
					lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).SetAcceptedLeave(nAcceptLeave);
					int nGetCurrentLeave = lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).GetLeave();
					int nAfterAcceptLeave = nGetCurrentLeave - nAcceptLeave;
					//int nApplyLeave = lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).GetApplyLeave();
					lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).SetRejectedLeave(nApplyLeave - nAcceptLeave);
					lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).SetLeave(nAfterAcceptLeave);
					lstEmpInfo.get(StoreIndex.get(nSelectEmployee - 1)).SetLeaveFlag(false);
					StoreIndex = null;
					bIsValidSelectEmployee = true;
					System.out.println("-------- LEAVE(S) IS/ARE ACCEPTED SUCCESSFULLY ---------");
				}
			} catch (Exception ex) {
				System.out.println("---------- ONLY INTEGER VALUE IS ACCEPTABLE ----------");
				bIsValidSelectEmployee = false;
				break;
				//continue;
			}

		} while (bIsValidSelectEmployee != true);
		return;
	}

	// Display employee report
	public void DisplayEmployeeReport() {
		if (lstEmpInfo == null) {
			System.out.println("--------------- EMPLOYEE LIST IS EMPTY ---------------");
			return;
		}

		if (lstEmpInfo.size() == 0) {
			System.out.println("--------------- EMPLOYEE LIST IS EMPTY ---------------");
			return;
		}

		int nCount = 0;
		for (int i = 0; i < lstEmpInfo.size(); i++) {
			if (lstEmpInfo.get(i).GetRA().equals(m_RA.GetUserId()) == true) {
				System.out.print("\n");
				System.out.println("------------------------------------------------------");
				nCount = nCount + 1;
				System.out.println("                   ID NO. : " + nCount);
				System.out.println("USERNAME OF THE EMPLOYEE  : " + lstEmpInfo.get(i).GetUserId());
				System.out.println("NO. OF REMAINING LEAVE(S) : " + lstEmpInfo.get(i).GetLeave());
				System.out.println("------------------------------------------------------");
				System.out.print("\n");

			}
			else
			{
				System.out.println("--------------- EMPLOYEE LIST IS EMPTY ---------------");
			}
		}
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
