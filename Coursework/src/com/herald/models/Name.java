package models;

public class Name {
	private String firstName;
	private String middleName;
	private String lastName;

	public Name(String firstName, String middleName, String lastName) {
		this.middleName = middleName;
		this.firstName = firstName;
		this.lastName = lastName;
	}

	public String getCredentials() {
		StringBuilder credentials = new StringBuilder();
		if (firstName != null && !firstName.isEmpty()) {
			credentials.append(firstName.charAt(0));
		}
		if (middleName != null && !middleName.isEmpty()) {
			credentials.append(middleName.charAt(0));
		}
		if (lastName != null && !lastName.isEmpty()) {
			credentials.append(lastName.charAt(0));
		}
		return credentials.toString();
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

}