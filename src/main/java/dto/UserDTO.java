package dto;

public class UserDTO {
    private int id;
    private String firstName;
    private String lastName;
    private String fingerPrintPath;
    private int accessLevel;
    private boolean isAdmin;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFingerPrintPath() {
        return fingerPrintPath;
    }

    public void setFingerPrintPath(String fingerPrintPath) {
        this.fingerPrintPath = fingerPrintPath;
    }

    public int getAccessLevel() {
        return accessLevel;
    }

    public void setAccessLevel(int accessLevel) {
        this.accessLevel = accessLevel;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    @Override
    public String toString() {
        return "UserDTO{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", fingerPrintPath='" + fingerPrintPath + '\'' +
                ", accessLevel=" + accessLevel +
                ", isAdmin=" + isAdmin +
                '}';
    }
}
