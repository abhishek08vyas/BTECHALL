import java.util.ArrayList;
import java.util.Scanner;

public class Main {
	private static ArrayList<Employee> lstEmpInfo = null;
	private static ArrayList<ReportingAuthority> lstRAInfo = null;
	
	public static void main(String[] args) {
		LoginScreen();
	}

	public static void LoginScreen() {
		System.out.println("------------------------------------------------------");
		System.out.println("\t\t\tLOGIN");
		System.out.println("------------------------------------------------------");
		
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
				Admin adminLgn = new Admin();
				adminLgn.AdminLogin();
				lstEmpInfo = adminLgn.GetEmpInfo();
				lstRAInfo = adminLgn.GetRAInfo();
				bIsValidChoice = false;
				break;
			case "2":
				ReportingAuthority raLgn = new ReportingAuthority(lstEmpInfo, lstRAInfo);
				raLgn.RALogin();
				bIsValidChoice = false;
				break;
			case "3":
				Employee empLgn = new Employee(lstEmpInfo);
				empLgn.EmployeeLogin();
				bIsValidChoice = false;
				break;
			default:
				System.out.println("---------- THERE'S NO SUCH OPTION AVAILABLE ----------");
				bIsValidChoice = false;
				continue;
			}

		} while (bIsValidChoice != true);
	}
}
