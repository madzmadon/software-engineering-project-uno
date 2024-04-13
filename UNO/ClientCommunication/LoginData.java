package ClientCommunication;

import java.io.Serializable;

public class LoginData implements Serializable {
    private static final long serialVersionUID = 1L;  // Helps ensure version compatibility during serialization
    private String username;
    private String password;

    public LoginData(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getters for username and password
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
