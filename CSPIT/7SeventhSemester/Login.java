/**
 * 
 */
package Com.LeaveManagement;

/**
 * @author Yashvi
 *
 */
public abstract class Login {

	/*----------Member variable----------*/
	// Store login true or false
	private boolean bIsLogin = false;
	// Store user type
	private EnLoginUserType enUserType = EnLoginUserType.Admin;
	// Store user id
	private String sUserid = "";
	// Store password
	private String sPassword = "";

	/*----------Property----------*/

	// Set Login flag
	protected void SetLoginFlag(boolean IsLogin) {
		this.bIsLogin = IsLogin;
	}

	// Get Login Flag
	protected boolean GetLoginFlag() {
		return this.bIsLogin;
	}

	// Set user type
	protected void SetUserType(EnLoginUserType enUserType) {
		this.enUserType = enUserType;
	}

	// Get user type
	protected EnLoginUserType getUserType() {
		return this.enUserType;
	}

	// Set user id
	protected void SetUserId(String sUserId) {
		this.sUserid = sUserId;
	}

	// Get user id
	protected String GetUserId() {
		return this.sUserid;
	}

	// Set password
	protected void SetPassword(String sPassword) {
		this.sPassword = sPassword;
	}

	// Get password
	protected String GetPassword() {
		return this.sPassword;
	}
}