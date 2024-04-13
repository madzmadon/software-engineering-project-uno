package ClientCommunication;

import java.io.Serializable;

public class CreateAccountData implements Serializable {
    private String username;
    private String password;
    private String email;

    public CreateAccountData(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getters and possibly setters if modification is needed after instantiation
    public String getUsername() { return username; }
    public String getPassword() { return password; }
    public String getEmail() { return email; }
}
