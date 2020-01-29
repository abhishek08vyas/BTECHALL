package Com.LeaveManagement;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Yashvi
 *
 */
public class Main {
	private static ArrayList<Employee> lstEmpInfo = null;
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		LoginScreen();
	}

	public static void LoginScreen() {
		System.out.println("-------------------------------------------------");
		System.out.println("\t\t\tLOGIN");
		System.out.println("-------------------------------------------------");

		boolean bIsValidChoice = false;
		do {
			System.out.println("1." + "Admin");
			System.out.println("2." + "Reporting Authority");
			System.out.println("3." + "Employee");
			Scanner sUserInput = new Scanner(System.in);
			// Store user choice
			String sLoginChoice = "";
			System.out.print("Enter login option:");
			sLoginChoice = sUserInput.nextLine();
			if (sLoginChoice == null) {
				System.out.println("Choice should not be empty!!");
				bIsValidChoice = false;
				continue;
			}

			if (sLoginChoice.length() == 0) {
				System.out.println("Choice should not be empty!!");
				bIsValidChoice = false;
				continue;
			}

			switch (sLoginChoice) {
			case "1":
				Admin adminLgn = new Admin();
				adminLgn.AdminLogin();
				lstEmpInfo=adminLgn.GetEmpInfo();
				bIsValidChoice = false;
				break;
			case "2":
				ReportingAuthority raLgn = new ReportingAuthority(lstEmpInfo);
				raLgn.RALogin();
				bIsValidChoice = false;
				break;
			case "3":
				Employee empLgn = new Employee(lstEmpInfo);
				empLgn.EmployeeLogin();
				bIsValidChoice = false;
				break;
			default:
				System.out.println("Invalid choice!!");
				bIsValidChoice = false;
				continue;
			}

		} while (bIsValidChoice != true);
	}
}