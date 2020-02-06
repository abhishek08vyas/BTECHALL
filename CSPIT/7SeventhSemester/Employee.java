import java.util.ArrayList;
import java.util.Scanner;

public class Employee {
	private Employee m_Emp = null;
	private int nLeave = 0;
	private int nTotalLeave = 10;
	private ArrayList<Employee> lstEmpInfo = null;
	private int nAcceptedLeave = 0;
	private int nRejectedLeave = 0;
	private int nApplyLeave = 0;
	private String UnderRA = "";
	boolean bLeaveFlag = false;
	// Store login true or false
			private boolean bIsLogin = false;
			// Store user type
			private EnLoginUserType enUserType = EnLoginUserType.Admin;
			// Store user id
			private String sUserid = "";
			// Store password
			private String sPassword = "";


	public Employee() {
	}

	public Employee(ArrayList<Employee> lstEmpInfo) {
		this.lstEmpInfo = lstEmpInfo;
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
	public void DisplayLeaveReport() {

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
}