package QUANLY;

import java.util.ArrayList;

public class USERS {
    private String username;
    private String password;
    private String role;
    ArrayList<USERS>  listUsers= new ArrayList<>();
    ArrayList<BENHNHAN> listBN = new ArrayList<>();
    BENHNHAN benhnhan = null;

    public USERS() {
    }

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

    public void setRole(String role) {
        this.role = role;
    }

    public USERS(String username, String password, String role) {
        benhnhan = new BENHNHAN("chuaUpdate",username,"chuaUpdate","chuaUpdate","chuaUpdate","chuaUpdate","chuaUpdate","chuaUpdate");
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public BENHNHAN getBenhnhan() {
        return benhnhan;
    }

    public void setBenhnhan(BENHNHAN benhnhan){
        this.benhnhan = benhnhan;
    }

    @Override
    public String toString() {
        return "USERS{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }

    public static void main(String[] args) {
        USERS users = new USERS("1234","12","admin");
        System.out.println(users);

        System.out.println(users.benhnhan.toString());
    }
}
