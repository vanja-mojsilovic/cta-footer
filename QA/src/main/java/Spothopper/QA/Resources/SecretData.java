package Spothopper.QA.Resources;

import com.fasterxml.jackson.annotation.*;

public class SecretData {
    @JsonProperty("sh_username")
    private String shUsername;

    @JsonProperty("sh_password")
    private String shPassword;

    // Getters and setters
    public String getShUsername() {
        return shUsername;
    }

    public void setShUsername(String shUsername) {
        this.shUsername = shUsername;
    }

    public String getShPassword() {
        return shPassword;
    }

    public void setShPassword(String shPassword) {
        this.shPassword = shPassword;
    }
}
