import org.jetbrains.annotations.Nullable;

public class Name {
    private String firstName;
    @Nullable
    private String middleName;
    private String lastName;

    private String credentials;

    public Name(String firstName, String lastName,String credentials) {
        this.credentials = credentials;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getCredentials() {
        return credentials;
    }
    public void setCredentials(String credentials) {
        this.credentials = credentials;
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
