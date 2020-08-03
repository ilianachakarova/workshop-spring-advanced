package org.softuni.workshopspringadvanced.registration.model;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
//@FieldMatch(first = "password",second = "confirmPassword",message = "The passwords do not match")
public class RegistrationDTO {

    private String email;
    private String password;
    private String confirmPassword;

    public RegistrationDTO() {
    }
    @Email
    @NotBlank
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    @NotBlank
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotBlank
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }
}
