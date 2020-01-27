/**
 * 
 */
package com.leavemanagement;

/**
 * @author Yashvi
 *
 */
public class Employee extends Login {
	
			private int nLeave=0;
			
			// Set Login flag
			public void SetLoginFlag(boolean IsLogin){
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
			public void SetUserId(String sUserId){
				super.SetUserId(sUserId);
			}
			// Get user id
			public String GetUserId() {
				return super.GetUserId();
			}
			
			// Set password
			public void SetPassword(String sPassword){
				super.SetPassword(sPassword);
			}
			// Get password
			public String GetPassword() {
				return super.GetPassword();
			}
			
			public int GetLeaveTotal(){
				return LeaveManagementDefination.nTotalLeave;
			}
			
			// Set leave
			public void SetLeave(int nLeave) {
				this.nLeave=nLeave;
			}
			
			// Get leave
			public int GetLeave() {
				return this.nLeave;
			}
}
