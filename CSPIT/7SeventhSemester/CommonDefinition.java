/**
 * 
 */
package com.leavemanagement;

/**
 * @author DELL
 *
 */
public class CommonDefinition {
	
	/*---------------------Definition----------------------*/
	public static final String DEF_USERID_REGEX="^[A-Za-z0-9_]{3,20}$";
	
	public static final String DEF_PASSWORD_REGEX="((?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%]).{6,20})";
	
	public static final String DEF_ADMIN_USERID="Admin";
	
	public static final String DEF_ADMIN_PASSWORD="Admin123";
	
	public static final String DEF_USERID_INPUT="Enter userid";
	
	public static final String DEF_PASSWORD_INPUT="Enter password";
	
	/*---------------------Error message----------------------*/
	public static final String ERR_USERID_NOT_EMPTY="Userid should not be empty!!";
	
	public static final String ERR_USERID_VALIDATE=String.format("userid accept only alphabet, number and underscore\r\n" + 
		            								"and It must Length at least 3 characters and maximum length of 20");

	
	
	public static final String ERR_PASSWORD_NOT_EMPTY="Userid should not be empty!!";
	
	public static final String ERR_PASSWORD_VALIDATE=String.format("must contains one digit from 0-9\r\n" + 
			"must contains one lowercase characters\r\n" + 
			"must contains one uppercase characters\r\n" + 
			"must contains one special symbols in the list@,#,$,%,\r\n" + 
			"match anything with previous condition checking\r\n" + 
			"length at least 6 characters and maximum of 20	");
	
	public static final String ERR_INVALID_USERID_PASSWORD="Invalid userid or password!!";
}
