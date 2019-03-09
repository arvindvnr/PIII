/**
 * 
 */
package users;

import java.io.Serializable;

/**
 *
 */
public class User implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8253402355421777134L;
	private String ID;
	private String password;
	protected Integer sessionID;
	
	/**
	 * Creates a User object.
	 * @param ID the ID of this User
	 * @param password the password of this User
	 */
	public User(String ID, String password) {
		// TODO Auto-generated constructor stub
		this.ID = ID;
		this.password = password;
		sessionID = -1;
	}
	
	
	
	/**
	 * @return the ID
	 */
	public String getID() {
		return ID;
	}

	/**
	 * @return the sessionID
	 */
	public Integer getSessionID() {
		return sessionID;
	}



	/**
	 * @param sessionID the setSessionID to set
	 */
	public void setSessionID(Integer sessionID) {
		this.sessionID = sessionID;
	}



	/**
	 * @param ID the ID to set
	 */
	public void setID(String ID) {
		this.ID = ID;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String newPassword) {
		password = newPassword;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return this.ID;
	}

}


