import java.util.ArrayList;
import java.util.Scanner;

public class Input {
	public static String getValidUserId() {

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
	public static String getValidPassword() {
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
