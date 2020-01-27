/**
 * 
 */
package com.leavemanagement;

/**
 * @author Yashvi
 *
 */
public class NotificationLeave {

	/*----------Member variable----------*/
	private String sUserid = "";
	// Store password
	private int nApplyLeave = 0;
	// Store Accepted leave
	private int nAcceptedLeave = 0;
	// Store rejected leave
	private int nLeaveRejected = 0;
	/*----------Property----------*/

	// Set user id
	public void SetUserId(String sUserId) {
		this.sUserid = sUserId;
	}

	// Get user id
	public String GetUserId() {
		return this.sUserid;
	}

	// Set leave
	public void SetApplyLeave(int nApplyLeave) {
		this.nApplyLeave = nApplyLeave;
	}

	// Get leave
	public int GetApplyLeave() {
		return this.nApplyLeave;
	}

	// Set leave
	public void SetAcceptedLeave(int nAcceptedLeave) {
		this.nAcceptedLeave = nAcceptedLeave;
	}

	// Get leave
	public int GetAcceptedLeave() {
		return this.nAcceptedLeave;
	}

	// Set leave
	public void SetRejectedLeave(int nLeaveRejected) {
		this.nLeaveRejected = nLeaveRejected;
	}

	// Get leave
	public int GetRejectedLeave() {
		return this.nLeaveRejected;
	}
}
