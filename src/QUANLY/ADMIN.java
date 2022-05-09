package QUANLY;

public class ADMIN  {
    private String username;
    private String password;
    private final String role = "admin";

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }
    public ADMIN(String username, String password) {
        this.username = username;
        this.password = password;
    }
}
