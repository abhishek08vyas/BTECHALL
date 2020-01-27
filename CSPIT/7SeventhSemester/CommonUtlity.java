/**
 * 
 */
package com.leavemanagement;

import java.util.regex.*;

/**
 * @author DELL
 *
 */
public class CommonUtlity {

	/*
	 * Validate user id.
	 */
	public static boolean IsValidUserId(String sUserId)
	{
		try
		{
			if(sUserId==null)
			{
				return false;
			}
			
			if(sUserId.length()==0)
			{
				
				return false;
			}
			
			if(Pattern.matches(CommonDefinition.DEF_USERID_REGEX, sUserId)==false) {
				
				return false;
			}	
		}
		catch(PatternSyntaxException ex) {
			
			return false;
		}
		catch(Exception ex) {
			
			return false;
		}
		
		return true;
	}
	
	/*
	 * Validate Password
	 */
	public static boolean IsValidPassword(String sPassword) {
		try
		{
			if(sPassword==null)
			{
				
				return false;
			}
			
			if(sPassword.length()==0)
			{
				
				return false;
			}
			
			if(Pattern.matches(CommonDefinition.DEF_PASSWORD_REGEX, sPassword)==false) {
				
				return false;
			}	
		}
		catch(PatternSyntaxException ex) {
						return false;
		}
		catch(Exception ex) {
			
			return false;
		}
		
		return true;
	}
}
